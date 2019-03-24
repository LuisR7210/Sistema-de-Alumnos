/**     
 * Copyright (c) 2018-2019 LR
 * La venta de este archivo por cualquier medio está estrictamente prohibida.
 * Todos los derechos reservados.
 */
/** DAO de Materias
 *
 * @author Luis Roberto Herrera Hernández
 * @version 1.0, 03/21/19
 *
 * Descripción: esta clase de 
 */
package alumnos.DAO.Materias;

import alumnos.modelos.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MateriasDAO implements IMateriasDAO{
  
   private final String tabla = "materia";
   
   public MateriasDAO(){}
   
  /**
   *
   * @param conexion
   * @return
   */
  @Override
   public ObservableList<Materia> crearLista(Connection conexion){
      ObservableList<Materia> experencias_educativas = FXCollections.observableArrayList();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT* FROM " + this.tabla
                 + " ORDER BY nombre");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            experencias_educativas.add(new Materia(resultado.getString("nombre"), 
                    resultado.getInt("horas_teoria"), resultado.getInt("horas_practica"), 
                    resultado.getInt("creditos"), resultado.getInt("nrc")));
         }
      }catch(SQLException ex){
        System.out.println("Error al cargar los datos de la tabla");
         System.out.print(ex);
      }
      return experencias_educativas;
   }
  
  /**
   *
   * @param experencias_educativas
   * @param conexion
   */
  @Override
   public void guardarLista(ObservableList<Materia> experencias_educativas, Connection conexion){
     try{
       eliminarTabla(conexion);
         PreparedStatement guardar = conexion.prepareStatement("insert into " + this.tabla
                 + "(nombre, horas_teoria, horas_practica, creditos, nrc) values (?, ?, ?, ?, ?)");
         for(int i=0; i<experencias_educativas.size(); i++){
           guardar.setString(1, experencias_educativas.get(i).getNombre());
           guardar.setInt(2, experencias_educativas.get(i).getHoras_teoria());
           guardar.setInt(3, experencias_educativas.get(i).getHoras_practica());
           guardar.setInt(4, experencias_educativas.get(i).getCreditos());
           guardar.setInt(5, experencias_educativas.get(i).getNrc());
           guardar.executeUpdate();
         }
      }catch(SQLException ex){
        System.out.println("Error al guardar los registros de la tabla");
         System.out.print(ex);
      }
   }
   
  /**
   *
   * @param conexion
   */
  @Override
   public void eliminarTabla(Connection conexion){
     try{
         PreparedStatement eliminar = conexion.prepareStatement("DELETE FROM "+this.tabla);
         eliminar.executeUpdate();
      }catch(SQLException ex){
        System.out.println("Error al eliminar los registros de la tabla");
         System.out.print(ex);
      }
   }
}