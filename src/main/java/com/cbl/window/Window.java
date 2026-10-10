package com.cbl.window;
import javax.swing.JFrame;

public class Window {
    public JFrame frame;

    public void createWindow(int w, int h, String title) {
        // Create window from specified dimensions
        frame = new JFrame(title);
        frame.setSize(w, h);

        frame.setLocationRelativeTo(null); //centers the window to the center of the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
