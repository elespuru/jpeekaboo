// jpeekaboo - auto hide note thing
// Copyright 2009 by Peter R. Elespuru
// All Rights Reserved.
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//

package com.xorcyst.jpeekaboo.core;

import java.util.*;

//
// Ideally this should store an xml config file or similar, or perhaps
// go so far as to bundle HSQLDB/Derby/whatever and provide config via
// embedded db. I'm sure there's an appropriate design pattern for this too...
//

public class Settings {
	
    private static Settings _instance = null;
	private HashMap<String, String> _settings;
    
    /**
     * @return the one and only instance (instantiates if necessary)
     */
    public synchronized static Settings getInstance() {
        if (_instance == null){
            _instance = new Settings();
        }
        
        return(_instance);
    }

    /**
     */
    private Settings() {
        _settings = new HashMap<String, String>();
    }
}
