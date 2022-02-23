public class InfantryUnit extends Unit{

    /**
     * Creates an object which represents an infantry unit in the battle
     * @param  name the name of the unit
     * @param health the damage this unit can sustain
     */
    public InfantryUnit(String name, int health) {
        super(name, health, 15, 10);
    }

    /**
     * The infantry units resist bonus
     * This unit does not have high Resist Bonus
     * @return the Resist bonus of this unit
     */
    @Override
    public int getResistBonus() {
        return 1;
    }

    /**
     * The infantry units attack bonus
     * This has a decent attack bonus
     * @return the Attack bonus of this unit
     */
    @Override
    public int getAttackBonus() {
        return 2;
    }
}
