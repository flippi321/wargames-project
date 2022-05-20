package no.ntnu.idatt2001.Wargames.Units;

import no.ntnu.idatt2001.Wargames.Units.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RangedUnitTest {
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

    @Nested
    @DisplayName("Combat tests")
    class combatStats   {
        @Test
        @DisplayName("Testing First Resist bonus")
        public void checkFirstResistBonus() {
            try {
                Unit newRanged = new RangedUnit("Sharpshooter", 100);
                assertEquals(6, newRanged.getResistBonus("Forest"));
            } catch (Exception e) {
                fail("checkFirstResistBonus failed");
            }
        }

        @Test
        @DisplayName("Testing Second Resist bonus")
        public void checkSecondResistBonus() {
            try {
                Unit newRanged = new RangedUnit("Sharpshooter", 100);
                newRanged.getResistBonus("Forest");
                assertEquals(4, newRanged.getResistBonus("Forest"));
            } catch (Exception e) {
                fail("checkSecondResistBonus failed");
            }
        }

        @Test
        @DisplayName("Testing Third Resist bonus")
        public void checkThirdResistBonus() {
            try {
                Unit newRanged = new RangedUnit("Sharpshooter", 100);
                newRanged.getResistBonus("Forest");
                newRanged.getResistBonus("Forest");
                assertEquals(2, newRanged.getResistBonus("Forest"));
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
                rangedAggressor.attack(rangedDefender, "Hill");
                assertTrue(rangedDefender.getHealth() < originalHealth);
            } catch (Exception e) {
                fail("checkInfantryName failed");
            }
        }
    }

    @Nested
    @DisplayName("Terrain and bonuses")
    class terrainValues {
        @Test
        @DisplayName("Default attack bonus on plains")
        public void checkAttackBonusOutsideHill() {
            try {
                Unit newRanged = new RangedUnit("Archer", 100);
                assertEquals(3, newRanged.getAttackBonus("Plains"));
            } catch (Exception e) {
                fail("checkAttackBonusOutsideHill failed");
            }
        }

        @Test
        @DisplayName("Attack bonus changes on hill")
        public void checkAttackBonusOnHill() {
            try {
                Unit newRanged = new RangedUnit("Archer", 100);
                assertEquals(7, newRanged.getAttackBonus("Hill"));
            } catch (Exception e) {
                fail("checkAttackBonusOnHill failed");
            }
        }
    }
}