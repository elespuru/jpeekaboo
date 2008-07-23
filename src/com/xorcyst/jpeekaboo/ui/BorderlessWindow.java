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

package com.xorcyst.jpeekaboo.ui;

import java.awt.*;
import javax.swing.*;

/**
 * The BorderlessWindow class implements a decoration-less JWindow
 */
public class BorderlessWindow extends JWindow {

    private static JFrame _frame;
    private static final long serialVersionUID = 8515365993272218057L;

    /**
     * default constructor
     */
    public BorderlessWindow() {

        super(_frame = new JFrame() {
            private static final long serialVersionUID = 138914985931428290L;

            public boolean isShowing() {
                return true;
            }
        });

        initializeSelf();
    }

    /**
     * helper method to modularize initialization code out a bit
     */
    private void initializeSelf() {

        final int width = 300;
        final int buffer = 100;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        _frame.setUndecorated(true);
        _frame.setTitle("jpeekaboo");
        this.setAlwaysOnTop(true);
        this.setBackground(new Color(255, 255, 153));
        this.setFocusable(true);
        this.setEnabled(true);
        this.setFocusableWindowState(true);
        this.setSize(width, screenSize.height - buffer);
        int x = (screenSize.width - width);
        int y = (buffer / 2);
        this.setLocation(x, y);
    }
}
