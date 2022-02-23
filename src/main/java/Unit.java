abstract class Unit {
    private String name;
    private int health;
    private int attack;
    private int armour;

    /**
     * Abstract class representing a unit in a battle
     * @param name the name of the unit
     * @param health how much damage this unit can withstand
     * @param attack how much damage this unit can afflict
     * @param armour how much damage this unit can resist
     */
    public Unit(String name, int health, int attack, int armour) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.armour = armour;
    }

    /**
     * Fuction for checking how much damage this unit does to another unit
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public int getArmour() {
        return armour;
    }

    public abstract int getResistBonus();

    public abstract int getAttackBonus();

    @Override
    public String toString() {
        return "Unit{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", attack=" + attack +
                ", armour=" + armour +
                '}';
    }
}
