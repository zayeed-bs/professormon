package com.cbl;
import java.util.HashMap;

import com.cbl.professor.BattleLogic;
import com.cbl.professor.Library;
import com.cbl.professor.Professor;
import com.cbl.professor.move.Move;
import com.cbl.window.StartWindow.StartWindow;

public class Main {
    public static void main(String[] args) {
        // Create window & Start game
        Library lib = new Library() {};
        lib.initializeLibrary();

        // Choose pokemon
        StartWindow start = new StartWindow(lib.getProfessors()[0], lib.getProfessors()[1], lib.getProfessors()[2]);
        start.initializeStartWindow();
    }
}