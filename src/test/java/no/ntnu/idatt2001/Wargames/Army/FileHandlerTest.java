package no.ntnu.idatt2001.Wargames.Army;

import no.ntnu.idatt2001.Wargames.Units.*;
import no.ntnu.idatt2001.Wargames.Army.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {
    @Nested
    @DisplayName("Core Functionality")
    class testingCoreFunctionality{
        /**
         * Saving an army which will be utilized later
         */
        @Test
        @DisplayName("Tests that it is possible to save a file")
        public void savingAnArmyFile(){
            try {
                UnitFactory factory = new UnitFactory();
                FileHandler fileHandler = new FileHandler();
                Army newArmy = new Army("TestingArmy");
                newArmy.add(new CommanderUnit("Town Chieftain", 250));
                newArmy.addAll(factory.getMultipleUnits("Infantry", 10, "Town Guard", 100));
                newArmy.addAll(factory.getMultipleUnits("Cavalry", 10, "Horse Guard", 150));
                newArmy.addAll(factory.getMultipleUnits("Infantry", 10, "Archer", 75));
                fileHandler.saveArmy(newArmy);
                assertTrue(true);
            } catch (Exception e){
                fail("savingAnArmyFile threw error when not supposed to");
            }
        }

        /**
         * Load army Saved in Previous Test
         */
        @Test
        @DisplayName("Tests that it is possible to load a file")
        public void loadFileTest(){
            try {
                FileHandler fileHandler = new FileHandler();
                Army loadedArmy = fileHandler.loadArmy("TestingArmy");

                // 10 Infantry + 10 Cavalry + 10 Ranged + 1 Commander = 31 Units Combined
                assertEquals(31, loadedArmy.getAllUnits().size());
            } catch (Exception e){
                fail("loadFileTest() threw an exception when not expected to");
            }
        }

        @Test
        @DisplayName("Tests that a loaded army will be identical to the source army")
        public void armyLoadingWillBeEqualToSource(){
            FileHandler fileHandler = new FileHandler();
            Army army1 = new Army("Army One");
            army1.add(new CommanderUnit("Town Chieftain", 250));
            for (int i = 0; i < 5; i++){
                army1.add(new InfantryUnit("Town Swordsmen", 100));
                army1.add(new CavalryUnit("Hussars", 150));
                army1.add(new RangedUnit("Novice Archers", 50));
            }

            try{
                fileHandler.saveArmy(army1);
                Army army2 = fileHandler.loadArmy(army1.getName());
                assertEquals(army1.getAllUnits().toString(), army2.getAllUnits().toString());
            } catch (Exception e){
                fail("armyLoadingWillBeEqualToSource threw error when not supposed to");
            }

        }

        @Test
        @DisplayName("Tests that it is possible to delete a saved army")
        public void deletingAnArmy(){
            FileHandler fileHandler = new FileHandler();
            UnitFactory factory = new UnitFactory();
            Army army = new Army("DeleteThisArmy");
            army.addAll(factory.getMultipleUnits("Infantry", 20, "Spearman", 100));
            //Save army
            try {
                // Save army
                fileHandler.saveArmy(army);

                // Tries to delete army
                assertTrue(fileHandler.deleteArmy(army.getName()));
            } catch (Exception e) {
                fail("deletingAnArmy");
            }

        }
    }

    @Nested
    @DisplayName("Exception Handling")
    class testingExceptions{
        @Test
        @DisplayName("Throws error when loading with a blank name")
        public void loadingArmyWithBlankName(){
            try {
                FileHandler fileHandler = new FileHandler();
                Army loadedArmy = fileHandler.loadArmy(" ");
                fail("loadingArmyWithBlankName did not throw an exception when expected to");
            } catch (Exception e){
                assertEquals("File name cannot be empty", e.getMessage());
            }
        }
    }
}
