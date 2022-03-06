import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CavalryUnitTest {
    @Test
    @DisplayName("Testing for correct automatic armour values")
    public void checkInfantryAutomaticValues() {
        try {
            Unit newCavalry = new CavalryUnit("Generic Cavalry", 10);
            assertEquals(12, newCavalry.getArmour());
        } catch (Exception e) {
            fail("checkInfantryAutomaticValues failed");
        }
    }

    @Test
    @DisplayName("Testing extended Constructor")
    public void checkInfantryConstructor() {
        try {
            Unit newCavalry = new CavalryUnit("Royal Steeds", 10, 10, 15);
            assertEquals(15, newCavalry.getArmour());
        } catch (Exception e) {
            fail("checkInfantryConstructor failed");
        }
    }

    @Test
    @DisplayName("Testing name is correct")
    public void checkInfantryName() {
        try {
            Unit newCavalry = new CavalryUnit("Hussars", 10);
            assertEquals("Hussars", newCavalry.getName());
        } catch (Exception e) {
            fail("checkInfantryName failed");
        }
    }
}