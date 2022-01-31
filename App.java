package espol.poo.PoryectoPOOcarlos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static String pathEmpleados = "archivos/empleados.ser";
    public static String pathDep="espol/poo/empadminjar/files/departamentos.csv";
    public static String pathImg = "files/";
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        
        scene.getStylesheets().add(App.class.getResource("css/estilos.css").toExternalForm());
      
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    //metodo para cambiar el contenido de la escena
    static void changeRoot(Parent rootNode) {
        scene.setRoot(rootNode);
    }
}