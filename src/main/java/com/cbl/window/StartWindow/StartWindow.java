package com.cbl.window.StartWindow;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cbl.professor.Professor;
import com.cbl.window.Window;

public class StartWindow {
    Professor bas;
    Professor kees;
    Professor bjorn;

    Window win;

    public StartWindow(Professor bas, Professor kees, Professor bjorn) {
        this.bas = bas;
        this.kees = kees;
        this.bjorn = bjorn;
    }
    public void initializeStartWindow() {
        win = new Window();
        win.createWindow(500, 500, "Start Menu");

        win.frame.setLayout(new BorderLayout());

        JPanel topContainer = new JPanel();
        topContainer.setLayout(new GridLayout());
        topContainer.add(new JLabel("PLAYER 1"));
        topContainer.add(new JLabel("PLAYER 2"));

        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new GridLayout(3, 2));
        ProfessorContainer basContainer = new ProfessorContainer(bas);
        ProfessorContainer keesContainer = new ProfessorContainer(kees);
        ProfessorContainer bjornContainer = new ProfessorContainer(bjorn);

        centerContainer.add(basContainer);
        centerContainer.add(keesContainer);
        centerContainer.add(bjornContainer);

        centerContainer.add(basContainer);
        centerContainer.add(keesContainer);
        centerContainer.add(bjornContainer);



        win.frame.add(topContainer, BorderLayout.NORTH);
        win.frame.add(centerContainer, BorderLayout.CENTER);

        win.frame.setVisible(true);
    }
    
}
