import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CavalryUnitTest {
    @Test
    @DisplayName("Testing for correct automatic armour values")
    public void checkCavalryAutomaticArmourValues() {
        try {
            Unit newCavalry = new CavalryUnit("Generic Cavalry", 100);
            assertEquals(12, newCavalry.getArmour());
        } catch (Exception e) {
            fail("checkCavalryAutomaticArmourValues failed");
        }
    }

    @Test
    @DisplayName("Testing for correct automatic attack values")
    public void checkCavalryAutomaticAttackValues() {
        try {
            Unit newCavalry = new CavalryUnit("Generic Cavalry", 100);
            assertEquals(20, newCavalry.getAttack());
        } catch (Exception e) {
            fail("checkCavalryAutomaticAttackValues failed");
        }
    }

    @Test
    @DisplayName("Testing extended Constructor")
    public void checkCavalryConstructor() {
        try {
            Unit newCavalry = new CavalryUnit("Royal Steed", 100, 10, 15);
            assertEquals(15, newCavalry.getArmour());
        } catch (Exception e) {
            fail("checkCavalryConstructor failed");
        }
    }

    @Test
    @DisplayName("Testing name is correct")
    public void checkCavalryName() {
        try {
            Unit newCavalry = new CavalryUnit("Hussar", 100);
            assertEquals("Hussars", newCavalry.getName());
        } catch (Exception e) {
            fail("checkCavalryName failed");
        }
    }

    @Test
    @DisplayName("Testing Charge Bonus")
    public void checkCavalryChargeBonus() {
        try {
            Unit newCavalry = new CavalryUnit("Hussar", 100);
            assertEquals(6, newCavalry.getAttackBonus());
        } catch (Exception e) {
            fail("checkCavalryChargeBonuse failed");
        }
    }

    @Test
    @DisplayName("Testing that Charge Bonus is removed")
    public void checkRemovalOfChargeBonus() {
        try {
            Unit newCavalry = new CavalryUnit("Hussar", 100);
            newCavalry.getAttackBonus();
            assertEquals(2, newCavalry.getAttackBonus());
        } catch (Exception e) {
            fail("checkRemovalOfChargeBonus failed");
        }
    }

    @Test
    @DisplayName("Testing Defence and attack")
    public void checkFighting() {
        try {
            int originalHealth = 150;
            Unit cavalryDefender = new CavalryUnit("Mongol Horde", originalHealth);
            Unit cavalryAggressor = new CavalryUnit("Royal Knight", 100);
            cavalryAggressor.attack(cavalryDefender);
            assertTrue((cavalryDefender.getHealth() < originalHealth));
        } catch (Exception e) {
            fail("checkInfantryName failed");
        }
    }
}