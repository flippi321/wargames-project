public class Battle {
    Army armyOne;
    Army armyTwo;

    public Battle(Army armyOne, Army armyTwo) {
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

    public Army simulate(){
        while (armyOne.hasUnits() && armyTwo.hasUnits()){
            Unit unitOne = armyOne.getRandom();
            Unit unitTwo = armyTwo.getRandom();
            unitOne.attack(unitTwo);
        }
        if (armyOne.hasUnits()){
            return armyOne;
        }
        return armyTwo;
    }
}
