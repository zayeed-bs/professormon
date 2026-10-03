package com.cbl;

public class Move {
    String name;
    int damage;

    boolean canStun = false;
    boolean canPoison = false;
    // idk what else we want to do 

    public Move(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public String getName() {
        return name;
    } 
    public int getDmg() {
        return damage;
    }

    public boolean stun(Professor prof) {
        if (this.canStun) {
            prof.stun();
            return true;
        } else {
            return false;
        }
    }
    // same shit with poison if we want that 
}
