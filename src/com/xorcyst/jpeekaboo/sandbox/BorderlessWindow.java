// jpeekaboo - auto hide note thing
// Copyright 2008 by Peter R. Elespuru
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

package com.xorcyst.jpeekaboo.sandbox;

//
//
// play time to prototype the notes stuff
// this will be a garbled mess of code that
// will later be broken out and cleaned up
// to provide the final deal
//
//


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.text.*;

class BorderlessMouseMoveListener implements MouseListener, MouseMotionListener {
    Component parent;
    Component target;
    
    static final int width = 300;
    
    public BorderlessMouseMoveListener(Component parent, Component target) {
        this.parent = parent;
        this.target = target;
    }
    
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked");
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("mouseEntered");
        parent.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width-width, parent.getLocationOnScreen().y);
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("mouseExited");
        
        int psx = parent.getLocationOnScreen().x;
        int psy = parent.getLocationOnScreen().y;
        int psw = parent.getSize().width;
        int psh = parent.getSize().height;
        int msx = e.getLocationOnScreen().x;
        int msy = e.getLocationOnScreen().y;
        
        if( msx > psx && msx < (psx+psw) && msy > psy && msy < (psy+psh-3) ) {
            System.out.println("psx: "+psx+" psy: "+psy+" psw: "+psw+" psh: "+psh+" msx: "+msx+" msy: "+msy);
            return;
        }
        
        parent.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width-3,parent.getLocationOnScreen().y);
        System.out.println("triggered exit");
    }

    public void mousePressed(MouseEvent e) {
        System.out.println("mousePressed");
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased");
    }

    public void mouseDragged(MouseEvent e) {
        System.out.println("mouseDragged");
        
        if ((e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != InputEvent.CTRL_DOWN_MASK) {
            return;
        }
        
        Point screenLocation = e.getLocationOnScreen();
        Point mouseLocation = e.getPoint();

        Point newLocation = 
            new Point(screenLocation.x-((parent.getWidth()/2)),screenLocation.y-((parent.getHeight()/2)));

        System.out.println("sx: "+screenLocation.x+" sy: "+screenLocation.y);
        System.out.println("mx: "+mouseLocation.x+" my: "+mouseLocation.y);
        System.out.println("nx: "+newLocation.x+" ny: "+newLocation.y);

        parent.setLocation(newLocation);
    }

    public void mouseMoved(MouseEvent e) {
        System.out.println("mouseMoved");
    }
}

public class BorderlessWindow extends JWindow {

    private static final long serialVersionUID = -3113393354034915899L;

    public BorderlessWindow() {
        super(new JFrame() {
            private static final long serialVersionUID = 138914985931428290L;
            public boolean isShowing() {
                return true;
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                // add save to file and sync to remote
            }
        });
    }

    public static void main(String[] args) {
        
        StateLock state = new StateLock("jpeekaboo");
        if( state.isAppActive() ) {
            //TODO: add a popup here saying there's already one running
            return;
        }
        
        final int width = 300;
        final int buffer = 100;

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

                BorderlessWindow window = new BorderlessWindow();

                window.setFocusable(true);
                window.setEnabled(true);
                window.setFocusableWindowState(true);
                window.setSize(width, screenSize.height-buffer);
                int x = (screenSize.width-width);
                int y = (buffer/2);
                window.setLocation(x, y);
                window.setAlwaysOnTop(true);
                window.setBackground(new Color(255,255,153));

                JTextArea note = new JTextArea(new PlainDocument());
                BorderlessMouseMoveListener mml = new BorderlessMouseMoveListener(window, note);
                note.addMouseListener(mml);
                note.addMouseMotionListener(mml);
                note.setEditable(true);
                note.setEnabled(true);
                note.setLineWrap(true);
                note.setWrapStyleWord(true);
                note.setBackground(new Color(255,255,153));
                
                JScrollPane noteScrollPane = new JScrollPane(note);
                noteScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                noteScrollPane.setPreferredSize(new Dimension(250, 250));
                window.getContentPane().add(noteScrollPane, BorderLayout.CENTER);

                window.setVisible(true);
                
                final TrayIcon trayIcon;
                if (SystemTray.isSupported()) {

                    SystemTray tray = SystemTray.getSystemTray();
                    Image image = Toolkit.getDefaultToolkit().getImage("tray.png");

                    ActionListener exitListener = new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            System.exit(0);
                        }
                    };
                            
                    PopupMenu popup = new PopupMenu();
                    popup.add(new MenuItem("Settings"));
                    popup.add(new MenuItem("Synchronize offsite"));
                    popup.add(new MenuItem(""));
                    MenuItem defaultItem = new MenuItem("Exit");
                    defaultItem.addActionListener(exitListener);
                    popup.add(defaultItem);
                    trayIcon = new TrayIcon(image, "jpeekaboo", popup);
                    trayIcon.setImageAutoSize(true);
                    
                    try {
                        tray.add(trayIcon);

                        /*
                        trayIcon.displayMessage("Finished loading", 
                                "Your Java application has finished loading",
                                TrayIcon.MessageType.INFO);
                                */

                    } catch (AWTException e) {
                        System.err.println("TrayIcon could not be added.");
                    }

                } else {

                    // throw up a popup saying not supported, sorry
                    //  System Tray is not supported

                }
                
            }
        });
    }
}
