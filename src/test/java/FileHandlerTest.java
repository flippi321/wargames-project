import no.ntnu.idatt2001.Wargames.Units.*;
import no.ntnu.idatt2001.Wargames.Army.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {
    @Test
    @DisplayName("Tests that it is possible to save a file")
    public void savingAnArmyFile(){
        try {
            Army newArmy = new Army("TestingArmy");
            newArmy.add(new CommanderUnit("Town Chieftain", 250));
            for (int i = 0; i < 10; i++){
                newArmy.add(new InfantryUnit("Town Swordsmen", 100));
                newArmy.add(new CavalryUnit("Hussars", 150));
                newArmy.add(new RangedUnit("Novice Archers", 50));
            }

            newArmy.saveArmy();
            assertTrue(true);
        } catch (Exception e){
            fail("could not save to file");
        }
    }


    @Test
    @DisplayName("Tests that it is possible to load a file")
    public void loadFileTest(){
        Army newArmy = new Army("TestingArmy");
        newArmy.add(new InfantryUnit("Swordsman", 100));
        try {
            assertTrue(newArmy.loadArmy());
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Tests that a loaded army will be identical to the source army")
    public void armyLoadingWillBeEqualToSource(){
        Army army1 = new Army("Army One");
        army1.add(new CommanderUnit("Town Chieftain", 250));
        for (int i = 0; i < 5; i++){
            army1.add(new InfantryUnit("Town Swordsmen", 100));
            army1.add(new CavalryUnit("Hussars", 150));
            army1.add(new RangedUnit("Novice Archers", 50));
        }
        army1.saveArmy();
        Army army2 = new Army("Army Two");

        army2.loadArmy("Army One");
        assertEquals(army1.getAllUnits().toString(), army2.getAllUnits().toString());
    }

    @Test
    @DisplayName("Tests that it is possible to delete a saved army")
    public void deletingAnArmy(){
        Army army = new Army("DeleteThisArmy");
        for(int i = 0; i < 20; i++){
            army.add(new InfantryUnit("Swordsman", 100));
        }
        army.saveArmy();

        //Checks that army is deleted
        assertTrue(army.deleteArmy());
        }

    @AfterAll
    public static void removingFileUsedForTesting(){
        Army army1 = new Army("TestingArmy");
        Army army2 = new Army("Army Two");
        try {
            army1.deleteArmy();
            army2.deleteArmy();
        } catch (Exception e){
            fail("Could not remove file");
        }
    }
}
