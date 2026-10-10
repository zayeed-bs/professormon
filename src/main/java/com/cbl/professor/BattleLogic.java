package com.cbl.professor;

import com.cbl.professor.move.DamagingMove;
import com.cbl.professor.move.Move;
import com.cbl.professor.move.StatMove;

import java.util.HashMap;
import java.util.Random;

public class BattleLogic {
    public void handleTurn(HashMap<Professor, Move> moveMap, Professor[] profs) {
        // check speed
        profs = orderBySpeed(profs);
        // handle damage / stat affect
        for (int i = 0; i < 2; i++) {
            Professor p = profs[i];
            System.out.println(p.name);
            if (moveMap.get(p) instanceof DamagingMove) {
                int damage = calculateDamage((DamagingMove) moveMap.get(p), p, getOther(profs, i));
                System.out.println(damage);
            } else if (moveMap.get(p) instanceof StatMove) {
                boolean updatedStat = updateStats((StatMove) moveMap.get(p), p, getOther(profs, i));
                System.out.println(updatedStat);
            }
        }
        // check death
    }

    public Professor[] orderBySpeed(Professor[] p) {
        Professor[] p_sorted = new Professor[2];

        if(p[0].currentSpeed > p[1].currentSpeed) {
            // same order, .clone to avoid errors
            System.out.println(p[0].currentSpeed + "is greater than" + p[1].currentSpeed);
            p_sorted = p.clone();
        } else if (p[0].currentSpeed == p[1].currentSpeed){
            // Speeds are matched, decide randomly
            Random rng = new Random();

            int randomIndex = rng.nextInt(0,2);
            p_sorted[0] = p[randomIndex];
            p_sorted[1] = randomIndex == 0 ? p[1] : p[0];

            System.out.println("fuck me");

        } else {
            // reverse order
            System.out.println(p[0].currentSpeed + "is less than" + p[1].currentSpeed);

            p_sorted[0] = p[1];
            p_sorted[1] = p[0];
        }
        
        return p_sorted;
    }

    public int calculateDamage(DamagingMove move, Professor attacker, Professor defender) {
        // damage formula
        // variation between 0.75x to 1.25x
        int damage = (int) (move.getBaseDamage() * (2 * attacker.getAttack() / (attacker.getAttack() + defender.getDefense())) * 0.5);

        Random rng = new Random();
        double mult = rng.nextDouble(0.75, 1.25);
        damage = (int) Math.round(damage * mult);

        return damage;
    }

    public boolean updateStats(StatMove move, Professor attacker, Professor defender) {
        if(move.GetOnSelf()) {
            return attacker.modifyStat(move.GetStatToChange(), 10);
        } else {
            return defender.modifyStat(move.GetStatToChange(), -10);
        }

        // return boolean will be used to display "Stat was increased / decreased" or "Stat could not be changed any further"
    }

    public void updateHp(int damage, Professor p) {
        p.currentHp -= damage;
    }

    public Professor getOther(Professor[] p, int i) {
        return i == 0 ? p[1] : p[0];
    }
}
