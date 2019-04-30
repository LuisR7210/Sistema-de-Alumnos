/**
 * Copyright (c) 2018-2019 LR
 * La venta de este archivo por cualquier medio está estrictamente prohibida.
 * Todos los derechos reservados.
 */
package alumnos.DAO.HorarioMaterias;

import alumnos.modelos.HorarioMateria;
import java.sql.Connection;
import javafx.collections.ObservableList;

/**
 *
 * @author Luis Roberto Herrera Hernández
 */
public interface IHorarioMateriasDAO {
  
  public ObservableList<HorarioMateria> cargarHorarios(Connection conexion);
  
  public void guardarHorarios(ObservableList<HorarioMateria> horarios, Connection conexion);
  
  public void eliminarTabla(Connection conexion);
  
  public ObservableList<HorarioMateria> agregarDias
        (ObservableList<HorarioMateria> horarios, Connection conexion);
  
  public void guardarDias(ObservableList<HorarioMateria> horarios, Connection conexion);
}
