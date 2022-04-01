import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Army {
    private String name;
    private List<Unit> units;

    /**
     * Constructor for an object representing an empty army
     * @param name what the army is called
     */
    public Army(String name) {
        if(name.isBlank()){
            throw new IllegalArgumentException("Name cannot be blank");
        }
        this.name = name;
        this.units = new ArrayList<>();
    }

    /**
     * Constructor for an object representing an army of units
     * @param name what the army is called
     * @param units the units that this army consists of
     */
    public Army(String name, List<Unit> units) {
        this.name = name;
        this.units = units;
    }

    /**
     * Method for acquiring the name of the army
     * @return the name of the army
     */
    public String getName() {
        return name;
    }

    /**
     * Method to add a new unit to the army
     * @param unit the unit which should be integrated in the army
     */
    public void add(Unit unit){
        units.add(unit);
    }

    /**
     * Method to add a list of new units to the army
     * @param units a list of units which should be integrated in the army
     */
    public void addAll(List<Unit> units){
        this.units.addAll(units);
    }

    /**
     * Method to remove a unit from the army
     * @param unit the unit who shall be removed from the army
     */
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

    /**
     * Method for checking if the army has any units
     * @return true if there are units, false if the army is empty
     */
    public boolean hasUnits(){
        return !units.isEmpty();
    }

    /**
     * Method to acquire all units in the army
     * @return all units in the army in a list
     */
    public List<Unit> getAllUnits(){
        return units;
    }

    /**
     * Method to acquire a random unit in the army
     * @return a random unit in the army
     */
    public Unit getRandom(){
        Random random = new Random();
        int unitNumber = random.nextInt(units.size());
        return units.get(unitNumber);
    }

    public List<Unit> getInfantryUnits(){
        return units.stream().filter(unit -> unit.getClass().equals(InfantryUnit.class)).toList();
    }

    public List<Unit> getCavalryUnits(){
        return units.stream().filter(unit -> unit.getClass().equals(CavalryUnit.class)).toList();
    }

    public List<Unit> getCommanderUnits(){
        return units.stream().filter(unit -> unit.getClass().equals(CommanderUnit.class)).toList();
    }

    public List<Unit> getRangedUnits(){
        return units.stream().filter(unit -> unit.getClass().equals(RangedUnit.class)).toList();
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public void loadArmy(String fileLocation){
        if (!fileLocation.endsWith(".csv")){
            fileLocation = (fileLocation + ".csv");
        }
        if(fileLocation.contains("\\")){
            throw new IllegalArgumentException("You used \\ when / was applicable, please change this");
        }

        try{
            FileInputStream fileInput = new FileInputStream(fileLocation);
            Scanner scanner = new Scanner(fileInput);
            Army army = new Army(scanner.nextLine());
            int line = 1;

            while(scanner.hasNextLine())
            {
                if(line>1 && !scanner.nextLine().isEmpty()){
                    String thisLine = scanner.nextLine();
                    String[] lineVariables = thisLine.split(", ");

                    // Making the unit
                    switch (lineVariables[0]) {
                        case "InfantryUnit" -> {
                            InfantryUnit unit = new InfantryUnit(lineVariables[1], Integer.parseInt(lineVariables[2]));
                            army.add(unit);
                        }
                        case "RangedUnit" -> {
                            RangedUnit unit = new RangedUnit(lineVariables[1], Integer.parseInt(lineVariables[2]));
                            army.add(unit);
                        }
                        case "CavalryUnit" -> {
                            CavalryUnit unit = new CavalryUnit(lineVariables[1], Integer.parseInt(lineVariables[2]));
                            army.add(unit);
                        }
                        case "CommanderUnit" -> {
                            CommanderUnit unit = new CommanderUnit(lineVariables[1], Integer.parseInt(lineVariables[2]));
                            army.add(unit);
                        }
                        default -> throw new IllegalArgumentException("Unit must have a valid name");
                    }
                }
                line++;
            }
            scanner.close();
            System.out.println(army.getInfantryUnits().toString());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void saveArmy(){
        String fileLocation = getName();

        if (!fileLocation.endsWith(".csv")){
            fileLocation = (fileLocation + ".csv");
        }
        if (!fileLocation.startsWith("src/main/resources/")){
            fileLocation = ("src/main/resources/"  + fileLocation);
        }
        if(fileLocation.contains("\\")){
            throw new IllegalArgumentException("You used \\ when / was applicable, please change this");
        }

        try{
            PrintWriter writer = new PrintWriter(fileLocation);
            writer.println(name);
            for (Unit unit : units){
                writer.println(String.format("%s, %s, %s", unit.getClass().getSimpleName(), unit.getName(), unit.getHealth()));
            }
            writer.close();
            System.out.println("Saved File");
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method used to represent an army's information in the form of a string
     * @return a string of information relating to the army
     */
    @Override
    public String toString() {
        return "This army is called " + name +
                " and has the units: " + units;
    }

    /**
     * Method for checking if to classes share the same name, which is the identifying factor of an army
     * @param o the class who the army is compared to
     * @return true if the armies share the same name, false if not they don't
     */
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

    /**

     */

    public static void main(String[] args){
        Army army = new Army("TestArmy2");
        army.add(new CommanderUnit("Geralt", 5000));
        for(int i = 0; i < 20; i++){
            army.add(new InfantryUnit("Swordsmen", 150));
        }
        army.saveArmy();
    }
}