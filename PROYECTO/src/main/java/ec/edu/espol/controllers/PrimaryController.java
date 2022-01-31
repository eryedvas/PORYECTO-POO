package ec.edu.espol.controllers;

import ec.edu.espol.proyecto.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

public class PrimaryController implements Initializable  {

    @FXML
    private MenuButton opcion;
    @FXML
    private MenuItem dueño;
    @FXML
    private MenuItem mascota;
    @FXML
    private MenuItem concurso;
    @FXML
    private MenuItem premio;
    @FXML
    private MenuItem criterio;
    @FXML
    private MenuItem inscripcion;
    @FXML
    private MenuItem miembro;
    @FXML
    private MenuItem evaluacion;
    @FXML
    private Button btnaccep;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void acceptar(MouseEvent event) {
    }

    
}
