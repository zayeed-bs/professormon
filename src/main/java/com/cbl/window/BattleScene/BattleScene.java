package com.cbl.window.BattleScene;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

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

    JLabel battleText;

    public BattleScene(Professor ownProfessor, Professor oppProfessor) {
        this.ownProfessor = ownProfessor;
        this.oppProfessor = oppProfessor;
    }

    public void initializeBattleScene() {
        win = new Window();

        win.createWindow(1280, 720, "Battle!");
        win.frame.setLayout(new BorderLayout());

        BattleComponents battleComponents = new BattleComponents(ownProfessor, oppProfessor);

        // battle field
        JPanel battleField = battleComponents.BattlefieldJPanel();
        win.frame.add(battleField, BorderLayout.CENTER);

        JPanel controls = battleComponents.ControlsPanel();
        win.frame.add(controls, BorderLayout.SOUTH);
    }

    public void OpenBattleScene() {
        win.frame.setVisible(true);
    }

    public void updateTextPanel(String text) {

    }
}
