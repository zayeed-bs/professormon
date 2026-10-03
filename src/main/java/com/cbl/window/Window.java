package com.cbl.window;
import java.io.*;
import javax.swing.*;

public class Window {
    public static void createWindow(int w, int h, String title) {
        // Create window from specified dimensions
        JFrame frame = new JFrame(title);
        frame.setSize(w, h);
        frame.setVisible(true);
    }
}
