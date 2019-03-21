/** Título
 *
 * @author Luis Roberto Herrera Hernández
 * @version 1.0, mm/dd/aa
 *
 * Descripción: aqui va la descripción
 */
package alumnos.DAO.Alumnos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import alumnos.modelos.Alumno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AlumnosDAO {
  
   private final String tabla = "alumno";
   
   public AlumnosDAO(){}
   
   public ObservableList<Alumno> crearLista(Connection conexion) throws SQLException{
      ObservableList<Alumno> estudiantes = FXCollections.observableArrayList();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT* FROM " + this.tabla + " ORDER BY a_paterno");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            estudiantes.add(new Alumno(resultado.getString("matricula"), resultado.getString("nombre"),
                    resultado.getString("a_paterno"), resultado.getString("a_materno")));
         }
      }catch(SQLException ex){
        System.out.println("Error al cargar los datos de la tabla");
         System.out.print(ex);
      }
      return estudiantes;
   }
  
   public void guardarLista(ObservableList<Alumno> estudiantes, Connection conexion) throws SQLException{
     eliminarTabla(conexion);
     try{
         PreparedStatement guardar = conexion.prepareStatement("insert into " +this.tabla+ " values (?, ?, ?, ?)");
         for(int i=0; i<estudiantes.size(); i++){
           guardar.setString(1, estudiantes.get(i).getMatricula());
           guardar.setString(2, estudiantes.get(i).getNombre());
           guardar.setString(3, estudiantes.get(i).getA_paterno());
           guardar.setString(4, estudiantes.get(i).getA_materno());
           guardar.executeUpdate();
         }
      }catch(SQLException ex){
        System.out.println("Error al guardar los registros de la tabla");
         System.out.print(ex);
      }
   }
   
   public void eliminarTabla(Connection conexion) throws SQLException{
     try{
         PreparedStatement eliminar = conexion.prepareStatement("DELETE FROM "+this.tabla);
         eliminar.executeUpdate();
      }catch(SQLException ex){
        System.out.println("Error al eliminar los registros de la tabla");
         System.out.print(ex);
      }
   }
}