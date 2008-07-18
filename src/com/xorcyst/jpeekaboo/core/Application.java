package com.xorcyst.jpeekaboo.core;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import com.xorcyst.jpeekaboo.ui.BorderlessWindow;
import com.xorcyst.jpeekaboo.ui.TaskbarMenu;

public class Application {

    public Application() {
        BorderlessWindow window = new BorderlessWindow();
        Note note = new Note();
        note.setParent(window);
        window.setVisible(true);
        setupSystemTray();
    }

    private void setupSystemTray() {

        final TrayIcon trayIcon;
        if (SystemTray.isSupported()) {

            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage("tray.png");

            trayIcon = new TrayIcon(image, "jpeekaboo", new TaskbarMenu());
            trayIcon.setImageAutoSize(true);

            try {
                tray.add(trayIcon);

                /*
                 * trayIcon.displayMessage("Finished loading",
                 * "Your Java application has finished loading",
                 * TrayIcon.MessageType.INFO);
                 */

            } catch (AWTException e) {
                System.err.println("TrayIcon could not be added.");
            }
        } else {
            // throw up a popup saying not supported, sorry
            // System Tray is not supported
        }
    }
}
