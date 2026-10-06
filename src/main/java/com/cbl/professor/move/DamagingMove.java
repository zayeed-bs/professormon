package com.cbl.professor.move;

public class DamagingMove extends Move {
    final int baseDamage;

    public DamagingMove(String name, int damage) {
        super.name = name;
        this.baseDamage = damage;
    }

    public int getBaseDamage() {
        return baseDamage;
    }
}
