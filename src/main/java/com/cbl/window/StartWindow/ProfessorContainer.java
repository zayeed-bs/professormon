package com.cbl.window.StartWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.cbl.professor.Professor;
import com.cbl.professor.move.Move;

public class ProfessorContainer {
    final String name;
    int hp;
    int attack;
    int defense;
    int speed;
    Move[] moves = new Move[4];
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
    
    public JPanel initializeProfContainer(ButtonGroup buttonGroup) {
        JPanel container = new JPanel();
        container.setLayout(new GridLayout());

        // name and image, maybe the select option
        JPanel left = new JPanel();
        left.setLayout(new BorderLayout());
        // name and radiobutton
        JPanel leftUp = new JPanel();
        leftUp.setLayout(new FlowLayout());
        leftUp.add(new JLabel(this.name));
        JRadioButton selectionButton = new JRadioButton();
        selectionButton.setActionCommand(this.name);
        buttonGroup.add(selectionButton);
        leftUp.add(selectionButton);
        left.add(leftUp, BorderLayout.NORTH);
        // image
        JLabel spriteImage = new JLabel(this.sprite);
        left.add(spriteImage, BorderLayout.CENTER);

        // Stats
        JPanel middle = new JPanel();
        middle.setLayout(new BorderLayout());
        middle.add(new JLabel("STATS"), BorderLayout.NORTH);
        
        JPanel middleCenter = new JPanel();
        middleCenter.setLayout(new GridLayout(5, 1));
        middleCenter.add(new JLabel("TYPE: " + this.type));
        middleCenter.add(new JLabel("HP: " + this.hp));
        middleCenter.add(new JLabel("ATTACK: " + this.attack));
        middleCenter.add(new JLabel("DEFENSE: " + this.defense));
        middleCenter.add(new JLabel("SPEED: " + this.speed));
        middle.add(middleCenter, BorderLayout.CENTER);

        // Moves
        JPanel right = new JPanel();
        right.setLayout(new BorderLayout());
        right.add(new JLabel("MOVES"), BorderLayout.NORTH);

        JPanel rightCenter = new JPanel();
        rightCenter.setLayout(new GridLayout(4, 1));
        for (int i = 0; i < moves.length; i++) {
            String moveName = moves[i].getName();
            rightCenter.add(new JLabel(moveName));
        }
        right.add(rightCenter, BorderLayout.CENTER);

        // completion
        container.add(left);
        container.add(middle);
        container.add(right);

        // adds border to the container
        container.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return container;
    }
}
