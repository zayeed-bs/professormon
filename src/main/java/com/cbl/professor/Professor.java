package com.cbl.professor;
import javax.swing.ImageIcon;

import com.cbl.professor.move.Move;

public class Professor {
    final String name;
    // base stats that should not be changed
    final int attack, defense, speed;
    // number of moves is hard coded here¨
    int hp;
    Move[] moves = new Move[4];
    Type type;
    final ImageIcon sprite; 
    
    // variables that are changed by buffs/debuffs (current = crnt)
    public int currentHp, currentAttack, currentDefense, currentSpeed;

    public enum Type {
        LOGIC,
        CALCULUS,
        PROGRAMMING
    } 


    public Professor(String name, int[] stats, Move[] moves, Type type, ImageIcon sprite) {
        this.name = name;
        this.hp = stats[0];

        this.attack = stats[1];
        this.defense = stats[2];
        this.speed = stats[3];

        this.currentAttack = attack;
        this.currentDefense = defense;
        this.currentSpeed = speed;
        this.currentHp = hp;

        this.moves = moves;
        this.type = type;
        this.sprite = sprite;
    }
    
    // getter methods
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public Move[] getMoves() {
        return moves;
    }

    public Type getType() {
        return type;
    }

    public ImageIcon getSprite() {
        return sprite;
    }


    // buffs/debuffs
    public boolean modifyStat(int statIndex, int modifier) {
        int statVal;

        switch (statIndex) {
            case 0: statVal = currentAttack; break;
            case 1: statVal = currentDefense; break;
            case 2: statVal = currentSpeed; break;
            default: throw new IllegalArgumentException("Unknown stat index: " + statIndex);
        }

        int newVal = Math.max(1, Math.min(100, statVal + modifier));
        if (newVal == statVal) {
            return false;
        }

        switch (statIndex) {
            case 0: currentAttack = newVal; break;
            case 1: currentDefense = newVal; break;
            case 2: currentSpeed = newVal; break;
        }

        return true;
    }
}
