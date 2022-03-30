/**
 * Abstract Unit Class
 * @author  chribrev
 * @version 1.0
 */
abstract class Unit {
    private String name;
    private int health;
    private int attack;
    private int armour;

    /**
     * Abstract class representing a unit in a battle
     * @param name the name of the unit
     * @param health how much damage this unit can sustain
     * @param attack how much damage this unit can afflict
     * @param armour how much damage this unit can resist, before loosing health
     */
    public Unit(String name, int health, int attack, int armour) {
        if (name.isBlank()){
            throw new IllegalArgumentException("Must have a name");
        }
        if (health <= 0){
            throw new IllegalArgumentException("Must have a health value above 0");
        }

        this.name = name;
        this.health = health;
        this.attack = attack;
        this.armour = armour;
    }

    /**
     * Method representing this unit attacking another unit and afflicting damage
     * Using the formula:
     * healthOpponent – (attack + attackBonus)this + (armor + resistBonus)opponent
     * @param opponent the unit under attack
     */
    public void attack(Unit opponent){
        int Defence = opponent.getArmour() + opponent.getResistBonus();
        int Attack = this.getAttack() + this.getAttackBonus();
        int newHealth = opponent.getHealth() + Defence - Attack;
        opponent.setHealth(newHealth);
    }

    public String getName() {
        return name;
    }

    /**
     * Method to acquire health value
     * Used when calculating new health
     * @return the current health value of the unit
     */
    public int getHealth() {
        return health;
    }

    /**
     * Method to change health value of unit
     * This is used when a unit suffers damage
     * @param health the new health value of the unit
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Method to acquire attack value of the unit
     * This value is used when attacking another unit
     * @return the current attack value of the unit
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Method to acquire armor value of the unit
     * This value is used when being attack
     * @return the current armour value of the unit
     */
    public int getArmour() {
        return armour;
    }

    /**
     * Method to acquire Resist (Defense) bonus,
     * Abstract since this value varies between unit types
     * @return the current resist value of the unit
     */
    public abstract int getResistBonus();

    /**
     * Method to acquire attack bonus,
     * Abstract since this value varies between unit types
     * @return the current attack bonus value of the unit
     */
    public abstract int getAttackBonus();

    /**
     * Method used to represent a unit's information in the form of a string
     * @return a string of information relating to the unit
     */
    @Override
    public String toString() {
        return "This unit, called " + name +
                "has a health value of " + health +
                ", attack value of " + attack +
                " and an armour value of " + armour;
    }
}
