import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Army {
    private String name;
    private List<Unit> units;

    public Army(String name) {
        this.name = name;
        this.units = null;
    }

    public Army(String name, List<Unit> units) {
        this.name = name;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void add(Unit unit){
        units.add(unit);
    }

    public void addAll(List<Unit> units){
        this.units.addAll(units);
    }

    public void remove(Unit unit){
        int i = 0;
        boolean unitRemoved = false;
        while (i<units.size() && !unitRemoved){
            if (unit.equals(units.get(i))){
                units.remove(i);
                unitRemoved = true;
            }
            i++;
        }
    }

    public boolean hasUnits(){
        return !units.isEmpty();
    }

    public List<Unit> getAllUnits(){
        return units;
    }

    public Unit getRandom(){
        Random random = new Random();
        int unitNumber = random.nextInt(units.size());
        return units.get(unitNumber);
    }

    @Override
    public String toString() {
        return "Army :" + name + ", with units: " + units;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return name.equals(army.name) && units.equals(army.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }
}
