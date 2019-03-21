/** Interfaz DAO de Alumnos
 *
 * @author Luis Roberto Herrera Hernández
 * @version 1.0, 03/21/19
 *
 * Descripción: aqui se crean los métodos que necesitaran acceso a la base de datos.
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
  
  /**
   *
   * @param conexion
   * @return ObservableList
   */
  public ObservableList<Alumno> crearLista(Connection conexion);

  /**
   *
   * @param conexion
   */
  public void eliminarTabla(Connection conexion);

  /**
   *
   * @param estudiantes
   * @param conexion
   */
  public void guardarLista(ObservableList<Alumno> estudiantes, Connection conexion);
  
}
