package no.ntnu.idatt2001.Wargames.Units;
/**
 * Ranged Unit
 * @author  chribrev
 * @version 1.0
 */
public class RangedUnit extends Unit{
    private int numberOfTimesAttacked;

    /**
     * Creates an object which represents a ranged unit in the battle
     * @param name the name of the unit
     * @param health the damage this unit can sustain
     */
    public RangedUnit(String name, int health) {
        super(name, health, 15, 8);
        numberOfTimesAttacked = 0;
    }

    /**
     * Creates an object which represents a ranged unit in the battle
     * @param name the name of the unit
     * @param health the damage this unit can sustain
     * @param attack how much damage this unit can afflict
     * @param armour how much damage this unit can resist, before loosing health
     */
    public RangedUnit(String name, int health, int attack, int armour) {
        super(name, health, attack, armour);
        numberOfTimesAttacked = 0;
    }

    /**
     * The Ranged units attack bonus
     * its Attack bonus strong due to its range advantage
     * @return the Attack bonus of this unit
     */
    @Override
    public int getAttackBonus(String terrain, String weather) {
        int attackbonus = 3;
        if (terrain.equalsIgnoreCase("Hill")) attackbonus+=4;
        else if (terrain.equalsIgnoreCase("Forest")) attackbonus+=2;
        if (weather.equalsIgnoreCase("Rainstorm")) attackbonus-=2;           //TODO Test
        else if (weather.equalsIgnoreCase("Heavy_fog")) return 0;            //TODO Test
        return attackbonus;
    }

    /**
     * The Resist bonus this unit receives due to range
     * the Resist bonus will diminish each time the unit is attacked
     * since this function is only called when the unit is attacked, we can simulate the change of range in this method
     * @return the Resist bonus of this unit
     */
    @Override
    public int getResistBonus(String terrain, String weather) {
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
