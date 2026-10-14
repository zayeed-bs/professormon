package com.cbl.window.StartWindow;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cbl.professor.Professor;
import com.cbl.window.Window;
import com.cbl.professor.Library;

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
        Professor[] profs = new Professor[2];

        win = new Window();
        win.createWindow(1280, 720, "Start Menu");

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
        ButtonGroup p1SelectionGroup = new ButtonGroup();
        ButtonGroup p2SelectionGroup = new ButtonGroup();

        centerContainer.add(basContainer.initializeProfContainer(p1SelectionGroup));
        centerContainer.add(basContainer.initializeProfContainer(p2SelectionGroup));

        centerContainer.add(keesContainer.initializeProfContainer(p1SelectionGroup));
        centerContainer.add(keesContainer.initializeProfContainer(p2SelectionGroup));

        centerContainer.add(bjornContainer.initializeProfContainer(p1SelectionGroup));
        centerContainer.add(bjornContainer.initializeProfContainer(p2SelectionGroup));


        JPanel bottomContainer = new JPanel();
        JButton starButton = new JButton("BATTLE!");
        bottomContainer.add(starButton);
        starButton.addActionListener(e -> {
            // check if the button was selected
            if (p1SelectionGroup.getSelection() == null || p2SelectionGroup.getSelection() == null) {
                starButton.setText("SELECT YOUR PROFESSOR!");
                return;
            }

            String p1ChosenProfessor = p1SelectionGroup.getSelection().getActionCommand();
            String p2ChosenProfessor = p2SelectionGroup.getSelection().getActionCommand();

            profs[0] = getProfFromName(p1ChosenProfessor);
            profs[1] = getProfFromName(p2ChosenProfessor);

            // Initialize battle scene with professors
            System.out.println(profs[0].getName());
            System.out.println(profs[1].getName());
            
            closeStartWindow();
        });
        

        win.frame.add(topContainer, BorderLayout.NORTH);
        win.frame.add(centerContainer, BorderLayout.CENTER);
        win.frame.add(starButton, BorderLayout.SOUTH);

        win.frame.setVisible(true);
    }
    
    private Professor getProfFromName(String name) {
        Library lib = new Library() {};
        lib.initializeLibrary();
        Professor[] profs = lib.getProfessors();

        for (Professor prof : profs) {
            if (prof.getName().equals(name)) {
                return prof;
            }
        }

        return null;
    }

    public void closeStartWindow() {
        win.frame.setVisible(false);
    }
}
