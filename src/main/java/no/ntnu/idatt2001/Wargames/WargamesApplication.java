package no.ntnu.idatt2001.Wargames;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class WargamesApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("Start-Page.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Wargames: BattleSimulation");
            stage.show();
            stage.setMinWidth(800);
            stage.setMinHeight(500);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        //stage.getIcons().add(new Image(this.getClass().getResource("icon.png").toExternalForm()));        //TODO: FIX
    }
}