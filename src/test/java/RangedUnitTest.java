import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class RangedUnitTest {
    @Test
    @DisplayName("Testing for correct automatic armour values")
    public void checkRangedAutomaticArmourValues() {
        try {
            Unit newRanged = new RangedUnit("Generic Archers", 10);
            assertEquals(8, newRanged.getArmour());
        } catch (Exception e) {
            fail("checkRangedAutomaticArmourValues failed");
        }
    }

    @Test
    @DisplayName("Testing for correct automatic attack values")
    public void checkRangedAutomaticAttackValues() {
        try {
            Unit newRanged = new RangedUnit("Generic Archers", 10);
            assertEquals(15, newRanged.getAttack());
        } catch (Exception e) {
            fail("checkRangedAutomaticAttackValues failed");
        }
    }

    @Test
    @DisplayName("Testing extended Constructor")
    public void checkRangedConstructor() {
        try {
            Unit newRanged = new RangedUnit("Kings Finest", 10, 12, 10);
            assertEquals(10, newRanged.getArmour());
        } catch (Exception e) {
            fail("checkRangedConstructor failed");
        }
    }

    @Test
    @DisplayName("Testing name is correct")
    public void checkRangedName() {
        try {
            Unit newRanged = new RangedUnit("Sharpshooters", 10);
            assertEquals("Sharpshooters", newRanged.getName());
        } catch (Exception e) {
            fail("checkRangedName failed");
        }
    }

    @Test
    @DisplayName("Testing First Resist bonus")
    public void checkFirstResistBonus() {
        try {
            Unit newRanged = new RangedUnit("Sharpshooters", 10);
            assertEquals(6, newRanged.getResistBonus());
        } catch (Exception e) {
            fail("checkFirstResistBonus failed");
        }
    }

    @Test
    @DisplayName("Testing Second Resist bonus")
    public void checkSecondResistBonus() {
        try {
            Unit newRanged = new RangedUnit("Sharpshooters", 10);
            newRanged.getResistBonus();
            assertEquals(4, newRanged.getResistBonus());
        } catch (Exception e) {
            fail("checkSecondResistBonus failed");
        }
    }

    @Test
    @DisplayName("Testing Third Resist bonus")
    public void checkThirdResistBonus() {
        try {
            Unit newRanged = new RangedUnit("Sharpshooters", 10);
            newRanged.getResistBonus();
            newRanged.getResistBonus();
            assertEquals(2, newRanged.getResistBonus());
        } catch (Exception e) {
            fail("checkThirdResistBonus failed");
        }
    }
}