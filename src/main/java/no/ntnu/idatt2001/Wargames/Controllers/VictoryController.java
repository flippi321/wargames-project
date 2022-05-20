package no.ntnu.idatt2001.Wargames.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import no.ntnu.idatt2001.Wargames.Army.Army;
import no.ntnu.idatt2001.Wargames.Battle.Battle;
import no.ntnu.idatt2001.Wargames.Units.UnitFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class VictoryController(Army army_one, Army army_two) implements Initializable {
    Army army1 = new Army(army_one);
    Army army2 = new Army(army_two);
    @FXML
    private TextField winnerKills;
    @FXML
    private TextField winnerName;
    @FXML
    private TextField winnerLosses;
    @FXML
    private Button seeLog;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
