package com.cbl.window;
import java.io.*;
import javax.swing.*;

public class Window {
    public JFrame frame;

    public void createWindow(int w, int h, String title) {
        // Create window from specified dimensions
        frame = new JFrame(title);
        frame.setSize(w, h);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
