package espol.poo.PoryectoPOOcarlos;

import espol.poo.PoryectoPOOcarlos.NuevoController;
import espol.poo.modelo.Departamento;
import espol.poo.modelo.Empleado;
import espol.poo.modelo.Genero;
import espol.poo.utils.Correo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class PrimaryController {

    @FXML
    ComboBox cmbDepartamento;
    @FXML
    ImageView imgViewFoto;
    @FXML
    Label lblNombre;

    @FXML
    Label lblMsj;

    @FXML
    TableView<Empleado> tvEmpleados;
    @FXML
    private TableColumn<Empleado, String> colCedula;
    @FXML
    private TableColumn<Empleado, String> colNombre;
    @FXML
    private TableColumn<Empleado, String> colDepa;
    @FXML
    private TableColumn<Empleado, Void> colOpc;

    @FXML
    private void initialize() {

        cmbDepartamento.getItems().setAll(Departamento.cargarDepartamentos(App.pathDep));
        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDepa.setCellValueFactory(new PropertyValueFactory<>("departamento"));

        agregarOpciones();//en este metodo se llenan los botones para cada fila

        //datos en listview
        tvEmpleados.getItems().setAll(Empleado.cargarEmpleados(App.pathEmpleados));

    }

    public void llenarTabletView() {
        tvEmpleados.getItems().setAll(Empleado.cargarEmpleados(App.pathEmpleados));
    }

    @FXML
    private void filtrarEmpleados() {

        ArrayList<Empleado> empleadosDepartamento = new ArrayList<>();
        for (Empleado e : Empleado.cargarEmpleados(App.pathEmpleados)) {
            System.out.println(e);
            if (e.getDepartamento().equals(cmbDepartamento.getValue())) {
                empleadosDepartamento.add(e);
            }
        }
        /*
        //filtrado usando stream y lambda
       
        List<Empleado> empleadosDepartamento =  Empleado.cargarEmpleados(App.pathEmpleados)
                .stream()
                .filter(p -> p.getDepartamento().equals(cmbDepartamento.getValue()))
                .collect(Collectors.toList());
        
        //mas sobre streams
        //https://windoctor7.github.io/API-Stream-Java8.html
        //https://www.oscarblancarteblog.com/2017/03/16/java-8-streams-2/
         */
        tvEmpleados.getItems().setAll(empleadosDepartamento);
    }

    @FXML
    private void mostrarVentana() throws IOException {
        //App.setRoot("nuevo");
        //se carga el fxml de nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("nuevo.fxml"));//no tiene el controlador especificado
        NuevoController ct = new NuevoController();

        fxmlLoader.setController(ct);//se asigna el controlador

        VBox root = (VBox) fxmlLoader.load();
        //luego que el fxml ha sido cargado puedo utilizar el controlador para realizar cambios
        ct.llenarCombo(Departamento.cargarDepartamentos(App.pathDep));
        App.changeRoot(root);
    }

    @FXML
    private void mostrarVentana2() throws IOException {
        // App.setRoot("secondary");
        System.out.println("Mostrar ventana usando fxml con controlador establecido");
        //se carga el fxml de nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("secondary.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        //se recupera el controlador
        SecondaryController c2 = fxmlLoader.getController();
        c2.llenarCombo(Departamento.cargarDepartamentos(App.pathDep));//se llama a algun metodo requerido
        c2.cmbDepartamento.setValue(cmbDepartamento.getValue());
        System.out.println();

        App.changeRoot(root);
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void mostrarDetalle() {
        Empleado e = (Empleado) tvEmpleados.getSelectionModel().getSelectedItem();
        //se puede recuperar el indice del elemento recuperado con getSelectedIndex
        System.out.println(tvEmpleados.getSelectionModel().getSelectedIndex());
        System.out.println(e);

        //mostrar la foto y nombre del empleado en la seccion derecha
        InputStream input = null;
        try {
            String fileName = App.pathImg + e.getCedula() + ".png";//armar la ruta de la foto
            lblNombre.setText(e.getNombre());
            //abrir el stream de la imagen de la persona
            input = App.class.getResource(fileName).openStream();

            //crear la imagen 
            Image image = new Image(input, 100, 100, false, false);
            imgViewFoto.setImage(image);

        } catch (Exception ex) {
            System.out.println("no se encuentra archivo de imagen");
            cargarFotoDefecto();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    System.out.println("no se pudo cerrar");
                }
            }
        }
    }

    private void cargarFotoDefecto() {
        InputStream input = null;
        try {
            input = App.class.getResource(App.pathImg + "nofoto.png").openStream();
            Image image = new Image(input, 100, 100, false, false);
            imgViewFoto.setImage(image);
        } catch (IOException ex) {
            System.out.println("No se pudo cargar foto por defecto");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    System.out.println("Error al cerrar el recurso");
                }
            }
        }
    }

    @FXML
    private void editarEmpleado() throws IOException {
        Empleado e = (Empleado) tvEmpleados.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("nuevo.fxml"));//no tiene el controlador especificado
        NuevoController ct = new NuevoController();

        fxmlLoader.setController(ct);//se asigna el controlador

        VBox root = (VBox) fxmlLoader.load();

        ct.llenarCombo(Departamento.cargarDepartamentos(App.pathDep));
        ct.llenarCampos(e);
        App.changeRoot(root);

    }

    @FXML
    private void eliminarEmpleado() {

    }

    @FXML
    private void probarCorreo() {
        System.out.println("Enviando correo...");
        lblMsj.setText("Enviando correo...");
        String destinatario = "gecarri@espol.edu.ec";
        String asunto = "Prueba de correo 4 de enero";
        String cuerpo = "Cuerpo de email.  Saludos.";
        Correo.enviarCorreo(destinatario, asunto, cuerpo);
        System.out.println("Correo enviado!");
        lblMsj.setText("Correo enviado!");
    }
    //basado en el siguiente ejemplo https://riptutorial.com/javafx/example/27946/add-button-to-tableview

    private void agregarOpciones() {

        Callback<TableColumn<Empleado, Void>, TableCell<Empleado, Void>> cellFactory = new Callback<TableColumn<Empleado, Void>, TableCell<Empleado, Void>>() {
            @Override
            public TableCell<Empleado, Void> call(final TableColumn<Empleado, Void> param) {
                TableCell<Empleado, Void> cell = new TableCell<Empleado, Void>() {
                   
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //hbox para ubicar los botones
                            HBox hbOpciones = new HBox(5);
                            //recuperar el empleado de la fila
                            Empleado emp = getTableView().getItems().get(getIndex());
                            //boton editar
                            Button btnEd = new Button("Editar");
                            btnEd.setOnAction(e ->editarEmpleado2(emp));
                               
                            //boton eliminar
                            Button btnEl = new Button("Eliminar");
                            //este boton si inhabilita para genero femenino
                            if (emp.getGenero().equals(Genero.FEMENINO))
                                btnEl.setDisable(true);
                            btnEl.setOnAction(e -> eliminarEmpleado2(emp));
                            //se agregan botones al hbox
                            hbOpciones.getChildren().addAll(btnEd,btnEl);
                            //se ubica hbox en la celda
                            setGraphic(hbOpciones);
                        }
                    }
                };
                return cell;
            }
        };

        colOpc.setCellFactory(cellFactory);

    }

    private void editarEmpleado2(Empleado e) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("nuevo.fxml"));//no tiene el controlador especificado
            NuevoController ct = new NuevoController();

            fxmlLoader.setController(ct);//se asigna el controlador

            VBox root = (VBox) fxmlLoader.load();

            ct.llenarCombo(Departamento.cargarDepartamentos(App.pathDep));
            ct.llenarCampos(e);
            App.changeRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void eliminarEmpleado2(Empleado e) {

        System.out.println("empleado : " + e);

    }
}
