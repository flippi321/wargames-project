package no.ntnu.idatt2001.Wargames.Units;

import no.ntnu.idatt2001.Wargames.Units.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InfantryUnitTest {
    /**
     * Makes sure that the unit is created as intended
     */
    @Nested
    @DisplayName("Constructor tests")
    class constructors {
        @Test
        @DisplayName("Correct automatic armour values")
        public void checkInfantryAutomaticArmourValues() {
            try {
                Unit newInfantry = new InfantryUnit("Generic Spearman", 10);
                assertEquals(10, newInfantry.getArmour());
            } catch (Exception e) {
                fail("checkInfantryAutomaticValues failed");
            }
        }

        @Test
        @DisplayName("Correct automatic attack values")
        public void checkInfantryAutomaticAttackValues() {
            try {
                Unit newInfantry = new InfantryUnit("Generic Spearman", 10);
                assertEquals(15, newInfantry.getAttack());
            } catch (Exception e) {
                fail("checkInfantryAutomaticValues failed");
            }
        }

        @Test
        @DisplayName("Extended Constructor works")
        public void checkInfantryConstructor() {
            try {
                Unit newInfantry = new InfantryUnit("Kings Guard", 100, 2, 5);
                assertEquals(5, newInfantry.getArmour());
            } catch (Exception e) {
                fail("checkInfantryConstructor failed");
            }
        }

        @Test
        @DisplayName("Name is correct")
        public void checkInfantryName() {
            try {
                Unit newInfantry = new InfantryUnit("Swordsman", 100);
                assertEquals("Swordsman", newInfantry.getName());
            } catch (Exception e) {
                fail("checkInfantryName failed");
            }
        }
    }

    /**
     * Makes sure that invalid inputs are handled correctly
     */
    @Nested
    @DisplayName("Error Handling")
    class errorHandling{
        @Test
        @DisplayName("Can't have an empty name")
        public void checkInfantryNameWithSpace() {
            try {
                Unit newInfantry = new InfantryUnit(" ", 100);
                fail("checkInfantryNameWithSpace should have thrown an Exception, but did not");
            } catch (Exception e) {
               assertEquals("Must have a name", e.getMessage());
            }
        }

        @Test
        @DisplayName("Can't have a comma in the name")
        public void checkInfantryNameWithComma() {
            try {
                Unit newInfantry = new InfantryUnit("Test,Name", 100);
                fail("checkInfantryNameWithComma should have thrown an Exception, but did not");
            } catch (Exception e) {
                assertEquals("The name cannot contain a comma", e.getMessage());
            }
        }
        @Test
        @DisplayName("Health must be above 0")
        public void checkInfantryWithInvalidHealth() {
            try {
                Unit newInfantry = new InfantryUnit("Swordman", 0);
                fail("checkInfantryWithInvalidHealth should have thrown an Exception, but did not");
            } catch (Exception e) {
                assertEquals("Must have a health value above 0", e.getMessage());
            }
        }
        @Test
        @DisplayName("Health must be above 0")
        public void checkInfantryWithInvalidAttack() {
            try {
                Unit newInfantry = new InfantryUnit("Swordman", 100, 0, 15);
                fail("checkInfantryWithInvalidAttack should have thrown an Exception, but did not");
            } catch (Exception e) {
                assertEquals("Must have an attack value above 0", e.getMessage());
            }
        }
        @Test
        @DisplayName("Health must be above 0")
        public void checkInfantryWithInvalidArmour() {
            try {
                Unit newInfantry = new InfantryUnit("Swordman", 100, 15, 0);
                fail("checkInfantryNameWithInvalidArmour should have thrown an Exception, but did not");
            } catch (Exception e) {
                assertEquals("Must have an armour value above 0", e.getMessage());
            }
        }
    }

    /**
     * Makes sure that unit fights as expected
     */
    @Nested
    @DisplayName("Combat tests")
    class combatStats {
        @Test
        @DisplayName("Defence and attack works as intended")
        public void checkFighting() {
            try {
                int originalHealth = 150;
                Unit infantryDefender = new InfantryUnit("Town Guard", originalHealth);
                Unit infantryAggressor = new InfantryUnit("Bandit", 100);
                infantryAggressor.attack(infantryDefender, "Forest", "Sunny");
                assertTrue((infantryDefender.getHealth() < originalHealth));
            } catch (Exception e) {
                fail("checkInfantryName failed");
            }
        }
    }

    /**
     * Makes sure that all Bonuses relating to Weather and Terrain are correct
     */
    @Nested
    @DisplayName("Terrain, Weather and bonuses")
    class terrainValues {
        @Test
        @DisplayName("Default attack bonus on plains")
        public void checkAttackBonusOutsideForest() {
            try {
                Unit newInfantry = new InfantryUnit("Swordsman", 100);
                assertEquals(2, newInfantry.getAttackBonus("Plains", "Heavy_fog"));
            } catch (Exception e) {
                fail("checkAttackBonusOutsideForest failed");
            }
        }

        @Test
        @DisplayName("Improved attack bonus in Sunny weather")
        public void checkAttackBonusInSun() {
            try {
                Unit newInfantry = new InfantryUnit("Swordsman", 100);
                assertEquals(4, newInfantry.getAttackBonus("Plains", "Sunny"));
            } catch (Exception e) {
                fail("checkAttackBonusInSun failed");
            }
        }

        @Test
        @DisplayName("Attack bonus changes in forest")
        public void checkAttackBonusInForest() {
            try {
                Unit newInfantry = new InfantryUnit("Swordsman", 100);
                assertEquals(6, newInfantry.getAttackBonus("Forest", "Heavy_fog"));
            } catch (Exception e) {
                fail("checkAttackBonusInForest failed");
            }
        }

        @Test
        @DisplayName("Default resist bonus on plains")
        public void checkResistBonusOutsideForest() {
            try {
                Unit newInfantry = new InfantryUnit("Swordsman", 100);
                assertEquals(1, newInfantry.getResistBonus("Plains", "Sunny"));
            } catch (Exception e) {
                fail("checkResistBonusOutsideForest failed");
            }
        }

        @Test
        @DisplayName("Resist bonus changes in forest")
        public void checkResistBonusInForest() {
            try {
                Unit newInfantry = new InfantryUnit("Swordsman", 100);
                assertEquals(5, newInfantry.getResistBonus("Forest", "Sunny"));
            } catch (Exception e) {
                fail("checkResistBonusInForest failed");
            }
        }
    }
}