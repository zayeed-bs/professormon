package com.cbl.window;
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
        ownName = new JLabel("self");
        oppName = new JLabel("opponent");


        ownSprite = new JLabel(new ImageIcon());
        oppSprite = new JLabel(new ImageIcon());;

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
