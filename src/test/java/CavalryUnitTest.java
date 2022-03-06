import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CavalryUnitTest {
    @Test
    @DisplayName("Testing for correct automatic armour values")
    public void checkCavalryAutomaticArmourValues() {
        try {
            Unit newCavalry = new CavalryUnit("Generic Cavalry", 10);
            assertEquals(12, newCavalry.getArmour());
        } catch (Exception e) {
            fail("checkCavalryAutomaticArmourValues failed");
        }
    }

    @Test
    @DisplayName("Testing for correct automatic attack values")
    public void checkCavalryAutomaticAttackValues() {
        try {
            Unit newCavalry = new CavalryUnit("Generic Cavalry", 10);
            assertEquals(20, newCavalry.getAttack());
        } catch (Exception e) {
            fail("checkCavalryAutomaticAttackValues failed");
        }
    }

    @Test
    @DisplayName("Testing extended Constructor")
    public void checkCavalryConstructor() {
        try {
            Unit newCavalry = new CavalryUnit("Royal Steeds", 10, 10, 15);
            assertEquals(15, newCavalry.getArmour());
        } catch (Exception e) {
            fail("checkCavalryConstructor failed");
        }
    }

    @Test
    @DisplayName("Testing name is correct")
    public void checkCavalryName() {
        try {
            Unit newCavalry = new CavalryUnit("Hussars", 10);
            assertEquals("Hussars", newCavalry.getName());
        } catch (Exception e) {
            fail("checkCavalryName failed");
        }
    }

    @Test
    @DisplayName("Testing Charge Bonus")
    public void checkCavalryChargeBonus() {
        try {
            Unit newCavalry = new CavalryUnit("Hussars", 10);
            assertEquals(6, newCavalry.getAttackBonus());
        } catch (Exception e) {
            fail("checkCavalryChargeBonuse failed");
        }
    }

    @Test
    @DisplayName("Testing that Charge Bonus is removed")
    public void checkRemovalOfChargeBonus() {
        try {
            Unit newCavalry = new CavalryUnit("Hussars", 10);
            newCavalry.getAttackBonus();
            assertEquals(2, newCavalry.getAttackBonus());
        } catch (Exception e) {
            fail("checkRemovalOfChargeBonus failed");
        }
    }
}