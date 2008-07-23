// jpeekaboo - auto hide note thing
// Copyright 2008 by Peter R. Elespuru
// All Rights Reserved.
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
//

package com.xorcyst.jpeekaboo.persistence;

import java.io.*;
import java.nio.channels.*;

/**
 * The StateLock class modularizes a user home directory based lock
 * mechanism to prevent more than one instance of the application from
 * running at any given time by the same user.
 */
public class StateLock {

    private String      appName;
    private File        file;
    private FileChannel channel;
    private FileLock    lock;

    /**
     * Constructor that uses the application name
     * to determine if the application is currently running
     * @param appName - name of application to check 
     */
    public StateLock(String appName) {
        this.appName = appName;
    }

    /**
     * help method that may be used to see if an app is already running
     * @return true if app is already running, false otherwise
     */
    public boolean isAppActive() {
        try {
            file = new File(System.getProperty("user.home"), "." + appName + ".lock" );
            channel = new RandomAccessFile(file, "rw").getChannel();

            try {
                lock = channel.tryLock();
            } catch (OverlappingFileLockException e) {
                closeLock();
                return true;
            }

            if (lock == null) {
                closeLock();
                return true;
            }

            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    closeLock();
                    deleteLockFile();
                }
            });

            return false;

        } catch (Exception e) {
            closeLock();
            return true;
        }
    }

    /**
     * closes the lock file
     */
    private void closeLock() {
        try { lock.release();
        } catch (Exception e) {
        }

        try { channel.close();
        } catch (Exception e) {
        }
    }

    /**
     * deletes the lock file
     */
    private void deleteLockFile() {
        try {
            file.delete();
        } catch (Exception e) {
        }
    }
}
