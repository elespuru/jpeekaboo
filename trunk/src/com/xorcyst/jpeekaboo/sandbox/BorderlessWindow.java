package com.xorcyst.jpeekaboo.sandbox;

//
//
// play time to prototype the notes stuff
//
//

//
// menubar/taskbar icon with settings options
//   color chooser
//   font chooser
//   sync settings
//
// exit program saves content of note
//
// add hold ctrl to resize and move
//   if near edge resize that edge
//   if move, pin to first edge it touches
//

import java.awt.BorderLayout;
import javax.swing.JWindow;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.text.PlainDocument;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Point;

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
        return;
/*
        Point screenLocation = e.getLocationOnScreen();
        Point mouseLocation = e.getPoint();

        Point newLocation = 
            new Point(screenLocation.x-((parent.getWidth()/2)),screenLocation.y-((parent.getHeight()/2)));

        System.out.println("sx: "+screenLocation.x+" sy: "+screenLocation.y);
        System.out.println("mx: "+mouseLocation.x+" my: "+mouseLocation.y);
        System.out.println("nx: "+newLocation.x+" ny: "+newLocation.y);

        parent.setLocation(newLocation);
*/
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

                JTextArea note = new JTextArea(new PlainDocument());
                BorderlessMouseMoveListener mml = new BorderlessMouseMoveListener(window, note);
                note.addMouseListener(mml);
                note.addMouseMotionListener(mml);
                note.setEditable(true);
                note.setEnabled(true);
                note.setLineWrap(true);
                note.setWrapStyleWord(true);
                
                JScrollPane noteScrollPane = new JScrollPane(note);
                noteScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                noteScrollPane.setPreferredSize(new Dimension(250, 250));
                window.getContentPane().add(noteScrollPane, BorderLayout.CENTER);

                window.setVisible(true);
            }
        });
    }
}
