package com.xorcyst.jpeekaboo.ui;

import java.awt.*;
import java.awt.event.*;

public class TaskbarMenu extends PopupMenu {

    private static final long serialVersionUID = 6815348601297451742L;

    public TaskbarMenu() {
        super();
        addAbout();
        addHelp();
        addFiller();
        addExit();
    }

    private void addHelp() {
        MenuItem helpItem = new MenuItem("Help");
        
        helpItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HelpDialog.getInstance().setVisible(true);
            }
        });

        this.add(helpItem);
    }
    
    private void addAbout() {
        MenuItem aboutItem = new MenuItem("About");
        
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AboutDialog.getInstance().setVisible(true);
            }
        });

        this.add(aboutItem);
    }

    private void addExit() {

        MenuItem exitItem = new MenuItem("Exit");

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.add(exitItem);
    }

    private void addFiller() {
        this.add(new MenuItem(""));
    }
}
