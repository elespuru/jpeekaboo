package com.xorcyst.jpeekaboo;

import javax.swing.SwingUtilities;
import com.xorcyst.jpeekaboo.ui.*;
import com.xorcyst.jpeekaboo.core.Application;
import com.xorcyst.jpeekaboo.persistence.StateLock;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (new StateLock("jpeekaboo").isAppActive()) {
                    return;
                }

                new Application();
            }
        });
    }
}
