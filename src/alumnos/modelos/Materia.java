/**
 * Copyright (c) 2018-2019 LR
 * La venta de este archivo por cualquier medio está estrictamente prohibida.
 * Todos los derechos reservados.
 */
package alumnos.modelos;

import java.util.concurrent.atomic.AtomicInteger;
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

  private static AtomicInteger CONTADOR = new AtomicInteger(0);
  private int id_materia;
  private final SimpleStringProperty nombre;
  private final SimpleIntegerProperty horas_teoria;
  private final SimpleIntegerProperty horas_practica;
  private final SimpleIntegerProperty creditos;

  public Materia(int id_materia, String nombre, Integer horas_teoria, Integer horas_practica,
          Integer creditos) {
    this.id_materia=id_materia;
    this.nombre = new SimpleStringProperty(nombre);
    this.horas_teoria = new SimpleIntegerProperty(horas_teoria);
    this.horas_practica = new SimpleIntegerProperty(horas_practica);
    this.creditos = new SimpleIntegerProperty(creditos);
  }
  
  public Materia(String nombre, Integer horas_teoria, Integer horas_practica,
          Integer creditos) {
    this.id_materia=CONTADOR.incrementAndGet();
    this.nombre = new SimpleStringProperty(nombre);
    this.horas_teoria = new SimpleIntegerProperty(horas_teoria);
    this.horas_practica = new SimpleIntegerProperty(horas_practica);
    this.creditos = new SimpleIntegerProperty(creditos);
  }
  
  public void setId_materia(int id){
    id_materia=id;
  }
  
  public void setId_materia_autoincremental(){
    id_materia=CONTADOR.incrementAndGet();
  }
  
  public int getId_materia(){
    return id_materia;
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

  
  @Override
  public String toString() {
    return nombre.get() + " " + horas_teoria.get() + " " + horas_practica.get() + " " + creditos.get();
  }

}
