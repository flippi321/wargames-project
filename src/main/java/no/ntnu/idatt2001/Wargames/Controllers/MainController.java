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

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private Battle battle;
    private UnitFactory unitFactory;
    private String log;
    FileHandler fileHandler;
    private final String[] TERRAINS = {"Forest", "Hill", "Plains"};
    private final String[] WEATHERS = {"Sunny", "Rainstorm", "Blizzard", "Heavy Fog"};
    private final String[] ARMYNAMES = {"Human", "Orkish", "Elvish", "Dwarven"};

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
    private Slider army2Quality;
    @FXML
    private Slider army1Quality;
    @FXML
    private ProgressBar armyComparison;
    @FXML
    private ChoiceBox<String> army1NameType;
    @FXML
    private ChoiceBox<String> army2NameType;

    private int valueOf(TextField text) {
        return Integer.parseInt(text.getText());
    }

    private Army generateArmy1(){
        try {
            return generateArmy(army1Name.getText(), valueOf(army1Infantry), valueOf(army1Cavalry), valueOf(army1Ranged),
                    valueOf(army1Commander), (int) Math.round(army1Quality.getValue()), army1NameType.getValue());
        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
            return null;
        }
    }

    private Army generateArmy2(){
        try {
            return generateArmy(army2Name.getText(), valueOf(army2Infantry), valueOf(army2Cavalry), valueOf(army2Ranged),
                    valueOf(army2Commander), (int) Math.round(army2Quality.getValue()), army2NameType.getValue());
        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
            return null;
        }
    }

    private Army generateArmy(String name, int infantry, int cavalry, int ranged, int commander, int quality,
                              String namingConvention) throws IllegalArgumentException{
        if (name.isBlank()) {
            throw new IllegalArgumentException("Army must have a valid name");
        }
        if(infantry < 0 | cavalry < 0 | ranged < 0 | commander < 0){
            throw new IllegalArgumentException("Cannot use negative values in an army");
        }
        if(quality < 0 | quality > 5){
            throw new IllegalArgumentException("Army Quality must be between 1 and 5");
        }
        Army army = new Army(name);
        unitFactory = new UnitFactory();
        army.setUnits(new ArrayList<>());
        String infantryName = "";
        String cavalryName = "";
        String rangedName = "";
        String commanderName = "";

        //Method to decide names
        switch (namingConvention){
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
            army.addAll(unitFactory.getMultipleUnits(UnitType.INFANTRY, infantry,
                    infantryName, 100*quality));
        }
        if (cavalry > 0) {
            army.addAll(unitFactory.getMultipleUnits(UnitType.CAVALRY, cavalry,
                    cavalryName, 150*quality));
        }
        if (ranged > 0) {
            army.addAll(unitFactory.getMultipleUnits(UnitType.RANGED, ranged,
                    rangedName, 75*quality));
        }
        if (commander > 0) {
            army.addAll(unitFactory.getMultipleUnits(UnitType.COMMANDER, commander,
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
            wargamesAdmin.setArmy1(generateArmy1());
            wargamesAdmin.setArmy2(generateArmy2());
            battle = new Battle(wargamesAdmin.getArmy1(), wargamesAdmin.getArmy2(), decideTerrain(), decideWeather());
            Army winner = battle.simulate();

            //Update values to reflect on the battle outcome
            wargamesAdmin.setWinnerArmy(winner);
            updateArmy1();
            updateArmy2();

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
            if (wargamesAdmin.getArmy2().loadArmy()) {
                return updateArmy1();
            }
        }

        // If not you must find it manually
        try {
            wargamesAdmin.setArmy2(getArmyFromFileChosen());
            return updateArmy2();
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
        wargamesAdmin.setArmy2(new Army(army2Name.getText()));
        try {
            wargamesAdmin.setArmy2(generateArmy2());
            return wargamesAdmin.getArmy2().saveArmy();
        } catch (Exception e) {
            errorMessage.setText((e.getMessage()));
        }

        //If nothing above works
        return false;
    }

    @FXML
    public boolean loadArmy1(ActionEvent actionEvent) {
        // If there is an army with the right name saved, it will be loaded automatically
        errorMessage.setText("");
        if (!army1Name.getText().isBlank()) {
            wargamesAdmin.setArmy1(new Army(army1Name.getText()));
            if (wargamesAdmin.getArmy1().loadArmy()) {
                return updateArmy1();
            }
        }

        // If not you must find it manually
        try {
            wargamesAdmin.setArmy1(getArmyFromFileChosen());
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
            wargamesAdmin.setArmy1(generateArmy1());
            return wargamesAdmin.getArmy1().saveArmy();
        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
        }

        //If nothing above works
        return false;
    }

    private boolean updateArmy1() {
        try {
            army1Name.setText(wargamesAdmin.getArmy1().getName());
            army1Cavalry.setText(String.valueOf(wargamesAdmin.getArmy1().getCavalryUnits().size()));
            army1Infantry.setText(String.valueOf(wargamesAdmin.getArmy1().getInfantryUnits().size()));
            army1Ranged.setText(String.valueOf(wargamesAdmin.getArmy1().getRangedUnits().size()));
            army1Commander.setText(String.valueOf(wargamesAdmin.getArmy1().getCommanderUnits().size()));
            return true;
        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
            return false;
        }
    }

    private boolean updateArmy2() {
        try {
            army2Name.setText(wargamesAdmin.getArmy2().getName());
            army2Cavalry.setText(String.valueOf(wargamesAdmin.getArmy2().getCavalryUnits().size()));
            army2Infantry.setText(String.valueOf(wargamesAdmin.getArmy2().getInfantryUnits().size()));
            army2Ranged.setText(String.valueOf(wargamesAdmin.getArmy2().getRangedUnits().size()));
            army2Commander.setText(String.valueOf(wargamesAdmin.getArmy2().getCommanderUnits().size()));
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

        Image loadedImage = new Image(imageLink, 350, 0, true, true);
        mainImage.setImage(loadedImage);
    }

    private void viewArmyUnit(Army army){
        JTextArea armyInfo = new JTextArea(army.toString());
        JScrollPane scrollPane = new JScrollPane(armyInfo);
        armyInfo.setLineWrap(true);
        armyInfo.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension( 500, 500 ));
        JOptionPane.showMessageDialog(null, scrollPane, (army.getName() + ":"),
                JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    public void viewArmy1Units(ActionEvent actionEvent) {
        try {
            // Army will be updated if not already
            if(wargamesAdmin.getArmy1().getAllUnits().size()==0){
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
            // Army will be updated if not already
            if(wargamesAdmin.getArmy2().getAllUnits().size()==0){
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
    public void updateSizeComparisonButton(ActionEvent actionEvent) {
        try {
            wargamesAdmin.setArmy1(generateArmy1());
            wargamesAdmin.setArmy2(generateArmy2());
            if (wargamesAdmin.getArmy1()!=null & wargamesAdmin.getArmy2()!=null){
                double army1Size = wargamesAdmin.getArmy1().getAllUnits().size();
                double army2Size = wargamesAdmin.getArmy2().getAllUnits().size();
                if(army1Size<=0 & army2Size <= 0){
                    armyComparison.setProgress(0.5);
                } else {
                    armyComparison.setProgress(army1Size/(army1Size+army2Size));
                }
            }
            else{
                throw new IllegalArgumentException("Armies can't be empty");
            }
        } catch (Exception e){
            errorMessage.setText(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wargamesAdmin.setArmy1(new Army("Army 1"));
        wargamesAdmin.setArmy2(new Army("Army 2"));

        //TODO FIX:
        army1Quality = new Slider();
        army2Quality = new Slider();
        army1Quality.setValue(1);
        army2Quality.setValue(1);


        terrain.getItems().addAll(TERRAINS);
        weather.getItems().addAll(WEATHERS);
        army1NameType.getItems().addAll(ARMYNAMES);
        army2NameType.getItems().addAll(ARMYNAMES);
        terrain.setValue(TERRAINS[0]);
        weather.setValue(WEATHERS[0]);
        army1NameType.setValue(ARMYNAMES[0]);
        army2NameType.setValue(ARMYNAMES[0]);

        //TODO CHECK IF NEEDED
        changeImage();
    }

    @FXML
    public void clearArmy2(ActionEvent actionEvent) {
        wargamesAdmin.setArmy2(new Army("Army 1"));
        updateArmy2();
    }

    @FXML
    public void clearArmy1(ActionEvent actionEvent) {
        wargamesAdmin.setArmy1(new Army("Army 1"));
        updateArmy1();
    }
}