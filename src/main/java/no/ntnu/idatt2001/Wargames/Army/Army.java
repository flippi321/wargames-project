package no.ntnu.idatt2001.Wargames.Army;
import no.ntnu.idatt2001.Wargames.Units.*;
import java.util.*;

/**
 * Army Class
 * @author  chribrev
 * @version 1.0
 */
public class Army {
    private String name;
    private int losses;
    private int kills;
    private List<Unit> units;
    private FileHandler fileHandler;

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
        this.fileHandler = new FileHandler();
        losses = 0;
        kills = 0;
    }

    /**
     * Constructor for an object representing an army of units
     * @param name what the army is called
     * @param units the units that this army consists of
     */
    public Army(String name, List<Unit> units) {
        this.name = name;
        this.units = units;
        this.fileHandler = new FileHandler();
        losses = 0;
        kills = 0;
    }

    /**
     * Accessor method for name
     * @return the name of the army
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method for Losses
     * @return the losses of the army
     */
    public int getLosses() {
        return losses;
    }

    /**
     * Method for increasing losses value
     */
    public void addLoss() {
        losses++;
    }

    /**
     * Accessor method for kills
     * @return the kills of the army
     */
    public int getKills() {
        return kills;
    }

    /**
     * Method for increasing kill value
     */
    public void addKill() {
        kills++;
    }

    /**
     * Method for adding a new unit to the army
     * @param unit the unit which should be integrated into the army
     */
    public void add(Unit unit){
        units.add(unit);
    }

    /**
     * Method to add a list of new units to the army
     * @param units a list of units which should be integrated into the army
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
     * @return true if there are units in the Army, false if the Army's unit list is empty
     */
    public boolean hasUnits(){
        return !units.isEmpty();
    }

    /**
     * Method to acquire all units in the army
     * @return all units in the army as a list
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

    public double getArmyValue(){
        double armyValue = 0;
        for (Unit unit : getAllUnits()){
            armyValue += (unit.getHealth() * (unit.getArmour() + unit.getAttack()));
        }
        return armyValue;
    }

    /**
     * Method used to represent an army's information in the form of a string
     * @return a string of information relating to the army
     */
    @Override
    public String toString() {
        int i = 1;
        StringBuilder sb = new StringBuilder();
        ArrayList<Unit> newList = new ArrayList<>(getAllUnits());
        String startMessage = ("ARMY INFO:\n" + "This army is called " + name + "\nIt consists of " + units.size() +
                " units, divided into:\n" +
                "    -" + getInfantryUnits().size() + " infantry units\n" +
                "    -" + getRangedUnits().size() + " ranged units\n" +
                "    -" + getCavalryUnits().size() + " cavalry units\n" +
                "    -" + getCommanderUnits().size() + " commander units\n\n" +
                "ALL UNITS IN ARMY:\n");
        sb.append(startMessage);
        for (Unit unit : newList) {
            sb.append("\nUnit ").append(i).append("\n");
            sb.append(unit);
            i++;
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return Objects.equals(name, army.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }
}