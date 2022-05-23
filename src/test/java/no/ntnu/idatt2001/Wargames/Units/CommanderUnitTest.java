package no.ntnu.idatt2001.Wargames.Units;

import no.ntnu.idatt2001.Wargames.Units.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommanderUnitTest {
    /**
     * Makes sure that the unit is created as intended
     */
    @Nested
    @DisplayName("Constructors")
    class constructors{
        @Test
        @DisplayName("Testing for correct automatic armour values")
        public void checkCommanderAutomaticArmourValues() {
            try {
                Unit newCommander = new CommanderUnit("Generic General", 150);
                assertEquals(15, newCommander.getArmour());
            } catch (Exception e) {
                fail("checkCommanderAutomaticArmourValues failed");
            }
        }

        @Test
        @DisplayName("Testing for correct automatic attack values")
        public void checkCommanderAutomaticAttackValues() {
            try {
                Unit newCommander = new CommanderUnit("Generic General", 180);
                assertEquals(25, newCommander.getAttack());
            } catch (Exception e) {
                fail("checkCommanderAutomaticAttackValues failed");
            }
        }

        @Test
        @DisplayName("Testing extended Constructor")
        public void checkCommanderName() {
            try {
                Unit newCommander = new CommanderUnit("High King", 200, 10, 15);
                assertEquals("High King", newCommander.getName());
            } catch (Exception e) {
                fail("checkCommanderName failed");
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
                Unit newCommander = new CommanderUnit(" ", 100);
                fail("checkInfantryNameWithSpace should have thrown an Exception, but did not");
            } catch (Exception e) {
                assertEquals("Must have a name", e.getMessage());
            }
        }

        @Test
        @DisplayName("Can't have a comma in the name")
        public void checkInfantryNameWithComma() {
            try {
                Unit newCommander = new CommanderUnit(",", 100);
                fail("checkInfantryNameWithComma should have thrown an Exception, but did not");
            } catch (Exception e) {
                assertEquals("The name cannot contain a comma", e.getMessage());
            }
        }
        @Test
        @DisplayName("Health must be above 0")
        public void checkInfantryWithInvalidHealth() {
            try {
                Unit newCommander = new CommanderUnit("General", 0);
                fail("checkInfantryWithInvalidHealth should have thrown an Exception, but did not");
            } catch (Exception e) {
                assertEquals("Must have a health value above 0", e.getMessage());
            }
        }
        @Test
        @DisplayName("Health must be above 0")
        public void checkInfantryWithInvalidAttack() {
            try {
                Unit newCommander = new CommanderUnit("General", 100, 0, 15);
                fail("checkInfantryWithInvalidAttack should have thrown an Exception, but did not");
            } catch (Exception e) {
                assertEquals("Must have an attack value above 0", e.getMessage());
            }
        }
        @Test
        @DisplayName("Health must be above 0")
        public void checkInfantryWithInvalidArmour() {
            try {
                Unit newCommander = new CommanderUnit("General", 100, 15, 0);
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
    @DisplayName("Combat Stats")
    class combatStats   {
        @Test
        @DisplayName("Testing Defence and attack")
        public void checkFighting() {
            try {
                int originalHealth = 180;
                Unit commanderDefender = new CavalryUnit("Mongol Warlord", originalHealth);
                Unit commanderAggressor = new CavalryUnit("Queen Consort", 150);
                commanderAggressor.attack(commanderDefender, "Plains", "Sun");
                assertTrue((commanderDefender.getHealth() < originalHealth));
            } catch (Exception e) {
                fail("checkInfantryName failed");
            }
        }
    }

    /**
     * Makes sure that all Bonuses have been successfully inherited
     */
    @Nested
    @DisplayName("Terrain, Weather and bonuses")
    class terrainValues {
        @Test
        @DisplayName("Default attack bonus on Hill")
        public void checkAttackBonusOutsidePlains() {
            try {
                Unit newCommander = new CommanderUnit("General", 100);
                assertEquals(6, newCommander.getAttackBonus("Hill", "Sunny"));
            } catch (Exception e) {
                fail("checkAttackBonusOutsidePlains failed");
            }
        }

        @Test
        @DisplayName("Attack bonus changes on Plains")
        public void checkAttackBonusOnPlains() {
            try {
                Unit newCommander = new CommanderUnit("General", 100);
                assertEquals(10, newCommander.getAttackBonus("Plains", "Sunny"));
            } catch (Exception e) {
                fail("checkAttackBonusOnPlains failed");
            }
        }

        @Test
        @DisplayName("Default resist bonus on plains")
        public void checkResistBonusOutsideForest() {
            try {
                Unit newCommander = new CommanderUnit("General", 100);
                assertEquals(1, newCommander.getResistBonus("Plains", "Sunny"));
            } catch (Exception e) {
                fail("checkResistBonusOutsideForest failed");
            }
        }

        @Test
        @DisplayName("Resist bonus is 0 in forest")
        public void checkResistBonusInForest() {
            try {
                Unit newCommander = new CommanderUnit("General", 100);
                assertEquals(0, newCommander.getResistBonus("Forest", "Sunny"));
            } catch (Exception e) {
                fail("checkResistBonusInForest failed");
            }
        }
    }
}

