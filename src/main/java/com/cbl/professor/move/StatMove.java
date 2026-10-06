package com.cbl.professor.move;

public class StatMove extends Move {
    int statToChange;
    boolean onSelf;

    public StatMove(String name, int statToChange, boolean onSelf) {
        super.name = name;
        this.statToChange = statToChange;
        this.onSelf = onSelf;
    }
}

// givelecutr = new StatMove()
