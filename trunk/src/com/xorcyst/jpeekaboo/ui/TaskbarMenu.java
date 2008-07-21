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
        this.add(new MenuItem("Help"));
    }
    
    private void addAbout() {
        this.add(new MenuItem("About"));
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
