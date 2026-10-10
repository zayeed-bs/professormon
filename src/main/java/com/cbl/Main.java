package com.cbl;
import java.util.HashMap;

import com.cbl.professor.BattleLogic;
import com.cbl.professor.Library;
import com.cbl.professor.Professor;
import com.cbl.professor.move.Move;
import com.cbl.window.BattleScene;
import com.cbl.window.StartWindow.StartWindow;

public class Main {
    public static void main(String[] args) {
        // Create window & Start game
        Library lib = new Library() {};
        lib.initializeLibrary();

        HashMap<Professor, Move> moveMap = new HashMap<Professor, Move>();
        moveMap.put(lib.getProfessors()[0], lib.getMoves()[1]);
        moveMap.put(lib.getProfessors()[2], lib.getMoves()[8]);

        BattleLogic battleLogic = new BattleLogic();

        battleLogic.handleTurn(moveMap, new Professor[] {lib.getProfessors()[0], lib.getProfessors()[2]});

        // Choose pokemon
        // StartMenu,java
        // p1 professor, p2 professor

        BattleScene b = new BattleScene(lib.getProfessors()[0], lib.getProfessors()[1]);
        b.initializeBattleScene();

        StartWindow start = new StartWindow(lib.getProfessors()[0], lib.getProfessors()[1], lib.getProfessors()[2]);
        start.initializeStartWindow();
    }
}