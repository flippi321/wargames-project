package no.ntnu.idatt2001.Wargames.Units;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UnitFactoryTest {

    //TODO
    // Check that this is can be removed
    /*
    @Nested
    @DisplayName("Error Handling")
    class errorHandling {
        @Test
        @DisplayName("Error is thrown with non exist Unit Type as input")
        public void TestingErrorHandlingWithWrongInput() {
            try {
                UnitFactory unitFactory = new UnitFactory();
                CommanderUnit commanderUnit = new CommanderUnit("Commander Greievous", 250);
                Unit unit = unitFactory.getUnit(UnitType., "Commander Greievous", 250);
                fail("TestingErrorHandlingWithWrongInput() failed");
            } catch (Exception e) {
                assertEquals("The unit type does not exist", e.getMessage());
            }
        }
    }
    */

    @Nested
    @DisplayName("Creating Units")
    class creatingUnits {
        @Test
        @DisplayName("Creating an Infantry Unit")
        public void CreatingInfantryUnit() {
            try {
                UnitFactory unitFactory = new UnitFactory();
                InfantryUnit infantryUnit = new InfantryUnit("Swordsman", 100);
                Unit unit = unitFactory.getUnit(UnitType.INFANTRY, "Swordsman", 100);
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
                Unit unit = unitFactory.getUnit(UnitType.RANGED, "Archer", 50);
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
                Unit unit = unitFactory.getUnit(UnitType.CAVALRY, "Hussar", 100);
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
                Unit unit = unitFactory.getUnit(UnitType.COMMANDER, "Commander Greievous", 250);
                assertEquals(commanderUnit.toString(), unit.toString());
            } catch (Exception e) {
                fail("CreatingInfantryUnit() failed");
            }
        }
    }

    @Nested
    @DisplayName("Creating Multiple Units")
    class creatingMultipleUnits {
        @Test
        @DisplayName("Creating multiple Infantry Units")
        public void CreatingMultipleInfantryUnit() {
            try {
                UnitFactory unitFactory = new UnitFactory();
                ArrayList<Unit> correctList = new ArrayList<>();
                for (int i = 0; i < 5; i++){
                    correctList.add(new InfantryUnit("Swordsman", 100));
                }
                ArrayList<Unit> list = unitFactory.getMultipleUnits(UnitType.INFANTRY, 5, "Swordsman", 100);
                assertEquals(correctList.toString(), list.toString());
            } catch (Exception e) {
                fail("CreatingMultipleInfantryUnits() failed");
            }
        }

        @Test
        @DisplayName("Creating multiple Ranged Units")
        public void CreatingMultipleRangedUnit() {
            try {
                UnitFactory unitFactory = new UnitFactory();
                ArrayList<Unit> correctList = new ArrayList<>();
                for (int i = 0; i < 10; i++){
                    correctList.add(new RangedUnit("Archer", 100));
                }
                ArrayList<Unit> list = unitFactory.getMultipleUnits(UnitType.RANGED, 10, "Archer", 100);
                assertEquals(correctList.toString(), list.toString());
            } catch (Exception e) {
                fail("CreatingMultipleRangedUnits() failed");
            }
        }

        @Test
        @DisplayName("Creating multiple Cavalry Unit")
        public void CreatingMultipleCavalryUnit() {
            try {
                UnitFactory unitFactory = new UnitFactory();
                ArrayList<Unit> correctList = new ArrayList<>();
                for (int i = 0; i < 20; i++){
                    correctList.add(new CavalryUnit("Hussar", 100));
                }
                ArrayList<Unit> list = unitFactory.getMultipleUnits(UnitType.CAVALRY, 20, "Hussar", 100);
                assertEquals(correctList.toString(), list.toString());
            } catch (Exception e) {
                fail("CreatingMultipleCavalryUnits() failed");
            }
        }

        @Test
        @DisplayName("Creating multiple Commander Units")
        public void CreatingMultipleCommanderUnits() {
            try {
                UnitFactory unitFactory = new UnitFactory();
                ArrayList<Unit> correctList = new ArrayList<>();
                for (int i = 0; i < 5; i++){
                    correctList.add(new CommanderUnit("General", 100));
                }
                ArrayList<Unit> list = unitFactory.getMultipleUnits(UnitType.COMMANDER, 5, "General", 100);
                assertEquals(correctList.toString(), list.toString());
            } catch (Exception e) {
                fail("CreatingMultipleCommanderUnits() failed");
            }
        }
    }
}
