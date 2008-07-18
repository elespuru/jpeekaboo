package com.xorcyst.jpeekaboo.core;

import com.xorcyst.jpeekaboo.event.NoteMouseListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JWindow;
import javax.swing.text.PlainDocument;

public class Note {

    private Component _parent;
    private JTextArea _note;
    private JScrollPane _noteScrollPane;
    private NoteMouseListener _mouseListener;
    private static final long serialVersionUID = -3113393354034915899L;
    private static boolean _hiding_enabled = true;

    public Note() {
        // does nothing, implies invoker knows
        // what to do next
    }
    
    public Note(Component parent) {
        _parent = parent;
        initializeSelf();
    }
    
    public void setParent(Component parent) {
        _parent = parent;
        initializeSelf();
    }
    
    private void initializeSelf() {
        _note = new JTextArea(new PlainDocument());
        _mouseListener = new NoteMouseListener(_parent, _note);
        _note.addMouseListener(_mouseListener);
        _note.addMouseMotionListener(_mouseListener);
        _note.setEditable(true);
        _note.setEnabled(true);
        _note.setLineWrap(true);
        _note.setWrapStyleWord(true);
        _note.setBackground(new Color(255,255,153));
        _noteScrollPane = new JScrollPane(_note);
        _noteScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        _noteScrollPane.setPreferredSize(new Dimension(250, 250));
        ((JWindow)_parent).getContentPane().add(_noteScrollPane, BorderLayout.CENTER);
    }
    
    public static void toggleHiding() {
        _hiding_enabled = !_hiding_enabled;
    }
    
    public static boolean isHidingEnabled() {
        return(_hiding_enabled);
    }
}
