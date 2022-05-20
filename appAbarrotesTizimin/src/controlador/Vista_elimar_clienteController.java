/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Cliente;
//import modelo.Direccion;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Vista_elimar_clienteController implements Initializable {

    @FXML
    private AnchorPane AP_eliminar_cliente;
    @FXML
    private TextField TXF_Id_cliente;
    @FXML
    private Button BTN_salir;
    @FXML
    private Button BTN_eliminar;
    @FXML
    private Label LB_mensaje;
    @FXML
    private Button BTN_buscar;
    @FXML
    private Label LB_telefono;
    @FXML
    private Label LB_apellido_paterno;
    @FXML
    private Label LB_nombre;
    @FXML
    private Label LB_apellido_materno;
    @FXML
    private Label LB_ciudad;
    @FXML
    private Label LB_calle;
    @FXML
    private Label LB_estado;
    @FXML
    private Label LB_colonia;
    @FXML
    private Label LB_codigo_postal;
    @FXML
    private Label LB_num_casa;
    
    private String id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        this.BTN_eliminar.setDisable(true);
    }    

    @FXML
    private void salir(ActionEvent event) {
        
        ((Stage)AP_eliminar_cliente.getScene().getWindow()).close();
        
    }

    @FXML
    private void eliminar(ActionEvent event) {
        
        //se crea un cliente auxiliar para despues eliminarlo
        Cliente clientent = new Cliente(id);
        ControladorCliente.getInstance().eliminarCliente(ControladorCliente.getInstance().regresarCliente(clientent));
        
        //se desabilita el boton de eliminar
        this.BTN_eliminar.setDisable(true);
        
        //se debliega un mensaje que indica que el cliente a sido elimininado correctamente
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Exito");
        alert.setContentText("Cliente eliminado");
        alert.showAndWait();
        
        //se ponen los cuadros de texto en null
        this.LB_nombre.setText(null);
        this.LB_apellido_paterno.setText(null);
        this.LB_apellido_materno.setText(null);
        this.LB_calle.setText(null);
        this.LB_ciudad.setText(null);
        this.LB_codigo_postal.setText(null);
        this.LB_colonia.setText(null);
        this.LB_estado.setText(null);
        this.LB_num_casa.setText(null);
        this.LB_telefono.setText(null);
        
    }

    @FXML
    private void buscar(ActionEvent event) {
        
        //se guarda el id en una variable 
        String ID=this.TXF_Id_cliente.getText();
        
        //se revisa que el cuadro de texto no este vacio
        //si esta vacio se realiza lo siguiente 
        if(this.TXF_Id_cliente.getText() == null || this.TXF_Id_cliente.getText().trim().isEmpty()){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Inténtelo de nuevo");
            alert.setContentText("Llene el cuadro de texto");
            alert.showAndWait();
            
        }else{//si no esta vacio se comiensa con lo siguiente: 
            
            //se crea un nuevo cliente como aux con solo el id
            Cliente cliente = new Cliente(ID);
            
            //revisamos si el cliente al que buscamos existe 
            if(ControladorCliente.getInstance().existeCliente(cliente)){//si existe se realiza lo siguiente 
                
                //se iguala el cliente aux a un cliente ya existente
                cliente = ControladorCliente.getInstance().regresarCliente(cliente);
                id=ID;
                
                //se despliega en los cuadro de texto la informacion del cliente
                this.LB_nombre.setText(cliente.getNombres());
                this.LB_apellido_paterno.setText(cliente.getApellidoPaterno());
                this.LB_apellido_materno.setText(cliente.getApellidoMaterno());
                this.LB_calle.setText(String.valueOf(cliente.getDireccion().getCalle()));
                this.LB_ciudad.setText(cliente.getDireccion().getCiudad());
                this.LB_codigo_postal.setText(String.valueOf(cliente.getDireccion().getCodigoPostal()));
                this.LB_colonia.setText(cliente.getDireccion().getColonia());
                this.LB_estado.setText(cliente.getDireccion().getEstado());
                this.LB_num_casa.setText(cliente.getDireccion().getNo_casa());
                this.LB_telefono.setText(String.valueOf(cliente.getTelefono()));
                
                this.BTN_eliminar.setDisable(false);
                
                 
            }else{//si el cliente no existe:
                
                //se depliega un mensaje de error que indica que el cliente no existe 
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Intentelo de nuevo");
                alert.setContentText("El cliente no existe");
                alert.showAndWait();
                
                //se desabilita el boton de eliminar 
                this.BTN_eliminar.setDisable(true);
                
                //se ponen los cuadros de texto en null
                this.LB_nombre.setText(null);
                this.LB_apellido_paterno.setText(null);
                this.LB_apellido_materno.setText(null);
                this.LB_calle.setText(null);
                this.LB_ciudad.setText(null);
                this.LB_codigo_postal.setText(null);
                this.LB_colonia.setText(null);
                this.LB_estado.setText(null);
                this.LB_num_casa.setText(null);
                this.LB_telefono.setText(null);
                
            }
        }
    }
    
}
