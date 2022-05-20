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
import modelo.Direccion;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Vista_agregar_clienteController implements Initializable {

    @FXML
    private AnchorPane AP_agregarCliente;
    @FXML
    private Label LB_nombre;
    @FXML
    private Label LB_apellido_1;
    @FXML
    private Label LB_ciudad;
    @FXML
    private Label LB_calle;
    @FXML
    private Label LB_num_casa;
    @FXML
    private TextField TXF_nombre;
    @FXML
    private TextField TXF_apellido_1;
    @FXML
    private TextField TXF_ciudad;
    @FXML
    private TextField TXF_calle;
    @FXML
    private TextField TXF_num_casa;
    @FXML
    private Button BTN_salir;
    @FXML
    private Button BTN_guardar;
    @FXML
    private Label LB_apellido_2;
    @FXML
    private TextField TXF_apellido_2;
    @FXML
    private Label LB_estado;
    @FXML
    private TextField TXF_estado;
    @FXML
    private Label LB_telefono;
    @FXML
    private TextField TXF_telefono;
    @FXML
    private Label LB_codigo_postal;
    @FXML
    private Label LB_colonia;
    @FXML
    private TextField TXF_colonia;
    @FXML
    private TextField TXF_codigo_postal;
    @FXML
    private Label LB_mensaje;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        
    }    

    @FXML
    private void salir(ActionEvent event) {
        
        ((Stage)AP_agregarCliente.getScene().getWindow()).close();
        
    }

    @FXML
    private void guardar(ActionEvent event) {
        //comprobamos que todos los campos de los cuadros de texto contengan elementos 
        //si alguno no tiene se realiza lo sigienete
        if(this.TXF_apellido_1.getText() == null || this.TXF_apellido_1.getText().trim().isEmpty() || this.TXF_apellido_2.getText()==null || this.TXF_apellido_2.getText().trim().isEmpty() || this.TXF_calle.getText()==null || this.TXF_calle.getText().trim().isEmpty() || this.TXF_ciudad.getText() == null || this.TXF_ciudad.getText().trim().isEmpty() || this.TXF_codigo_postal.getText() == null || this.TXF_codigo_postal.getText().trim().isEmpty() || this.TXF_colonia.getText() == null || this.TXF_colonia.getText().trim().isEmpty() || this.TXF_estado.getText() == null || this.TXF_estado.getText().trim().isEmpty() || this.TXF_nombre.getText() == null || this.TXF_nombre.getText().trim().isEmpty() || this.TXF_num_casa.getText() == null || this.TXF_num_casa.getText().trim().isEmpty() || this.TXF_telefono.getText() == null || this.TXF_telefono.getText().trim().isEmpty()){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Inténtelo de nuevo");
            alert.setContentText("Llene los cuadro de texto");
            alert.showAndWait();
            
        }else{//si todos los cuadros de texto estan llenos se realiza lo siguiente 
            
            try{//por si hay algun error en la convercion de elementos de los cuadros de texto 

                //se toma toda la informacion de los cuadros de texto y se guarda en variables 
                String nombres = this.TXF_nombre.getText();
                String apellidoPaterno = this.TXF_apellido_1.getText();
                String apellidoMaterno = this.TXF_apellido_2.getText();
                Long telefono = Long.parseLong(this.TXF_telefono.getText());
                Integer calle = Integer.parseInt(this.TXF_calle.getText());
                String no_casa = this.TXF_num_casa.getText();
                String colonia = this.TXF_colonia.getText();
                Integer codigoPostal = Integer.parseInt(this.TXF_codigo_postal.getText());
                String ciudad = this.TXF_ciudad.getText();
                String estado = this.TXF_estado.getText();
                String id = ControladorCliente.getInstance().generarId();

                //se crea una dirreccion y un cliente 
                Direccion direccion = new Direccion(calle, no_casa, colonia, codigoPostal, ciudad, estado);
                Cliente cliente = new Cliente(id, nombres, apellidoPaterno, apellidoMaterno, 0., direccion, telefono);
                
                //revisamos si el cliente al que buscamos existe por nombres y apellidos
                if(ControladorCliente.getInstance().existeClienteNom(cliente)){//si no existe se realiza lo siguiente
                    
                    //se agrega el nuevo cliente
                    ControladorCliente.getInstance().agregarCliente(cliente);
                    
                    //se despliega un mensage de que a sido agregado correctamente
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Éxito");
                    alert.setContentText("Cliente agregado al inventario");
                    alert.showAndWait();
                    
                    //se ponen los cuadros de texto en null
                    this.TXF_nombre.setText(null);
                    this.TXF_apellido_1.setText(null);
                    this.TXF_apellido_2.setText(null);
                    this.TXF_calle.setText(null);
                    this.TXF_ciudad.setText(null);
                    this.TXF_codigo_postal.setText(null);
                    this.TXF_colonia.setText(null);
                    this.TXF_estado.setText(null);
                    this.TXF_num_casa.setText(null);
                    this.TXF_telefono.setText(null);
                    
                }else{//si el cliente existe se despliega un mensaje de que ya existe
                    
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Inténtelo de nuevo");
                    alert.setContentText("El Cliente ya existe");
                    alert.showAndWait();
                
                }
                               
             }catch(NumberFormatException e){//dspliega un mensaje en caso de que ocurra un error con la convercion de datos

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Inténtelo de nuevo");
                alert.setContentText("Ingrese correctamente los datos");
                alert.showAndWait();

            }   
        }  
    }
    
}
