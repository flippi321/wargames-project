import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    public FileHandler() {
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

    public Army loadArmy(String fileLocation){
        //Fixing potential errors with the location
        fileLocation = testFileLocation(fileLocation);

        try{
            FileInputStream fileInput = new FileInputStream(fileLocation);
            Scanner scanner = new Scanner(fileInput);
            List<Unit> newUnits = new ArrayList<>();

            //Creating army with name
            Army newArmy = new Army(scanner.nextLine());

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
            newArmy.setUnits(newUnits);

            // Returning Army
            return newArmy;
        } catch (IOException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Method to save an army as a CSV file
     * The army will be saved in the resources folder
     * @param name the name of the Army
     * @param units the Units in the Army
     */
    public void saveArmy(String name, List<Unit> units){
        String fileLocation = testFileLocation(name.trim());
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
    public void deleteArmy(String fileLocation){
        String fixedFileLocation = testFileLocation(fileLocation);
        File file = new File(fixedFileLocation);
        // Will try to remove file, if not possible, it will throw new Exception
        if (!file.delete()) {
            throw new IllegalArgumentException("Failed to delete the file");
        }
    }
}
