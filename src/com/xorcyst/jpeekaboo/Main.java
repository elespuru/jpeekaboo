package com.xorcyst.jpeekaboo;

import javax.swing.*;
import com.xorcyst.jpeekaboo.core.*;
import com.xorcyst.jpeekaboo.persistence.*;

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
