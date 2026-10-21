package com.cbl.window;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.cbl.professor.Professor;

public class BattleScene {
    Professor p1;
    Professor p2;

    // Components
    Window win;

    JLabel p1Name;
    JLabel p1Sprite;
    JLabel p1Health;

    JLabel p2Name;
    JLabel p2Sprite;
    JLabel p2Health;

    JLabel battleText;

    public BattleScene(Professor p1, Professor p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void initializeBattleScene() {
        win = new Window();

        win.createWindow(500, 500, "Battle!");

        // Attach params from Professor class
        win.frame.setLayout(new FlowLayout());
    
        p1Name = new JLabel(p1.getName());
        p2Name = new JLabel(p2.getName());

        p1Sprite = new JLabel(p1.getSprite());
        p2Sprite = new JLabel(p2.getSprite());;

        battleText = new JLabel("Battle Started");
        
        win.frame.add(p1Name);
        win.frame.add(p2Name);
        
        win.frame.add(p1Sprite);
        win.frame.add(p2Sprite);
        win.frame.add(battleText);

        win.frame.setVisible(true);

        // HERE INITIALIZE THE WINDOW!


        // START TURN
        updateTextPanel("Start battle!");
    }

    public void updateTextPanel(String text) {
        // ui implementation
    }

    public void handleNextAction() {
        // this is called whenever the NEXT button in the UI is pressed

        // so if we're at Start battle, we move on to choosing moves, then playing out the dialogue
    }

}
