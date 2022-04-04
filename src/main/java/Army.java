import java.io.*;
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

    /**
     * Method to get all Infantry Units in the army
     * @return a list of all InfantryUnits
     */
    public List<Unit> getInfantryUnits(){
        return units.stream().filter(unit -> unit.getClass().equals(InfantryUnit.class)).toList();
    }

    /**
     * Method to get all Cavalry Units in the army
     * Will not return CommanderUnits
     * @return a list of all CavalryUnits
     */
    public List<Unit> getCavalryUnits(){
        return units.stream().filter(unit -> unit.getClass().equals(CavalryUnit.class)).toList();
    }

    /**
     * Method to get all Commander Units in the army
     * @return a list of all CommanderUnits
     */
    public List<Unit> getCommanderUnits(){
        return units.stream().filter(unit -> unit.getClass().equals(CommanderUnit.class)).toList();
    }

    /**
     * Method to get all Ranged Units in the army
     * @return a list of all RangedUnits
     */
    public List<Unit> getRangedUnits(){
        return units.stream().filter(unit -> unit.getClass().equals(RangedUnit.class)).toList();
    }

    /**
     * Method to change name of the army
     * @param name that the army should be called
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to change all units in the army
     * @param units the units that the army should compose of
     */
    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    /**
     * Method to test and correct a file location
     * @param fileLocation the file location, may only be the name of the file or the full location and type
     * @return a sorted and corrected file location, type only using '/'
     */
    private String testFileLocation(String fileLocation){
        String fixedFileLocation = fileLocation;
        //Making sure the location ends with .csv
        if (!fixedFileLocation.endsWith(".csv")){
            fixedFileLocation = (fixedFileLocation + ".csv");
        }
        //Making sure the file ends up in the resources folder
        if (!fixedFileLocation.startsWith("src/main/resources/")){
            fixedFileLocation = ("src/main/resources/"  + fixedFileLocation);
        }
        //Using '/' instead of '\' so that the application can be used on Linux and MacOS
        if(fixedFileLocation.contains("\\")){
            throw new IllegalArgumentException("You used \\ when / was applicable, please change this");
        }
        return fixedFileLocation;
    }

    /**
     * Method to load an army from a file location
     * Will be called upon by an army to change its unit list and name to match the army from the file
     * @param fileLocation the file location, may only be the name of the file or the full location and type
     */
    public void loadArmy(String fileLocation){
        //Fixing potential errors with the location
        fileLocation = testFileLocation(fileLocation);

        try{
            FileInputStream fileInput = new FileInputStream(fileLocation);
            Scanner scanner = new Scanner(fileInput);
            List<Unit> newUnits = new ArrayList<>();

            //Removing current units and adding name
            name = scanner.nextLine();
            units.clear();

            //Adding saved units
            while(scanner.hasNextLine())
            {
                String thisLine = scanner.nextLine();
                String[] lineVariables = thisLine.split(",");
                if(lineVariables.length>1 && !thisLine.isBlank()){
                    // Making the unit
                    switch (lineVariables[0].trim()) {
                        case "InfantryUnit" -> {
                            InfantryUnit unit = new InfantryUnit(lineVariables[1], Integer.parseInt(lineVariables[2]));
                            newUnits.add(unit);
                        }
                        case "RangedUnit" -> {
                            RangedUnit unit = new RangedUnit(lineVariables[1], Integer.parseInt(lineVariables[2]));
                            newUnits.add(unit);
                        }
                        case "CavalryUnit" -> {
                            CavalryUnit unit = new CavalryUnit(lineVariables[1], Integer.parseInt(lineVariables[2]));
                            newUnits.add(unit);
                        }
                        case "CommanderUnit" -> {
                            CommanderUnit unit = new CommanderUnit(lineVariables[1], Integer.parseInt(lineVariables[2]));
                            newUnits.add(unit);
                        }
                        default -> throw new IllegalArgumentException("Unit must have a valid name");
                    }
                }
            }
            //Closing file and changing units in the Army file
            scanner.close();
            setUnits(newUnits);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to save an army as a CSV file
     * The army will be saved in the resources folder
     */
    public void saveArmy(){
        String fileLocation = testFileLocation(getName().trim());
        try{
            PrintWriter writer = new PrintWriter(fileLocation);
            writer.println(name);
            for (Unit unit : units){
                writer.println(String.format("%s,%s,%s", unit.getClass().getSimpleName(), unit.getName(), unit.getHealth()));
            }
            writer.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to remove a saved file
     * Used primarily for removing the test file after testing is done
     * @param fileLocation the location of the saved army
     */
    public void removeSavedArmy(String fileLocation){
        String fixedFileLocation = testFileLocation(fileLocation);
        File file = new File(fixedFileLocation);
        // Will try to remove file, if not possible, it will throw new Exception
        if (!file.delete()) {
            throw new IllegalArgumentException("Failed to delete the file");
        }
    }

    /**
     * Method used to represent an army's information in the form of a string
     * @return a string of information relating to the army
     */
    @Override
    public String toString() {
        return "This army is called " + name +
                " and has the units: " + units.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }
}