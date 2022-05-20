package no.ntnu.idatt2001.Wargames.Controllers;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import no.ntnu.idatt2001.Wargames.Army.Army;
import no.ntnu.idatt2001.Wargames.Battle.Battle;
import no.ntnu.idatt2001.Wargames.Battle.Terrain;
import no.ntnu.idatt2001.Wargames.Battle.Weather;
import no.ntnu.idatt2001.Wargames.Units.InfantryUnit;
import no.ntnu.idatt2001.Wargames.Units.UnitFactory;
import no.ntnu.idatt2001.Wargames.Units.UnitType;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    private Army Army1;
    private Army Army2;
    private Battle battle;
    private UnitFactory unitFactory;
    private final String[] TERRAINS = {"Forest", "Hill", "Plains"};
    private final String[] WEATHERS = {"Sunny", "Rainstorm", "Blizzard", "Heavy Fog"};

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
    private ChoiceBox<String> weather;
    @FXML
    private ChoiceBox<String> terrain;
    @FXML
    private Text errorMessage;
    @FXML
    private ImageView mainImage;

    @Deprecated
    private int Army1Value(ActionEvent actionEvent) {
        return Integer.parseInt(army1Infantry.getText()) + Integer.parseInt(army1Cavalry.getText()) +
                Integer.parseInt(army1Ranged.getText()) + Integer.parseInt(army1Commander.getText());
    }

    private int valueOf(TextField text) {
        return Integer.parseInt(text.getText());
    }

    private Army generateArmy(String name, int infantry, int cavalry, int ranged, int commander) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Army must have a valid name");
        }

        Army army = new Army(name);
        unitFactory = new UnitFactory();
        army.setUnits(new ArrayList<>());
        if(infantry < 0 | cavalry < 0 | ranged < 0 | commander < 0){
            throw new IllegalArgumentException("Cannot use negative values in an army");
        }
        if (infantry > 0) {
            army.addAll(unitFactory.getMultipleUnits(UnitType.INFANTRY, infantry,
                    "Swordsman", 100));
        }
        if (cavalry > 0) {
            army.addAll(unitFactory.getMultipleUnits(UnitType.CAVALRY, cavalry,
                    "Knight", 150));
        }
        if (ranged > 0) {
            army.addAll(unitFactory.getMultipleUnits(UnitType.RANGED, ranged,
                    "Archer", 75));
        }
        if (commander > 0) {
            army.addAll(unitFactory.getMultipleUnits(UnitType.COMMANDER, commander,
                    "General", 500));
        }
        return army;
    }

    private Terrain decideTerrain() {
        return Terrain.FOREST;
    }

    @FXML
    private void Simulate(ActionEvent actionEvent) {
        errorMessage.setText("");
        try {
            Army1 = generateArmy(army1Name.getText(), valueOf(army1Infantry), valueOf(army1Cavalry), valueOf(army1Ranged),
                    valueOf(army1Commander));
            Army2 = generateArmy(army2Name.getText(), valueOf(army2Infantry), valueOf(army2Cavalry), valueOf(army2Ranged),
                    valueOf(army2Commander));
            battle = new Battle(Army1, Army2, decideTerrain());
            System.out.println(battle.simulate().getName());
        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
            return;
        }

        //Update units
        updateArmy1();
        updateArmy2();
        //TODO
        // Add multiple terrains
        // Add pop up for who wins
    }

    @FXML
    public boolean loadArmy2(ActionEvent actionEvent) {
        errorMessage.setText("");
        if (army2Name.getText().isBlank()) {
            errorMessage.setText("Army name is empty");
            return false;
        }

        // If there is an army with the right name saved, it will be loaded automatically
        Army2 = new Army(army2Name.getText());
        try {
            if (Army2.loadArmy()) {
                return updateArmy2();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // If not, you must find it manually
        // TODO Add manual file acquisition
        return false;
    }

    @FXML
    public boolean saveArmy2(ActionEvent actionEvent) {
        errorMessage.setText("");
        if (army2Name.getText().isBlank()) {
            errorMessage.setText("Army name is empty");
            return false;
        }

        // Will try to Save army
        Army2 = new Army(army2Name.getText());
        try {
            Army2 = generateArmy(army2Name.getText(), valueOf(army2Infantry), valueOf(army2Cavalry), valueOf(army2Ranged),
                    valueOf(army2Commander));
            return Army2.saveArmy();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //If nothing above works
        return false;
    }

    @FXML
    public boolean loadArmy1(ActionEvent actionEvent) {
        errorMessage.setText("");
        if (army1Name.getText().isBlank()) {
            errorMessage.setText("Army name is empty");
            return false;
        }

        // If there is an army with the right name saved, it will be loaded automatically
        Army1 = new Army(army1Name.getText());
        if (Army1.loadArmy()) {
            return updateArmy1();
        }

        // If not, you must find it manually
        // TODO Add manual file acquisition
        return false;
    }

    @FXML
    public boolean saveArmy1(ActionEvent actionEvent) {
        errorMessage.setText("");
        if (army1Name.getText().isBlank()) {
            errorMessage.setText("Army name is empty");
            return false;
        }

        // Will try to Save army
        try {
            Army1 = generateArmy(army1Name.getText(), valueOf(army1Infantry), valueOf(army1Cavalry), valueOf(army1Ranged),
                    valueOf(army1Commander));
            return Army1.saveArmy();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //If nothing above works
        return false;
    }

    private boolean updateArmy1() {
        try {
            army1Name.setText(Army1.getName());
            army1Cavalry.setText(String.valueOf(Army1.getCavalryUnits().size()));
            army1Infantry.setText(String.valueOf(Army1.getInfantryUnits().size()));
            army1Ranged.setText(String.valueOf(Army1.getRangedUnits().size()));
            army1Commander.setText(String.valueOf(Army1.getCommanderUnits().size()));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean updateArmy2() {
        try {
            army2Name.setText(Army2.getName());
            army2Cavalry.setText(String.valueOf(Army2.getCavalryUnits().size()));
            army2Infantry.setText(String.valueOf(Army2.getInfantryUnits().size()));
            army2Ranged.setText(String.valueOf(Army2.getRangedUnits().size()));
            army2Commander.setText(String.valueOf(Army2.getCommanderUnits().size()));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Method for changing the image on the applicaiton main page
     * The image depends on the weather and terrain chosen by the user
     * The default values are Sunny and Forest.
     */
    private void changeImage(){
        String imageLink = "file:src/main/resources/Images/";

        //Decide Weather
        switch (weather.getValue()) {
            default -> {
                imageLink += "Sunny ";
            }
            case "Rainstorm" -> {
                imageLink += "Rainy ";
            }
            case "Heavy Fog" -> {
                imageLink += "Foggy ";
            }
            case "Blizzard" -> {
                imageLink += "Snowy ";
            }
        }

        //Decide terrain
        switch (terrain.getValue()) {
            default -> {
                imageLink += "Forest.jpg";
            }
            case "Hill" -> {
                imageLink += "Hills.jpg";
            }
            case "Plains" -> {
                imageLink += "Plains.jpg";
            }
        }

        Image loadedImage = new Image(imageLink, 270, 0, true, true);
        mainImage.setImage(loadedImage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        terrain.getItems().addAll(TERRAINS);
        weather.getItems().addAll(WEATHERS);
        terrain.setValue(TERRAINS[0]);
        weather.setValue(WEATHERS[0]);
        changeImage();
    }

    //TODO
    // Function to initiate changeImage() when chosen either Weather or Terrain
}