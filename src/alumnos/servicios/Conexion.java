/** Título
 *
 * @author Luis Roberto Herrera Hernández
 * @version 1.0, mm/dd/aa
 *
 * Descripción: aqui va la descripción
 */
package alumnos.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Otros
 */
public class Conexion {
  
  private static Connection conn;
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String USER = "luis_alumnos";
  private static final String PASSWORD = "hdks729";
  private static final String HOST = "localhost";
  private static final String BD = "alumnos";
  private static final String URL = "jdbc:mysql://"+HOST+"/"+BD;
  
  public Conexion(){
    conn=null;
    try{
      Class.forName(DRIVER).newInstance();
      conn=DriverManager.getConnection(URL, USER, PASSWORD);
      if(conn!=null){
        System.out.println(" Conexion con la base de datos establecida");
      }
    } catch(ClassNotFoundException e){
      System.out.println(" Clase DRIVER no encontrada");
    } catch(SQLException s){
      System.out.println(" Error al conectar con la base de datos:\n "+s.getMessage());
    } catch (InstantiationException | IllegalAccessException ex) {
      Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
    }
    
  }
  
  public Connection getConexion(){
    return conn;
  }
  
  public void desconectar(){
    conn=null;
    if(conn==null){
      System.out.println(" Conexion terminada");
    }
  }
  
}