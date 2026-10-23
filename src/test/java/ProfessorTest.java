import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.Before;
import org.junit.Test;

import com.cbl.professor.Professor;
import com.cbl.professor.move.DamagingMove;
import com.cbl.professor.move.Move;
import com.cbl.professor.move.StatMove;

public class ProfessorTest {

    private static final int ATTACK = 0;
    private static final int DEFENSE = 1;
    private static final int SPEED = 2;

    private Professor prof;
    private Move[] moves;
    private ImageIcon sprite;

    @Before
    public void setUp() {
        moves = new Move[] {
            new DamagingMove("Heavy", 60),
            new DamagingMove("Light", 30),
            new StatMove("Debuff", DEFENSE, false),
            new StatMove("Buff", ATTACK, true)
        };
        sprite = new ImageIcon();
        // stats order: {hp, attack, defense, speed}
        prof = new Professor("Test Prof", new int[] {70, 30, 80, 40}, moves, Professor.Type.LOGIC, sprite);
    }

    private Professor profWith(int attack, int defense, int speed) {
        return new Professor("P", new int[] {50, attack, defense, speed}, moves, Professor.Type.CALCULUS, sprite);
    }

    // ---------- constructor / getters ----------

    @Test
    public void constructorSetsName() {
        assertEquals("Test Prof", prof.getName());
    }

    @Test
    public void constructorMapsStatsInOrder() {
        assertEquals(70, prof.getHp());
        assertEquals(30, prof.getAttack());
        assertEquals(80, prof.getDefense());
        assertEquals(40, prof.getSpeed());
    }

    @Test
    public void currentStatsStartEqualToBaseStats() {
        assertEquals(prof.getHp(), prof.currentHp);
        assertEquals(prof.getAttack(), prof.currentAttack);
        assertEquals(prof.getDefense(), prof.currentDefense);
        assertEquals(prof.getSpeed(), prof.currentSpeed);
    }

    @Test
    public void constructorStoresMovesTypeAndSprite() {
        assertSame(moves, prof.getMoves());
        assertEquals(4, prof.getMoves().length);
        assertEquals(Professor.Type.LOGIC, prof.getType());
        assertSame(sprite, prof.getSprite());
    }

    // ---------- modifyStat ----------

    @Test
    public void increasingAttackRaisesCurrentAttack() {
        assertTrue(prof.modifyStat(ATTACK, 10));
        assertEquals(40, prof.currentAttack);
    }

    @Test
    public void decreasingDefenseLowersCurrentDefense() {
        assertTrue(prof.modifyStat(DEFENSE, -10));
        assertEquals(70, prof.currentDefense);
    }

    @Test
    public void increasingSpeedRaisesCurrentSpeed() {
        assertTrue(prof.modifyStat(SPEED, 10));
        assertEquals(50, prof.currentSpeed);
    }

    @Test
    public void modifyStatOnlyChangesTheRequestedStat() {
        prof.modifyStat(ATTACK, 10);
        assertEquals(80, prof.currentDefense);
        assertEquals(40, prof.currentSpeed);
    }

    @Test
    public void modifyStatDoesNotChangeBaseStats() {
        prof.modifyStat(ATTACK, 10);
        prof.modifyStat(DEFENSE, -10);
        prof.modifyStat(SPEED, 10);
        assertEquals(30, prof.getAttack());
        assertEquals(80, prof.getDefense());
        assertEquals(40, prof.getSpeed());
    }

    @Test
    public void modifyStatDoesNotChangeHp() {
        prof.modifyStat(ATTACK, 10);
        assertEquals(70, prof.currentHp);
        assertEquals(70, prof.getHp());
    }

    @Test
    public void statIsClampedAtMaximum() {
        Professor p = profWith(95, 50, 50);
        assertTrue(p.modifyStat(ATTACK, 10));
        assertEquals(100, p.currentAttack);
    }

    @Test
    public void statIsClampedAtMinimum() {
        Professor p = profWith(50, 5, 50);
        assertTrue(p.modifyStat(DEFENSE, -10));
        assertEquals(1, p.currentDefense);
    }

    @Test
    public void cannotIncreaseStatAtMaximum() {
        Professor p = profWith(100, 50, 50);
        assertFalse(p.modifyStat(ATTACK, 10));
        assertEquals(100, p.currentAttack);
    }

    @Test
    public void cannotDecreaseStatAtMinimum() {
        Professor p = profWith(50, 50, 1);
        assertFalse(p.modifyStat(SPEED, -10));
        assertEquals(1, p.currentSpeed);
    }

    @Test
    public void canDecreaseStatAtMaximum() {
        Professor p = profWith(100, 50, 50);
        assertTrue(p.modifyStat(ATTACK, -10));
        assertEquals(90, p.currentAttack);
    }

    @Test
    public void canIncreaseStatAtMinimum() {
        Professor p = profWith(50, 50, 1);
        assertTrue(p.modifyStat(SPEED, 10));
        assertEquals(11, p.currentSpeed);
    }

    @Test
    public void repeatedBuffsEventuallyHitCapAndReportFailure() {
        boolean last = true;
        int calls = 0;
        while (last && calls < 100) {
            last = prof.modifyStat(ATTACK, 10);
            calls++;
        }
        assertFalse(last);
        assertEquals(100, prof.currentAttack);
    }

    @Test
    public void repeatedDebuffsEventuallyHitFloorAndReportFailure() {
        boolean last = true;
        int calls = 0;
        while (last && calls < 100) {
            last = prof.modifyStat(DEFENSE, -10);
            calls++;
        }
        assertFalse(last);
        assertEquals(1, prof.currentDefense);
    }
}