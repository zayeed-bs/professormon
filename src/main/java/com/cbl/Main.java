package com.cbl;
import com.cbl.professor.Library;
import com.cbl.window.BattleScene;
public class Main {
    public static void main(String[] args) {
        // Create window & Start game
        Library lib = new Library() {};
        lib.initializeLibrary();

        // Choose pokemon
        // StartMenu,java
        // p1 professor, p2 professor

        BattleScene b = new BattleScene(lib.getProfessors()[0], lib.getProfessors()[1]);
        b.initializeBattleScene();
    }
}