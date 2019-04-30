/**
 * Copyright (c) 2018-2019 LR
 * La venta de este archivo por cualquier medio está estrictamente prohibida.
 * Todos los derechos reservados.
 */
package alumnos.modelos;

import java.time.LocalTime;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Luis Roberto Herrera Hernández
 */
public class DiaMateria {
  
  private String dia;
  private SimpleObjectProperty<LocalTime> hora_inicio;
  private SimpleObjectProperty<LocalTime> hora_fin;
  private SimpleStringProperty salon;
  private int id_horario;
  
  public DiaMateria(){}
  public DiaMateria (String dia, LocalTime inicio, LocalTime fin, String salon, int horario){
    this.dia=dia;
    this.hora_inicio= new SimpleObjectProperty<>(inicio);
    this.hora_fin=new SimpleObjectProperty<>(fin);
    this.salon=new SimpleStringProperty(salon);
    this.id_horario=horario;
  }

  public String getDia() {
    return dia;
  }

  public LocalTime getHora_inicio() {
    return hora_inicio.get();
  }

  public SimpleObjectProperty<LocalTime> getHora_inicioProperty(){
    return hora_inicio;
  }
  
  public LocalTime getHora_fin() {
    return hora_fin.get();
  }

  public SimpleObjectProperty<LocalTime> getHora_finProperty(){
    return hora_fin;
  }
  
  public String getSalon() {
    return salon.get();
  }
  
  public SimpleStringProperty getSalonProperty(){
    return salon;
  }

  public int getId_horario() {
    return id_horario;
  }

  @Override
  public String toString() {
    return "DiaMateria{" + "dia=" + dia + ", hora_inicio=" + hora_inicio + ", hora_fin=" + hora_fin + ", salon=" + salon + ", id_horario=" + id_horario + '}';
  }
  
  
}
