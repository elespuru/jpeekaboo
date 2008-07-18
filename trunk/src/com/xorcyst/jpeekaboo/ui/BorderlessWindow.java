package com.xorcyst.jpeekaboo.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JWindow;

public class BorderlessWindow extends JWindow {

    private static final long serialVersionUID = 8515365993272218057L;

    public BorderlessWindow() {

        super(new JFrame() {
            private static final long serialVersionUID = 138914985931428290L;

            public boolean isShowing() {
                return true;
            }
        });

        initializeSelf();
    }

    private void initializeSelf() {
        final int width = 300;
        final int buffer = 100;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setFocusable(true);
        this.setEnabled(true);
        this.setFocusableWindowState(true);
        this.setSize(width, screenSize.height - buffer);
        int x = (screenSize.width - width);
        int y = (buffer / 2);
        this.setLocation(x, y);
        this.setAlwaysOnTop(true);
        this.setBackground(new Color(255, 255, 153));
    }
}
