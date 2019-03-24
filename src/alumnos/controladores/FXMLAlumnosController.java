/**
 * Copyright (c) 2018-2019 LR
 * La venta de este archivo por cualquier medio está estrictamente prohibida.
 * Todos los derechos reservados.
 */
package alumnos.controladores;

import alumnos.modelos.Alumno;
import alumnos.DAO.Alumnos.AlumnosDAO;
import alumnos.Main;
import alumnos.servicios.Conexion;
import java.net.URL;
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

/**
 * <h1>Controlador de la Interfaz de Alumno</h1>
 *
 * En este archivo se controlan los elementos de la interfaz de Alumnos y se definen las funciones
 * de dichos elementos. Además se encuentran aquí definidas las funciones CRUD de la lista de
 * alumnos. Creado el 11/03/2019.
 *
 * @author Luis Roberto H. H.
 * @since 1.0
 * @version 1.0, 2019-03-11
 */
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
  
  @FXML
  private Button boton_inicio;
  @FXML
  private Font x6;
  @FXML
  private Button boton_materias;

  private Main principal;
  private final AlumnosDAO sql_alumnos = new AlumnosDAO();
  private Conexion conn;

  private Stage stage;
  private Alert alerta;
  private int index_modificar = -1;

  private ObservableList<Alumno> estudiantes = FXCollections.observableArrayList();
 
  /**
   * Constructor vacío.
   */
  public FXMLAlumnosController() {
  }

  public void setPrincipal(Main programa_principal) {
        this.principal = programa_principal;
    }
  
  /**
   * Prepara la tabla de alumnos y sus columnas para recibir listas de alumnos. Asigna una lista
   * vacía a la tabla de alumnos.
   *
   * @param url Ubicación usada para resolver rutas relativas del objeto raíz.
   * @param rb Recursos utilizados para localizar el objeto raíz.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    tabla_alumnos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    alumnos_columna_matricula.setCellValueFactory(
            cellData -> cellData.getValue().getMatriculaProperty());
    alumnos_columna_nombre.setCellValueFactory(
            cellData -> cellData.getValue().getNombreProperty());
    alumnos_columna_paterno.setCellValueFactory(
            cellData -> cellData.getValue().getPaternoProperty());
    alumnos_columna_materno.setCellValueFactory(
            cellData -> cellData.getValue().getMaternoProperty());
    tabla_alumnos.setPlaceholder(new Label(" No hay alumnos registrados."));
    tabla_alumnos.setItems(estudiantes);
  }

  /**
   * Se activa al presionar el botón "salir" y cierra la ventana.
   *
   * @param event Tipo de acción "Evento".
   */
  @FXML
  private void salir(ActionEvent event) {
    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    stage.close();
  }

  /**
   * Solicita confirmación para llamar al DAO de alumnos y pedir una lista de alumnos. La lista
   * pedida reemplaza la que se tiene actualmente en la tabla de alumnos.
   *
   * @param event Tipo de acción "Evento".
   */
  @FXML
  private void cargarAlumnos(ActionEvent event) {
    alerta = new Alert(AlertType.CONFIRMATION);
    alerta.setTitle("Confirmación");
    alerta.setHeaderText("¿Estás seguro de cargar la lista de alumnos de la base de datos?");
    alerta.setContentText(
            "La lista se sobreescribirá en la lista actual. Esta acción no podra deshacerse.");
    Optional<ButtonType> resultado = alerta.showAndWait();
    if (resultado.get() == ButtonType.OK) {
      conn = new Conexion();
      estudiantes = sql_alumnos.crearLista(conn.getConexion());
      conn.desconectar();
      tabla_alumnos.setItems(estudiantes);
    }
  }

  /**
   * Envía la lista de alumnos actual a una función del DAO de alumnos para guardarse en la base de
   * datos.
   *
   * @param event Tipo de acción "Evento".
   */
  @FXML
  private void guardarAlumnos(ActionEvent event) {
    conn = new Conexion();
    sql_alumnos.guardarLista(estudiantes, conn.getConexion());
    conn.desconectar();
  }

  /**
   * Crea una lista vacía que reemplaza la lista de alumnos asignada actualmente a la tabla.
   *
   * @param event Tipo de acción "Evento".
   */
  @FXML
  private void crearListaNueva(ActionEvent event) {
    alerta = new Alert(AlertType.CONFIRMATION);
    alerta.setTitle("Confirmación");
    alerta.setHeaderText("¿Estás seguro de crear una nueva lista de alumnos?");
    alerta.setContentText(
            "La nueva lista reemplazará la lista actual. Esta acción no podrá deshacerse.");
    Optional<ButtonType> resultado = alerta.showAndWait();
    if (resultado.get() == ButtonType.OK) {
      estudiantes = FXCollections.observableArrayList();
      tabla_alumnos.setItems(estudiantes);
    }
  }

  /**
   * Crea un nuevo alumno a partir de lo escrito en los textfields, si alguno de ellos está vacío
   * termina. De lo contrario, verifica si se está modificando un alumno, si es cierto remplaza el
   * alumno que se está modificando; sino llama al método "buscarPorId" y manda el alumno para ser
   * agregado a la lista.
   *
   * @param event Tipo de acción "Evento".
   */
  @FXML
  private void agregar(ActionEvent event) {
    Alumno temporal = new Alumno(
            tf_matricula.getText(),
            tf_nombre.getText(),
            tf_paterno.getText(),
            tf_materno.getText());
    if (verificarTextfieldsVacios()) {
      tf_matricula.requestFocus();
      return;
    }
    if (index_modificar != -1) {
      estudiantes.set(index_modificar, temporal);
      index_modificar = -1;
    } else {
      buscarPorID(temporal);
    }
    tf_matricula.clear();
    tf_nombre.clear();
    tf_paterno.clear();
    tf_materno.clear();
    tf_matricula.requestFocus();
  }

  /**
   * Verifica si se está modificando un alumno y termina si es así. En caso contrario elimina los
   * alumnos seleccionados en la tabla de la lista de alumnos.
   *
   * @param event Tipo de acción "Evento".
   */
  @FXML
  private void eliminar(ActionEvent event) {
    if (tabla_alumnos.getSelectionModel().isEmpty()) {
      return;
    }
    if (index_modificar != -1) {
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
    if (resultado.get() == ButtonType.OK) {
      tabla_alumnos.getItems().removeAll(tabla_alumnos.getSelectionModel().getSelectedItems());
      tabla_alumnos.getSelectionModel().clearSelection();
    }
  }

  /**
   * Escribe los atributos en los textfields del último alumno seleccionado en la tabla (si es que
   * hay alguno seleccionado) y guarda su índice (su posición en la lista) en un atributo de esta
   * clase.
   *
   * @param event Tipo de acción "Evento".
   */
  @FXML
  private void modificar(ActionEvent event) {
    if (tabla_alumnos.getSelectionModel().isEmpty()) {
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

  /**
   * Compara la matricula del alumno parámetro con la de los demás registrados en la lista de
   * alumnos y reemplaza si hay una coincidencia o lo agrega a la lista en caso contrario.
   *
   * @param temporal De tipo Alumno. Se refiere a un alumno creado a partir de lo escrito en los
   * textfields.
   */
  public void buscarPorID(Alumno temporal) {
    for (int index = 0; index < estudiantes.size(); index++) {
      if (estudiantes.get(index).getMatricula().equals(temporal.getMatricula())) {
        alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText("Reemplazar datos");
        alerta.setContentText("¿Desea remplazar los datos del estudiante "
                + estudiantes.get(index).toString() + " con los nuevos?");
        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.get() == ButtonType.OK) {
          estudiantes.set(index, temporal);
          return;
        } else {
          return;
        }
      }
    }
    estudiantes.add(temporal);
  }

  /**
   * Verifica si alguno de los textfields está vacío, indica cúal a través del label correspondiente
   * a cada textfield y devuelve un boolean.
   *
   * @return alguno_vacio Es true si alguno de los textfield está vacío y viceversa.
   */
  public boolean verificarTextfieldsVacios() {
    boolean alguno_vacio = false;
    limpiarLabelsVacios();
    if (tf_matricula.getText().isEmpty()) {
      label_matricula.setText("* Campo Obligatorio");
      alguno_vacio = true;
    }
    if (tf_nombre.getText().isEmpty()) {
      label_nombre.setText("* Campo Obligatorio");
      alguno_vacio = true;
    }
    if (tf_paterno.getText().isEmpty()) {
      label_paterno.setText("* Campo Obligatorio");
      alguno_vacio = true;
    }
    if (tf_materno.getText().isEmpty()) {
      label_materno.setText("* Campo Obligatorio");
      alguno_vacio = true;
    }
    return alguno_vacio;
  }

  /**
   * Limpia el contenido de los labels asignados a cada textfield.
   */
  public void limpiarLabelsVacios() {
    label_matricula.setText("");
    label_nombre.setText("");
    label_paterno.setText("");
    label_materno.setText("");
  }

  @FXML
  private void abrirVentanaPrincipal(ActionEvent event) {
    principal.mostrarVentanaPrincipal();
    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    stage.close();
  }

  @FXML
  private void abrirVentanaMaterias(ActionEvent event) {
    principal.mostrarVentanaMaterias();
    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    stage.close();
  }

}
