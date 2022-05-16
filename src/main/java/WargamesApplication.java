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
        loader.setLocation(getClass().getClassLoader().getResource("hello-view.fxml"));
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        stage.setTitle("Poker Simulation Application");
        stage.show();
        stage.setMinWidth(550);
        stage.setMinHeight(350);
    }
}