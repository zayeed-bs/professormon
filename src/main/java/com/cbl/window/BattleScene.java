package com.cbl.window;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.cbl.professor.Professor;

public class BattleScene {
    Professor ownProfessor;
    Professor oppProfessor;

    // Components
    Window win;

    JLabel ownName;
    JLabel ownSprite;
    JLabel ownHealth;

    JLabel oppName;
    JLabel oppSprite;
    JLabel oppHealth;

    JLabel battleText;

    public BattleScene(Professor ownProfessor, Professor oppProfessor) {
        this.ownProfessor = ownProfessor;
        this.oppProfessor = oppProfessor;
    }

    public void initializeBattleScene() {
        win = new Window();

        win.createWindow(500, 500, "Battle!");

        // Attach params from Professor class
        win.frame.setLayout(new FlowLayout());
    
        ownName = new JLabel(ownProfessor.getName());
        oppName = new JLabel(oppProfessor.getName());

        ownSprite = new JLabel(ownProfessor.getSprite());
        oppSprite = new JLabel(oppProfessor.getSprite());;

        battleText = new JLabel("Battle Started");
        
        win.frame.add(ownName);
        win.frame.add(oppName);
        
        win.frame.add(ownSprite);
        win.frame.add(oppSprite);
        win.frame.add(battleText);

        win.frame.setVisible(true);
    }

    public void updateTextPanel(String text) {

    }
}
