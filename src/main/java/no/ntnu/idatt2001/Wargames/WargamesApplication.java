package no.ntnu.idatt2001.Wargames;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WargamesApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("Wargames-view.fxml"));
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        stage.setTitle("Wargames");
        stage.show();
        stage.setMinWidth(600);
        stage.setMinHeight(400);
    }
}