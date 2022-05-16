import no.ntnu.idatt2001.Wargames.Units.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InfantryUnitTest {
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
                infantryAggressor.attack(infantryDefender, "Forest");
                assertTrue((infantryDefender.getHealth() < originalHealth));
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
        public void checkAttackBonusOutsideForest() {
            try {
                Unit newInfantry = new InfantryUnit("Swordsman", 100);
                assertEquals(2, newInfantry.getAttackBonus("Plains"));
            } catch (Exception e) {
                fail("checkAttackBonusOutsideForest failed");
            }
        }

        @Test
        @DisplayName("Attack bonus changes in forest")
        public void checkAttackBonusInForest() {
            try {
                Unit newInfantry = new InfantryUnit("Swordsman", 100);
                assertEquals(6, newInfantry.getAttackBonus("Forest"));
            } catch (Exception e) {
                fail("checkAttackBonusInForest failed");
            }
        }

        @Test
        @DisplayName("Default resist bonus on plains")
        public void checkResistBonusOutsideForest() {
            try {
                Unit newInfantry = new InfantryUnit("Swordsman", 100);
                assertEquals(1, newInfantry.getResistBonus("Plains"));
            } catch (Exception e) {
                fail("checkResistBonusOutsideForest failed");
            }
        }

        @Test
        @DisplayName("Resist bonus changes in forest")
        public void checkResistBonusInForest() {
            try {
                Unit newInfantry = new InfantryUnit("Swordsman", 100);
                assertEquals(5, newInfantry.getResistBonus("Forest"));
            } catch (Exception e) {
                fail("checkResistBonusInForest failed");
            }
        }
    }
}