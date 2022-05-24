package no.ntnu.idatt2001.Wargames.Controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import no.ntnu.idatt2001.Wargames.Army.Army;
import no.ntnu.idatt2001.Wargames.Army.FileHandler;
import no.ntnu.idatt2001.Wargames.Battle.Battle;
import no.ntnu.idatt2001.Wargames.Battle.Terrain;
import no.ntnu.idatt2001.Wargames.Battle.Weather;
import no.ntnu.idatt2001.Wargames.Units.UnitFactory;
import no.ntnu.idatt2001.Wargames.Units.UnitType;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * MainController Class
 * @author  chribrev
 * @version 1.0
 */
public class MainController implements Initializable {
    FileHandler fileHandler = new FileHandler();
    private final String[] TERRAINS = {"Forest", "Hill", "Plains"};
    private final String[] WEATHERS = {"Sunny", "Rainstorm", "Blizzard", "Heavy Fog"};
    private final String[] ARMYNAMES = {"Human", "Orkish", "Elvish", "Dwarven"};
    private boolean army1Defined;
    private boolean army2Defined;

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
    private Slider army2Quality = new Slider(1, 5, 0);
    @FXML
    private Slider army1Quality = new Slider(1, 5, 0);
    @FXML
    private ProgressBar armyComparison;
    @FXML
    private ChoiceBox<String> army1NameType;
    @FXML
    private ChoiceBox<String> army2NameType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Play backround music
        try {
            AudioClip theme = new AudioClip(getClass().getResource("/music/WargamesTheme.mp3").toExternalForm());
            theme.setVolume(0.5);
            theme.setCycleCount(AudioClip.INDEFINITE);
            theme.play();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        //Generate armies
        wargamesAdmin.setArmy1(new Army("Army 1"));
        wargamesAdmin.setArmy2(new Army("Army 2"));
        army1Defined = false;
        army2Defined = false;

        terrain.getItems().addAll(TERRAINS);
        weather.getItems().addAll(WEATHERS);
        army1NameType.getItems().addAll(ARMYNAMES);
        army2NameType.getItems().addAll(ARMYNAMES);
        terrain.setValue(TERRAINS[0]);
        weather.setValue(WEATHERS[0]);
        army1NameType.setValue(ARMYNAMES[0]);
        army2NameType.setValue(ARMYNAMES[0]);
        changeImage();
    }

    private int valueOf(TextField text) {
        return Integer.parseInt(text.getText());
    }

    private Army generateArmy1() throws IllegalArgumentException{
        return generateArmy(army1Name, army1Infantry, army1Cavalry, army1Ranged,
                army1Commander, army1Quality, army1NameType);
    }

    private Army generateArmy2() throws IllegalArgumentException{
        return generateArmy(army2Name, army2Infantry, army2Cavalry, army2Ranged,
                army2Commander, army2Quality, army2NameType);
    }

    private Army generateArmy(TextField armyName, TextField infantryValue, TextField cavalryValue, TextField rangedValue,
                              TextField commanderValue, Slider armyQuality, ChoiceBox<String> namingConvention)
            throws IllegalArgumentException{
        String name = armyName.getText();
        int infantry = valueOf(infantryValue);
        int ranged = valueOf(rangedValue);
        int cavalry = valueOf(cavalryValue);
        int commander = valueOf(commanderValue);
        int quality = (int) Math.round(armyQuality.getValue());
        String nameType = namingConvention.getValue();

        if (name.isBlank()) {
            throw new IllegalArgumentException("Army must have a valid name");
        }
        if(infantry < 0 | cavalry < 0 | ranged < 0 | commander < 0){
            throw new IllegalArgumentException("Cannot use negative values in an army");
        }
        if(quality < 1 | quality > 5){
            throw new IllegalArgumentException("Army Quality must be between 1 and 5");
        }
        Army army = new Army(name);
        UnitFactory unitFactory = new UnitFactory();
        army.setUnits(new ArrayList<>());
        String infantryName;
        String cavalryName;
        String rangedName;
        String commanderName;

        //Method to decide names
        switch (nameType){
            default -> {
                infantryName = "Imperial Swordsman";
                cavalryName = "Mounted Knight";
                rangedName = "Imperial Archer";
                commanderName = "Imperial General";
            }
            case "Orkish" -> {
                infantryName = "Ork Grunt";
                cavalryName = "Mounted Ork";
                rangedName = "Goblin Archer";
                commanderName = "Da Warboss";
            }
            case "Elvish" -> {
                infantryName = "Elven Spearman";
                cavalryName = "Golden Steed";
                rangedName = "Elven Sharpshooter";
                commanderName = "Elven Prince";
            }
            case "Dwarven" -> {
                infantryName = "Angry Blacksmith";
                cavalryName = "Dwarven rider";
                rangedName = "Dwarwen musketeer";
                commanderName = "Dwarf General";
            }
        }

        if (infantry > 0) {
            army.addAll(unitFactory.getMultipleUnits(UnitType.INFANTRY.name(), infantry,
                    infantryName, 100*quality));
        }
        if (cavalry > 0) {
            army.addAll(unitFactory.getMultipleUnits(UnitType.CAVALRY.name(), cavalry,
                    cavalryName, 125*quality));
        }
        if (ranged > 0) {
            army.addAll(unitFactory.getMultipleUnits(UnitType.RANGED.name(), ranged,
                    rangedName, 75*quality));
        }
        if (commander > 0) {
            army.addAll(unitFactory.getMultipleUnits(UnitType.COMMANDER.name(), commander,
                    commanderName, 300*quality));
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

    private Weather decideWeather() {
        switch (weather.getValue()) {
            default -> {
                return Weather.Sunny;
            }
            case "Blizzard" -> {
                return Weather.Blizzard;
            }
            case "Rainstorm" -> {
                return Weather.Rainstorm;
            }
            case "Heavy Fog" -> {
                return Weather.Heavy_Fog;
            }
        }
    }

    @FXML
    private void Simulate(ActionEvent actionEvent) {
        errorMessage.setText("");
        try {
            if(!army1Defined){
                wargamesAdmin.setArmy1(generateArmy1());
                wargamesAdmin.setArmy1(generateArmy1());
            }
            if(!army2Defined){
                wargamesAdmin.setArmy2(generateArmy2());
                wargamesAdmin.setArmy2(generateArmy2());
            }
            if(wargamesAdmin.getArmy2().getAllUnits().size() <= 0 | wargamesAdmin.getArmy2().getAllUnits().size() <= 0){
                throw new IllegalArgumentException("Armies must have units to fight");
            }
            // Save army stated before battle
            wargamesAdmin.setPreBattleArmy1(wargamesAdmin.getArmy1());
            wargamesAdmin.setPreBattleArmy2(wargamesAdmin.getArmy2());

            //Simulate Battle
            wargamesAdmin.setBattle(new Battle(wargamesAdmin.getArmy1(), wargamesAdmin.getArmy2(), decideTerrain(), decideWeather()));
            wargamesAdmin.setWinnerArmy(wargamesAdmin.getBattle().simulate());
            wargamesAdmin.setBattleLog(wargamesAdmin.getBattle().getLog());

            //Open Victory Screen
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Winner-Page.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Battle Results");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
        }
    }

    private Army getArmyFromFileChosen() throws Exception {
        fileHandler = new FileHandler();
        FileChooser fileChooser = new FileChooser();
        File chosenFile = fileChooser.showOpenDialog(null);
        String path = chosenFile.getCanonicalPath();
        return fileHandler.loadArmyFromLocation(path);
    }

    @FXML
    public boolean saveArmy1(ActionEvent actionEvent) throws FileNotFoundException {
        errorMessage.setText("");
        if (army1Name.getText().isBlank()) {
            errorMessage.setText("Army name is empty");
            return false;
        }

        // Will try to Save army
        if(!army1Defined){
            wargamesAdmin.setArmy1(generateArmy1());
            army1Defined = true;
        }

        return fileHandler.saveArmy(wargamesAdmin.getArmy1());
    }

    @FXML
    public boolean saveArmy2(ActionEvent actionEvent) throws IllegalArgumentException, FileNotFoundException {
        errorMessage.setText("");
        if (army2Name.getText().isBlank()) {
            errorMessage.setText("Army name is empty");
            return false;
        }

        // Will try to Save army
        if(!army2Defined){
            wargamesAdmin.setArmy2(generateArmy2());
            army2Defined = true;
        }

        return fileHandler.saveArmy(wargamesAdmin.getArmy2());
    }

    @FXML
    public boolean loadArmy1(ActionEvent actionEvent) throws Exception {
        try {
            wargamesAdmin.getArmy1().setName(army1Name.getText());
            errorMessage.setText("");

            // If there is an army with the right name saved, it will be loaded automatically
            try {
                wargamesAdmin.setArmy1(fileHandler.loadArmyFromName(army1Name.getText()));
                wargamesAdmin.setPreBattleArmy1(wargamesAdmin.getArmy1());
                return updateArmy1();
            } // If no army is found matching the result, it must be found manually
            catch (Exception e) {
                wargamesAdmin.setArmy1(getArmyFromFileChosen());
                wargamesAdmin.setPreBattleArmy1(wargamesAdmin.getArmy1());
                return updateArmy1();
            }
        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
            return false;
        }
    }

    @FXML
    public boolean loadArmy2(ActionEvent actionEvent) throws Exception {
        try {
            wargamesAdmin.getArmy2().setName(army2Name.getText());
            errorMessage.setText("");

            // If there is an army with the right name saved, it will be loaded automatically
            try {
                wargamesAdmin.setArmy2(fileHandler.loadArmyFromName(army2Name.getText()));
                wargamesAdmin.setPreBattleArmy2(wargamesAdmin.getArmy2());
                return updateArmy2();
            } // If no army is found matching the result, it must be found manually
            catch (Exception e) {
                wargamesAdmin.setArmy2(getArmyFromFileChosen());
                wargamesAdmin.setPreBattleArmy2(wargamesAdmin.getArmy2());
                return updateArmy2();
            }
        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
            return false;
        }
    }

    private boolean updateArmy1() throws IllegalArgumentException {
        Army army = wargamesAdmin.getPreBattleArmy1();
        army1Name.setText(wargamesAdmin.getArmy1().getName());
        army1Cavalry.setText(String.valueOf(army.getCavalryUnits().size()));
        army1Infantry.setText(String.valueOf(army.getInfantryUnits().size()));
        army1Ranged.setText(String.valueOf(army.getRangedUnits().size()));
        army1Commander.setText(String.valueOf(army.getCommanderUnits().size()));
        return true;
    }

    private boolean updateArmy2() throws IllegalArgumentException {
        Army army = wargamesAdmin.getPreBattleArmy2();
        army2Name.setText(army.getName());
        army2Cavalry.setText(String.valueOf(army.getCavalryUnits().size()));
        army2Infantry.setText(String.valueOf(army.getInfantryUnits().size()));
        army2Ranged.setText(String.valueOf(army.getRangedUnits().size()));
        army2Commander.setText(String.valueOf(army.getCommanderUnits().size()));
        return true;
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

        Image loadedImage = new Image(imageLink, 400, 0, true, true);
        mainImage.setImage(loadedImage);
    }

    @FXML
    public void viewArmy1Units(ActionEvent actionEvent) {
        try {
            // If the army is not defined or the units have been changed since loading, the army must be generated
            if(!wargamesAdmin.getArmy1().hasUnits() |
                    wargamesAdmin.getArmy1().getAllUnits().size() != generateArmy1().getAllUnits().size()){
                wargamesAdmin.setArmy1(generateArmy1());
            }


            //Open Units Screen
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Army1_Units.fxml"));
            Stage stage = new Stage();
            stage.setTitle(wargamesAdmin.getArmy1().getName() + " Units");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e){
            errorMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void viewArmy2Units(ActionEvent actionEvent) {
        try {
            // If the army is not defined or the units have been changed since loading, the army must be generated
            if(!wargamesAdmin.getArmy2().hasUnits() |
                    wargamesAdmin.getArmy2().getAllUnits().size() != generateArmy2().getAllUnits().size()){
                wargamesAdmin.setArmy2(generateArmy2());
            }

            //Open Units Screen
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Army2_Units.fxml"));
            Stage stage = new Stage();
            stage.setTitle(wargamesAdmin.getArmy2().getName() + " Units");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e){
            errorMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void updateValuesButton(Event event) {
        changeImage();
    }

    @FXML
    public void clearArmy2(ActionEvent actionEvent) {
        wargamesAdmin.setArmy2(new Army("Army 1"));
        updateArmy2();
        army1Defined = false;
    }

    @FXML
    public void clearArmy1(ActionEvent actionEvent) {
        wargamesAdmin.setArmy1(new Army("Army 1"));
        updateArmy1();
        army2Defined = false;
    }

    @FXML
    public void updateSizeComparisonButton(ActionEvent actionEvent) {
        try {
            if(!army1Defined){
                wargamesAdmin.setArmy1(generateArmy1());
            }
            if(!army2Defined){
                wargamesAdmin.setArmy2(generateArmy2());
            }
            if (wargamesAdmin.getArmy1()!=null & wargamesAdmin.getArmy2()!=null){
                double army1Value = wargamesAdmin.getArmy1().getArmyValue();
                double army2Value = wargamesAdmin.getArmy2().getArmyValue();
                if(army1Value<=0 & army2Value <= 0){
                    armyComparison.setProgress(0.5);
                } else {
                    armyComparison.setProgress(army1Value/(army1Value+army2Value));
                }
            }
            else{
                throw new IllegalArgumentException("Armies can't be empty");
            }
        } catch (Exception e){
            errorMessage.setText(e.getMessage());
        }
    }
}