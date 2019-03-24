/**
 * Copyright (c) 2018-2019 LR
 * La venta de este archivo por cualquier medio está estrictamente prohibida.
 * Todos los derechos reservados.
 */
package alumnos.controladores;

import alumnos.DAO.Materias.MateriasDAO;
import alumnos.Main;
import alumnos.modelos.Materia;
import alumnos.servicios.Conexion;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Luis Roberto Herrera Hernández
 */
public class FXMLMateriasController implements Initializable {

  @FXML
  private Color x1;
  @FXML
  private Button boton_salir;
  @FXML
  private Font x3;
  @FXML
  private TableView<Materia> tabla_materias;
  @FXML
  private TableColumn<Materia, String> materias_columna_nombre;
  @FXML
  private TableColumn<Materia, Number> materias_columna_horas_teoria;
  @FXML
  private TableColumn<Materia, Number> materias_columna_horas_practica;
  @FXML
  private TableColumn<Materia, Number> materias_columna_creditos;
  @FXML
  private TableColumn<Materia, Number> materias_columna_nrc;
  @FXML
  private Font x2;
  @FXML
  private TextField tf_nombre;
  @FXML
  private TextField tf_horas_teoria;
  @FXML
  private TextField tf_horas_practica;
  @FXML
  private TextField tf_creditos;
  @FXML
  private TextField tf_nrc;
  @FXML
  private Label label_nombre;
  @FXML
  private Button boton_agregar;
  @FXML
  private Color x5;
  @FXML
  private Font x4;
  @FXML
  private Label label_horas_teoria;
  @FXML
  private Color x8;
  @FXML
  private Color x7;
  @FXML
  private Label label_horas_practica;
  @FXML
  private Label label_creditos;
  @FXML
  private Label label_nrc;
  @FXML
  private Button boton_editar;
  @FXML
  private Button boton_eliminar;
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
  private Button boton_alumnos;
  
  private Main principal;
  private final MateriasDAO sql_materias = new MateriasDAO();
  private Conexion conn;

  private Stage stage;
  private Alert alerta;
  private int index_modificar = -1;

  private ObservableList<Materia> experencias_educativas = FXCollections.observableArrayList();

  public FXMLMateriasController() {
  }
  
  public void setPrincipal(Main programa_principal) {
        this.principal = programa_principal;
    }
  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    tabla_materias.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    materias_columna_nombre.setCellValueFactory(
            cellData -> cellData.getValue().getNombreProperty());
    materias_columna_horas_teoria.setCellValueFactory(
            cellData -> cellData.getValue().getHoras_teoriaProperty());
    materias_columna_horas_practica.setCellValueFactory(
            cellData -> cellData.getValue().getHoras_practicaProperty());
    materias_columna_creditos.setCellValueFactory(
            cellData -> cellData.getValue().getCreditosProperty());
    materias_columna_nrc.setCellValueFactory(
            cellData -> cellData.getValue().getNrcProperty());
    tabla_materias.setPlaceholder(new Label(" No hay experencias educativas registrados."));
    tabla_materias.setItems(experencias_educativas);
  }  

  @FXML
  private void salir(ActionEvent event) {
    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    stage.close();
  }

  @FXML
  private void agregar(ActionEvent event) {
    Materia temporal = new Materia(
            tf_nombre.getText(),
            Integer.parseInt(tf_horas_teoria.getText()),
            Integer.parseInt(tf_horas_practica.getText()),
            Integer.parseInt(tf_creditos.getText()),
            Integer.parseInt(tf_nrc.getText()));
    if (verificarTextfieldsVacios()) {
      tf_nombre.requestFocus();
      return;
    }
    if (index_modificar != -1) {
      experencias_educativas.set(index_modificar, temporal);
      index_modificar = -1;
    } else {
      buscarPorID(temporal);
    }
    tf_nombre.clear();
    tf_horas_teoria.clear();
    tf_horas_practica.clear();
    tf_creditos.clear();
    tf_nrc.clear();
    tf_nombre.requestFocus();
  }

  @FXML
  private void modificar(ActionEvent event) {
    if (tabla_materias.getSelectionModel().isEmpty()) {
      return;
    }
    Materia temporal = tabla_materias.getSelectionModel().getSelectedItem();
    tf_nombre.setText(temporal.getNombre());
    tf_horas_teoria.setText(Integer.toString(temporal.getHoras_teoria()));
    tf_horas_practica.setText(Integer.toString(temporal.getHoras_practica()));
    tf_creditos.setText(Integer.toString(temporal.getCreditos()));
    tf_nrc.setText(Integer.toString(temporal.getNrc()));
    tf_nombre.requestFocus();
    index_modificar = tabla_materias.getSelectionModel().getSelectedIndex();
    tabla_materias.getSelectionModel().clearSelection();
  }

  @FXML
  private void eliminar(ActionEvent event) {
    if (tabla_materias.getSelectionModel().isEmpty()) {
      return;
    }
    if (index_modificar != -1) {
      tabla_materias.getSelectionModel().clearSelection();
      alerta = new Alert(AlertType.WARNING);
      alerta.setTitle("Advertencia");
      alerta.setHeaderText("Primero debes terminar de modificar la experencia educativa");
      alerta.showAndWait();
      tf_nombre.requestFocus();
      return;
    }
    limpiarLabelsVacios();
    alerta = new Alert(AlertType.CONFIRMATION);
    alerta.setTitle("Confirmación");
    alerta.setHeaderText("¿Estás seguro de eliminar las experencias educativas seleccionadas?");
    Optional<ButtonType> resultado = alerta.showAndWait();
    if (resultado.get() == ButtonType.OK) {
      tabla_materias.getItems().removeAll(tabla_materias.getSelectionModel().getSelectedItems());
      tabla_materias.getSelectionModel().clearSelection();
    }
  }

  @FXML
  private void crearListaNueva(ActionEvent event) {
    alerta = new Alert(AlertType.CONFIRMATION);
    alerta.setTitle("Confirmación");
    alerta.setHeaderText("¿Estás seguro de crear una nueva lista de experencias educativas?");
    alerta.setContentText(
            "La nueva lista reemplazará la lista actual. Esta acción no podrá deshacerse.");
    Optional<ButtonType> resultado = alerta.showAndWait();
    if (resultado.get() == ButtonType.OK) {
      experencias_educativas = FXCollections.observableArrayList();
      tabla_materias.setItems(experencias_educativas);
    }
  }

  @FXML
  private void cargarMaterias(ActionEvent event) {
    alerta = new Alert(AlertType.CONFIRMATION);
    alerta.setTitle("Confirmación");
    alerta.setHeaderText("¿Estás seguro de cargar la lista de experencias educativas de la base de datos?");
    alerta.setContentText(
            "La lista se sobreescribirá en la lista actual. Esta acción no podra deshacerse.");
    Optional<ButtonType> resultado = alerta.showAndWait();
    if (resultado.get() == ButtonType.OK) {
      conn = new Conexion();
      experencias_educativas = sql_materias.crearLista(conn.getConexion());
      conn.desconectar();
      tabla_materias.setItems(experencias_educativas);
    }
  }

  @FXML
  private void guardarMaterias(ActionEvent event) {
    conn = new Conexion();
    sql_materias.guardarLista(experencias_educativas, conn.getConexion());
    conn.desconectar();
  }

  @FXML
  private void abrirVentanaPrincipal(ActionEvent event) {
    principal.mostrarVentanaPrincipal();
    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    stage.close();
  }

  @FXML
  private void abrirVentanaAlumnos(ActionEvent event) {
    principal.mostrarVentanaAlumnos();
    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    stage.close();
  }
  
  /**
   * Compara la matricula del alumno parámetro con la de los demás registrados en la lista de
   * alumnos y reemplaza si hay una coincidencia o lo agrega a la lista en caso contrario.
   *
   * @param temporal De tipo Materia. Se refiere a un alumno creado a partir de lo escrito en los
   * textfields.
   */
  public void buscarPorID(Materia temporal) {
    for (int index = 0; index < experencias_educativas.size(); index++) {
      if (experencias_educativas.get(index).getNrc().equals(temporal.getNrc())) {
        alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText("Reemplazar datos");
        alerta.setContentText("¿Desea remplazar los datos de la experencias educativa "
                + experencias_educativas.get(index).toString() + " con los nuevos?");
        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.get() == ButtonType.OK) {
          experencias_educativas.set(index, temporal);
          return;
        } else {
          return;
        }
      }
    }
    experencias_educativas.add(temporal);
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
    if (tf_nombre.getText().isEmpty()) {
      label_nombre.setText("* Campo Obligatorio");
      alguno_vacio = true;
    }
    if (tf_horas_teoria.getText().isEmpty()) {
      label_horas_teoria.setText("* Campo Obligatorio");
      alguno_vacio = true;
    }
    if (tf_horas_practica.getText().isEmpty()) {
      label_horas_practica.setText("* Campo Obligatorio");
      alguno_vacio = true;
    }
    if (tf_creditos.getText().isEmpty()) {
      label_creditos.setText("* Campo Obligatorio");
      alguno_vacio = true;
    }
    if (tf_nrc.getText().isEmpty()) {
      label_nrc.setText("* Campo Obligatorio");
      alguno_vacio = true;
    }
    return alguno_vacio;
  }

  /**
   * Limpia el contenido de los labels asignados a cada textfield.
   */
  public void limpiarLabelsVacios() {
    label_nombre.setText("");
    label_horas_teoria.setText("");
    label_horas_practica.setText("");
    label_creditos.setText("");
    label_nrc.setText("");
  }
  
}
