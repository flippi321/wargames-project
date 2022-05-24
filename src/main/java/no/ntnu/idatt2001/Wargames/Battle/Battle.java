package no.ntnu.idatt2001.Wargames.Battle;

import no.ntnu.idatt2001.Wargames.Army.Army;
import no.ntnu.idatt2001.Wargames.Units.*;
import java.util.ArrayList;
import java.util.Random;

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
    public Army simulate() {
        if(armyOne == null | armyTwo == null){
            throw new IllegalArgumentException("Armies must be defined");
        }
        if(!armyOne.hasUnits() & !armyTwo.hasUnits()){
            throw new IllegalArgumentException("At least one army must have units");
        }
        if(armyOne.equals(armyTwo)){
            throw new IllegalArgumentException("Armies must be different");
        }
        while (armyOne.hasUnits() && armyTwo.hasUnits()){
            round++;
            log.add("\nRound " + round +":");

            // Every 50 rounds, the weather will affect the units
            if (round%50==0 & !weather.name().equals("Sunny")){
                switch (weather.name()){
                    // Rainstorm: Lightning kills 1 random unit from each army
                    default -> {
                        System.out.println("Rain");
                        Unit unitOne = armyOne.getRandom();
                        Unit unitTwo = armyTwo.getRandom();
                        log.add(String.format("[Lightning Hits! %10s and %10s are killed]",
                                unitOne.getName(), unitTwo.getName()));
                        armyOne.remove(unitTwo);
                        armyTwo.remove(unitTwo);
                    }
                    // Blizzard: All units take damage from frostbite
                    case "Blizzard" -> {
                        System.out.println("Blizzard");
                        log.add("[Blizzard Hits! All units take 10 damage]");
                        armyOne.damageAll(10);
                        armyTwo.damageAll(10);
                    }
                    // Heavy Fog: There is a 5% chance that all cavalry units gets lost in the fog
                    case "Heavy_Fog" -> {
                        System.out.println("Fog");
                        log.add("[Heavy fog hits]");
                        int ranNum = random.nextInt(20);
                        if (ranNum == 19){
                            armyOne.removeAllCavalry();
                            System.out.println();
                            log.add(String.format("[Heavy Fog! %s's cavalry division gets lost]", armyOne.getName()));
                        } else if (ranNum == 18){
                            armyTwo.removeAllCavalry();
                            log.add(String.format("[Heavy Fog! %s's cavalry division gets lost]", armyTwo.getName()));
                        }
                    }
                }
            } else {
                //Checks that neither of the Armies are wiped out by the weather effects
                if(armyOne.hasUnits() & armyTwo.hasUnits()){
                    // One unit from armyOne attacks one from armyTwo
                    Unit unitOne = armyOne.getRandom();
                    Unit unitTwo = armyTwo.getRandom();
                    unitOne.attack(unitTwo, terrain.name(), weather.name());

                    // Checks if the unit is wiped out
                    if (unitTwo.getHealth() <= 0){
                        armyOne.addKill();
                        armyTwo.addLoss();
                        armyTwo.remove(unitTwo);
                        log.add(unitOne.getName() + " killed " + unitTwo.getName());
                    } else {
                        log.add(unitOne.getName() + " damaged " + unitTwo.getName());
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
                            log.add(unitFour.getName() + " killed " + unitThree.getName());
                        } else {
                            log.add(unitFour.getName() + " damaged " + unitThree.getName());
                        }
                    }
                }
            }
        }
        if (armyOne.hasUnits()){
            return armyOne;
        } if (armyTwo.hasUnits()){
            return armyTwo;
        }
        return new Army("Draw");
    }

    /**
     * Accessor method for Terrain value
     * @return the terrain value for the battle
     */
    public Terrain getTerrain() {
        return terrain;
    }

    /**
     * Accessor method for Weather value
     * @return the weather value for the battle
     */
    public Weather getWeather() {
        return weather;
    }

    /**
     * Accessor method for the battle Log
     * @return the log of the battle
     */
    public ArrayList<String> getLog() {
        return log;
    }

    /**
     * Method to display battle information about the battle
     * @return the terrain value for the battle
     */
    @Override
    public String toString() {
        return "This battle is between" + armyOne.getName() +
                " & " + armyTwo.getName() + " and includes " +
                (armyOne.getAllUnits().size()+armyTwo.getAllUnits().size() + " units");
    }
}
