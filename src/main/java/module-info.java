module no.ntnu.idatt2001.Wargames {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens no.ntnu.idatt2001.Wargames to javafx.fxml;
    exports no.ntnu.idatt2001.Wargames;

    opens no.ntnu.idatt2001.Wargames.Controllers to javafx.fxml;
    exports no.ntnu.idatt2001.Wargames.Controllers;
}