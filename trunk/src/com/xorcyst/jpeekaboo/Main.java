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

package com.xorcyst.jpeekaboo;

import javax.swing.*;
import com.xorcyst.jpeekaboo.core.*;
import com.xorcyst.jpeekaboo.persistence.*;

/**
 * Get the party started
 */
public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                if (new StateLock("jpeekaboo").isAppActive()) {
                    return;
                }

                new Application();
            }
        });
    }
}
