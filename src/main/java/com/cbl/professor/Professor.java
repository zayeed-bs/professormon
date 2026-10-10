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
    Ability ability;
    Type type;
    final ImageIcon sprite; 
    
    // variables that are changed by buffs/debuffs (current = crnt)
    int currentHp, currentAttack, currentDefense, currentSpeed;

    public enum Type {
        LOGIC,
        CALCULUS,
        PROGRAMMING
    } 


    public Professor(String name, int[] stats, Move[] moves, Ability ability, Type type, ImageIcon sprite) {
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
        this.ability = ability;
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

    public Ability getAbility() {
        return ability;
    }

    public Type getType() {
        return type;
    }

    public ImageIcon getSprite() {
        return sprite;
    }


    // buffs/debuffs
    public boolean modifyStat(int statIndex, int modifier) {
        // now modifier is flat, cuz percetage based can change int to float (dont know is that is a problem)
        int statVal = new int[]{currentAttack, currentDefense, currentSpeed}[statIndex];

        if(statVal == 100 | statVal == 1) {
            return false;
        }

        new int[]{currentAttack, currentDefense, currentSpeed}[statIndex] = Math.clamp(statVal + modifier, 1, 100);
        return true;
    }
}
