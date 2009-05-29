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
import java.util.concurrent.*;

//
// Ideally this should store an xml config file or similar, or perhaps
// go so far as to bundle HSQLDB/Derby/whatever and provide config via
// embedded db. I'm sure there's an appropriate design pattern for this too...
// and I don't want to use properties files.
//

public class Settings {
	
	private static final ConcurrentHashMap<String, String> _settings = new ConcurrentHashMap<String, String>();
    
	//
	// initial/default settings 
	// 
	static {
		_settings.put("pinLeft","false");
		_settings.put("pinRight","true");
		_settings.put("verticalPercentage","60");
	}
	
	/**
	 * @param name
	 * @return
	 */
    public static String get(String name) {
    	return(_settings.get(name));
    }

    /**
     * @param name
     * @param value
     */
    public static void put(String name, String value) {
    	_settings.put(name, value);
    }
}
