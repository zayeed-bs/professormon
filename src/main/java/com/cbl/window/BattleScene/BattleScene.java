package com.cbl.window.BattleScene;
import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cbl.professor.BattleLogic;
import com.cbl.professor.Professor;
import com.cbl.window.Window;

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

    BattleComponents battleComponents;
    private boolean battleOver = false;


    public BattleScene(Professor ownProfessor, Professor oppProfessor) {
        this.ownProfessor = ownProfessor;
        this.oppProfessor = oppProfessor;
    }

    public void initializeBattleScene() {
        win = new Window();

        win.createWindow(1280, 720, "Battle!");
        win.frame.setLayout(new BorderLayout());

        battleComponents = new BattleComponents(ownProfessor, oppProfessor);

        // battle field
        JPanel battleField = battleComponents.BattlefieldJPanel();
        win.frame.add(battleField, BorderLayout.CENTER);

        JPanel controls = battleComponents.ControlsPanel();
        win.frame.add(controls, BorderLayout.SOUTH);

        // add functionality to continue button
        battleComponents.continueButton.addActionListener(e -> {
            if (battleOver) {
                win.frame.setVisible(false);
            }
            HandleNext();
        });
    }

    public void OpenBattleScene() {
        win.frame.setVisible(true);
    }

    public void HandleNext() {
        if (battleComponents.selectedMoves.size() == 2) {
            BattleLogic bl = new BattleLogic();
            String log = bl.handleTurn(battleComponents.selectedMoves, new Professor[] {ownProfessor, oppProfessor});
            battleComponents.UpdateBattleText(log);

            // check for death
            if (log.contains("defeated")) {
                battleOver = true;
            }

            // update hp as well
            battleComponents.selectedMoves = new HashMap<>();
        } else {
            battleComponents.UpdateBattleText("Both players need to select a move.");
        }
    }
}
