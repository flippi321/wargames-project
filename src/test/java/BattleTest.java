import no.ntnu.idatt2001.Wargames.Army.*;
import no.ntnu.idatt2001.Wargames.Battle.*;
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
        public void checkOrkishVicotry() {
            try {
                // Orkish army
                Army armyOne = new Army("Horde");
                int i = 0;
                while (i < 500){
                    armyOne.add(new InfantryUnit("Grunt", 100));
                    if(i < 200){
                        armyOne.add(new RangedUnit("Spearman", 100));
                    }
                    if(i < 100){
                        armyOne.add(new CavalryUnit("Raider", 100));
                    }
                    if(i < 1){
                        armyOne.add(new CommanderUnit("Gul'Dan", 180));
                    }
                    i++;
                }

                // Human army
                Army armyTwo = new Army("Alliance");
                i = 0;
                while (i < 300) {
                    armyOne.add(new InfantryUnit("Footman", 100));
                    if (i < 100) {
                        armyOne.add(new RangedUnit("Archer", 100));
                    }
                    if (i < 50) {
                        armyOne.add(new CavalryUnit("Knight", 100));
                    }
                    if (i < 1) {
                        armyOne.add(new CommanderUnit("Mountain King", 180));
                    }
                    i++;
                }
                //Battle
                Battle epicBattle = new Battle(armyOne, armyTwo, Terrain.FOREST);
                assertEquals(armyOne, epicBattle.simulate());
            } catch (Exception e) {
                fail("checkHasUnitsWhenNotEmpty failed");
            }
        }

        @Test
        @DisplayName("Testing a battle which the Humans should win")
        public void checkHumanVicotry() {
            try {
                // Orkish army
                Army armyOne = new Army("Horde");
                int i = 0;
                while (i < 300){
                    armyOne.add(new InfantryUnit("Grunt", 100));
                    if(i < 100){
                        armyOne.add(new RangedUnit("Spearman", 100));
                    }
                    if(i < 50){
                        armyOne.add(new CavalryUnit("Raider", 100));
                    }
                    if(i < 1){
                        armyOne.add(new CommanderUnit("Gul'Dan", 180));
                    }
                    i++;
                }

                // Human army
                Army armyTwo = new Army("Alliance");
                i = 0;
                while (i < 500) {
                    armyOne.add(new InfantryUnit("Footman", 100));
                    if (i < 200) {
                        armyOne.add(new RangedUnit("Archer", 100));
                    }
                    if (i < 100) {
                        armyOne.add(new CavalryUnit("Knight", 100));
                    }
                    if (i < 1) {
                        armyOne.add(new CommanderUnit("Mountain King", 180));
                    }
                    i++;
                }
                //Battle
                Battle epicBattle = new Battle(armyOne, armyTwo, Terrain.FOREST);
                assertEquals(armyOne, epicBattle.simulate());

            } catch (Exception e) {
                fail("checkHasUnitsWhenNotEmpty failed");
            }
        }
    }

    @Nested
    @DisplayName("Loading two armies who should fight")
    class testingBattlesWithLoadedArmies{
        @Test
        @DisplayName("Testing Withcer Victory")
        public void checkWitcherVictory(){
            Army witchers = new Army("Witchers");
            Army ghouls = new Army("Ghoul Army");
            witchers.loadArmy();
            ghouls.loadArmy();
            Battle witcherGhoulBattle = new Battle(witchers, ghouls, Terrain.FOREST);
            assertEquals(witchers.getName(),witcherGhoulBattle.simulate().getName());
        }

        @Test
        @DisplayName("Testing Ghoul Victory")
        public void checkGhoulVictory(){
            Army witchers = new Army("Witchers");
            Army ghouls = new Army("Ghoul Armada");
            witchers.loadArmy();
            ghouls.loadArmy();
            Battle witcherGhoulBattle = new Battle(witchers, ghouls, Terrain.FOREST);
            assertEquals(ghouls.getName(),witcherGhoulBattle.simulate().getName());
        }
    }
}
