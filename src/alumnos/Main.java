/**     
 * Copyright (c) 2018-2019 LR
 * La venta de este archivo por cualquier medio está estrictamente prohibida.
 * Todos los derechos reservados.
 */
package alumnos;

import alumnos.controladores.FXMLAlumnosController;
import alumnos.controladores.FXMLMateriasController;
import alumnos.controladores.FXMLPrincipalController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  
  private Stage stage_principal;
  private Parent root;
  
  public Main(){}
  
  public void mostrarVentanaPrincipal(){
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaces/FXMLPrincipal.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            Stage principal=new Stage();
            principal.setTitle("Sistema de Administración de Alumnos");
            principal.setScene(scene);
            FXMLPrincipalController controlador = loader.getController();
            controlador.setPrincipal(this);
            this.stage_principal=principal;
            this.stage_principal.show();
        } catch (IOException e) {
          System.out.println("No se encuentra el archivo de la interfaz");
        }
  }
  
  public void mostrarVentanaAlumnos(){
    try {
      stage_principal.hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaces/FXMLAlumnos.fxml"));
            Parent p_alumnos = loader.load();
            Scene scene = new Scene(p_alumnos);
            Stage ventana_alumnos=new Stage();
            ventana_alumnos.setTitle("Alumnos");
            ventana_alumnos.setScene(scene);
            FXMLAlumnosController controlador = loader.getController();
            controlador.setPrincipal(this);
            ventana_alumnos.show();
        } catch (IOException e) {
          System.out.println("No se encuentra el archivo de la interfaz");
        }
  }
  
  public void mostrarVentanaMaterias(){
    try {
      stage_principal.hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaces/FXMLMaterias.fxml"));
            Parent p_materias = loader.load();
            Scene scene = new Scene(p_materias);
            Stage ventana_materias=new Stage();
            ventana_materias.setTitle("Materias");
            ventana_materias.setScene(scene);
            FXMLMateriasController controlador = loader.getController();
            controlador.setPrincipal(this);
            ventana_materias.show();
        } catch (IOException e) {
          System.out.println("No se encuentra el archivo de la interfaz");
          System.out.println(e);
        }
  }
  
  @Override
  public void start(Stage stage) throws Exception {
    this.mostrarVentanaPrincipal();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
  
}
