package com.cbl.window.BattleScene;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.cbl.professor.Professor;
import com.cbl.professor.move.Move;

public class BattleComponents {
    Professor p1Prof, p2Prof;
    public HashMap<Professor, Move> selectedMoves = new HashMap<>();

    public JButton continueButton;
    private JLabel text;

    public BattleComponents(Professor p1Prof, Professor p2Prof) {
        this.p1Prof = p1Prof;
        this.p2Prof = p2Prof;
    }

    public JPanel MovesContainer(Professor p, JLabel battleLog) {
        JPanel movesCont = new JPanel();
        movesCont.setLayout(new GridLayout(4, 1));
        Move[] moves = p.getMoves();
        for (Move move : moves) {
            String moveName = move.getName();
            JButton moveButton = new JButton(moveName);
            moveButton.addActionListener(event -> {
                selectedMoves.put(p, move);
                battleLog.setText(p.getName() + " selected " + move.getName());
            });
            movesCont.add(moveButton);

        }
        return movesCont;
    }

    public Move getSelectedMove(Professor p) {
        return selectedMoves.get(p);
    }

    public JPanel PlayerPanel(Professor p) {
        JPanel playerCont = new JPanel();
        playerCont.setLayout(new BorderLayout());
        JLabel hpLabel = new JLabel("HP: " + p.getHp());
        playerCont.add(hpLabel, BorderLayout.NORTH);
        
        JLabel spriteImage = new JLabel(p.getSprite());
        playerCont.add(spriteImage, BorderLayout.CENTER);
        
        return playerCont;
    }

    public JPanel ControlsPanel() {
        // Moves and battle log
        JPanel controls = new JPanel();
        controls.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        JPanel middle = new JPanel();
        middle.setLayout(new BorderLayout());
        text = new JLabel("Battle Start!", SwingConstants.CENTER);
        middle.add(text, BorderLayout.CENTER);
        continueButton = new JButton("CONTINUE");
        middle.add(continueButton, BorderLayout.PAGE_END);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        controls.add(middle, c);

        JPanel movesP1 = MovesContainer(this.p1Prof, text);
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 40;
        c.gridx = 0;
        c.gridy = 0;
        controls.add(movesP1, c);


        JPanel movesP2 = MovesContainer(this.p2Prof, text);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 0;
        controls.add(movesP2, c);

        controls.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return controls;
    }

    public JPanel BattlefieldJPanel() {
        JPanel battlefield = new JPanel();
        battlefield.setLayout(new GridLayout(0, 2));
        JPanel p1Panel = PlayerPanel(this.p1Prof);
        JPanel p2Panel = PlayerPanel(this.p2Prof);
        battlefield.add(p1Panel);
        battlefield.add(p2Panel);
        return battlefield;
    }

    public void UpdateBattleText(String t) {
        text.setText(t);
    }
}
