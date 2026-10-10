package com.cbl.window.StartWindow;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cbl.professor.Professor;
import com.cbl.professor.move.Move;

public class ProfessorContainer extends JPanel {
    final String name;
    int hp, attack, defense, speed;
    Move[] moves = new Move[4];

    // fuck ability

    Professor.Type type;
    final ImageIcon sprite; 

    public ProfessorContainer(Professor prof) {
        this.name = prof.getName();
        this.hp = prof.getHp();
        this.attack = prof.getAttack();
        this.defense = prof.getDefense();
        this.speed = prof.getSpeed();
        this.moves = prof.getMoves();
        this.type = prof.getType();
        this.sprite = prof.getSprite();
    }
    public void initializeProfContainer() {
        JPanel container = new JPanel();
        container.setLayout(new GridLayout());

        // name and image, maybe the select option
        JPanel left = new JPanel();
        left.setLayout(new BorderLayout());
        left.add(new JLabel(this.name), BorderLayout.NORTH);
        JLabel spriteImage = new JLabel(this.sprite);
        left.add(spriteImage, BorderLayout.CENTER);

        // Stats
        JPanel middle = new JPanel();
        middle.setLayout(new GridLayout(5, 1));
        middle.add(new JLabel("TYPE: " + this.type));
        middle.add(new JLabel("HP: " + this.hp));
        middle.add(new JLabel("ATTACK: " + this.attack));
        middle.add(new JLabel("DEFENSE: " + this.defense));
        middle.add(new JLabel("SPEED: " + this.speed));

        // Moves
        JPanel right = new JPanel();
        for (int i = 0; i < moves.length; i++) {
            String moveName = moves[i].getName();
            right.add(new JLabel(moveName));
        }

        container.add(left);
        container.add(middle);
        container.add(right);
    }
}
