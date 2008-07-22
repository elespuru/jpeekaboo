package com.xorcyst.jpeekaboo.event;

import com.xorcyst.jpeekaboo.core.*;
import java.awt.*;
import java.awt.event.*;

public class NoteMouseListener implements MouseListener, MouseMotionListener {
    Component        _parent;
    Component        _target;

    static final int _initial_width = 300;
    static final int _resize_buffer = 10;

    static final int _not_resizing = 0;
    static final int _top_resizing = 1;
    static final int _bottom_resizing = 2;
    static final int _left_resizing = 3;
    static final int _right_resizing = 4;

    public NoteMouseListener(Component parent, Component target) {
        this._parent = parent;
        this._target = target;
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked");
        
        //if they're holding down ctrl, ignore the request
        if ((e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != InputEvent.CTRL_DOWN_MASK) {
            return;
        }

        //if they've double clicked
        if ( e.getClickCount() == 2 ) {
            Note.toggleHiding();
            System.out.println("toggled hiding");
        }
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("mouseEntered");
        
        if ( !Note.isHidingEnabled() ) {
            return;
        }
        
        _parent.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - _initial_width, _parent.getLocationOnScreen().y);
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("mouseExited");

        if ( !Note.isHidingEnabled() ) {
            return;
        }

        //if they're holding down ctrl, ignore the request
        if ((e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) == InputEvent.CTRL_DOWN_MASK) {
            return;
        }

        int psx = _parent.getLocationOnScreen().x;
        int psy = _parent.getLocationOnScreen().y;
        int psw = _parent.getSize().width;
        int psh = _parent.getSize().height;
        int msx = e.getLocationOnScreen().x;
        int msy = e.getLocationOnScreen().y;

        if (msx > psx && msx < (psx + psw) && msy > psy && msy < (psy + psh - 3)) {
            System.out.println("psx: " + psx + " psy: " + psy + " psw: " + psw + " psh: " + psh + " msx: " + msx + " msy: " + msy);
            return;
        }

        _parent.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - 3, _parent.getLocationOnScreen().y);
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

        if ( !Note.isHidingEnabled() ) {
            return;
        }
        
        // only respond to drag events if CTRL is pressed too
        if ((e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != InputEvent.CTRL_DOWN_MASK) {
            return;
        }

        Point screenLocation = e.getLocationOnScreen();
        Point mouseLocation = e.getPoint();

        System.out.println("sx: " + screenLocation.x + " sy: " + screenLocation.y);
        System.out.println("mx: " + mouseLocation.x + " my: " + mouseLocation.y);

        int resizeRegion = inResizeRegion(e);
        
        // top
        if ( resizeRegion == _top_resizing ) {
            resizeTop(e);
        }
        // left
        else if ( resizeRegion == _left_resizing ) {
            resizeLeft(e);
        }
        // right
        else if ( resizeRegion == _right_resizing ) {
            resizeRight(e);
        }
        // bottom
        else if ( resizeRegion == _bottom_resizing ) {
            resizeBottom(e);
        } 
    }
    
    private void resizeTop(MouseEvent e) {
        //Point screenLocation = e.getLocationOnScreen();
        
        int y = 0;
        int height = 0;
        Point newLocation = new Point(_parent.getLocation().x, y);
        _parent.setSize(_parent.getWidth(), height);
        _parent.setLocation(newLocation);
    }
    
    private void resizeBottom(MouseEvent e) {
        Point screenLocation = e.getLocationOnScreen();
        _parent.setSize(_parent.getWidth(), screenLocation.y);
    }
    
    private void resizeLeft(MouseEvent e) {
        Point screenLocation = e.getLocationOnScreen();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        int x = screenLocation.x;
        int width = screenSize.width - screenLocation.y;
        Point newLocation = new Point(x, _parent.getLocation().y);
        _parent.setSize(width, _parent.getHeight());
        _parent.setLocation(newLocation);
    }
    
    private void resizeRight(MouseEvent e) {
        
    }
    
    private int inResizeRegion( MouseEvent e ) {
        Point mouseLocation = e.getPoint();

        // do I have a hit in any resizing region ?!?
        //
        // top
        if ( mouseLocation.y >= 0 && mouseLocation.y <= _resize_buffer ) {
            return(_top_resizing);
        }
        // left
        else if ( mouseLocation.x >= 0 && mouseLocation.x <= _resize_buffer ) {
            return(_left_resizing);
        }
        // right
        else if( mouseLocation.x >= (_target.getWidth() - _resize_buffer) && mouseLocation.x <= _target.getWidth() ) {
            return(_right_resizing);
        }
        // bottom
        else if ( mouseLocation.y > (_target.getHeight() - _resize_buffer) && mouseLocation.y < _target.getHeight()) {
            return(_bottom_resizing);
        }
        // nothing matched, not in a resize region
        else {
            return(_not_resizing);
        }
    }

    public void mouseMoved(MouseEvent e) {
        System.out.println("mouseMoved");
    }

}
