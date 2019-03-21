/** Título
 *
 * @author Luis Roberto Herrera Hernández
 * @version 1.0, mm/dd/aa
 *
 * Descripción: 
 */
package alumnos.modelos;

import javafx.beans.property.SimpleStringProperty;

public class Alumno{
   private final SimpleStringProperty matricula;
   private final SimpleStringProperty nombre;
   private final SimpleStringProperty a_paterno;
   private final SimpleStringProperty a_materno;
   
   public Alumno(String matricula, String nombre, String a_paterno, String a_materno) {
      this.matricula = new SimpleStringProperty(matricula);
      this.nombre = new SimpleStringProperty(nombre);
      this.a_paterno = new SimpleStringProperty(a_paterno);
      this.a_materno = new SimpleStringProperty(a_materno);
   }
   public String getMatricula() {
      return matricula.get();
   }
   public SimpleStringProperty getMatriculaProperty(){
     return matricula;
   }
   public String getNombre() {
      return nombre.get();
   }
   public SimpleStringProperty getNombreProperty(){
     return nombre;
   }
   public String getA_paterno() {
      return a_paterno.get();
   }
   public SimpleStringProperty getPaternoProperty() {
     return a_paterno;
   }
   public String getA_materno() {
      return a_materno.get();
   }
   public SimpleStringProperty getMaternoProperty(){
     return a_materno;
   }
   public void setMatricula(String matricula){
     this.matricula.set(matricula);
   }
   public void setNombre(String nombre) {
      this.nombre.set(nombre);
   }
   public void setA_paterno(String a_paterno) {
      this.a_paterno.set(a_paterno);
   }
   public void setA_materno(String a_materno) {
      this.a_materno.set(a_materno);
   }
   @Override
   public String toString() {
      return matricula.get() +" "+ nombre.get() +" "+ a_paterno.get() +" "+ a_materno.get();
   } 

}