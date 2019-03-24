/**
 * Copyright (c) 2018-2019 LR
 * La venta de este archivo por cualquier medio está estrictamente prohibida.
 * Todos los derechos reservados.
 */
package alumnos.controladores;

import alumnos.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Luis Roberto Herrera Hernández
 */
public class FXMLPrincipalController implements Initializable {

  @FXML
  private Font x1;
  
  private Main principal;
  private Stage stage;

  public void setPrincipal(Main programa_principal) {
        this.principal = programa_principal;
    }
  
  /**
   * Initializes the controller class.
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }  

  @FXML
  private void abrirVentanaAlumnos(ActionEvent event) {
    principal.mostrarVentanaAlumnos();
  }

  @FXML
  private void abrirVentanaMaterias(ActionEvent event) {
    principal.mostrarVentanaMaterias();
  }

  @FXML
  private void salir(ActionEvent event) {
    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    stage.close();
  }
  
}
