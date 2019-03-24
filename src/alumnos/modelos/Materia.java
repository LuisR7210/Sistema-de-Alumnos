/**
 * Copyright (c) 2018-2019 LR
 * La venta de este archivo por cualquier medio está estrictamente prohibida.
 * Todos los derechos reservados.
 */
package alumnos.modelos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * <h1>Modelo de Materia</h1>
 *
 * En este archivo se definen los atributos y métodos necesarios del objeto Materia. 
 * Creado el 24/03/2019.
 *
 * @author Luis Roberto H. H.
 * @since 1.0
 * @version 1.0, 2019-03-24
 */
public class Materia {

  private final SimpleStringProperty nombre;
  private final SimpleIntegerProperty horas_teoria;
  private final SimpleIntegerProperty horas_practica;
  private final SimpleIntegerProperty creditos;
  private final SimpleIntegerProperty nrc;

  public Materia(String nombre, Integer horas_teoria, Integer horas_practica, Integer creditos, Integer nrc) {
    this.nombre = new SimpleStringProperty(nombre);
    this.horas_teoria = new SimpleIntegerProperty(horas_teoria);
    this.horas_practica = new SimpleIntegerProperty(horas_practica);
    this.creditos = new SimpleIntegerProperty(creditos);
    this.nrc = new SimpleIntegerProperty(nrc);
  }

  public String getNombre() {
    return nombre.get();
  }

  public SimpleStringProperty getNombreProperty() {
    return nombre;
  }

  public Integer getHoras_teoria(){
    return horas_teoria.get();
  }
  
  public SimpleIntegerProperty getHoras_teoriaProperty() {
    return horas_teoria;
  }

  public Integer getHoras_practica(){
    return horas_practica.get();
  }
  
  public SimpleIntegerProperty getHoras_practicaProperty() {
    return horas_practica;
  }

  public Integer getCreditos(){
    return creditos.get();
  }
  
  public SimpleIntegerProperty getCreditosProperty() {
    return creditos;
  }

  public Integer getNrc(){
    return nrc.get();
  }
  
  public SimpleIntegerProperty getNrcProperty() {
    return nrc;
  }

  
  @Override
  public String toString() {
    return nombre.get() + " " + horas_teoria.get() + " " + horas_practica.get() + " " + creditos + " " + nrc;
  }

}
