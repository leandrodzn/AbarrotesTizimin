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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Vista_administrar_clienteController implements Initializable {

   
    @FXML
    private Button BTN_agregarCliente;
    @FXML
    private Button BTN_modificarCliente;
    @FXML
    private Button BTN_eliminarCliente;
    @FXML
    private Button BTN_verClientes;
    @FXML
    private Button BTN_salir;
    @FXML
    private Button BTN_notificar;
    @FXML
    private AnchorPane AP_administrador_cliente;
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
    private void agregarCliente(ActionEvent event) {
        
        try {
            
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Vista_agregar_cliente.fxml"));
            Parent root=  loader.load();
            Vista_agregar_clienteController controlador = loader.getController();
            Scene scene= new Scene(root);
            Stage stage = new Stage();
            
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getBounds();
            stage.setResizable(false);
            

            stage.setTitle("Agregar cliente");
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
    private void modificarCliente(ActionEvent event) {
        
        try {
            
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Vista_modificar_cliente.fxml"));
            Parent root=  loader.load();
            Vista_modificar_clienteController controlador = loader.getController();
            Scene scene= new Scene(root);
            Stage stage = new Stage();
            
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getBounds();
            stage.setResizable(false);
            stage.setTitle("Modificar cliente");
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
    private void eliminarCliente(ActionEvent event) {
        
        try {
            
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Vista_elimar_cliente.fxml"));
            Parent root=  loader.load();
            Vista_elimar_clienteController controlador = loader.getController();
            Scene scene= new Scene(root);
            Stage stage = new Stage();
            
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getBounds();
            stage.setResizable(false);
            stage.setTitle("Eliminar cliente");
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
    private void verClientes(ActionEvent event) {
        
        try {
            
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Vista_lista_clientes.fxml"));
            Parent root=  loader.load();
            Vista_lista_clientesController controlador = loader.getController();
            Scene scene= new Scene(root);
            Stage stage = new Stage();
            
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getBounds();
            //stage.setMaximized(true);
            stage.setResizable(false);
            stage.setTitle("Lista de clientes");
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

    @FXML
    private void notificar(ActionEvent event) {
        
        ControladorCliente.getInstance().notificarTodos();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Clientes notificados");
        alert.setTitle("Éxito");
        alert.setContentText("Los clientes han sido notificados con sus puntos");
        alert.showAndWait();
        
    }
    
}
