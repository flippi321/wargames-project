package no.ntnu.idatt2001.Wargames.Army;
import no.ntnu.idatt2001.Wargames.Units.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * FileHandler Class
 * @author  chribrev
 * @version 1.0
 */
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
        if (!fixedFileLocation.startsWith("src/main/resources/armyFiles/")){
            fixedFileLocation = ("src/main/resources/ArmyFiles/"  + fixedFileLocation);
        }

        //TODO: Add method where \ is replaced by /
        return fixedFileLocation;
    }

    /**
     * Method to generate an Army from a file location
     * @param armyName the name of the Army
     * @return an army matching the name
     * @throws IllegalArgumentException thrown if army name is empty
     * @throws FileNotFoundException thrown if a file matching the name is not found.
     */
    public Army loadArmyFromName(String armyName) throws IllegalArgumentException, FileNotFoundException {
        if (armyName.isBlank()){
            throw new IllegalArgumentException("File name cannot be empty");
        }

        //Making a fileLocation to the given name
        String fileLocation = testFileLocation(armyName);
        return loadArmyFromLocation(fileLocation);
    }

    /**
     * Method to generate an Army from a file location
     * @param fileLocation the location of the Army file
     * @return an army matching the location
     * @throws IllegalArgumentException thrown if army name is empty
     * @throws FileNotFoundException thrown if a file matching the name is not found
     */
    public Army loadArmyFromLocation(String fileLocation) throws IllegalArgumentException, FileNotFoundException {
        //Making a fileLocation to the given name
        if (fileLocation.isBlank()){
            throw new IllegalArgumentException("File name cannot be empty");
        }

        FileInputStream fileInput = new FileInputStream(fileLocation);
        Scanner scanner = new Scanner(fileInput);
        List<Unit> newUnits = new ArrayList<>();

        //Creating army with name
        String name = scanner.nextLine();

        //Adding saved units
        while(scanner.hasNextLine())
        {
            String thisLine = scanner.nextLine();
            String[] lineVariables = thisLine.split(",");
            if(lineVariables.length > 1 && !thisLine.isBlank()){
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
        //Closing file
        scanner.close();

        // Returning Army
        return new Army(name, newUnits);
    }

    /**
     * Method to save an army as a CSV file
     * The army will be saved in the resources/armyFiles folder
     * @param army that should be saved
     */
    public boolean saveArmy(Army army) throws FileNotFoundException {
        String name = army.getName();
        List<Unit> units = army.getAllUnits();
        String fileLocation = testFileLocation(name.trim());
        PrintWriter writer = new PrintWriter(fileLocation);
        writer.println(name);
        for (Unit unit : units){
            writer.println(String.format("%s,%s,%s", unit.getClass().getSimpleName(), unit.getName(), unit.getHealth()));
        }
        writer.close();
        return true;
    }

    /**
     * Method to remove a saved file
     * Used primarily for removing the test file after testing is done
     * @param fileLocation the location of the saved army
     */
    public boolean deleteArmy(String fileLocation) throws IllegalArgumentException {
        String fixedFileLocation = testFileLocation(fileLocation);
        File file = new File(fixedFileLocation);

        // Will try to remove file, if not possible, it will throw new Exception
        if (!file.delete()) {
            throw new IllegalArgumentException("Failed to delete the file");
        }
        return true;
    }
}
