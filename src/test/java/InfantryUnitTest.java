import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfantryUnitTest {
    @Nested
    @DisplayName("Constructors")
    class constructors {
        @Test
        @DisplayName("Testing for correct automatic armour values")
        public void checkInfantryAutomaticArmourValues() {
            try {
                Unit newInfantry = new InfantryUnit("Generic Spearman", 10);
                assertEquals(10, newInfantry.getArmour());
            } catch (Exception e) {
                fail("checkInfantryAutomaticValues failed");
            }
        }

        @Test
        @DisplayName("Testing for correct automatic armour values")
        public void checkInfantryAutomaticAttackValues() {
            try {
                Unit newInfantry = new InfantryUnit("Generic Spearman", 10);
                assertEquals(15, newInfantry.getAttack());
            } catch (Exception e) {
                fail("checkInfantryAutomaticValues failed");
            }
        }

        @Test
        @DisplayName("Testing extended Constructor")
        public void checkInfantryConstructor() {
            try {
                Unit newInfantry = new InfantryUnit("Kings Guard", 100, 2, 5);
                assertEquals(5, newInfantry.getArmour());
            } catch (Exception e) {
                fail("checkInfantryConstructor failed");
            }
        }

        @Test
        @DisplayName("Testing name is correct")
        public void checkInfantryName() {
            try {
                Unit newInfantry = new InfantryUnit("Swordsman", 100);
                assertEquals("Swordsman", newInfantry.getName());
            } catch (Exception e) {
                fail("checkInfantryName failed");
            }
        }
    }

    @Nested
    @DisplayName("Combat Stats")
    class combatStats {
        @Test
        @DisplayName("Testing Defence and attack")
        public void checkFighting() {
            try {
                int originalHealth = 150;
                Unit infantryDefender = new InfantryUnit("Town Guard", originalHealth);
                Unit infantryAggressor = new InfantryUnit("Bandit", 100);
                infantryAggressor.attack(infantryDefender, Terrain.FOREST);
                assertTrue((infantryDefender.getHealth() < originalHealth));
            } catch (Exception e) {
                fail("checkInfantryName failed");
            }
        }
    }
}