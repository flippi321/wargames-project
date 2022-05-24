package no.ntnu.idatt2001.Wargames.Battle;

import no.ntnu.idatt2001.Wargames.Army.*;
import no.ntnu.idatt2001.Wargames.Units.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BattleTest {
    @Nested
    @DisplayName("Core Battle Mechanics")
    class testingBattleMechanics{
        @Test
        @DisplayName("Testing a battle which the Orks should win")
        public void checkOrkishVictory() {
            UnitFactory factory = new UnitFactory();
            Army orks = new Army("Horde");
            Army humans = new Army("Alliance");
            try {
                //Ork army
                orks.addAll(factory.getMultipleUnits("Infantry", 500, "Grunt", 100));
                orks.addAll(factory.getMultipleUnits("Ranged", 250, "Spearman", 150));
                orks.addAll(factory.getMultipleUnits("Cavalry", 150, "Raider", 75));
                orks.addAll(factory.getMultipleUnits("Commander", 1, "Gul'Dan", 300));

                // Human army
                humans.addAll(factory.getMultipleUnits("Infantry", 500, "Footman", 75));
                humans.addAll(factory.getMultipleUnits("Ranged", 250, "Archer", 100));
                humans.addAll(factory.getMultipleUnits("Cavalry", 150, "Knight", 50));
                humans.addAll(factory.getMultipleUnits("Commander", 1, "Mountain King", 200));

                //Battle
                Battle epicBattle = new Battle(orks, humans, Terrain.FOREST, Weather.Sunny);
                assertEquals(orks.getName(), epicBattle.simulate().getName());
            } catch (Exception e) {
                fail("checkHasUnitsWhenNotEmpty failed");
            }
        }

        @Test
        @DisplayName("Testing a battle which the Humans should win")
        public void checkHumanVicotry() {
            UnitFactory factory = new UnitFactory();
            Army orks = new Army("Horde");
            Army humans = new Army("Alliance");
            try {
                //Ork army
                orks.addAll(factory.getMultipleUnits("Infantry", 500, "Grunt", 75));
                orks.addAll(factory.getMultipleUnits("Ranged", 250, "Spearman", 100));
                orks.addAll(factory.getMultipleUnits("Cavalry", 150, "Raider", 50));
                orks.addAll(factory.getMultipleUnits("Commander", 1, "Gul'Dan", 200));

                // Human army
                humans.addAll(factory.getMultipleUnits("Infantry", 500, "Footman", 100));
                humans.addAll(factory.getMultipleUnits("Ranged", 250, "Archer", 150));
                humans.addAll(factory.getMultipleUnits("Cavalry", 150, "Knight", 75));
                humans.addAll(factory.getMultipleUnits("Commander", 1, "Mountain King", 300));

                //Battle
                Battle epicBattle = new Battle(orks, humans, Terrain.FOREST, Weather.Sunny);
                assertEquals(humans.getName(), epicBattle.simulate().getName());
            } catch (Exception e) {
                fail("checkHasUnitsWhenNotEmpty failed");
            }
        }
    }

    @Nested
    @DisplayName("Loading two armies who should fight")
    class testingBattlesWithLoadedArmies{
        @Test
        @DisplayName("Testing Witcher Victory")
        public void checkWitcherVictory(){
            try {
                FileHandler fileHandler = new FileHandler();
                Army witchers = fileHandler.loadArmyFromName("Witchers");
                Army ghouls =fileHandler.loadArmyFromName("Ghoul Army");
                Battle witchersVsGhouls = new Battle(witchers, ghouls, Terrain.FOREST, Weather.Heavy_Fog);
                assertEquals(witchers.getName(), witchersVsGhouls.simulate().getName());
            } catch (Exception e){
                fail("checkWitcherVictory failed");
            }
        }

        @Test
        @DisplayName("Testing Ghoul Victory")
        public void checkGhoulVictory(){
            try {
                FileHandler fileHandler = new FileHandler();
                Army witchers = fileHandler.loadArmyFromName("Witchers");
                Army ghouls =fileHandler.loadArmyFromName("Ghoul Armada");
                Battle witcherGhoulBattle = new Battle(witchers, ghouls, Terrain.FOREST, Weather.Sunny);
                assertEquals(ghouls.getName(),witcherGhoulBattle.simulate().getName());
            } catch (Exception e){
                fail("checkGhoulVictory failed");
            }
        }
    }

    @Nested
    @DisplayName("Weather Effects work as intended")
    class testingThatWeatherEffectsWorksAsIntended{

    }

    @Nested
    @DisplayName("TestException Handling")
    class testingExceptionHandling{
        @Test
        @DisplayName("Throws Exception when using ")
        public void testingUsingEmptyArmiesInBattle(){
            Army army1 = new Army("Army1", new ArrayList<Unit>());
            Army army2 = new Army("Army2", new ArrayList<Unit>());
            try {
                Battle battle = new Battle(army1, army2, Terrain.FOREST, Weather.Sunny);
                battle.simulate();
                fail("testingUsingEmptyArmiesInBattle() did not throw Exception when expected to");
            } catch (Exception e){
                assertEquals("At least one army must have units", e.getMessage());
            }
        }

        @Test
        @DisplayName("Throws Exception when using ")
        public void testingUsingEqualArmies(){
            UnitFactory factory = new UnitFactory();
            ArrayList<Unit> units = factory.getMultipleUnits("Infantry", 10, "Swordman", 100);
            Army army1 = new Army("Army1", units);
            Army army2 = new Army("Army1", units);
            try {
                Battle battle = new Battle(army1, army2, Terrain.FOREST, Weather.Sunny);
                battle.simulate();
                fail("testingUsingEmptyArmiesInBattle() did not throw Exception when expected to");
            } catch (Exception e){
                assertEquals("Armies must be different", e.getMessage());
            }
        }
    }
}
