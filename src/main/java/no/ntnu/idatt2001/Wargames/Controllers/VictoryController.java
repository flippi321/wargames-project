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

public class VictoryController implements Initializable {
    @FXML
    private TextField winnerKills;
    @FXML
    private TextField winnerName;
    @FXML
    private TextField winnerLosses;
    @FXML
    private Button seeLog;
    /*
    String winner = winnerName.getText();
    int losses = Integer.parseInt(winnerLosses.getText());
    int kills = Integer.parseInt(winnerKills.getText());
    String log;

    public void setWinnerValues(String winnerName, int winnerKills, int winnerLosses){
        this.winnerName.setText(winnerName);
        this.winnerKills.setText(String.valueOf(winnerKills));
        this.winnerLosses.setText(String.valueOf(winnerLosses));

        //TODO
        // Send info from MainController to VicotryController
    }
*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        winnerName.setText(wargamesAdmin.getWinnerArmy().getName());
        winnerKills.setText(String.valueOf(wargamesAdmin.getWinnerArmy().getKills()));
        winnerLosses.setText(String.valueOf(wargamesAdmin.getWinnerArmy().getLosses()));
    }
}
