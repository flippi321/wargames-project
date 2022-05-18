package no.ntnu.idatt2001.Wargames.Army;

import no.ntnu.idatt2001.Wargames.Army.*;
import no.ntnu.idatt2001.Wargames.Units.*;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArmyTest {
    @Nested
    @DisplayName("Constructors")
    class constructors  {
        @Test
        @DisplayName("Testing that the constructor can work with just the name")
        public void checkWithOnlyName() {
            try {
                Army newArmy = new Army("Grand army of the Republic");
                assertEquals("Grand army of the Republic", newArmy.getName());
            } catch (Exception e) {
                fail("checkGetName failed");
            }
        }

        @Test
        @DisplayName("Testing getName")
        public void checkGetName() {
            try {
                Army newArmy = new Army("Empty Horde");
                assertEquals("Empty Horde", newArmy.getName());
            } catch (Exception e) {
                fail("checkGetName failed");
            }
        }
    }

    @Nested
    @DisplayName("Adding and Removing Units")
    class addingAndRemovingUnits{
        @Test
        @DisplayName("Testing adding a new unit")
        public void checkAddingNewUnit() {
            try {
                Army newArmy = new Army("Empty Horde");
                Unit newCavalry = new CavalryUnit("Mongol Rider", 100);
                newArmy.add(newCavalry);
                assertEquals(newCavalry, newArmy.getAllUnits().get(0));
            } catch (Exception e) {
                fail("checkAddingNewUnit failed");
            }
        }

        @Test
        @DisplayName("Testing adding units")
        public void checkAddingUnits() {
            try {
                Unit Cavalry = new CavalryUnit("Mongol Rider", 250);
                Unit Infantry = new InfantryUnit("Mongol Footman", 150);
                List<Unit> smallArmy = new ArrayList<>();
                smallArmy.add(Cavalry);
                smallArmy.add(Infantry);

                Army newArmy = new Army("Empty Horde", smallArmy);
                Unit newInfantry = new InfantryUnit("Mongol Veteran", 250, 15, 15);
                newArmy.add(newInfantry);
                assertEquals(3, newArmy.getAllUnits().size());
            } catch (Exception e) {
                fail("checkAddingUnits failed");
            }
        }

        @Test
        @DisplayName("Testing adding all units from list")
        public void checkAddAllUnits() {
            try {
                Army newArmy = new Army("Empty Horde");
                Unit Cavalry = new CavalryUnit("Mongol Rider", 100);
                Unit Infantry = new InfantryUnit("Mongol Footman", 150);
                Unit Ranged = new InfantryUnit("Mongol Archer", 50);
                List<Unit> smallArmy = new ArrayList<>();
                smallArmy.add(Cavalry);
                smallArmy.add(Infantry);
                smallArmy.add(Ranged);
                newArmy.addAll(smallArmy);
                assertEquals(3, newArmy.getAllUnits().size());
            } catch (Exception e) {
                fail("checkAddingUnits failed");
            }
        }

        @Test
        @DisplayName("Testing adding and removing units from army")
        public void checkRemoveUnits() {
            try {
                Unit Cavalry = new CavalryUnit("Orc Rider", 100);
                Unit Infantry = new InfantryUnit("Ork Footman", 150);
                Unit Ranged = new InfantryUnit("Goblin Archer", 100);
                Army newArmy = new Army("Orc Waargh");
                newArmy.add(Cavalry);
                newArmy.add(Infantry);
                newArmy.add(Ranged);
                newArmy.remove(Infantry);
                newArmy.remove(Cavalry);

                assertEquals(1, newArmy.getAllUnits().size());
            } catch (Exception e) {
                fail("checkAddingUnits failed");
            }
        }
    }

    @Nested
    @DisplayName("hasUnits and Random")
    class hasUnitsAndRandom   {
        @Test
        @DisplayName("Testing hasUnits on empty list")
        public void checkHasUnitsWhenEmpty() {
            try {
                Army newArmy = new Army("Empty Horde");
                assertFalse(newArmy.hasUnits());
            } catch (Exception e) {
                fail("checkHasUnitsWhenEmpty failed");
            }
        }

        @Test
        @DisplayName("Testing hasUnits on not empty list")
        public void checkHasUnitsWhenNotEmpty() {
            try {
                Army newArmy = new Army("Empty Horde");
                Unit newCavalry = new CavalryUnit("Mongol Rider", 100);
                newArmy.add(newCavalry);
                assertTrue(newArmy.hasUnits());
            } catch (Exception e) {
                fail("checkHasUnitsWhenNotEmpty failed");
            }
        }

        /**
         * Checks that getRandom selects a new unit each time
         * Done up to five times since there exists a possibility it may choose the same unit
         */
        @Test
        @DisplayName("Testing that getRandom chooses different units each time")
        public void checkGetRandom() {
            try {
                Army newArmy = new Army("Confederacy of Independent States");
                Unit newInfantry = new InfantryUnit("Robot knights", 100);
                Unit newRanged = new RangedUnit("Naval Artillery", 50);
                Unit newCavalry = new CavalryUnit("Speed Bikes", 250);
                newArmy.add(newInfantry);
                newArmy.add(newCavalry);
                newArmy.add(newRanged);

                int i = 0;
                boolean different = false;
                //T
                while (i < 10 && !different){
                    if(!newArmy.getRandom().equals(newArmy.getRandom())){
                        different = true;
                    }
                    i++;
                }
                assertTrue(different);
            } catch (Exception e) {
                fail("checkHasUnitsWhenNotEmpty failed");
            }
        }
    }

    @Nested
    @DisplayName("Testing getUnit functions")
    class getUnitFunctions  {
        @Test
        @DisplayName("Testing that getInfantry Function returns all infantry units")
        public void checkGetInfantryUnits(){
            Army newArmy = new Army("Confederacy of Independent States");
            Unit newInfantry = new InfantryUnit("Robot knights", 100);
            Unit newRanged = new RangedUnit("Naval Artillery", 50);
            Unit newCavalry = new CavalryUnit("Speed Bikes", 250);
            for (int i = 0; i < 10; i++){
                newArmy.add(newInfantry);
                newArmy.add(newCavalry);
                newArmy.add(newRanged);
            }
            assertEquals(10,newArmy.getInfantryUnits().size());
        }

        @Test
        @DisplayName("Testing that getCavalry Function returns all cavalry units")
        public void checkGetCavalryUnits(){
            Army newArmy = new Army("Confederacy of Independent States");
            Unit newInfantry = new InfantryUnit("Robot knights", 100);
            Unit newRanged = new RangedUnit("Naval Artillery", 50);
            Unit newCavalry = new CavalryUnit("Speed Bikes", 250);
            for (int i = 0; i < 5; i++){
                newArmy.add(newInfantry);
                newArmy.add(newCavalry);
                newArmy.add(newRanged);
            }
            assertEquals(5,newArmy.getCavalryUnits().size());
        }

        @Test
        @DisplayName("Testing that getCommander Function returns the commander unit")
        public void checkGetCommanderUnits(){
            Army newArmy = new Army("Confederacy of Independent States");
            Unit newInfantry = new InfantryUnit("Robot knights", 100);
            Unit newRanged = new RangedUnit("Naval Artillery", 50);
            Unit newCavalry = new CavalryUnit("Speed Bikes", 250);
            Unit newCommander = new CommanderUnit("Commander Grievous", 300);
            newArmy.add(newCommander);
            for (int i = 0; i < 5; i++){
                newArmy.add(newInfantry);
                newArmy.add(newCavalry);
                newArmy.add(newRanged);
            }
            assertEquals(1,newArmy.getCommanderUnits().size());
        }

        @Test
        @DisplayName("Testing that getRanged Function returns all infantry units")
        public void checkGetRangedUnits(){
            Army newArmy = new Army("Confederacy of Independent States");
            Unit newInfantry = new InfantryUnit("Robot knights", 100);
            Unit newRanged = new RangedUnit("Naval Artillery", 50);
            Unit newCavalry = new CavalryUnit("Speed Bikes", 250);
            for (int i = 0; i < 25; i++){
                newArmy.add(newInfantry);
                newArmy.add(newCavalry);
                newArmy.add(newRanged);
            }
            assertEquals(25,newArmy.getRangedUnits().size());
        }
    }
}