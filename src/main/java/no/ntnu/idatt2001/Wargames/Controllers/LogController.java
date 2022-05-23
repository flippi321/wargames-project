package no.ntnu.idatt2001.Wargames.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import no.ntnu.idatt2001.Wargames.Units.Unit;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LogController implements Initializable {

    @FXML
    private TextArea battleLog;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        ArrayList<String> log = wargamesAdmin.getBattleLog();
        sb.append("The battle lasted for ").append(log.size()).append(" rounds:\n\n");
        for (String event : log) {
            sb.append("Round ").append(i).append("\n").append(event).append("\n\n");
            i++;
        }
        battleLog.setText(sb.toString());
    }
}
