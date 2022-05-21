package no.ntnu.idatt2001.Wargames.Controllers;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import no.ntnu.idatt2001.Wargames.Army.Army;
import no.ntnu.idatt2001.Wargames.Army.FileHandler;
import no.ntnu.idatt2001.Wargames.Battle.Battle;
import no.ntnu.idatt2001.Wargames.Battle.Terrain;
import no.ntnu.idatt2001.Wargames.Units.UnitFactory;
import no.ntnu.idatt2001.Wargames.Units.UnitType;

import java.io.File;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    private Army army1;
    private Army army2;
    private Battle battle;
    private UnitFactory unitFactory;
    private String log;
    FileHandler fileHandler;
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
    @FXML
    private TextField army2Quality;
    @FXML
    private TextField army1Quality;

    @Deprecated
    private int Army1Value(ActionEvent actionEvent) {
        return Integer.parseInt(army1Infantry.getText()) + Integer.parseInt(army1Cavalry.getText()) +
                Integer.parseInt(army1Ranged.getText()) + Integer.parseInt(army1Commander.getText());
    }

    private int valueOf(TextField text) {
        return Integer.parseInt(text.getText());
    }

    private Army generateArmy(String name, int infantry, int cavalry, int ranged, int commander, int quality) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Army must have a valid name");
        }

        Army army = new Army(name);
        unitFactory = new UnitFactory();
        army.setUnits(new ArrayList<>());
        if(infantry < 0 | cavalry < 0 | ranged < 0 | commander < 0){
            throw new IllegalArgumentException("Cannot use negative values in an army");
        }
        if(quality < 0 | quality > 5){
            throw new IllegalArgumentException("Army Quality must be between 1 and 5");
        }
        if (infantry > 0) {
            army.addAll(unitFactory.getMultipleUnits(UnitType.INFANTRY, infantry,
                    "Swordsman", 100*quality));
        }
        if (cavalry > 0) {
            army.addAll(unitFactory.getMultipleUnits(UnitType.CAVALRY, cavalry,
                    "Knight", 150*quality));
        }
        if (ranged > 0) {
            army.addAll(unitFactory.getMultipleUnits(UnitType.RANGED, ranged,
                    "Archer", 75*quality));
        }
        if (commander > 0) {
            army.addAll(unitFactory.getMultipleUnits(UnitType.COMMANDER, commander,
                    "General", 300*quality));
        }
        return army;
    }

    private Terrain decideTerrain() {
        switch (terrain.getValue()) {
            default -> {
                return Terrain.FOREST;
            }
            case "Hill" -> {
                return Terrain.HILL;
            }
            case "Plains" -> {
                return Terrain.PLAINS;
            }
        }
    }

    @FXML
    private void Simulate(ActionEvent actionEvent) {
        errorMessage.setText("");
        int quality1 = valueOf(army1Quality);
        int quality2 = valueOf(army2Quality);
        try {
            army1 = generateArmy(army1Name.getText(), valueOf(army1Infantry), valueOf(army1Cavalry), valueOf(army1Ranged),
                    valueOf(army1Commander), valueOf(army1Quality));
            army2 = generateArmy(army2Name.getText(), valueOf(army2Infantry), valueOf(army2Cavalry), valueOf(army2Ranged),
                    valueOf(army2Commander), valueOf(army2Quality));
            battle = new Battle(army1, army2, decideTerrain());
            Army winner = battle.simulate();


            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Victory-Screen.fxml"));
            /*
            //Open Victory Screen
            VictoryController victoryController = loader.getController();
            victoryController.setWinnerValues(winner.getName(), 0, 0);
            //TODO
            // Calculate kills and losses
            // Fix error here
            */

            //Open Screen
            Stage stage = new Stage();
            stage.setTitle("Battle Results");
            stage.setScene(new Scene(loader.load()));
            stage.show();

        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
            return;
        }

        //Update units
        updateArmy1();
        updateArmy2();
    }

    private Army getArmyFromFileChosen() throws Exception{
        fileHandler = new FileHandler();
        FileChooser fileChooser = new FileChooser();
        File chosenFile = fileChooser.showOpenDialog(null);
        String path = chosenFile.getName();
        return fileHandler.loadArmy(path);
    }

    @FXML
    public boolean loadArmy2(ActionEvent actionEvent) {
        // If there is an army with the right name saved, it will be loaded automatically
        errorMessage.setText("");
        if (!army2Name.getText().isBlank()) {
            army2 = new Army(army2Name.getText());
            if (army2.loadArmy()) {
                return updateArmy1();
            }
        }

        // If not you must find it manually
        try {
            army2 = getArmyFromFileChosen();
            updateArmy2();
            return updateArmy1();
        } catch (Exception e){
            errorMessage.setText(e.getMessage());
        }
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
        army2 = new Army(army2Name.getText());
        try {
            army2 = generateArmy(army2Name.getText(), valueOf(army2Infantry), valueOf(army2Cavalry), valueOf(army2Ranged),
                    valueOf(army2Commander), valueOf(army2Quality));
            return army2.saveArmy();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //If nothing above works
        return false;
    }

    @FXML
    public boolean loadArmy1(ActionEvent actionEvent) {
        // If there is an army with the right name saved, it will be loaded automatically
        errorMessage.setText("");
        if (!army1Name.getText().isBlank()) {
            army1 = new Army(army1Name.getText());
            if (army1.loadArmy()) {
                return updateArmy1();
            }
        }

        // If not you must find it manually
        try {
            army1 = getArmyFromFileChosen();
            updateArmy1();
            return updateArmy1();
        } catch (Exception e){
            errorMessage.setText(e.getMessage());
        }
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
            army1 = generateArmy(army1Name.getText(), valueOf(army1Infantry), valueOf(army1Cavalry), valueOf(army1Ranged),
                    valueOf(army1Commander), valueOf(army1Quality));
            return army1.saveArmy();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //If nothing above works
        return false;
    }

    private boolean updateArmy1() {
        try {
            army1Name.setText(army1.getName());
            army1Cavalry.setText(String.valueOf(army1.getCavalryUnits().size()));
            army1Infantry.setText(String.valueOf(army1.getInfantryUnits().size()));
            army1Ranged.setText(String.valueOf(army1.getRangedUnits().size()));
            army1Commander.setText(String.valueOf(army1.getCommanderUnits().size()));
            return true;
        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
            return false;
        }
    }

    private boolean updateArmy2() {
        try {
            army2Name.setText(army2.getName());
            army2Cavalry.setText(String.valueOf(army2.getCavalryUnits().size()));
            army2Infantry.setText(String.valueOf(army2.getInfantryUnits().size()));
            army2Ranged.setText(String.valueOf(army2.getRangedUnits().size()));
            army2Commander.setText(String.valueOf(army2.getCommanderUnits().size()));
            return true;
        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
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
    @FXML
    public void changeImage(Event event) {
        changeImage();
    }
}