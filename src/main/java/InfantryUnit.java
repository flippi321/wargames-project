/**
 * Infantry Unit
 * @author  chribrev
 * @version 1.0
 */
public class InfantryUnit extends Unit{

    /**
     * Creates an object which represents an infantry unit in the battle
     * @param name the name of the unit
     * @param health the damage this unit can sustain
     */
    public InfantryUnit(String name, int health) {
        super(name, health, 15, 10);
    }

    /**
     * Creates an object which represents an infantry unit in the battle
     * @param name the name of the unit
     * @param health the damage this unit can sustain
     * @param attack how much damage this unit can afflict
     * @param armour how much damage this unit can resist, before loosing health
     */
    public InfantryUnit(String name, int health, int attack, int armour) {
        super(name, health, attack, armour);
    }

    /**
     * The infantry units resist bonus
     * This unit does not have high Resist Bonus
     * @return the Resist bonus of this unit
     */
    @Override
    public int getResistBonus(String terrain) {
        if (terrain.equalsIgnoreCase("Forest")) {
            return 5;
        }
        return 1;
    }

    /**
     * The infantry units attack bonus
     * This has a decent attack bonus
     * @return the Attack bonus of this unit
     */
    @Override
    public int getAttackBonus(String terrain) {
        if (terrain.equalsIgnoreCase("Forest")) {
            return 6;
        }
        return 2;
    }
}
