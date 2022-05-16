package no.ntnu.idatt2001.Wargames.Controllers;
import no.ntnu.idatt2001.Wargames.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import no.ntnu.idatt2001.Wargames.Army.Army;
import no.ntnu.idatt2001.Wargames.Battle.Battle;
import no.ntnu.idatt2001.Wargames.Battle.Terrain;
import no.ntnu.idatt2001.Wargames.Units.InfantryUnit;
import no.ntnu.idatt2001.Wargames.Units.UnitFactory;

public class MainController {
    private Army Army1;
    private Army Army2;
    private Battle battle;

    //Army 1 values
    @FXML
    private TextField army1Name;
    @FXML
    private TextField army1Infantry;
    @FXML
    private TextField army1Cavalry;
    @FXML
    private TextField army1Ranged;
    @FXML
    private TextField army1Commander;

    //Army 2 values
    @FXML
    private TextField army2Name;
    @FXML
    private TextField army2Infantry;
    @FXML
    private TextField army2Cavalry;
    @FXML
    private TextField army2Ranged;
    @FXML
    private TextField army2Commander;

    @FXML
    protected void Simulate(ActionEvent actionEvent) {
        UnitFactory unitFactory = new UnitFactory();
        //Make Army 1
        Army1 = new Army(army1Name.getText());
        Army1.addAll(unitFactory.getMultipleUnits("InfantryUnit", Integer.parseInt(army1Infantry.getText()),
                "Swordsman", 100));
        Army1.addAll(unitFactory.getMultipleUnits("CavalryUnit", Integer.parseInt(army1Cavalry.getText()),
                "Knight", 100));
        Army1.addAll(unitFactory.getMultipleUnits("RangedUnit", Integer.parseInt(army1Ranged.getText()),
                "Archer", 100));
        Army1.addAll(unitFactory.getMultipleUnits("CommanderUnit", Integer.parseInt(army1Commander.getText()),
                "General", 100));

        //Make Army 2
        Army2 = new Army(army2Name.getText());
        Army2.addAll(unitFactory.getMultipleUnits("InfantryUnit", Integer.parseInt(army2Infantry.getText()),
                "Swordsman", 100));
        Army2.addAll(unitFactory.getMultipleUnits("CavalryUnit", Integer.parseInt(army2Cavalry.getText()),
                "Swordsman", 100));
        Army2.addAll(unitFactory.getMultipleUnits("RangedUnit", Integer.parseInt(army2Ranged.getText()),
                "Swordsman", 100));
        Army2.addAll(unitFactory.getMultipleUnits("CommanderUnit", Integer.parseInt(army2Commander.getText()),
                "Swordsman", 100));

        battle = new Battle(Army1, Army2, Terrain.FOREST);
        //TODO
        // Add multiple terrains
        // Add pop up for who wins

        System.out.println(battle.simulate().getName());
    }
}