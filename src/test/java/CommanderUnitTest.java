import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommanderUnitTest {
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
                commanderAggressor.attack(commanderDefender, "Plains");
                assertTrue((commanderDefender.getHealth() < originalHealth));
            } catch (Exception e) {
                fail("checkInfantryName failed");
            }
        }
    }
}

