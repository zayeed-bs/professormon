package com.cbl;

public class Professor {
    final String name;
    // base stats that should not be changed
    final int hp, attack, defense, speed;
    // number of moves is hard coded here
    Move[] moves = new Move[4];
    Ability ability;
    Type type;
    
    // variables that are changed by buffs/debuffs (current = crnt)
    int crntHp, crntAttack, crntDefense, crntSpeed;

    boolean isStunned = false;

    public enum Type {
        LOGIC,
        CALCULUS,
        PROGRAMMING
    } 


    public Professor(String name, int hp, int attack, int defense, int speed, Move[] moves, Ability ability, Type type) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.moves = moves;
        this.ability = ability;
        this.type = type;
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

    // buffs/debuffs
    public int modifyStat(int stat, int modifier) {
        // now modifier is flat, cuz percetage based can change int to float (dont know is that is a problem)
        int newStat = stat + modifier;
        return newStat;
    }
    public void modifyAttack(int modifier) {
        this.crntAttack = modifyStat(attack, modifier);
    }
    public void modifyDefense(int modifier) {
        this.crntDefense = modifyStat(defense, modifier);
    }
    public void modifySpeed(int modifier) {
        this.crntSpeed = modifyStat(speed, modifier);
    }

    // modifying hp is basically taking damage, unless we want to increase it, and then we prob need to distinguish between max and current hp
    // if we dont do that, hp should not be final var
    public void modifyHp(int modifier) {
        this.crntHp = modifyStat(hp, modifier);
    }

    public void stun() {
        isStunned = true;
    }

}
