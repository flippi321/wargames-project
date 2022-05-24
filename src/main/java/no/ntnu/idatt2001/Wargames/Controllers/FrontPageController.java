package no.ntnu.idatt2001.Wargames.Controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * FrontPageController Class
 * @author  chribrev
 * @version 1.0
 */
public class FrontPageController {
    @FXML
    private Button mainButton;

    @FXML
    public void startApplication(Event event) {
        try {
            //Open main page
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Main-Page.fxml"));
            Stage stage = new Stage();      // TODO (Stage) WargamesApplication.getScene().getWindow
            stage.setTitle("Wargames Launcher");
            stage.setScene(new Scene(loader.load()));
            stage.setMinWidth(800);
            stage.setMinHeight(500);
            stage.show();

            //Close page
            mainButton.getScene().getWindow().hide();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
