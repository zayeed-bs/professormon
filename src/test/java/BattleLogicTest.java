import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;

import org.junit.Before;
import org.junit.Test;

import com.cbl.professor.BattleLogic;
import com.cbl.professor.Professor;
import com.cbl.professor.move.DamagingMove;
import com.cbl.professor.move.Move;
import com.cbl.professor.move.StatMove;



public class BattleLogicTest {

    private static final int ATTACK = 0;
    private static final int DEFENSE = 1;

    private BattleLogic logic;
    private Professor a;
    private Professor b;
    private DamagingMove hit;
    private StatMove buffSelf;
    private StatMove debuffOpp;

    // stats order: {hp, attack, defense, speed}
    private Professor makeProf(String name, int hp, int attack, int defense, int speed) {
        Move[] moves = new Move[] {
            new DamagingMove("m1", 30), new DamagingMove("m2", 60),
            new StatMove("m3", ATTACK, true), new StatMove("m4", DEFENSE, false)
        };
        return new Professor(name, new int[] {hp, attack, defense, speed}, moves,
                Professor.Type.LOGIC, new ImageIcon());
    }

    @Before
    public void setUp() {
        logic = new BattleLogic();
        a = makeProf("Alpha", 100, 50, 50, 50);
        b = makeProf("Beta", 100, 50, 50, 50);
        hit = new DamagingMove("Hit", 60);
        buffSelf = new StatMove("Buff", ATTACK, true);
        debuffOpp = new StatMove("Debuff", DEFENSE, false);
    }

    // ---------- orderBySpeed ----------

    @Test
    public void fasterProfessorGoesFirst() {
        a.currentSpeed = 80;
        b.currentSpeed = 20;
        Professor[] order = logic.orderBySpeed(new Professor[] {a, b});
        assertSame(a, order[0]);
        assertSame(b, order[1]);
    }

    @Test
    public void slowerFirstInInputIsSwapped() {
        a.currentSpeed = 20;
        b.currentSpeed = 80;
        Professor[] order = logic.orderBySpeed(new Professor[] {a, b});
        assertSame(b, order[0]);
        assertSame(a, order[1]);
    }

    @Test
    public void orderBySpeedDoesNotModifyInputArray() {
        a.currentSpeed = 20;
        b.currentSpeed = 80;
        Professor[] input = new Professor[] {a, b};
        logic.orderBySpeed(input);
        assertSame(a, input[0]);
        assertSame(b, input[1]);
    }

    @Test
    public void orderBySpeedUsesCurrentSpeedNotBaseSpeed() {
        // a is slower at base, but buffed above b
        a = makeProf("Alpha", 100, 50, 50, 10);
        b = makeProf("Beta", 100, 50, 50, 50);
        a.currentSpeed = 90;
        Professor[] order = logic.orderBySpeed(new Professor[] {a, b});
        assertSame(a, order[0]);
    }

    @Test
    public void equalSpeedReturnsBothAndIsRandom() {
        a.currentSpeed = 50;
        b.currentSpeed = 50;
        Set<Professor> firstPlaces = new HashSet<>();
        for (int i = 0; i < 200; i++) {
            Professor[] order = logic.orderBySpeed(new Professor[] {a, b});
            assertNotSame(order[0], order[1]);
            firstPlaces.add(order[0]);
        }
        assertEquals("Both professors should sometimes go first on a tie", 2, firstPlaces.size());
    }

    // ---------- calculateDamage ----------

    @Test
    public void damageStaysWithinVariationRange() {
        // attack == defense -> ratio 1, base = 60 * 1 * 0.5 = 30, range 22.5..37.5
        for (int i = 0; i < 500; i++) {
            int dmg = logic.calculateDamage(hit, a, b);
            assertTrue("damage " + dmg + " too low", dmg >= 22);
            assertTrue("damage " + dmg + " too high", dmg <= 38);
        }
    }

    @Test
    public void damageIsNeverNegative() {
        Professor weak = makeProf("Weak", 100, 1, 100, 50);
        DamagingMove tiny = new DamagingMove("Tiny", 1);
        for (int i = 0; i < 200; i++) {
            assertTrue(logic.calculateDamage(tiny, weak, b) >= 0);
        }
    }

    @Test
    public void higherAttackDealsMoreDamageOnAverage() {
        Professor strong = makeProf("Strong", 100, 90, 50, 50);
        Professor weak = makeProf("Weak", 100, 10, 50, 50);
        long strongTotal = 0, weakTotal = 0;
        for (int i = 0; i < 300; i++) {
            strongTotal += logic.calculateDamage(hit, strong, b);
            weakTotal += logic.calculateDamage(hit, weak, b);
        }
        assertTrue(strongTotal > weakTotal);
    }

    @Test
    public void higherDefenseTakesLessDamageOnAverage() {
        Professor tanky = makeProf("Tanky", 100, 50, 90, 50);
        Professor squishy = makeProf("Squishy", 100, 50, 10, 50);
        long tankyTotal = 0, squishyTotal = 0;
        for (int i = 0; i < 300; i++) {
            tankyTotal += logic.calculateDamage(hit, a, tanky);
            squishyTotal += logic.calculateDamage(hit, a, squishy);
        }
        assertTrue(tankyTotal < squishyTotal);
    }

    @Test
    public void strongerMoveDealsMoreDamageOnAverage() {
        DamagingMove light = new DamagingMove("Light", 20);
        DamagingMove heavy = new DamagingMove("Heavy", 80);
        long lightTotal = 0, heavyTotal = 0;
        for (int i = 0; i < 300; i++) {
            lightTotal += logic.calculateDamage(light, a, b);
            heavyTotal += logic.calculateDamage(heavy, a, b);
        }
        assertTrue(heavyTotal > lightTotal);
    }

    @Test
    public void damageUsesCurrentAttackNotBaseAttack() {
        // FAILS until calculateDamage uses currentAttack / currentDefense
        Professor buffed = makeProf("Buffed", 100, 50, 50, 50);
        buffed.currentAttack = 100;
        long buffedTotal = 0, normalTotal = 0;
        for (int i = 0; i < 300; i++) {
            buffedTotal += logic.calculateDamage(hit, buffed, b);
            normalTotal += logic.calculateDamage(hit, a, b);
        }
        assertTrue(buffedTotal > normalTotal);
    }

    @Test
    public void damageUsesCurrentDefenseNotBaseDefense() {
        // FAILS until calculateDamage uses currentAttack / currentDefense
        Professor debuffed = makeProf("Debuffed", 100, 50, 50, 50);
        debuffed.currentDefense = 1;
        long debuffedTotal = 0, normalTotal = 0;
        for (int i = 0; i < 300; i++) {
            debuffedTotal += logic.calculateDamage(hit, a, debuffed);
            normalTotal += logic.calculateDamage(hit, a, b);
        }
        assertTrue(debuffedTotal > normalTotal);
    }

    // ---------- updateHp ----------

    @Test
    public void updateHpSubtractsDamage() {
        logic.updateHp(25, a);
        assertEquals(75, a.currentHp);
    }

    @Test
    public void updateHpWithZeroDamageChangesNothing() {
        logic.updateHp(0, a);
        assertEquals(100, a.currentHp);
    }

    @Test
    public void updateHpDoesNotChangeBaseHp() {
        logic.updateHp(25, a);
        assertEquals(100, a.getHp());
    }

    // ---------- getOther ----------

    @Test
    public void getOtherReturnsOppositeProfessor() {
        Professor[] profs = new Professor[] {a, b};
        assertSame(b, logic.getOther(profs, 0));
        assertSame(a, logic.getOther(profs, 1));
    }

    // ---------- updateStats ----------

    @Test
    public void selfStatMoveBuffsAttacker() {
        assertTrue(logic.updateStats(buffSelf, a, b));
        assertEquals(60, a.currentAttack);
    }

    @Test
    public void selfStatMoveDoesNotAffectDefender() {
        logic.updateStats(buffSelf, a, b);
        assertEquals(50, b.currentAttack);
    }

    @Test
    public void opponentStatMoveDebuffsDefender() {
        assertTrue(logic.updateStats(debuffOpp, a, b));
        assertEquals(40, b.currentDefense);
    }

    @Test
    public void opponentStatMoveDoesNotAffectAttacker() {
        logic.updateStats(debuffOpp, a, b);
        assertEquals(50, a.currentDefense);
    }

    @Test
    public void updateStatsReturnsFalseWhenStatCannotChange() {
        a.currentAttack = 100;
        assertFalse(logic.updateStats(buffSelf, a, b));
    }

    // ---------- handleTurn ----------

    @Test
    public void handleTurnLogsBothMovesAndDamagesBoth() {
        a.currentSpeed = 80;
        b.currentSpeed = 20;
        HashMap<Professor, Move> moves = new HashMap<>();
        moves.put(a, hit);
        moves.put(b, hit);

        String log = logic.handleTurn(moves, new Professor[] {a, b});

        assertTrue(log.contains("Alpha used Hit"));
        assertTrue(log.contains("Beta used Hit"));
        assertTrue(a.currentHp < 100);
        assertTrue(b.currentHp < 100);
    }

    @Test
    public void handleTurnFasterProfessorActsFirst() {
        a.currentSpeed = 10;
        b.currentSpeed = 90;
        HashMap<Professor, Move> moves = new HashMap<>();
        moves.put(a, hit);
        moves.put(b, hit);

        String log = logic.handleTurn(moves, new Professor[] {a, b});
        assertTrue(log.indexOf("Beta used") < log.indexOf("Alpha used"));
    }

    @Test
    public void handleTurnLogsDamageDealt() {
        a.currentSpeed = 90;
        b.currentSpeed = 10;
        HashMap<Professor, Move> moves = new HashMap<>();
        moves.put(a, hit);
        moves.put(b, hit);

        String log = logic.handleTurn(moves, new Professor[] {a, b});
        assertTrue(log.contains("It dealt "));
        assertTrue(log.contains(" damage."));
    }

    @Test
    public void handleTurnStopsWhenFirstAttackerDefeatsOpponent() {
        a.currentSpeed = 90;
        b.currentSpeed = 10;
        b.currentHp = 1;
        HashMap<Professor, Move> moves = new HashMap<>();
        moves.put(a, hit);
        moves.put(b, hit);

        String log = logic.handleTurn(moves, new Professor[] {a, b});

        assertTrue(log.contains("Beta was defeated"));
        assertFalse("defeated professor must not act", log.contains("Beta used"));
        assertEquals("attacker should be untouched", 100, a.currentHp);
        assertTrue(b.currentHp <= 0);
    }

    @Test
    public void handleTurnWithStatMovesLogsStatChanges() {
        a.currentSpeed = 90;
        b.currentSpeed = 10;
        HashMap<Professor, Move> moves = new HashMap<>();
        moves.put(a, new StatMove("Buff", ATTACK, true));
        moves.put(b, new StatMove("Debuff", DEFENSE, false));

        String log = logic.handleTurn(moves, new Professor[] {a, b});
        assertTrue(log.contains("It increased his attack"));
        assertTrue(log.contains("It decreased the opponent's defense"));
    }

    @Test
    public void handleTurnStatMovesActuallyChangeStats() {
        // FAILS until modifyStat is fixed
        a.currentSpeed = 90;
        b.currentSpeed = 10;
        HashMap<Professor, Move> moves = new HashMap<>();
        moves.put(a, new StatMove("Buff", ATTACK, true));
        moves.put(b, new StatMove("Debuff", DEFENSE, false));

        logic.handleTurn(moves, new Professor[] {a, b});
        assertEquals(60, a.currentAttack);
        assertEquals(40, a.currentDefense);
    }

    @Test
    public void handleTurnStatMovesDoNotDealDamage() {
        a.currentSpeed = 90;
        b.currentSpeed = 10;
        HashMap<Professor, Move> moves = new HashMap<>();
        moves.put(a, buffSelf);
        moves.put(b, debuffOpp);

        logic.handleTurn(moves, new Professor[] {a, b});
        assertEquals(100, a.currentHp);
        assertEquals(100, b.currentHp);
    }
}