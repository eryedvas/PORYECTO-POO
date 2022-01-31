module ec.edu.espol.proyecto {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.proyecto to javafx.fxml;
    exports ec.edu.espol.proyecto;
    opens ec.edu.espol.controllers to javafx.fxml;
    exports ec.edu.espol.controllers;
    opens ec.edu.espol.model to javafx.fxml;
    exports ec.edu.espol.model;
    requires javafx.graphicsEmpty;
    requires java.base;
}
