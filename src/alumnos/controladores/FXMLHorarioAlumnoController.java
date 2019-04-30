/**
 * Copyright (c) 2018-2019 LR
 * La venta de este archivo por cualquier medio está estrictamente prohibida.
 * Todos los derechos reservados.
 */
package alumnos.controladores;

import alumnos.DAO.HorarioAlumnos.HorarioAlumnosDAO;
import alumnos.DAO.HorarioMaterias.HorarioMateriasDAO;
import alumnos.DAO.Materias.MateriasDAO;
import alumnos.modelos.Alumno;
import alumnos.modelos.HorarioMateria;
import alumnos.modelos.Materia;
import alumnos.servicios.Conexion;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Luis Roberto Herrera Hernández
 */
public class FXMLHorarioAlumnoController implements Initializable{

  @FXML
  private Color x1;
  @FXML
  private TableView<FilaTablaHorarioAlumno> tabla_horario_alumno;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, Number> horario_columna_nrc;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, String> horario_columna_nombre;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, String> horario_columna_profesor;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, LocalTime> horario_columna_lunes_inicio;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, LocalTime> horario_columna_lunes_fin;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, String> horario_columna_lunes_salon;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, LocalTime> horario_columna_martes_inicio;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, LocalTime> horario_columna_martes_fin;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, String> horario_columna_martes_salon;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, LocalTime> horario_columna_miercoles_inicio;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, LocalTime> horario_columna_miercoles_fin;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, String> horario_columna_miercoles_salon;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, LocalTime> horario_columna_jueves_inicio;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, LocalTime> horario_columna_jueves_fin;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, String> horario_columna_jueves_salon;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, LocalTime> horario_columna_viernes_inicio;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, LocalTime> horario_columna_viernes_fin;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, String> horario_columna_viernes_salon;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, LocalTime> horario_columna_sabado_inicio;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, LocalTime> horario_columna_sabado_fin;
  @FXML
  private TableColumn<FilaTablaHorarioAlumno, String> horario_columna_sabado_salon;
  @FXML
  private Button boton_regresar;
  @FXML
  private Font x6;
  @FXML
  private Pane pane_horario_alumno;
  @FXML
  private Label label_alumno;
  @FXML
  private Button boton_agregar_materia;
  @FXML
  private Font x3;
  @FXML
  private Button boton_eliminar_materia;
  @FXML
  private Button boton_finalizar_horario;
  @FXML
  private Font x2;
  @FXML
  private Font x4;
  @FXML
  private Label label_creditos;
  @FXML
  private Pane pane_materias;
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
  private Button boton_ver_horarios;
  @FXML
  private Button boton_regresar_pane_horario;
  @FXML
  private Pane pane_horarios_materia;
  @FXML
  private TableView<HorarioMateria> tabla_horarios_materia;
  @FXML
  private TableColumn<HorarioMateria, Number> horariosmateria_columna_nrc;
  @FXML
  private TableColumn<HorarioMateria, String> horariosmateria_columna_profesor;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> horariosmateria_columna_lunes_inicio;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> horariosmateria_columna_lunes_fin;
  @FXML
  private TableColumn<HorarioMateria, String> horariosmateria_columna_lunes_salon;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> horariosmateria_columna_martes_inicio;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> horariosmateria_columna_martes_fin;
  @FXML
  private TableColumn<HorarioMateria, String> horariosmateria_columna_martes_salon;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> horariosmateria_columna_miercoles_inicio;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> horariosmateria_columna_miercoles_fin;
  @FXML
  private TableColumn<HorarioMateria, String> horariosmateria_columna_miercoles_salon;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> horariosmateria_columna_jueves_inicio;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> horariosmateria_columna_jueves_fin;
  @FXML
  private TableColumn<HorarioMateria, String> horariosmateria_columna_jueves_salon;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> horariosmateria_columna_viernes_inicio;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> horariosmateria_columna_viernes_fin;
  @FXML
  private TableColumn<HorarioMateria, String> horariosmateria_columna_viernes_salon;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> horariosmateria_columna_sabado_inicio;
  @FXML
  private TableColumn<HorarioMateria, LocalTime> horariosmateria_columna_sabado_fin;
  @FXML
  private TableColumn<HorarioMateria, String> horariosmateria_columna_sabado_salon;
  @FXML
  private Button boton_incluir;
  @FXML
  private Button boton_regresar_pane_materias;
  
  private Stage stage;
  private Alumno estudiante;
  private Conexion conn;
  private Alert alerta;
  private final MateriasDAO sql_materias = new MateriasDAO();
  private final HorarioMateriasDAO sql_horarios = new HorarioMateriasDAO();
  private final HorarioAlumnosDAO sql_horario_alumno=new HorarioAlumnosDAO();
  
  private ObservableList<Materia> experiencias_educativas = FXCollections.observableArrayList();
  private ObservableList<HorarioMateria> todos_los_horarios = FXCollections.observableArrayList();
  private ObservableList<FilaTablaHorarioAlumno> filas=FXCollections.observableArrayList();
  
  public class FilaTablaHorarioAlumno{
    
    private Materia materia;
    private HorarioMateria horario;
    
    public FilaTablaHorarioAlumno(Materia materia, HorarioMateria horario) {
      this.materia = materia;
      this.horario = horario;
    }

    public Materia getMateria() {
      return this.materia;
    }

    public HorarioMateria getHorarioMateria() {
      return this.horario;
    }

  }
          
  public FXMLHorarioAlumnoController(){}
  
  public void setAlumno(Alumno estudiante){
    this.estudiante=estudiante;
    label_alumno.setText(this.estudiante.getMatricula() + " - " + this.estudiante.getNombre()
            + " " + this.estudiante.getA_paterno() + " " + this.estudiante.getA_materno());
    this.inicializarHorarioAlumno();
  }
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.pane_materias.setVisible(false);
    this.pane_horarios_materia.setVisible(false);
    conn = new Conexion();
    experiencias_educativas = sql_materias.crearLista(conn.getConexion());
    todos_los_horarios = sql_horarios.cargarHorarios(conn.getConexion());
    conn.desconectar();
    this.inicializarTablaHorarioAlumno();
    this.inicializarTablaMaterias();
    this.inicializarTablaHorariosMateria();
  }
  
  public void inicializarTablaHorarioAlumno(){
    tabla_horario_alumno.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    horario_columna_nrc.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().getNrcProperty());
    horario_columna_nombre.setCellValueFactory(
            cellData -> cellData.getValue().getMateria().getNombreProperty());
    horario_columna_profesor.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().getProfesorProperty());
    horario_columna_lunes_inicio.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().dias.get(0).getHora_inicioProperty());
    horario_columna_lunes_fin.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().dias.get(0).getHora_finProperty());
    horario_columna_lunes_salon.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().dias.get(0).getSalonProperty());
    horario_columna_martes_inicio.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().dias.get(1).getHora_inicioProperty());
    horario_columna_martes_fin.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().dias.get(1).getHora_finProperty());
    horario_columna_martes_salon.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().dias.get(1).getSalonProperty());
    horario_columna_miercoles_inicio.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().dias.get(2).getHora_inicioProperty());
    horario_columna_miercoles_fin.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().dias.get(2).getHora_finProperty());
    horario_columna_miercoles_salon.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().dias.get(2).getSalonProperty());
    horario_columna_jueves_inicio.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().dias.get(3).getHora_inicioProperty());
    horario_columna_jueves_fin.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().dias.get(3).getHora_finProperty());
    horario_columna_jueves_salon.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().dias.get(3).getSalonProperty());
    horario_columna_viernes_inicio.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().dias.get(4).getHora_inicioProperty());
    horario_columna_viernes_fin.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().dias.get(4).getHora_finProperty());
    horario_columna_viernes_salon.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().dias.get(4).getSalonProperty());
    horario_columna_sabado_inicio.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().dias.get(5).getHora_inicioProperty());
    horario_columna_sabado_fin.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().dias.get(5).getHora_finProperty());
    horario_columna_sabado_salon.setCellValueFactory(
            cellData -> cellData.getValue().getHorarioMateria().dias.get(5).getSalonProperty());
    tabla_horario_alumno.setPlaceholder(new Label(" No hay experencias educativas agregadas al horario."));
    tabla_horario_alumno.setItems(filas);
  }
  
  public void inicializarTablaMaterias(){
    tabla_materias.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    materias_columna_nombre.setCellValueFactory(
            cellData -> cellData.getValue().getNombreProperty());
    materias_columna_horas_teoria.setCellValueFactory(
            cellData -> cellData.getValue().getHoras_teoriaProperty());
    materias_columna_horas_practica.setCellValueFactory(
            cellData -> cellData.getValue().getHoras_practicaProperty());
    materias_columna_creditos.setCellValueFactory(
            cellData -> cellData.getValue().getCreditosProperty());
    tabla_materias.setPlaceholder(new Label(" No hay experencias educativas registradas."));
    tabla_materias.setItems(experiencias_educativas);
  }
  
  public void inicializarTablaHorariosMateria(){
    tabla_horarios_materia.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    horariosmateria_columna_nrc.setCellValueFactory(
            cellData -> cellData.getValue().getNrcProperty());
    horariosmateria_columna_profesor.setCellValueFactory(
            cellData -> cellData.getValue().getProfesorProperty());
    horariosmateria_columna_lunes_inicio.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(0).getHora_inicioProperty());
    horariosmateria_columna_lunes_fin.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(0).getHora_finProperty());
    horariosmateria_columna_lunes_salon.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(0).getSalonProperty());
    horariosmateria_columna_martes_inicio.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(1).getHora_inicioProperty());
    horariosmateria_columna_martes_fin.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(1).getHora_finProperty());
    horariosmateria_columna_martes_salon.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(1).getSalonProperty());
    horariosmateria_columna_miercoles_inicio.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(2).getHora_inicioProperty());
    horariosmateria_columna_miercoles_fin.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(2).getHora_finProperty());
    horariosmateria_columna_miercoles_salon.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(2).getSalonProperty());
    horariosmateria_columna_jueves_inicio.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(3).getHora_inicioProperty());
    horariosmateria_columna_jueves_fin.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(3).getHora_finProperty());
    horariosmateria_columna_jueves_salon.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(3).getSalonProperty());
    horariosmateria_columna_viernes_inicio.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(4).getHora_inicioProperty());
    horariosmateria_columna_viernes_fin.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(4).getHora_finProperty());
    horariosmateria_columna_viernes_salon.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(4).getSalonProperty());
    horariosmateria_columna_sabado_inicio.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(5).getHora_inicioProperty());
    horariosmateria_columna_sabado_fin.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(5).getHora_finProperty());
    horariosmateria_columna_sabado_salon.setCellValueFactory(
            cellData -> cellData.getValue().dias.get(5).getSalonProperty());
    tabla_horarios_materia.setPlaceholder(new Label(" No hay horarios registrados."));
  }

  public void inicializarHorarioAlumno(){
    conn=new Conexion();
    ArrayList<Integer> arreglo_id_horarios=sql_horario_alumno.cargarHorario(estudiante.getMatricula(), conn.getConexion());
   conn.desconectar();
   HorarioMateria temporal;
    for (int i = 0; i < arreglo_id_horarios.size(); i++) {
      temporal=buscarHorario(arreglo_id_horarios.get(i));
      if(temporal!=null){
        filas.add(new FilaTablaHorarioAlumno(
                buscarMateria(temporal.getId_materia()),
                temporal
        ));
      }
    }
  }
  
  private HorarioMateria buscarHorario(int id_horario){
    for (int i = 0; i < todos_los_horarios.size(); i++) {
      if(todos_los_horarios.get(i).getId_horario()==id_horario){
        return todos_los_horarios.get(i);
      }
    }
    return null;
  }
  
  private Materia buscarMateria(int id_materia){
    for (int i = 0; i < experiencias_educativas.size(); i++) {
      if(experiencias_educativas.get(i).getId_materia()==id_materia){
        return experiencias_educativas.get(i);
      }
    }
    return null;
  }
  
  @FXML
  private void abrirVentanaMaterias(ActionEvent event) {
    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    stage.close();
  }

  @FXML
  private void mostrarPaneMaterias(ActionEvent event) {
    this.pane_horario_alumno.setVisible(false);
    this.pane_materias.setVisible(true);
  }

  @FXML
  private void eliminarMateria(ActionEvent event) {
    if (tabla_horario_alumno.getSelectionModel().isEmpty()) {
      this.alertaNoElementoSeleccionado();
      return;
    }
  }

  @FXML
  private void finalizarHorario(ActionEvent event) {
    int[] arreglo_id_horarios=new int[filas.size()];
    for (int i = 0; i < filas.size(); i++) {
      arreglo_id_horarios[i]=filas.get(i).getHorarioMateria().getId_horario();
    }
    conn=new Conexion();
    sql_horario_alumno.guardarHorario(estudiante.getMatricula(), arreglo_id_horarios, conn.getConexion());
    conn.desconectar();
  }

  @FXML
  private void mostrarPaneHorariosMateria(ActionEvent event) {
    if (tabla_materias.getSelectionModel().isEmpty()) {
      this.alertaNoElementoSeleccionado();
      return;
    }
    this.pane_materias.setVisible(false);
    this.pane_horarios_materia.setVisible(true);
    ObservableList<HorarioMateria> horarios_por_materia = FXCollections.observableArrayList();
    for (int i = 0; i < todos_los_horarios.size(); i++) {
      if(todos_los_horarios.get(i).getId_materia() == tabla_materias.getSelectionModel().getSelectedItem().getId_materia()){
        horarios_por_materia.add(todos_los_horarios.get(i));
      }
    }
    this.tabla_horarios_materia.setItems(horarios_por_materia);
  }

  @FXML
  private void mostrarPaneHorarioRegreso(ActionEvent event) {
    this.pane_materias.setVisible(false);
    this.pane_horario_alumno.setVisible(true);
  }

  @FXML
  private void incluirHorarioMateria(ActionEvent event) {
    if (tabla_horarios_materia.getSelectionModel().isEmpty()) {
      this.alertaNoElementoSeleccionado();
      return;
    }
    FilaTablaHorarioAlumno temporal=new FilaTablaHorarioAlumno(
            tabla_materias.getSelectionModel().getSelectedItem(), 
            tabla_horarios_materia.getSelectionModel().getSelectedItem());
    filas.add(temporal);
  }

  @FXML
  private void mostrarPaneMateriasRegreso(ActionEvent event) {
    this.pane_horarios_materia.setVisible(false);
    this.pane_materias.setVisible(true);
  }
  
  public void alertaNoElementoSeleccionado(){
    alerta = new Alert(Alert.AlertType.WARNING);
      alerta.setTitle("Indicación");
      alerta.setHeaderText("Primero debes seleccionar un elemento de la tabla");
      alerta.showAndWait();
  }
  
}
