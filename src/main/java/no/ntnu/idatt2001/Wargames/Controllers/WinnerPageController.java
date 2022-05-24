package no.ntnu.idatt2001.Wargames.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * WinnarPageController Class
 * @author  chribrev
 * @version 1.0
 */
public class WinnerPageController implements Initializable {
    @FXML
    private TextField winnerKills;
    @FXML
    private TextField winnerLosses;
    @FXML
    private TextField army2Commander;
    @FXML
    private TextField army2Ranged;
    @FXML
    private TextField army2Infantry;
    @FXML
    private TextField army1Cavalry;
    @FXML
    private TextField army2Cavalry;
    @FXML
    private TextField army1Infantry;
    @FXML
    private TextField army1Ranged;
    @FXML
    private TextField army1Commander;
    @FXML
    private TextField winnerName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateValues();
    }

    @FXML
    public void replayBattle(ActionEvent actionEvent) {
        try {
            wargamesAdmin.setArmy1(wargamesAdmin.getPreBattleArmy1());
            wargamesAdmin.setArmy2(wargamesAdmin.getPreBattleArmy2());

            //Close Winner Window so that the user may replay the battle
            winnerName.getScene().getWindow().hide();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void updateValues(){
        winnerName.setText(wargamesAdmin.getWinnerArmy().getName());
        winnerKills.setText(String.valueOf(wargamesAdmin.getWinnerArmy().getKills()));
        winnerLosses.setText(String.valueOf(wargamesAdmin.getWinnerArmy().getLosses()));

        //Army 1
        army1Infantry.setText(String.valueOf(wargamesAdmin.getArmy1().getInfantryUnits().size()));
        army1Cavalry.setText(String.valueOf(wargamesAdmin.getArmy1().getCavalryUnits().size()));
        army1Ranged.setText(String.valueOf(wargamesAdmin.getArmy1().getRangedUnits().size()));
        army1Commander.setText(String.valueOf(wargamesAdmin.getArmy1().getCommanderUnits().size()));

        //Army 2
        army2Infantry.setText(String.valueOf(wargamesAdmin.getArmy2().getInfantryUnits().size()));
        army2Cavalry.setText(String.valueOf(wargamesAdmin.getArmy2().getCavalryUnits().size()));
        army2Ranged.setText(String.valueOf(wargamesAdmin.getArmy2().getRangedUnits().size()));
        army2Commander.setText(String.valueOf(wargamesAdmin.getArmy2().getCommanderUnits().size()));
    }

    @FXML
    public void seeLog(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Battle-Log.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Battle Results");
            stage.setScene(new Scene(loader.load()));
            stage.show();

            winnerKills.getScene().getWindow().hide();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void seeRemainingArmy1(ActionEvent actionEvent) {
        try {
            //Open Units Screen
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Army1_Units.fxml"));
            Stage stage = new Stage();
            stage.setTitle(wargamesAdmin.getArmy1().getName() + " Units");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    public void seeRemainingArmy2(ActionEvent actionEvent) {
        try {
            //Open Units Screen
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Army2_Units.fxml"));
            Stage stage = new Stage();
            stage.setTitle(wargamesAdmin.getArmy2().getName() + " Units");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
