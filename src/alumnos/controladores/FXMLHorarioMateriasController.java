/**
 * Copyright (c) 2018-2019 LR
 * La venta de este archivo por cualquier medio está estrictamente prohibida.
 * Todos los derechos reservados.
 */
package alumnos.controladores;

import alumnos.Main;
import alumnos.modelos.DiaMateria;
import alumnos.modelos.HorarioMateria;
import alumnos.modelos.Materia;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;

/**
 * FXML Controller class
 *
 * @author Luis Roberto Herrera Hernández
 */
public class FXMLHorarioMateriasController implements Initializable {

  @FXML
  private Color x1;
  @FXML
  private Font x3;
  @FXML
  private Label label_experiencia_educativa;
  @FXML
  private TableView<HorarioMateria> tabla_horarios;
  @FXML
  private TableColumn<HorarioMateria, Number> materias_columna_nrc;
  @FXML
  private TableColumn<HorarioMateria, String> materias_columna_profesor;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> materias_columna_lunes_inicio;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> materias_columna_lunes_fin;
  @FXML
  private TableColumn<HorarioMateria, String> materias_columna_lunes_salon;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> materias_columna_martes_inicio;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> materias_columna_martes_fin;
  @FXML
  private TableColumn<HorarioMateria, String> materias_columna_martes_salon;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> materias_columna_miercoles_inicio;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> materias_columna_miercoles_fin;
  @FXML
  private TableColumn<HorarioMateria, String> materias_columna_miercoles_salon;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> materias_columna_jueves_inicio;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> materias_columna_jueves_fin;
  @FXML
  private TableColumn<HorarioMateria, String> materias_columna_jueves_salon;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> materias_columna_viernes_inicio;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> materias_columna_viernes_fin;
  @FXML
  private TableColumn<HorarioMateria, String> materias_columna_viernes_salon;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> materias_columna_sabado_inicio;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> materias_columna_sabado_fin;
  @FXML
  private TableColumn<HorarioMateria, String> materias_columna_sabado_salon;
  @FXML
  private Button boton_agregar_horario;
  @FXML
  private Button boton_regresar;
  @FXML
  private Font x6;
  @FXML
  private Font x2;
  @FXML
  private ComboBox<String> comboBox_dias;
  @FXML
  private TextField tf_hora_inicio;
  @FXML
  private TextField tf_hora_fin;
  @FXML
  private TextField tf_salon;
  @FXML
  private Button boton_agregar_dia;
  @FXML
  private TextField tf_profesor;
  @FXML
  private TextField tf_nrc;
  @FXML
  private Button boton_editar_horario;
  @FXML
  private Button boton_eliminar_horario;
  @FXML
  private Button boton_eliminar_dia;
  @FXML
  private Label label_campos_horario;
  @FXML
  private Label label_campos_dia;
  @FXML
  private Color x5;
  @FXML
  private Font x4;
 
  private Main principal;
  private Stage stage;
  private Materia materia;
  private int index_modificar = -1;
  private Alert alerta;
  
  private final ObservableList<String> dias = FXCollections.observableArrayList();
  private ObservableList<HorarioMateria> todos_los_horarios = FXCollections.observableArrayList();
  private ObservableList<HorarioMateria> horarios = FXCollections.observableArrayList();
  
  public FXMLHorarioMateriasController() {
  }

  public void setPrincipal(Main programa_principal) {
        this.principal = programa_principal;
        this.cargarHorarios();
    tabla_horarios.setItems(horarios);
    }
  
  public void setMateria(Materia ee){
    this.materia=ee;
    label_experiencia_educativa.setText(materia.getNombre());
    tabla_horarios.setPlaceholder(new Label(" No hay horarios registrados de "
            + materia.getNombre()));
  }
  
  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    tabla_horarios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    this.inicializarColumnas();
    this.inicializarTextfieldsHoras();
    dias.addAll("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado");
    comboBox_dias.setItems(dias);
    comboBox_dias.setValue("Lunes");
  }  

  private void inicializarColumnas(){
    materias_columna_nrc.setCellValueFactory(
            cellData -> cellData.getValue().getNrcProperty());
    materias_columna_profesor.setCellValueFactory(
            cellData -> cellData.getValue().getProfesorProperty());
    materias_columna_lunes_inicio.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(0).getHora_inicioProperty());
    materias_columna_lunes_fin.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(0).getHora_finProperty());
    materias_columna_lunes_salon.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(0).getSalonProperty());
    materias_columna_martes_inicio.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(1).getHora_inicioProperty());
    materias_columna_martes_fin.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(1).getHora_finProperty());
    materias_columna_martes_salon.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(1).getSalonProperty());
    materias_columna_miercoles_inicio.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(2).getHora_inicioProperty());
    materias_columna_miercoles_fin.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(2).getHora_finProperty());
    materias_columna_miercoles_salon.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(2).getSalonProperty());
    materias_columna_jueves_inicio.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(3).getHora_inicioProperty());
    materias_columna_jueves_fin.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(3).getHora_finProperty());
    materias_columna_jueves_salon.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(3).getSalonProperty());
    materias_columna_viernes_inicio.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(4).getHora_inicioProperty());
    materias_columna_viernes_fin.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(4).getHora_finProperty());
    materias_columna_viernes_salon.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(4).getSalonProperty());
    materias_columna_sabado_inicio.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(5).getHora_inicioProperty());
    materias_columna_sabado_fin.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(5).getHora_finProperty());
    materias_columna_sabado_salon.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(5).getSalonProperty());
  }
  
  private void inicializarTextfieldsHoras(){
    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    try {
      tf_hora_inicio.setTextFormatter(new TextFormatter<>
        (new DateTimeStringConverter(format), format.parse("00:00")));
      tf_hora_fin.setTextFormatter(new TextFormatter<>
        (new DateTimeStringConverter(format), format.parse("00:00")));
    } catch (ParseException ex) {
      System.out.println("Error al asignar formato de tiempo a los textfields de inicio y fin");
    }
  }
  
  @FXML
  private void abrirVentanaMaterias(ActionEvent event) {
    this.guardarHorarios();
    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    stage.close();
  }

  @FXML
  private void agregar_horario(ActionEvent event) {
    label_campos_horario.setText("");
    if(tf_profesor.getText().isEmpty() || tf_nrc.getText().isEmpty()){
      label_campos_horario.setText("* Todos los campos son obligatorios");
      return;
    }
    HorarioMateria temporal = new HorarioMateria(
            tf_profesor.getText(),
            Integer.parseInt(tf_nrc.getText()),
            materia.getId_materia());
    if (index_modificar != -1) {
      horarios.get(index_modificar).setProfesor(temporal.getProfesor());
      horarios.get(index_modificar).setNrc(temporal.getNrc());
      index_modificar = -1;
      tabla_horarios.refresh();
    } else {
      horarios.add(verificarIdUnico(temporal));
    }
    tf_profesor.clear();
    tf_nrc.clear();
  }

  @FXML
  private void modificar_horario(ActionEvent event) {
    if(tabla_horarios.getSelectionModel().isEmpty()){
      this.alertaNoHorarioSeleccionado();
      return;
    }
    label_campos_horario.setText("");
    HorarioMateria temporal=tabla_horarios.getSelectionModel().getSelectedItem();
    tf_profesor.setText(temporal.getProfesor());
    tf_nrc.setText(Integer.toString(temporal.getNrc()));
    tf_profesor.requestFocus();
    index_modificar=tabla_horarios.getSelectionModel().getSelectedIndex();
    tabla_horarios.getSelectionModel().clearSelection();
  }

  @FXML
  private void eliminar_horario(ActionEvent event) {
    if(tabla_horarios.getSelectionModel().isEmpty()){
      this.alertaNoHorarioSeleccionado();
      return;
    }
    if (index_modificar != -1) {
      tabla_horarios.getSelectionModel().clearSelection();
      alerta = new Alert(Alert.AlertType.WARNING);
      alerta.setTitle("Advertencia");
      alerta.setHeaderText("Primero debes terminar de modificar el horario");
      alerta.setContentText("Termina de llenar de llenar los campos de profesor y nrc "
              + "y da clic en Agregar horario");
      alerta.showAndWait();
      tf_profesor.requestFocus();
      return;
    }
    label_campos_horario.setText("");
    alerta = new Alert(Alert.AlertType.CONFIRMATION);
    alerta.setTitle("Confirmación");
    alerta.setHeaderText("¿Estás seguro de eliminar los horarios seleccionados?");
    Optional<ButtonType> resultado = alerta.showAndWait();
    if (resultado.get() == ButtonType.OK) {
      tabla_horarios.getItems().removeAll(tabla_horarios.getSelectionModel().getSelectedItems());
      tabla_horarios.getSelectionModel().clearSelection();
    }
  }

  @FXML
  private void agregar_dia(ActionEvent event) {
    label_campos_dia.setText("");
    if(tabla_horarios.getSelectionModel().isEmpty()){
      this.alertaNoHorarioSeleccionado();
      return;
    }
    if(tf_salon.getText().isEmpty()){
      label_campos_dia.setText("* Todos los campos son obligatorios");
      return;
    }
    if(LocalTime.parse(tf_hora_inicio.getText())
            .compareTo(LocalTime.parse(tf_hora_fin.getText())) >=0){
      label_campos_dia.setText("* La hora de fin debe ser mayor a la de inicio");
      return;
    }
    DiaMateria temporal = new DiaMateria(
            comboBox_dias.getValue(),
            LocalTime.parse(tf_hora_inicio.getText()),
            LocalTime.parse(tf_hora_fin.getText()),
            tf_salon.getText(),
            tabla_horarios.getSelectionModel().getSelectedItem().getId_horario());
    tabla_horarios.getSelectionModel().getSelectedItem().
            addDia(asignarIndiceDias(temporal.getDia()), temporal);
    tabla_horarios.refresh();
    this.limpiarTextfieldsDias();
  }

  @FXML
  private void eliminar_dia(ActionEvent event) {
    label_campos_dia.setText("");
    if(tabla_horarios.getSelectionModel().isEmpty()){
      this.alertaNoHorarioSeleccionado();
      return;
    }
    DiaMateria dia_vacio=new DiaMateria();
    tabla_horarios.getSelectionModel().getSelectedItem().
            addDia(asignarIndiceDias(comboBox_dias.getValue()), dia_vacio);
    tabla_horarios.refresh();
    this.limpiarTextfieldsDias();
  }
  
  public HorarioMateria verificarIdUnico(HorarioMateria temporal) {
    for (int index = 0; index < todos_los_horarios.size(); index++) {
      if (todos_los_horarios.get(index).getId_horario()==(temporal.getId_horario())) {
        temporal.setId_horarioAutoincremental();
      }
    }
    return temporal;
  }
  
  public int asignarIndiceDias(String dia){
    int indice=0;
    switch (dia) {
      case "Martes":
        indice=1;
        break;
      case "Miércoles":
        indice=2;
        break;
      case "Jueves":
        indice=3;
        break;
      case "Viernes":
        indice=4;
        break;
      case "Sábado":
        indice=5;
        break;
    }
    return indice;
  }
  
  public void limpiarTextfieldsDias(){
    tf_hora_inicio.setText("00:00");
    tf_hora_fin.setText("00:00");
    tf_salon.clear();
  }
  
  public void alertaNoHorarioSeleccionado(){
    alerta = new Alert(Alert.AlertType.WARNING);
      alerta.setTitle("Indicación");
      alerta.setHeaderText("Primero debes seleccionar un horario de la tabla");
      alerta.showAndWait();
  }
  
  public void cargarHorarios(){
    todos_los_horarios=principal.getHorariosMaterias();
    for (int i = 0; i < todos_los_horarios.size(); i++) {
      if(todos_los_horarios.get(i).getId_materia()==materia.getId_materia()){
        horarios.add(todos_los_horarios.get(i));
      }
    }
  }
  
  public void guardarHorarios() {
    Predicate<HorarioMateria> predicado_horarios = p -> p.getId_materia() == materia.getId_materia();
    todos_los_horarios.removeIf(predicado_horarios);
    for (int i = 0; i < horarios.size(); i++) {
      todos_los_horarios.add(horarios.get(i));
    }
    principal.setHorariosMaterias(todos_los_horarios);
  }
   
}
