package no.ntnu.idatt2001.Wargames.Units;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CavalryUnitTest {
    @Nested
    @DisplayName("Constructor tests")
    class constructors  {
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
                assertEquals("Hussar", newCavalry.getName());
            } catch (Exception e) {
                fail("checkCavalryName failed");
            }
        }
    }

    @Nested
    @DisplayName("Combat tests")
    class combatStats{
        @Test
        @DisplayName("Testing Charge Bonus")
        public void checkCavalryChargeBonus() {
            try {
                Unit newCavalry = new CavalryUnit("Hussar", 100);
                assertEquals(6, newCavalry.getAttackBonus("Forest", "Sunny"));
            } catch (Exception e) {
                fail("checkCavalryChargeBonuse failed");
            }
        }

        @Test
        @DisplayName("Testing that Charge Bonus is removed")
        public void checkRemovalOfChargeBonus() {
            try {
                Unit newCavalry = new CavalryUnit("Hussar", 100);
                newCavalry.getAttackBonus("Forest", "Sunny");
                assertEquals(2, newCavalry.getAttackBonus("Forest", "Sunny"));
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
                cavalryAggressor.attack(cavalryDefender, "Plains", "Sun");
                assertTrue((cavalryDefender.getHealth() < originalHealth));
            } catch (Exception e) {
                fail("checkInfantryName failed");
            }
        }
    }

    @Nested
    @DisplayName("Terrain and bonuses")
    class terrainValues {
        @Test
        @DisplayName("Default attack bonus on Hill")
        public void checkAttackBonusOutsidePlains() {
            try {
                Unit newCavalry = new CavalryUnit("Hussar", 100);
                assertEquals(6, newCavalry.getAttackBonus("Hill", "Sunny"));
            } catch (Exception e) {
                fail("checkAttackBonusOutsidePlains failed");
            }
        }

        @Test
        @DisplayName("Attack bonus changes on Plains")
        public void checkAttackBonusOnPlains() {
            try {
                Unit newCavalry = new CavalryUnit("Hussar", 100);
                assertEquals(10, newCavalry.getAttackBonus("Plains", "Sunny"));
            } catch (Exception e) {
                fail("checkAttackBonusOnPlains failed");
            }
        }

        @Test
        @DisplayName("Default resist bonus on plains")
        public void checkResistBonusOutsideForest() {
            try {
                Unit newCavalry = new CavalryUnit("Hussar", 100);
                assertEquals(1, newCavalry.getResistBonus("Plains", "Sunny"));
            } catch (Exception e) {
                fail("checkResistBonusOutsideForest failed");
            }
        }

        @Test
        @DisplayName("Resist bonus is 0 in forest")
        public void checkResistBonusInForest() {
            try {
                Unit newCavalry = new CavalryUnit("Hussar", 100);
                assertEquals(0, newCavalry.getResistBonus("Forest", "Sunny"));
            } catch (Exception e) {
                fail("checkResistBonusInForest failed");
            }
        }
    }
}