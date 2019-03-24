/**     
 * Copyright (c) 2018-2019 LR
 * La venta de este archivo por cualquier medio está estrictamente prohibida.
 * Todos los derechos reservados.
 */
/** 
 * Interfaz DAO de Alumnos
 *
 * @author Luis Roberto Herrera Hernández
 * @version 1.0, 03/21/19
 *
 * Descripción: aqui se crean los métodos que necesitaran acceso a la base de datos.
 */
package alumnos.DAO.Materias;

import alumnos.modelos.Materia;
import java.sql.Connection;
import javafx.collections.ObservableList;

/**
 *
 * @author Otros
 */
public interface IMateriasDAO {
  
  /**
   *
   * @param conexion
   * @return ObservableList
   */
  public ObservableList<Materia> crearLista(Connection conexion);

  /**
   *
   * @param conexion
   */
  public void eliminarTabla(Connection conexion);

  /**
   *
   * @param experencias_educativas
   * @param conexion
   */
  public void guardarLista(ObservableList<Materia> experencias_educativas, Connection conexion);
  
}
