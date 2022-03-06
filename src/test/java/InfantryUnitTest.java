import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class InfantryUnitTest {
    @Test
    @DisplayName("Testing for correct automatic armour values")
    public void checkInfantryAutomaticArmourValues() {
        try {
            Unit newInfantry = new InfantryUnit("Generic Spearmen", 10);
            assertEquals(10, newInfantry.getArmour());
        } catch (Exception e) {
            fail("checkInfantryAutomaticValues failed");
        }
    }

    @Test
    @DisplayName("Testing for correct automatic armour values")
    public void checkInfantryAutomaticAttackValues() {
        try {
            Unit newInfantry = new InfantryUnit("Generic Spearmen", 10);
            assertEquals(15, newInfantry.getAttack());
        } catch (Exception e) {
            fail("checkInfantryAutomaticValues failed");
        }
    }

    @Test
    @DisplayName("Testing extended Constructor")
    public void checkInfantryConstructor() {
        try {
            Unit newInfantry = new InfantryUnit("Kings Guard", 10, 2, 5);
            assertEquals(5, newInfantry.getArmour());
        } catch (Exception e) {
            fail("checkInfantryConstructor failed");
        }
    }

    @Test
    @DisplayName("Testing name is correct")
    public void checkInfantryName() {
        try {
            Unit newInfantry = new InfantryUnit("Swordsmen", 10);
            assertEquals("Swordsmen", newInfantry.getName());
        } catch (Exception e) {
            fail("checkInfantryName failed");
        }
    }
}