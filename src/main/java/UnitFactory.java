import java.util.ArrayList;
import java.util.List;

public class UnitFactory {
    public UnitFactory() {
    }

    public Unit getUnit(String unitType, String name, int health){
        if(unitType.equalsIgnoreCase("InfantryUnit")){
            return new InfantryUnit(name, health, 15, 10);
        }
        if(unitType.equalsIgnoreCase("CavalryUnit")) {
            return new CavalryUnit(name, health,20, 12);
        }
        if(unitType.equalsIgnoreCase("CommanderUnit")) {
            return new CommanderUnit(name, health, 25, 15);
        }
        if(unitType.equalsIgnoreCase("RangedUnit")) {
            return new RangedUnit(name, health, 15, 8);
        }
        else throw new IllegalArgumentException("The unit type does not exist");
    }

    public ArrayList<Unit> getMultipleUnits(String unitType, int numberOfUnits, String name, int health){
        ArrayList<Unit> units = new ArrayList<>();
        if(numberOfUnits<=0) throw new IllegalArgumentException("Number of Units must be above 0");
        for(int n = 0; n < numberOfUnits; n++){
            units.add(getUnit(unitType,name,health));
        }

        return units;
    }
}