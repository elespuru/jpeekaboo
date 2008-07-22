package com.xorcyst.jpeekaboo.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

public class AboutDialog extends JDialog {

    private static final long serialVersionUID = -8773180054993490155L;
    private static final int _width = 250;
    private static final int _height = 150;
    private static AboutDialog _instance = null;
    
    public static AboutDialog getInstance() {
        if (_instance == null){
            _instance = new AboutDialog(new JFrame());
        }
        
        return(_instance);
    }

    private AboutDialog(JFrame parent) {
        
        super(parent, "About jpeekaboo", true);

        Box b = Box.createVerticalBox();
        b.add(Box.createGlue());
        b.add(new JLabel("jpeekaboo - jpeekaboo.xorcyst.com"));
        b.add(new JLabel("Copyright 2008 Peter R. Elespuru"));
        b.add(new JLabel("email: peter@xorcyst.com"));
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
