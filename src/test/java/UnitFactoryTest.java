import no.ntnu.idatt2001.Wargames.Units.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UnitFactoryTest {
    @Nested
    @DisplayName("Error Handling")
    class errorHandling {
        @Test
        @DisplayName("Error is thrown with non exist Unit Type as input")
        public void TestingErrorHandlingWithWrongInput() {
            try {
                UnitFactory unitFactory = new UnitFactory();
                CommanderUnit commanderUnit = new CommanderUnit("Commander Greievous", 250);
                Unit unit = unitFactory.getUnit("SuperUltraUnit", "Commander Greievous", 250);
                fail("TestingErrorHandlingWithWrongInput() failed");
            } catch (Exception e) {
                assertEquals("The unit type does not exist", e.getMessage());
            }
        }
    }

    @Nested
    @DisplayName("Creating Units")
    class creatingUnits {
        @Test
        @DisplayName("Creating an Infantry Unit")
        public void CreatingInfantryUnit() {
            try {
                UnitFactory unitFactory = new UnitFactory();
                InfantryUnit infantryUnit = new InfantryUnit("Swordsman", 100);
                Unit unit = unitFactory.getUnit("InfantryUnit", "Swordsman", 100);
                assertEquals(infantryUnit.toString(), unit.toString());
            } catch (Exception e) {
                fail("CreatingInfantryUnit() failed");
            }
        }

        @Test
        @DisplayName("Creating a Ranged Unit")
        public void CreatingRangedUnit() {
            try {
                UnitFactory unitFactory = new UnitFactory();
                RangedUnit rangedUnit = new RangedUnit("Archer", 50);
                Unit unit = unitFactory.getUnit("RangedUnit", "Archer", 50);
                assertEquals(rangedUnit.toString(), unit.toString());
            } catch (Exception e) {
                fail("CreatingInfantryUnit() failed");
            }
        }

        @Test
        @DisplayName("Creating a Cavalry Unit")
        public void CreatingCavalryUnit() {
            try {
                UnitFactory unitFactory = new UnitFactory();
                CavalryUnit cavalryUnit = new CavalryUnit("Hussar", 100);
                Unit unit = unitFactory.getUnit("CavalryUnit", "Hussar", 100);
                assertEquals(cavalryUnit.toString(), unit.toString());
            } catch (Exception e) {
                fail("CreatingInfantryUnit() failed");
            }
        }

        @Test
        @DisplayName("Creating a Commander Unit")
        public void CreatingCommanderUnit() {
            try {
                UnitFactory unitFactory = new UnitFactory();
                CommanderUnit commanderUnit = new CommanderUnit("Commander Greievous", 250);
                Unit unit = unitFactory.getUnit("CommanderUnit", "Commander Greievous", 250);
                assertEquals(commanderUnit.toString(), unit.toString());
            } catch (Exception e) {
                fail("CreatingInfantryUnit() failed");
            }
        }
    }
}
