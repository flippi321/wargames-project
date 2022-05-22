package no.ntnu.idatt2001.Wargames.Controllers;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import no.ntnu.idatt2001.Wargames.WargamesApplication;

import javax.print.attribute.standard.Media;
import java.io.File;

public class FrontPageController {
    @FXML
    public void startApplication(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Main-Page.fxml"));
            Stage stage = new Stage();      // TODO (Stage) WargamesApplication.getScene().getWindow
            stage.setTitle("Wargames Launcher");
            stage.setScene(new Scene(loader.load()));
            stage.setMinWidth(800);
            stage.setMinHeight(500);
            stage.show();

            //TODO
            // Music?
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
