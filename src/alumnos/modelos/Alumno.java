/**
 * Copyright (c) 2018-2019 LR
 * La venta de este archivo por cualquier medio está estrictamente prohibida.
 * Todos los derechos reservados.
 */
package alumnos.modelos;

import javafx.beans.property.SimpleStringProperty;

/**
 * <h1>Modelo de Alumno</h1>
 *
 * En este archivo se definen los atributos y métodos necesarios del objeto Alumno. 
 * Creado el 11/03/2019.
 *
 * @author Luis Roberto H. H.
 * @since 1.0
 * @version 1.0, 2019-03-11
 */
public class Alumno {

  private final SimpleStringProperty matricula;
  private final SimpleStringProperty nombre;
  private final SimpleStringProperty a_paterno;
  private final SimpleStringProperty a_materno;

  /**
   *
   * @param matricula
   * @param nombre
   * @param a_paterno
   * @param a_materno
   */
  public Alumno(String matricula, String nombre, String a_paterno, String a_materno) {
    this.matricula = new SimpleStringProperty(matricula);
    this.nombre = new SimpleStringProperty(nombre);
    this.a_paterno = new SimpleStringProperty(a_paterno);
    this.a_materno = new SimpleStringProperty(a_materno);
  }

  /**
   *
   * @return
   */
  public String getMatricula() {
    return matricula.get();
  }

  /**
   *
   * @return
   */
  public SimpleStringProperty getMatriculaProperty() {
    return matricula;
  }

  /**
   *
   * @return
   */
  public String getNombre() {
    return nombre.get();
  }

  /**
   *
   * @return
   */
  public SimpleStringProperty getNombreProperty() {
    return nombre;
  }

  /**
   *
   * @return
   */
  public String getA_paterno() {
    return a_paterno.get();
  }

  /**
   *
   * @return
   */
  public SimpleStringProperty getPaternoProperty() {
    return a_paterno;
  }

  /**
   *
   * @return
   */
  public String getA_materno() {
    return a_materno.get();
  }

  /**
   *
   * @return
   */
  public SimpleStringProperty getMaternoProperty() {
    return a_materno;
  }

  @Override
  public String toString() {
    return matricula.get() + " " + nombre.get() + " " + a_paterno.get() + " " + a_materno.get();
  }

}
