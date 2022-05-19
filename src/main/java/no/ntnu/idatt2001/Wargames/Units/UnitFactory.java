package no.ntnu.idatt2001.Wargames.Units;
import java.util.ArrayList;

public class UnitFactory {
    public UnitFactory() {
    }

    // TODO
    //  USE ENUM DIRECTLY HERE?
    public Unit getUnit(UnitType unitType, String name, int health){
        if(unitType.name().equalsIgnoreCase("INFANTRY")){
            return new InfantryUnit(name, health, 15, 10);
        }
        if(unitType.name().equalsIgnoreCase("CAVALRY")) {
            return new CavalryUnit(name, health,20, 12);
        }
        if(unitType.name().equalsIgnoreCase("COMMANDER")) {
            return new CommanderUnit(name, health, 25, 15);
        }
        if(unitType.name().equalsIgnoreCase("RANGED")) {
            return new RangedUnit(name, health, 15, 8);
        }
        else throw new IllegalArgumentException("The unit type does not exist");
    }

    public ArrayList<Unit> getMultipleUnits(UnitType unitType, int numberOfUnits, String name, int health){
        ArrayList<Unit> units = new ArrayList<>();
        if(numberOfUnits<=0) throw new IllegalArgumentException("Number of Units must be above 0");
        for(int n = 0; n < numberOfUnits; n++){
            units.add(getUnit(unitType,name,health));
        }

        return units;
    }
}