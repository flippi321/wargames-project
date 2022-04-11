public class UnitFactory {
    public UnitFactory() {
    }

    public Unit getUnit(String type, String name, int health, int attack, int armour){
        if(type.equalsIgnoreCase("InfantryUnit")) return new InfantryUnit(name, health, attack, armour);
        if(type.equalsIgnoreCase("CavalryUnit")) return new CavalryUnit(name, health, attack, armour);
        if(type.equalsIgnoreCase("CommanderUnit")) return new CommanderUnit(name, health, attack, armour);
        if(type.equalsIgnoreCase("RangedUnit")) return new RangedUnit(name, health, attack, armour);
        else throw new IllegalArgumentException("The unit type does not exist");
    }
}
