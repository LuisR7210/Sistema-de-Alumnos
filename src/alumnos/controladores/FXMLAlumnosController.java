/** Controlador de Interfaz de Alumnos
 *
 * @author Luis Roberto Herrera Hernández
 * @version 1.0, 03/21/19
 *
 * Descripción: aqui se controla los elementos de la interfaz de Alumnos y se definen las funciones 
 * de dichos elementos. Además se encuentran aquí definidas las funciones CRUD de la lista de 
 * alumnos.
 */
package alumnos.controladores;

import alumnos.modelos.Alumno;
import alumnos.DAO.Alumnos.AlumnosDAO;
import alumnos.servicios.Conexion;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

public class FXMLAlumnosController implements Initializable {

  @FXML
  private TableColumn<Alumno, String> alumnos_columna_materno;

  @FXML
  private TableColumn<Alumno, String> alumnos_columna_matricula;

  @FXML
  private TableColumn<Alumno, String> alumnos_columna_nombre;

  @FXML
  private TableColumn<Alumno, String> alumnos_columna_paterno;

  @FXML
  private TableView<Alumno> tabla_alumnos;

  @FXML
  private Button boton_agregar;

  @FXML
  private Button boton_editar;

  @FXML
  private Button boton_eliminar;

  @FXML
  private Button boton_salir;

  @FXML
  private TextField tf_materno;

  @FXML
  private TextField tf_matricula;

  @FXML
  private TextField tf_nombre;

  @FXML
  private TextField tf_paterno;
  
  @FXML
  private Label label_matricula;
  
  @FXML
  private Label label_nombre;
  
  @FXML
  private Label label_paterno;
  
  @FXML
  private Label label_materno;
  
  @FXML
  private Color x1;
  @FXML
  private Font x3;
  @FXML
  private Font x2;
  
  @FXML
  private Color x5;
  @FXML
  private Font x4;
  @FXML
  private Menu menu_alumnos;
  @FXML
  private MenuItem menu_nuevo;
  @FXML
  private MenuItem menu_cargar;
  @FXML
  private MenuItem menu_guardar;

  private final AlumnosDAO sql_alumnos = new AlumnosDAO();
  private Conexion conn;
  
  private Stage stage;
  private Alert alerta;
  private int index_modificar=-1;
  
  private ObservableList<Alumno> estudiantes = FXCollections.observableArrayList();

  public FXMLAlumnosController() {
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    tabla_alumnos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    alumnos_columna_matricula.setCellValueFactory(cellData -> cellData.getValue().getMatriculaProperty());
    alumnos_columna_nombre.setCellValueFactory(cellData -> cellData.getValue().getNombreProperty());
    alumnos_columna_paterno.setCellValueFactory(cellData -> cellData.getValue().getPaternoProperty());
    alumnos_columna_materno.setCellValueFactory(cellData -> cellData.getValue().getMaternoProperty());
    tabla_alumnos.setPlaceholder(new Label(" No hay alumnos registrados."));
    tabla_alumnos.setItems(estudiantes);
  }

  @FXML
  private void salir(ActionEvent event) {
    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    stage.close();
  }

  @FXML
  private void agregar(ActionEvent event) {
    Alumno temporal = new Alumno (
      tf_matricula.getText(), 
      tf_nombre.getText(),
      tf_paterno.getText(),
      tf_materno.getText());
    if(verificarVacios()){
      tf_matricula.requestFocus();
      return;
    }
    if(index_modificar!=-1){
      estudiantes.set(index_modificar, temporal);
      index_modificar=-1;
    }
    else{
      buscarPorID(temporal);
    }
    tf_matricula.clear();
    tf_nombre.clear();
    tf_paterno.clear();
    tf_materno.clear();
    tf_matricula.requestFocus();
  }
  
  @FXML
  private void eliminar(ActionEvent event) {
    if(tabla_alumnos.getSelectionModel().isEmpty()){
      return;
    }
    if(index_modificar!=-1){
      tabla_alumnos.getSelectionModel().clearSelection();
      alerta = new Alert(AlertType.WARNING);
      alerta.setTitle("Advertencia");
      alerta.setHeaderText("Primero debes terminar de modificar el estudiante");
      alerta.showAndWait();
      tf_matricula.requestFocus();
      return;
    }
    limpiarLabelsVacios();
    alerta = new Alert(AlertType.CONFIRMATION);
    alerta.setTitle("Confirmación");
    alerta.setHeaderText("¿Estás seguro de eliminar los estudiantes seleccionados?");
    Optional<ButtonType> resultado = alerta.showAndWait();
    if (resultado.get() == ButtonType.OK){
      tabla_alumnos.getItems().removeAll(tabla_alumnos.getSelectionModel().getSelectedItems());
      tabla_alumnos.getSelectionModel().clearSelection();
    }
  }
  
  @FXML
  private void modificar(ActionEvent event) {
    if(tabla_alumnos.getSelectionModel().isEmpty()){
      return;
    }
    Alumno temporal = tabla_alumnos.getSelectionModel().getSelectedItem();
    tf_matricula.setText(temporal.getMatricula());
    tf_nombre.setText(temporal.getNombre());
    tf_paterno.setText(temporal.getA_paterno());
    tf_materno.setText(temporal.getA_materno());
    tf_matricula.requestFocus();
    index_modificar = tabla_alumnos.getSelectionModel().getSelectedIndex();
    tabla_alumnos.getSelectionModel().clearSelection();
  }
  
  public void buscarPorID(Alumno temporal){
    for(int index=0; index<estudiantes.size(); index++){
      if(estudiantes.get(index).getMatricula().equals(temporal.getMatricula())){
        alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText("Reemplazar datos");
        alerta.setContentText("¿Desea remplazar los datos del estudiante " + estudiantes.get(index).toString() + " con los nuevos?");
        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.get() == ButtonType.OK){
          estudiantes.set(index,temporal);
          return;
        }
        else{
          return;
        }
      }
    }
    estudiantes.add(temporal);
  }
  
  public boolean verificarVacios(){
    boolean alguno_vacio=false;
    limpiarLabelsVacios();
    if(tf_matricula.getText().isEmpty()){
      label_matricula.setText("* Campo Obligatorio");
      alguno_vacio=true;
    }
    if(tf_nombre.getText().isEmpty()){
      label_nombre.setText("* Campo Obligatorio");
      alguno_vacio=true;
    }
    if(tf_paterno.getText().isEmpty()){
      label_paterno.setText("* Campo Obligatorio");
      alguno_vacio=true;
    }
    if(tf_materno.getText().isEmpty()){
      label_materno.setText("* Campo Obligatorio");
      alguno_vacio=true;
    }
    return alguno_vacio;
  }
  
  public void limpiarLabelsVacios(){
    label_matricula.setText("");
    label_nombre.setText("");
    label_paterno.setText("");
    label_materno.setText("");
  }

  @FXML
  private void cargarAlumnos(ActionEvent event) {
    alerta = new Alert(AlertType.CONFIRMATION);
    alerta.setTitle("Confirmación");
    alerta.setHeaderText("¿Estás seguro de cargar la lista de alumnos de la base de datos?");
    alerta.setContentText("La lista se sobreescribirá en la lista actual. Esta acción no podra deshacerse.");
    Optional<ButtonType> resultado = alerta.showAndWait();
    if (resultado.get() == ButtonType.OK){
      conn= new Conexion();
      estudiantes = sql_alumnos.crearLista(conn.getConexion());
      conn.desconectar();
      tabla_alumnos.setItems(estudiantes);
    }
  }
    

  @FXML
  private void guardarAlumnos(ActionEvent event) {
    conn= new Conexion();
    sql_alumnos.guardarLista(estudiantes, conn.getConexion());
    conn.desconectar();
  }

  @FXML
  private void crearListaNueva(ActionEvent event) {
    alerta = new Alert(AlertType.CONFIRMATION);
    alerta.setTitle("Confirmación");
    alerta.setHeaderText("¿Estás seguro de crear una nueva lista de alumnos?");
    alerta.setContentText("La nueva lista de sobreescribirá en la lista actual. Esta acción no podra deshacerse.");
    Optional<ButtonType> resultado = alerta.showAndWait();
    if (resultado.get() == ButtonType.OK){
      estudiantes = FXCollections.observableArrayList();
      tabla_alumnos.setItems(estudiantes);
    }
  }
  
}