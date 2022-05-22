package no.ntnu.idatt2001.Wargames.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PostBattleController implements Initializable {
    @FXML
    private TextField winnerKills;
    @FXML
    private TextField winnerName;
    @FXML
    private TextField winnerLosses;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        winnerName.setText(wargamesAdmin.getWinnerArmy().getName());
        winnerKills.setText(String.valueOf(wargamesAdmin.getWinnerArmy().getKills()));
        winnerLosses.setText(String.valueOf(wargamesAdmin.getWinnerArmy().getLosses()));
    }

    @FXML
    public void seeBattleLog(ActionEvent actionEvent) {
        try {
            //Open Battle Log Screen
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Battle-Log.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Battle Results");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void replayBattle(ActionEvent actionEvent) {
    }
}
