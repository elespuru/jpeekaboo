package com.xorcyst.jpeekaboo.sandbox;

//
//
// play time to prototype the notes stuff
//
//

import java.awt.BorderLayout;
import javax.swing.JWindow;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class MouseMoveListener implements MouseListener, MouseMotionListener {
    Component target;
    
    public MouseMoveListener(Component target) {
        this.target = target;
    }
    
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked");
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("mouseEntered");
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("mouseExited");
    }

    public void mousePressed(MouseEvent e) {
        System.out.println("mousePressed");
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased");
    }

    public void mouseDragged(MouseEvent e) {
        System.out.println("mouseDragged");
        System.exit(0);
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
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

                BorderlessWindow window = new BorderlessWindow();

                window.setFocusable(true);
                window.setEnabled(true);
                window.setFocusableWindowState(true);
                window.setSize(640, 480);
                int xCenter = ((screenSize.width - window.getWidth()) / 2);
                int yCenter = ((screenSize.height - window.getHeight()) / 2);
                window.setLocation(xCenter, yCenter);

                JTextArea note = new JTextArea();
                MouseMoveListener mml = new MouseMoveListener(note);
                note.addMouseListener(mml);
                note.addMouseMotionListener(mml);
                note.setEditable(true);
                note.setEnabled(true);
                note.setLineWrap(true);
                note.setWrapStyleWord(true);
                note.setText("abcdefghijklmnopqrstuvwxyz");

                JScrollPane noteScrollPane = new JScrollPane(note);
                noteScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                noteScrollPane.setPreferredSize(new Dimension(250, 250));
                window.getContentPane().add(noteScrollPane, BorderLayout.CENTER);

                window.setVisible(true);
            }
        });
    }
}
