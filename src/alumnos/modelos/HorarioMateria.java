/**
 * Copyright (c) 2018-2019 LR
 * La venta de este archivo por cualquier medio está estrictamente prohibida.
 * Todos los derechos reservados.
 */
package alumnos.modelos;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Luis Roberto Herrera Hernández
 */
public class HorarioMateria {
    
  private static AtomicInteger CONTADOR = new AtomicInteger(0);
  private int id_horario;
  private SimpleStringProperty profesor;
  private SimpleIntegerProperty nrc;
  private final int id_materia;
  public ArrayList<DiaMateria> dias;
  

  public HorarioMateria(String profesor, int nrc, int id_materia) {
    this.id_horario=CONTADOR.incrementAndGet();
    this.profesor = new SimpleStringProperty(profesor);
    this.nrc=new SimpleIntegerProperty(nrc);
    this.id_materia = id_materia;
    this.inicializarArregloDias();
  }
  
  public HorarioMateria(int id_horario, String profesor, int nrc, int id_materia) {
    this.id_horario=id_horario;
    this.profesor = new SimpleStringProperty(profesor);
    this.nrc=new SimpleIntegerProperty(nrc);
    this.id_materia = id_materia;
    this.inicializarArregloDias();
  }

  public int getId_horario(){
    return id_horario;
  }
  
  public void setId_horario(int id_horario){
    this.id_horario=id_horario;
  }
  
  public void setId_horarioAutoincremental(){
    this.id_horario=CONTADOR.incrementAndGet();
  }
  
  public String getProfesor(){
    return profesor.get();
  }
  
  public SimpleStringProperty getProfesorProperty() {
    return profesor;
  }
  
  public void setProfesor(String profesor){
    this.profesor= new SimpleStringProperty(profesor);
  }
  
  public Integer getNrc(){
    return nrc.get();
  }
  
  public SimpleIntegerProperty getNrcProperty(){
    return nrc;
  }
  
  public void setNrc(int nrc){
    this.nrc=new SimpleIntegerProperty(nrc);
  }

  public int getId_materia() {
    return id_materia;
  }
  
  public void addDia(int index, DiaMateria dia){
    dias.set(index, dia);
  }
  
  public DiaMateria getDia(int index){
    return dias.get(index);
  }
  
  private void inicializarArregloDias(){
    this.dias = new ArrayList<>(6);
    DiaMateria dia=new DiaMateria();
    for(int i=0; i<6; i++){
      dias.add(dia);
    }
  }
  
}
