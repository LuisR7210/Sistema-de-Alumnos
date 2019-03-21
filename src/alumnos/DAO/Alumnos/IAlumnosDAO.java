/** Título
 *
 * @author Luis Roberto Herrera Hernández
 * @version 1.0, mm/dd/aa
 *
 * Descripción: aqui va la descripción
 */
package alumnos.DAO.Alumnos;

import alumnos.modelos.Alumno;
import java.sql.Connection;
import javafx.collections.ObservableList;

/**
 *
 * @author Otros
 */
public interface IAlumnosDAO {
  
  public ObservableList<Alumno> crearLista(Connection conexion);
  public void eliminarTabla(Connection conexion);
  public void guardarLista(ObservableList<Alumno> estudiantes, Connection conexion);
  
}
