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
      ObservableList<Materia> experiencias_educativas = FXCollections.observableArrayList();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT* FROM " + this.tabla
                 + " ORDER BY Id_materia");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            experiencias_educativas.add(new Materia(resultado.getInt("id_materia"), 
                    resultado.getString("nombre"), 
                    resultado.getInt("horas_teoria"), resultado.getInt("horas_practica"), 
                    resultado.getInt("creditos")));
         }
      }catch(SQLException ex){
        System.out.println("Error al cargar los datos de la tabla");
         System.out.print(ex);
      }
      return experiencias_educativas;
   }
  
  /**
   *
   * @param experiencias_educativas
   * @param conexion
   */
  @Override
   public void guardarLista(ObservableList<Materia> experiencias_educativas, Connection conexion){
     this.eliminarTabla(conexion);
     try{
         PreparedStatement guardar = conexion.prepareStatement("insert into " + this.tabla
                 + " values (?, ?, ?, ?, ?)");
         for(int i=0; i<experiencias_educativas.size(); i++){
           guardar.setInt(1, experiencias_educativas.get(i).getId_materia());
           guardar.setString(2, experiencias_educativas.get(i).getNombre());
           guardar.setInt(3, experiencias_educativas.get(i).getHoras_teoria());
           guardar.setInt(4, experiencias_educativas.get(i).getHoras_practica());
           guardar.setInt(5, experiencias_educativas.get(i).getCreditos());
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
        System.out.println("Error al eliminar los registros de la tabla materias");
         System.out.print(ex);
      }
   }
}