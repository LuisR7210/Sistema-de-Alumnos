/**
 * Copyright (c) 2018-2019 LR
 * La venta de este archivo por cualquier medio está estrictamente prohibida.
 * Todos los derechos reservados.
 */
package alumnos.DAO.HorarioAlumnos;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Luis Roberto Herrera Hernández
 */
public interface IHorarioAlumnosDAO {
  
  public ArrayList<Integer> cargarHorario(String matricula, Connection conexion);
  
  public void guardarHorario(String matricula, int arreglo_id_horarios[], Connection conexion);
  
  public void eliminarTabla(String matricula, Connection conexion);
  
}
