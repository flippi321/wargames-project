package no.ntnu.idatt2001.Wargames.Units;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class RangedUnitTest {
    /**
     * Makes sure that the unit is created as intended
     */
    @Nested
    @DisplayName("Constructor tests")
    class constructors {
        @Test
        @DisplayName("Testing for correct automatic armour values")
        public void checkRangedAutomaticArmourValues() {
            try {
                Unit newRanged = new RangedUnit("Generic Archer", 100);
                assertEquals(8, newRanged.getArmour());
            } catch (Exception e) {
                fail("checkRangedAutomaticArmourValues failed");
            }
        }

        @Test
        @DisplayName("Testing for correct automatic attack values")
        public void checkRangedAutomaticAttackValues() {
            try {
                Unit newRanged = new RangedUnit("Generic Archer", 100);
                assertEquals(15, newRanged.getAttack());
            } catch (Exception e) {
                fail("checkRangedAutomaticAttackValues failed");
            }
        }

        @Test
        @DisplayName("Testing extended Constructor")
        public void checkRangedConstructor() {
            try {
                Unit newRanged = new RangedUnit("Kings Finest", 100, 12, 10);
                assertEquals(10, newRanged.getArmour());
            } catch (Exception e) {
                fail("checkRangedConstructor failed");
            }
        }

        @Test
        @DisplayName("Testing name is correct")
        public void checkRangedName() {
            try {
                Unit newRanged = new RangedUnit("Sharpshooter", 100);
                assertEquals("Sharpshooter", newRanged.getName());
            } catch (Exception e) {
                fail("checkRangedName failed");
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
                Unit newRanged = new RangedUnit(" ", 100);
                fail("checkInfantryNameWithSpace should have thrown an Exception, but did not");
            } catch (Exception e) {
                assertEquals("Must have a name", e.getMessage());
            }
        }

        @Test
        @DisplayName("Can't have a comma in the name")
        public void checkInfantryNameWithComma() {
            try {
                Unit newRanged = new RangedUnit("Test,Name", 100);
                fail("checkInfantryNameWithComma should have thrown an Exception, but did not");
            } catch (Exception e) {
                assertEquals("The name cannot contain a comma", e.getMessage());
            }
        }
        @Test
        @DisplayName("Health must be above 0")
        public void checkInfantryWithInvalidHealth() {
            try {
                Unit newRanged = new RangedUnit("Swordman", 0);
                fail("checkInfantryWithInvalidHealth should have thrown an Exception, but did not");
            } catch (Exception e) {
                assertEquals("Must have a health value above 0", e.getMessage());
            }
        }
        @Test
        @DisplayName("Health must be above 0")
        public void checkInfantryWithInvalidAttack() {
            try {
                Unit newRanged = new RangedUnit("Swordman", 100, 0, 15);
                fail("checkInfantryWithInvalidAttack should have thrown an Exception, but did not");
            } catch (Exception e) {
                assertEquals("Must have an attack value above 0", e.getMessage());
            }
        }
        @Test
        @DisplayName("Health must be above 0")
        public void checkInfantryWithInvalidArmour() {
            try {
                Unit newRanged = new RangedUnit("Swordman", 100, 15, 0);
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
    class combatStats   {
        @Test
        @DisplayName("Testing First Resist bonus")
        public void checkFirstResistBonus() {
            try {
                Unit newRanged = new RangedUnit("Sharpshooter", 100);
                assertEquals(6, newRanged.getResistBonus("Forest", "Sunny"));
            } catch (Exception e) {
                fail("checkFirstResistBonus failed");
            }
        }

        @Test
        @DisplayName("Testing Second Resist bonus")
        public void checkSecondResistBonus() {
            try {
                Unit newRanged = new RangedUnit("Sharpshooter", 100);
                newRanged.getResistBonus("Forest", "Sunny");
                assertEquals(4, newRanged.getResistBonus("Forest", "Sunny"));
            } catch (Exception e) {
                fail("checkSecondResistBonus failed");
            }
        }

        @Test
        @DisplayName("Testing Third Resist bonus")
        public void checkThirdResistBonus() {
            try {
                Unit newRanged = new RangedUnit("Sharpshooter", 100);
                newRanged.getResistBonus("Forest", "Sunny");
                newRanged.getResistBonus("Forest", "Sunny");
                assertEquals(2, newRanged.getResistBonus("Forest", "Sunny"));
            } catch (Exception e) {
                fail("checkThirdResistBonus failed");
            }
        }

        @Test
        @DisplayName("Testing Defence and attack")
        public void checkFighting() {
            try {
                int originalHealth = 150;
                Unit rangedDefender = new RangedUnit("Deserter", originalHealth);
                Unit rangedAggressor = new RangedUnit("Town archer", 100);
                rangedAggressor.attack(rangedDefender, "Hill", "Sun");
                assertTrue(rangedDefender.getHealth() < originalHealth);
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
        public void checkAttackBonusOutsideHill() {
            try {
                Unit newRanged = new RangedUnit("Archer", 100);
                assertEquals(3, newRanged.getAttackBonus("Plains", "Sunny"));
            } catch (Exception e) {
                fail("checkAttackBonusOutsideHill failed");
            }
        }

        @Test
        @DisplayName("Attack bonus changes on hill")
        public void checkAttackBonusOnHill() {
            try {
                Unit newRanged = new RangedUnit("Archer", 100);
                assertEquals(7, newRanged.getAttackBonus("Hill", "Sunny"));
            } catch (Exception e) {
                fail("checkAttackBonusOnHill failed");
            }
        }
    }
}