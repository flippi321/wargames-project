import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BattleTest {
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
            Battle epicBattle = new Battle(armyOne, armyTwo);
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
            Battle epicBattle = new Battle(armyOne, armyTwo);
            assertEquals(armyOne, epicBattle.simulate());

        } catch (Exception e) {
            fail("checkHasUnitsWhenNotEmpty failed");
        }
    }
}
