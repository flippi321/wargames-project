package no.ntnu.idatt2001.Wargames.Battle;

import no.ntnu.idatt2001.Wargames.Army.*;
import no.ntnu.idatt2001.Wargames.Units.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BattleTest {
    @Nested
    @DisplayName("Testing Battle Mechanics")
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
                Army witchers = new Army("Witchers");
                Army ghouls = new Army("Ghoul Army");
                witchers.loadArmy();
                ghouls.loadArmy();
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
                Army witchers = new Army("Witchers");
                Army ghouls = new Army("Ghoul Armada");
                witchers.loadArmy();
                ghouls.loadArmy();
                Battle witcherGhoulBattle = new Battle(witchers, ghouls, Terrain.FOREST, Weather.Sunny);
                assertEquals(ghouls.getName(),witcherGhoulBattle.simulate().getName());
            } catch (Exception e){
                fail("checkGhoulVictory failed");
            }
        }
    }

    //TODO
    // Add exeption handling tests
}
