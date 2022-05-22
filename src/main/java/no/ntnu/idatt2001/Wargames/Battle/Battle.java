package no.ntnu.idatt2001.Wargames.Battle;
import no.ntnu.idatt2001.Wargames.Army.Army;
import no.ntnu.idatt2001.Wargames.Units.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Battle Class
 * @author  chribrev
 * @version 1.0
 */
public class Battle {
    Army armyOne;
    Army armyTwo;
    Terrain terrain;
    Weather weather;
    int round;
    Random random;
    ArrayList<String> log;

    /**
     * Constructor for a class that represents a battle between two armies
     * @param armyOne the first army in the battle
     * @param armyTwo the second army in the battle
     */
    public Battle(Army armyOne, Army armyTwo, Terrain terrain, Weather weather) {
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
        this.terrain = terrain;
        this.weather = weather;
        this.round = 0;
        random = new Random();
        log = new ArrayList<>();
    }

    /**
     * Method to simulate a battle between army1 and army2
     * @return the army that wins the battle
     */
    public Army simulate(){
        if(!armyOne.hasUnits() & !armyTwo.hasUnits()){
            throw new IllegalArgumentException("At least one army must have units");
        }
        while (armyOne.hasUnits() && armyTwo.hasUnits()){
            round++;
            // Every 50 rounds, the weather will affect the units
            if (round%50==0 | !weather.name().equals("Sunny")){
                switch (weather.name()){
                    // Rainstorm: Lightning kills 1 random unit from each army
                    default -> {
                        Unit unitOne = armyOne.getRandom();
                        Unit unitTwo = armyTwo.getRandom();
                        log.add(String.format("[Lightning Hits! %10s and %10s are killed]",
                                unitOne.getName(), unitTwo.getName()));
                        armyOne.remove(unitTwo);
                        armyTwo.remove(unitTwo);
                    }
                    // Blizzard: All units take damage from frostbite
                    case "Blizzard" -> {
                        log.add("[Blizzard Hits! All units take 2 damage]");
                        armyOne.damageAll(2);
                        armyTwo.damageAll(2);
                    }
                    // Heavy Fog: There is a 5% chance that all cavalry units gets lost in the fog
                    case "Heavy_fog" -> {
                        log.add("[Heavy fog hits]");
                        int ranNum = random.nextInt(20);
                        if (ranNum == 19){
                            armyOne.removeAllCavalry();
                            log.add(String.format("[Heavy Fog! %s cavalry division gets lost]", armyOne.getName()));
                        } else if (ranNum == 18){
                            armyOne.removeAllCavalry();
                            log.add(String.format("[Heavy Fog! %s cavalry division gets lost]", armyTwo.getName()));
                        };
                    }
                }
            }

            //TODO
            // Optimize

            //Checks that neither of the Armies are wiped out by the weather effects
            if(armyOne.hasUnits() && armyTwo.hasUnits()){

                // One unit from armyOne attacks one from armyTwo
                Unit unitOne = armyOne.getRandom();
                Unit unitTwo = armyTwo.getRandom();
                unitOne.attack(unitTwo, terrain.name(), weather.name());

                // Checks if the unit is wiped out
                if (unitTwo.getHealth() <= 0){
                    armyOne.addKill();
                    armyTwo.addLoss();
                    armyTwo.remove(unitTwo);

                }

                // The second army can't counterattack if wiped out from the attack
                if (armyTwo.hasUnits()){
                    // One unit from armyTwo attacks one from armyOne
                    Unit unitThree = armyOne.getRandom();
                    Unit unitFour = armyTwo.getRandom();
                    unitFour.attack(unitThree, terrain.name(), weather.name());

                    // Checks if the unit is wiped out
                    if (unitThree.getHealth() <= 0){
                        armyTwo.addKill();
                        armyOne.addLoss();
                        armyOne.remove(unitThree);
                    }
                }
            }
        }
        if (armyOne.hasUnits()){
            return armyOne;
        }
        if (armyTwo.hasUnits()){
            return armyTwo;
        }
        return null;
    }

    @Override
    public String toString() {
        return "This battle is between" + armyOne +
                " & " + armyTwo;
    }
}
