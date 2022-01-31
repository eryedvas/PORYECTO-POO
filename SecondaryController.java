package espol.poo.PoryectoPOOcarlos;

import espol.poo.modelo.Departamento;
import espol.poo.modelo.Empleado;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class SecondaryController {


    @FXML
    TextField txtCedula;
    @FXML
    TextField txtNombre;
    @FXML
    ComboBox cmbDepartamento;

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
        Empleado e = new Empleado(txtCedula.getText(), txtNombre.getText(), (Departamento) cmbDepartamento.getValue());
        empleados.add(e);//agregar empleado a la lista
        System.out.println("Nuevo Empleado:" + e);
        
        //serializar la lista
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathEmpleados))){
          
            out.writeObject(empleados);
            out.flush();

            //mostrar informacion
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Resultado de la operaci+on");
            alert.setContentText("Nuevo empleado agregado exitosamente");

            alert.showAndWait();
            //carga la ventana principal
            
            App.setRoot("primary");

        } catch (IOException ex) {
            System.out.println("IOException:" + ex.getMessage());
        } 

    }
    

}
