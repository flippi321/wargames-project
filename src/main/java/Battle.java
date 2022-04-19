/**
 * Battle Class
 * @author  chribrev
 * @version 1.0
 */
public class Battle {
    Army armyOne;
    Army armyTwo;
    Terrain terrain;

    /**
     * Constructor for a class that represents a battle between two armies
     * @param armyOne the first army in the battle
     * @param armyTwo the second army in the battle
     */
    public Battle(Army armyOne, Army armyTwo, Terrain terrain) {
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
        this.terrain = terrain;
    }

    /**
     * Method to simulate a battle between army1 and army2
     * @return the army that wins the battle
     */
    public Army simulate(){
        while (armyOne.hasUnits() && armyTwo.hasUnits()){
            // One unit from armyOne attacks one from armyTwo
            Unit unitOne = armyOne.getRandom();
            Unit unitTwo = armyTwo.getRandom();
            unitOne.attack(unitTwo);

            // Checks if the unit is wiped out
            if (unitTwo.getHealth() <= 0){
                armyTwo.remove(unitTwo);
            }

            // The second army can't counterattack if wiped out from the attack
            if (armyTwo.hasUnits()){
                // One unit from armyTwo attacks one from armyOne
                Unit unitThree = armyOne.getRandom();
                Unit unitFour = armyTwo.getRandom();
                unitFour.attack(unitThree);

                // Checks if the unit is wiped out
                if (unitThree.getHealth() <= 0){
                    armyOne.remove(unitThree);
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
