/**
 * Copyright (c) 2018-2019 LR
 * La venta de este archivo por cualquier medio está estrictamente prohibida.
 * Todos los derechos reservados.
 */
package alumnos.DAO.HorarioAlumnos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luis Roberto Herrera Hernández
 */
public class HorarioAlumnosDAO implements IHorarioAlumnosDAO{
  
  private final String tabla = "horario_alumno";

  @Override
  public ArrayList<Integer> cargarHorario(String matricula, Connection conexion) {
    ArrayList<Integer> arreglo_id_horarios = new ArrayList<>();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT* FROM " + this.tabla 
                 + " WHERE matricula=" + "'"+matricula+"'");
         ResultSet resultado=consulta.executeQuery();
         while(resultado.next()){
            arreglo_id_horarios.add(resultado.getInt("id_horario"));
         }
      }catch(SQLException ex){
        System.out.println("Error al cargar los datos de la tabla");
         System.out.print(ex);
      }
      return arreglo_id_horarios;
  }

  @Override
  public void guardarHorario(String matricula, int[] arreglo_id_horarios, Connection conexion) {
    eliminarTabla(matricula, conexion);
    try{
         PreparedStatement guardar = conexion.prepareStatement("insert into " + this.tabla
                 + " values (?, ?)");
         for(int i=0; i<arreglo_id_horarios.length; i++){
           guardar.setString(1, matricula);
           guardar.setInt(2, arreglo_id_horarios[i]);
           guardar.executeUpdate();
         }
      }catch(SQLException ex){
        System.out.println("Error al guardar los registros de la tabla");
         System.out.print(ex);
      }
  }

  @Override
  public void eliminarTabla(String matricula, Connection conexion) {
    try{
         PreparedStatement eliminar = conexion.prepareStatement("DELETE FROM " + this.tabla
         + " WHERE matricula=" + "'"+matricula+"'");
         eliminar.executeUpdate();
      }catch(SQLException ex){
        System.out.println("Error al eliminar los registros de la tabla horarios");
         System.out.print(ex);
      }
  }
  
}
