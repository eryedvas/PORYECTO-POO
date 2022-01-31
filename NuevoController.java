package espol.poo.PoryectoPOOcarlos;

import espol.poo.modelo.Departamento;
import espol.poo.modelo.Empleado;
import espol.poo.modelo.Genero;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class NuevoController {
    @FXML
    ComboBox cmbDepartamento;
    @FXML
    TextField txtCedula;
    @FXML
    TextField txtNombre;
    
    @FXML
    Button btnBuscar;  
    @FXML
    ImageView  ivFoto;  
    @FXML
    ToggleGroup genero;
    
    @FXML
    private RadioButton rbF;
    @FXML
    private RadioButton rbM;
    @FXML
    private Label lblTitulo;   
    
    
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }




    public void llenarCombo(ArrayList<Departamento> departamentos) {
        cmbDepartamento.getItems().setAll(departamentos);
    }
    
     @FXML
    private void guardarEmpleado() {
        ArrayList<Empleado> empleados = Empleado.cargarEmpleados(App.pathEmpleados);//cargar la lista del archivo
        System.out.println("Guardando empleado");
        RadioButton selectedRadioButton = (RadioButton) genero.getSelectedToggle();
        String genero = selectedRadioButton.getText();
        System.out.println(genero);
        Empleado e = new Empleado(txtCedula.getText(), 
                                  txtNombre.getText(), 
                                  (Departamento) cmbDepartamento.getValue(),
                                   Genero.valueOf(genero.toUpperCase()));
        empleados.add(e);//agregar empleado a la lista
        System.out.println("Nuevo Empleado:" + e);
        
        //serializar la lista
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathEmpleados))){
            out.writeObject(empleados);
            out.flush();

            //mostrar informacion
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Resultado de la operación");
            alert.setContentText("Nueva persona agregada exitosamente");

            alert.showAndWait();
            App.setRoot("primary");

        } catch (IOException ex) {
            System.out.println("IOException:" + ex.getMessage());
        } 

    }
    
    @FXML
    private void buscarArchivo() throws IOException {
       
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar archivo");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(null);

        // Mostar la imagen
        if (imgFile != null) {
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            System.out.println(imgFile.getAbsolutePath());
            ivFoto.setImage(image);
            //copiar la imagen
            Path from = Paths.get(imgFile.toURI());
            Path to = Paths.get("archivos/" + imgFile.getName());
            Files.copy(from, to);
        }
    }

    public void llenarCampos(Empleado e){
        lblTitulo.setText("Editar Empleado");
        txtCedula.setEditable(false);
        txtCedula.setText(e.getCedula());
        txtNombre.setText(e.getNombre());
        cmbDepartamento.setValue(e.getDepartamento());
        if (e.getGenero().equals(Genero.FEMENINO))
            rbF.setSelected(true);
        else
            rbM.setSelected(true);
        
        
    }
    
}
