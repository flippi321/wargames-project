package no.ntnu.idatt2001.Wargames.Units;
/**
 * Commander Unit
 * @author  chribrev
 * @version 1.0
 */
public class CommanderUnit extends CavalryUnit{

    /**
     * Creates an object which represents a commander in the battle
     * It's first strike will multiply its Attack Bonus with 3, which is represented by the boolean Charges
     * @param name the name of the unit
     * @param health how much damage this unit can sustain
     * @param attack how much damage this unit can afflict
     * @param armour how much damage this unit can resist, before loosing health
     */
    public CommanderUnit(String name, int health, int attack, int armour) {
        super(name, health, attack, armour);
    }

    /**
     * Creates an object which represents a commander in the battle
     * @param name the name of the commander
     * @param health how much damage this unit can sustain
     */
    public CommanderUnit(String name, int health) {
        super(name, health, 25, 15);
    }
}
