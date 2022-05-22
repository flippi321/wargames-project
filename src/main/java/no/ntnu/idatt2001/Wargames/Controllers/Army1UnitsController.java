package no.ntnu.idatt2001.Wargames.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class Army1UnitsController implements Initializable {

    @FXML
    private TextArea armyText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        armyText.setText(wargamesAdmin.getArmy1().toString());
    }
}
