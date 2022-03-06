import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CommanderUnitTest {
    @Test
    @DisplayName("Testing for correct automatic armour values")
    public void checkCommanderAutomaticArmourValues() {
        try {
            Unit newCommander = new CommanderUnit("Generic General", 10);
            assertEquals(15, newCommander.getArmour());
        } catch (Exception e) {
            fail("checkCommanderAutomaticArmourValues failed");
        }
    }

    @Test
    @DisplayName("Testing for correct automatic attack values")
    public void checkCommanderAutomaticAttackValues() {
        try {
            Unit newCommander = new CommanderUnit("Generic General", 10);
            assertEquals(25, newCommander.getAttack());
        } catch (Exception e) {
            fail("checkCommanderAutomaticAttackValues failed");
        }
    }

    @Test
    @DisplayName("Testing extended Constructor")
    public void checkCommanderName() {
        try {
            Unit newCommander = new CommanderUnit("High King", 10, 10, 15);
            assertEquals("High King", newCommander.getName());
        } catch (Exception e) {
            fail("checkCommanderName failed");
        }
    }
}

