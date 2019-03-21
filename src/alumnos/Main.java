/** Título
 *
 * @author Luis Roberto Herrera Hernández
 * @version 1.0, mm/dd/aa
 *
 * Descripción: aqui va la descripción
 */
package alumnos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  
  public Main(){
  }
  
  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaces/FXMLAlumnos.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stage.setTitle("Sistema de Administración de Alumnos");
    stage.setScene(scene);
    stage.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
  
}
