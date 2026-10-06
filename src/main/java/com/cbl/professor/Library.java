package com.cbl.professor;

import com.cbl.professor.move.DamagingMove;
import com.cbl.professor.move.Move;
import com.cbl.professor.move.StatMove;

public abstract class Library {
    Move[] MOVES = new Move[12];
    Professor[] professors = new Professor[3];


    // Professor definition
    public void initializeLibrary() {
        // Moves
        Move basAttackLight = new DamagingMove("No Laptops", 30);
        Move basAttackHeavy = new DamagingMove("Do you have a question?", 60);
        Move basStatOpp = new StatMove("Weekly Test", 3, false);
        Move basStatSelf = new StatMove("", 2, true);

        Move keesAttackLight = new DamagingMove("Turn off screen", 30);
        Move keesAttackHeavy = new DamagingMove("Give Lecture", 60);
        Move keesStatOpp = new StatMove("Tell a joke", 2, false);
        Move keesStatSelf = new StatMove("Random Analogy", 1, true);

        Move bjornAttackLight = new DamagingMove("Follow the recipe", 30);
        Move bjornAttackHeavy = new DamagingMove("Complete the square", 60);
        Move bjornStatOpp = new StatMove("Tests at 7pm", 3, false);
        Move bjornStatSelf = new StatMove("End the lecture early", 3, true);

        // Sprites
        


        // Professors
        Professor bas = new Professor("Bas Luttik", new int[] {30, 80, 30, 70}, new Move[] {basAttackHeavy, basAttackLight, basStatOpp, basStatSelf}, null, Professor.Type.LOGIC, null);
        Professor kees = new Professor("Kees Huizing", new int[] {90, 20, 90, 15}, new Move[] {keesAttackHeavy, keesAttackLight, keesStatOpp, keesStatSelf}, null, Professor.Type.PROGRAMMING, null);
        Professor bjorn = new Professor("Bjorn Baumeier", new int[] {60, 40, 45, 90}, new Move[] {bjornAttackHeavy, bjornAttackLight, bjornStatOpp, bjornStatSelf}, null, Professor.Type.CALCULUS, null);
    
        professors[0] = bas;
        professors[1] = kees;
        professors[2] = bjorn;
    }
}