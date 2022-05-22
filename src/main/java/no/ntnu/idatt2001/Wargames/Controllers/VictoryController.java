package no.ntnu.idatt2001.Wargames.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class VictoryController implements Initializable {
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
        winnerName.setText(wargamesAdmin.getWinnerArmy().getName());
        winnerKills.setText(String.valueOf(wargamesAdmin.getWinnerArmy().getKills()));
        winnerLosses.setText(String.valueOf(wargamesAdmin.getWinnerArmy().getLosses()));
    }
}
