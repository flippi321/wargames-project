package no.ntnu.idatt2001.Wargames.Units;
/**
 * Cavalry Unit
 * @author  chribrev
 * @version 1.0
 */
public class CavalryUnit extends Unit{
    boolean Charges;

    /**
     * Creates an object which represents a Cavalry unit in the battle
     * It's first strike will multiply its Attack Bonus with 3, which is represented by the boolean Charges
     * @param  name the name of the unit
     * @param health the damage this unit can sustain
     */
    public CavalryUnit(String name, int health) {
        super(name, health,20, 12);
        Charges = true;
    }

    /**
     * Constructor which is used by the CommanderUnit
     * It's first strike will multiply its Attack Bonus with 3, which is represented by the boolean Charges
     * @param name the name of the unit
     * @param health how much damage this unit can sustain
     * @param attack how much damage this unit can afflict
     * @param armour how much damage this unit can resist, before loosing health
     */
    public CavalryUnit(String name, int health, int attack, int armour) {
        super(name, health, attack, armour);
        Charges = true;
    }

    /**
     * The Cavalry units Resist bonus
     * This unit does not have high Resist Bonus
     * @return the Resist bonus of this unit
     */
    @Override
    public int getResistBonus(String terrain, String weather) {
        if(terrain.equalsIgnoreCase("Forest")){
            return 0;
        }
        return 1;
    }

    /**
     * The Cavalry units attack bonus
     * It's first attack will be stronger than every other due to its Charge bonus
     * @return the Attack bonus of this unit
     */
    @Override
    public int getAttackBonus(String terrain, String weather) {
        int bonus = 0;
        if (terrain.equalsIgnoreCase("Plains")) bonus += 4;
        if (Charges) {
            Charges = false;
            return 6 + bonus;
        }
        return 2 + bonus;
    }
}
