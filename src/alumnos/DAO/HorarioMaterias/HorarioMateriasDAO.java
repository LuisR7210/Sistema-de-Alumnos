/**
 * Copyright (c) 2018-2019 LR
 * La venta de este archivo por cualquier medio está estrictamente prohibida.
 * Todos los derechos reservados.
 */
package alumnos.DAO.HorarioMaterias;

import alumnos.modelos.DiaMateria;
import alumnos.modelos.HorarioMateria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Luis Roberto Herrera Hernández
 */
public class HorarioMateriasDAO implements IHorarioMateriasDAO{
  
  private final String tabla = "horario_materia";
  private final String tabla2 = "dia_materia";
  
  public HorarioMateriasDAO (){}

  @Override
  public ObservableList<HorarioMateria> cargarHorarios (Connection conexion) {
    ObservableList<HorarioMateria> todos_los_horarios = FXCollections.observableArrayList();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT* FROM " + this.tabla 
                 + " ORDER BY id_horario");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            todos_los_horarios.add(new HorarioMateria(resultado.getInt("id_horario"), 
                    resultado.getString("profesor"), 
                    resultado.getInt("nrc"), 
                    resultado.getInt("id_materia")));
         }
      }catch(SQLException ex){
        System.out.println("Error al cargar los datos de la tabla");
         System.out.print(ex);
      }
      return agregarDias(todos_los_horarios, conexion);
  }

  @Override
  public void guardarHorarios(ObservableList<HorarioMateria> horarios, Connection conexion) {
    try{
         PreparedStatement guardar = conexion.prepareStatement("insert into " + this.tabla
                 + " values (?, ?, ?, ?)");
         for(int i=0; i<horarios.size(); i++){
           guardar.setInt(1, horarios.get(i).getId_horario());
           guardar.setString(2, horarios.get(i).getProfesor());
           guardar.setInt(3, horarios.get(i).getNrc());
           guardar.setInt(4, horarios.get(i).getId_materia());
           guardar.executeUpdate();
         }
      }catch(SQLException ex){
        System.out.println("Error al guardar los registros de la tabla");
         System.out.print(ex);
      }
    guardarDias(horarios, conexion);
  }
  
  @Override
  public void eliminarTabla(Connection conexion) {
    try{
         PreparedStatement eliminar = conexion.prepareStatement("DELETE FROM " + this.tabla);
         eliminar.executeUpdate();
      }catch(SQLException ex){
        System.out.println("Error al eliminar los registros de la tabla horarios");
         System.out.print(ex);
      }
  }
  
  @Override
  public ObservableList<HorarioMateria> agregarDias(ObservableList<HorarioMateria> horarios, Connection conexion) {
    DiaMateria temporal;
    for (int i = 0; i < horarios.size(); i++) {
      try {
        PreparedStatement consulta = conexion.prepareStatement("SELECT* FROM " + this.tabla2
                + " WHERE id_horario=" + horarios.get(i).getId_horario() + " ORDER BY id_horario");
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
          temporal=new DiaMateria(resultado.getString("dia"),
                  LocalTime.parse(resultado.getString("hora_inicio")),
                  LocalTime.parse(resultado.getString("hora_fin")),
                  resultado.getString("salon"),
                  resultado.getInt("id_horario"));
          horarios.get(i).addDia(asignarIndiceDias(temporal.getDia()), temporal);
        }
      } catch (SQLException ex) {
        System.out.println("Error al cargar los datos de la tabla");
        System.out.print(ex);
      }
    }
    return horarios;
  }
  
  @Override
  public void guardarDias(ObservableList<HorarioMateria> horarios, Connection conexion){
    try{
         PreparedStatement guardar = conexion.prepareStatement("insert into " + this.tabla2
                 + " (dia, hora_inicio, hora_fin, salon, id_horario)" 
                         + " values (?, ?, ?, ?, ?)");
         for(int i=0; i<horarios.size(); i++){
           for (int j = 0; j < 6; j++) {
             if(horarios.get(i).getDia(j).getDia()!=null){
               guardar.setString(1, horarios.get(i).getDia(j).getDia());
           guardar.setString(2, horarios.get(i).getDia(j).getHora_inicio().toString());
           guardar.setString(3, horarios.get(i).getDia(j).getHora_fin().toString());
           guardar.setString(4, horarios.get(i).getDia(j).getSalon());
           guardar.setInt(5, horarios.get(i).getDia(j).getId_horario());
           guardar.executeUpdate();
             }
           }
         }
      }catch(SQLException ex){
        System.out.println("Error al guardar los registros de la tabla");
         System.out.print(ex);
      }
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

}
