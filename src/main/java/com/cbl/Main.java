package com.cbl;
import com.cbl.professor.Library;
import com.cbl.window.BattleScene;
import com.cbl.window.StartWindow.StartWindow;

public class Main {
    public static void main(String[] args) {
        // Create window & Start game
        Library lib = new Library() {};
        lib.initializeLibrary();

        BattleScene b = new BattleScene(lib.getProfessors()[0], lib.getProfessors()[1]);
        b.initializeBattleScene();

        StartWindow start = new StartWindow(lib.getProfessors()[0], lib.getProfessors()[1], lib.getProfessors()[2]);
        start.initializeStartWindow();
    }
}