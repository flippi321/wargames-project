import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class RangedUnitTest {
    @Test
    @DisplayName("Testing for correct automatic armour values")
    public void checkInfantryAutomaticValues() {
        try {
            Unit newRanged = new RangedUnit("Generic Archers", 10);
            assertEquals(8, newRanged.getArmour());
        } catch (Exception e) {
            fail("checkInfantryAutomaticValues failed");
        }
    }

    @Test
    @DisplayName("Testing extended Constructor")
    public void checkInfantryConstructor() {
        try {
            Unit newRanged = new RangedUnit("Kings Finest", 10, 12, 10);
            assertEquals(10, newRanged.getArmour());
        } catch (Exception e) {
            fail("checkInfantryConstructor failed");
        }
    }

    @Test
    @DisplayName("Testing name is correct")
    public void checkInfantryName() {
        try {
            Unit newRanged = new RangedUnit("Sharpshooters", 10);
            assertEquals("Sharpshooters", newRanged.getName());
        } catch (Exception e) {
            fail("checkInfantryName failed");
        }
    }
}