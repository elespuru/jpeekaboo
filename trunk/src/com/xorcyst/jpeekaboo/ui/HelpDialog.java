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

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * The HelpDialog class provides what the name suggests, a dialog box
 * that displays help information.
 * 
 * To avoid multiple dialogs, it implements the singleton design pattern
 */
public class HelpDialog extends JDialog {

    private static final long serialVersionUID = -8773180054993490155L;
    private static final int _width = 400;
    private static final int _height = 400;
    private static HelpDialog _instance = null;
    private static final String _help = "jpeekaboo Help\n\n>>> To disable hiding, hold down control and double click anywhere in the note\n\n>>> To resize the note, first disable hiding, then hold down control and drag an edge (your mouse icon will change when you're able to resize)";
    
    /**
     * @return the one and only instance of this dialog (instantiate if necessary)
     */
    public static HelpDialog getInstance() {
        if (_instance == null){
            _instance = new HelpDialog(new JFrame());
        }
        
        return(_instance);
    }
    
    /**
     * private constructor to create and initialize the dialog the first time
     * @param parent
     */
    private HelpDialog(JFrame parent) {
        super(parent, "Help", true);
        
        Box b = Box.createVerticalBox();
        b.add(Box.createGlue());
        JTextPane content = new JTextPane();
        content.setText(_help);
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_CENTER);
        content.getStyledDocument().setParagraphAttributes(0,content.getText().length(), attributes, true);
        b.add(content);
        b.add(Box.createGlue());
        getContentPane().add(b, "Center");

        JPanel p2 = new JPanel();
        JButton ok = new JButton("Close");
        p2.add(ok);
        getContentPane().add(p2, "South");

        ok.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            setVisible(false);
          }
        });

        setSize(_width, _height);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = ((screenSize.width/2) - (_width/2));
        int y = ((screenSize.height/2) - (_height/2));
        this.setLocation(x, y);
    }
}
