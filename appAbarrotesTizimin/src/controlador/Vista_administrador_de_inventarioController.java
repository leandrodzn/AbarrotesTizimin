/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
   import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Vista_administrador_de_inventarioController implements Initializable {
    @FXML
    private Button BTN_agregarProducto;
    @FXML
    private Button BTN_modificarProducto;
    @FXML
    private Button BTN_eliminarProducto;
    @FXML
    private Button BTN_verInventario;
    @FXML
    private Button BTN_salir;
    @FXML
    private AnchorPane AP_administrador_de_inventario;
    @FXML
    private Label AP_administrador_inventario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void agregarProducto(ActionEvent event) {
        
        try {
            
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Vista_agregar_producto.fxml"));
            Parent root=  loader.load();
            Vista_agregar_productoController controlador = loader.getController();
            Scene scene= new Scene(root);
            Stage stage = new Stage();
            
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getBounds();
            stage.setResizable(false);
            
            //stage.initModality(Modality.APPLICATION_MODAL
            stage.setTitle("Agregar producto");
            stage.setScene(scene);
            stage.showAndWait();
            
        } catch (IOException ex) {
            Logger.getLogger(Vista_administrador_de_inventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void modificarProducto(ActionEvent event) {
        
        try {
            
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Vista_modificar_producto.fxml"));
            Parent root=  loader.load();
            Modificar_productoController controlador = loader.getController();
            Scene scene= new Scene(root);
            Stage stage = new Stage();
            
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getBounds();
            stage.setResizable(false);
            
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Modificar producto");
            stage.setScene(scene);
            stage.showAndWait();
            
        } catch (IOException ex) {
            Logger.getLogger(Vista_administrador_de_inventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void eliminarProducto(ActionEvent event) {
        
        try {
            
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Vista_eliminar_producto.fxml"));
            Parent root=  (Parent)loader.load();
            Vista_eliminar_productoController controlador = loader.getController();
            Scene scene= new Scene(root);
            
            Stage stage = new Stage();
            
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getBounds();
            stage.setResizable(false);
            
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Eliminar producto");
            stage.setScene(scene);
            stage.showAndWait();
            
        } catch (IOException ex) {
            Logger.getLogger(Vista_administrador_de_inventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void verInventario(ActionEvent event) {
        
        try {
            
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Vista_elementos_inventario.fxml"));
            Parent root=  loader.load();
            Vista_elementos_inventarioController controlador = loader.getController();
            Scene scene= new Scene(root);
            Stage stage = new Stage();
            
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getBounds();
            //stage.setMaximized(true);
            stage.setResizable(false);
            stage.setTitle("Lista de productos");
            //stage.setX(30);
            //stage.setY(40);
            //stage.setWidth(100);
            //stage.setHeight(100);
            
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
              
        } catch (IOException ex) {
            Logger.getLogger(Vista_administrador_de_inventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void salir(ActionEvent event) {
        
        try{
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Main.class.getResource("/vista/VistaMenuPrincipal.fxml"));
            
            ControladorVistaMenuPrincipal controlador = loader.getController();
            
            Pane ventana = (Pane) loader.load();

            Scene scene = new Scene(ventana);
            Stage stage = new Stage();
            
            stage.setResizable(false);
            stage.setTitle("Menú principal");
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.BTN_salir.getScene().getWindow();
            myStage.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        
    }
    
}
