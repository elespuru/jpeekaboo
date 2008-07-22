package com.xorcyst.jpeekaboo.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

public class HelpDialog extends JDialog {

    private static final long serialVersionUID = -8773180054993490155L;
    private static final int _width = 400;
    private static final int _height = 400;
    private static HelpDialog _instance = null;
    
    public static HelpDialog getInstance() {
        if (_instance == null){
            _instance = new HelpDialog(new JFrame());
        }
        
        return(_instance);
    }
    
    private HelpDialog(JFrame parent) {
        super(parent, "Help", true);
        
        Box b = Box.createVerticalBox();
        b.add(Box.createGlue());
        b.add(new JLabel("Application Help"));
        b.add(new JLabel("disable hiding: hold the control key and double click anywhere in the note to disable hiding"));
        b.add(new JLabel("resize: 1st) disable hiding, 2nd) hold the control key and drag an edge to resize in that direction"));
        b.add(Box.createGlue());
        getContentPane().add(b, "Center");

        JPanel p2 = new JPanel();
        JButton ok = new JButton("Ok");
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
