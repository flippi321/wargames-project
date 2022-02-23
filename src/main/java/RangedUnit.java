public class RangedUnit extends Unit{
    private int numberOfTimesAttacked = 0;

    /**
     * Creates an object which represents a ranged unit in the battle
     * @param  name the name of the unit
     * @param health the damage this unit can sustain
     */
    public RangedUnit(String name, int health) {
        super(name, health, 15, 8);

    }

    /**
     * The Ranged units attack bonus
     * its Attack bonus strong due to its range advantage
     * @return the Attack bonus of this unit
     */
    @Override
    public int getAttackBonus() {
        return 3;
    }

    /**
     * The Resist bonus this unit receives due to range
     * the Resist bonus will diminish each time the unit is attacked
     * since this function is only called when the unit is attacked, we can simulate the change of range in this method
     * @return the Resist bonus of this unit
     */
    @Override
    public int getResistBonus() {
        numberOfTimesAttacked++;
        if (numberOfTimesAttacked == 1){
            return 6;
        }
        else if (numberOfTimesAttacked == 2){
            return 4;
        }
        return 2;
    }
}
