package com.xorcyst.jpeekaboo.core;

import com.xorcyst.jpeekaboo.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.swing.text.*;

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

		restoreContent();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
				saveContent();
            }
        });
    }
    
    public static void toggleHiding() {
        _hiding_enabled = !_hiding_enabled;
    }
    
    public static boolean isHidingEnabled() {
        return(_hiding_enabled);
    }

	private void restoreContent() {
		
		try {
        	File saveFile = new File(System.getProperty("user.home"), ".jpeekaboo" + ".save" );
			BufferedReader input = new BufferedReader(new FileReader(saveFile));
			StringBuffer restore = new StringBuffer();
		
			while( true ) {
				String line = input.readLine();
				if( line == null ) { break; }
				restore.append(line);
				restore.append("\n");
			}
		
			_note.setText(restore.toString());
			input.close();
		} catch (FileNotFoundException fnfx) {
			//TODO: add a popup to taskbar for unable to open file
		} catch (IOException iox) {
		}
	}

	private void saveContent() {
		
		try {
        	File saveFile = new File(System.getProperty("user.home"), ".jpeekaboo" + ".save" );
			BufferedWriter output = new BufferedWriter(new FileWriter(saveFile));
			output.write(_note.getText());
			output.flush();
			output.close();
		} catch(FileNotFoundException fnfx) {
			//TODO: add a popup to taskbar for unable save content
		} catch(IOException iox) {
		}
	}
}
