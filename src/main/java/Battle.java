public class Battle {
    Army armyOne;
    Army armyTwo;

    public Battle(Army armyOne, Army armyTwo) {
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

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
        return armyTwo;
    }

    @Override
    public String toString() {
        return "This battle is between" + armyOne +
                " & " + armyTwo;
    }
}
