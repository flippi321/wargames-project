package no.ntnu.idatt2001.Wargames.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import no.ntnu.idatt2001.Wargames.Army.Army;
import no.ntnu.idatt2001.Wargames.Battle.Battle;
import no.ntnu.idatt2001.Wargames.Battle.Terrain;
import no.ntnu.idatt2001.Wargames.Units.InfantryUnit;
import no.ntnu.idatt2001.Wargames.Units.UnitFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private Army Army1;
    private Army Army2;
    private Battle battle;
    private UnitFactory unitFactory;

    @FXML
    private TextField army2Commander;
    @FXML
    private TextField army2Ranged;
    @FXML
    private TextField army1Name;
    @FXML
    private TextField army2Infantry;
    @FXML
    private TextField army1Cavalry;
    @FXML
    private TextField army2Cavalry;
    @FXML
    private TextField army2Value;
    @FXML
    private TextField army1Value;
    @FXML
    private TextField army2Name;
    @FXML
    private TextField army1Infantry;
    @FXML
    private TextField army1Ranged;
    @FXML
    private TextField army1Commander;

    @FXML
    private int Army1Value(ActionEvent actionEvent){
        return Integer.parseInt(army1Infantry.getText()) + Integer.parseInt(army1Cavalry.getText()) +
                Integer.parseInt(army1Ranged.getText()) + Integer.parseInt(army1Commander.getText());
    }

    private int valueOf(TextField text){
        return Integer.parseInt(army1Infantry.getText());
    }

    private Army generateArmy(String name, int infantry, int cavalry, int ranged, int commander){
        unitFactory = new UnitFactory();
        Army army = new Army(name);
        if(infantry>0){
            army.addAll(unitFactory.getMultipleUnits("InfantryUnit", infantry,
                    "Swordsman", 100));
        }
        if(cavalry>0){
            army.addAll(unitFactory.getMultipleUnits("CavalryUnit", cavalry,
                    "Knight", 150));
        }
        if(ranged>0){
            army.addAll(unitFactory.getMultipleUnits("CavalryUnit", ranged,
                    "Archer", 75));
        }
        if(commander>0){
            army.addAll(unitFactory.getMultipleUnits("CavalryUnit", commander,
                    "General", 500));
        }
        return army;
    }

    private Terrain decideTerrain(){
        return Terrain.FOREST;
    }

    @FXML
    private void Simulate(ActionEvent actionEvent) {
        Army1 = generateArmy(army1Name.getText(), valueOf(army1Infantry), valueOf(army1Cavalry), valueOf(army1Ranged), valueOf(army1Commander));
        Army2 = generateArmy(army2Name.getText(), valueOf(army2Infantry), valueOf(army2Cavalry), valueOf(army2Ranged), valueOf(army2Commander));
        battle = new Battle(Army1, Army2, decideTerrain());
        System.out.println(battle.simulate().getName());
        //TODO
        // Add multiple terrains
        // Add pop up for who wins
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}