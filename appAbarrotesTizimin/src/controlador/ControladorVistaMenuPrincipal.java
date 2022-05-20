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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leodz
 */
public class ControladorVistaMenuPrincipal implements Initializable {

    @FXML
    private Label labelMenu;
    @FXML
    private Button btnRealizarCompra;
    @FXML
    private Button btnAdmInventario;
    @FXML
    private Button btnAdmCliente;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnTicketReciente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickRealizarCompra(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Main.class.getResource("/vista/VistaHacerCompra.fxml"));
            
            //ControladorVistaRegistrarPersonas controlador = loader.getController();
            
            Pane ventana = (Pane) loader.load();

            Scene scene = new Scene(ventana);
            Stage stage = new Stage();
            
            stage.setResizable(false);
            stage.setTitle("Compra");
            stage.setScene(scene);
            stage.show();
            
            Stage myStage = (Stage) this.btnRealizarCompra.getScene().getWindow();
            myStage.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void clickAdmInventario(ActionEvent event) {        
        try{
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Main.class.getResource("/vista/Vista_administrador_de_inventario.fxml"));
            
            Vista_administrador_de_inventarioController controlador = loader.getController();
            
            Pane ventana = (Pane) loader.load();

            Scene scene = new Scene(ventana);
            Stage stage = new Stage();
            
            stage.setResizable(false);
            stage.setTitle("Administrador de inventario");
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.btnAdmInventario.getScene().getWindow();
            myStage.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void clickAdmCliente(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Main.class.getResource("/vista/Vista_administrar_cliente.fxml"));
            
            Vista_administrar_clienteController controlador = loader.getController();
            
            Pane ventana = (Pane) loader.load();

            Scene scene = new Scene(ventana);
            Stage stage = new Stage();
            
            stage.setResizable(false);
            stage.setTitle("Realizar compra");
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.btnAdmCliente.getScene().getWindow();
            myStage.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }


    @FXML
    private void clickSalir(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickTicketReciente(ActionEvent event) {
        if(GeneradorTicket.getInstance().getTicketReciente() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Sin existencia");
            alert.setTitle("Lo sentimos");
            alert.setContentText("Aún no se ha generado algún ticket de compra");
            alert.showAndWait();
        }else{
            GeneradorTicket.getInstance().generarTicket(GeneradorTicket.getInstance().getTicketReciente().getCompra());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Éxito");
                        alert.setContentText("Ticket más reciente generado");
                        alert.showAndWait();
        }
    }
    
}
