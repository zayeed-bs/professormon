package com.cbl.professor;

import java.util.HashMap;
import java.util.Random;

import com.cbl.professor.move.DamagingMove;
import com.cbl.professor.move.Move;
import com.cbl.professor.move.StatMove;

public class BattleLogic {
    public String handleTurn(HashMap<Professor, Move> moveMap, Professor[] profs) {
        // check speed
        profs = orderBySpeed(profs);
        String log = "";

        // handle damage / stat affect
        for (int i = 0; i < 2; i++) {
            Professor p = profs[i];
            log += "\n" + profs[i].getName() + " used " + moveMap.get(p).getName() + ". ";

            if (moveMap.get(p) instanceof DamagingMove) {
                int damage = calculateDamage((DamagingMove) moveMap.get(p), p, getOther(profs, i));
                log += "\n" + "It dealt " + damage + " damage. ";
                updateHp(damage, getOther(profs, i));

                if (getOther(profs, i).currentHp <= 0) {
                    log += "\n" + getOther(profs, i).getName() + " was defeated. ";
                    break;
                }

            } else if (moveMap.get(p) instanceof StatMove) {
                StatMove move = (StatMove) moveMap.get(p);
                boolean updateState = updateStats(move, p, getOther(profs, i));
                log += "\n";
                log += move.getOnSelf() ? "It increased his " : "It decreased the opponent's ";

                switch (move.getStatToChange()) {
                    case 0:
                        log += "attack. ";
                        break;
                    case 1:
                        log += "defense. ";
                        break;
                    case 2:
                        log += "speed. ";
                        break;
                    default:
                        break;
                }
            }
        }

        return log;
    }

    public Professor[] orderBySpeed(Professor[] p) {
        Professor[] pSorted = new Professor[2];

        if (p[0].currentSpeed > p[1].currentSpeed) {
            // same order, .clone to avoid errors
            pSorted = p.clone();
        } else if (p[0].currentSpeed == p[1].currentSpeed) {
            // Speeds are matched, decide randomly
            Random rng = new Random();
            int randomIndex = rng.nextInt(0, 2);
            pSorted[0] = p[randomIndex];
            pSorted[1] = randomIndex == 0 ? p[1] : p[0];

        } else {
            // reverse order
            pSorted[0] = p[1];
            pSorted[1] = p[0];
        }
        
        return pSorted;
    }

    public int calculateDamage(DamagingMove move, Professor attacker, Professor defender) {
        // damage formula
        // variation between 0.75x to 1.25x
        double ratio = 2.0 * attacker.currentAttack 
            / (attacker.currentAttack + defender.currentDefense);
        double damage = move.getBaseDamage() * ratio * 0.5;

        Random rng = new Random();
        double mult = rng.nextDouble(0.75, 1.25);
        damage = (int) Math.round(damage * mult);
        
        return (int) damage;
    }

    public boolean updateStats(StatMove move, Professor attacker, Professor defender) {
        if (move.getOnSelf()) {
            return attacker.modifyStat(move.getStatToChange(), 10);
        } else {
            return defender.modifyStat(move.getStatToChange(), -10);
        }

        // return boolean will be used to display "Stat was increased / decreased" or 
        // "Stat could not be changed any further"
    }

    public void updateHp(int damage, Professor p) {
        p.currentHp -= damage;
    }

    public Professor getOther(Professor[] p, int i) {
        return i == 0 ? p[1] : p[0];
    }
}
