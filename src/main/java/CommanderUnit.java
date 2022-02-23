public class CommanderUnit extends CavalryUnit{

    /**
     * Creates an object which represents a commander in the battle
     * @param name the name of the commander
     * @param health how much damage this unit can sustain
     */
    public CommanderUnit(String name, int health) {
        super(name, health, 25, 15);
    }
}
