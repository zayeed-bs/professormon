package com.cbl.window.BattleScene;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cbl.professor.Professor;

public class PlayerPanel extends JPanel {
    private JLabel hpLabel; 
    private JLabel spriteImage;

    public PlayerPanel(Professor p) {
        setLayout(new BorderLayout());
        
        this.hpLabel = new JLabel("HP: " + p.getCurrentHp());
        add(hpLabel, BorderLayout.NORTH);
        
        this.spriteImage = new JLabel(p.getSprite());
        add(spriteImage, BorderLayout.CENTER);
    }

    public void updateHp(int currentHp) {
        hpLabel.setText("HP: " + currentHp);
    }
}
