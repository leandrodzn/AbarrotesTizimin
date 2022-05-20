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
public class Vista_modificar_clienteController implements Initializable {

    @FXML
    private AnchorPane AP_modificar_cliente;
    @FXML
    private Label LB_nombre;
    @FXML
    private Label LB_Id_cliente;
    @FXML
    private Label LB_apellido_1;
    @FXML
    private Label LB_ciudad;
    @FXML
    private Label LB_calle;
    @FXML
    private TextField TXF_nombre;
    @FXML
    private TextField TXF_Id_cliente;
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
    private Label LB_colonia;
    @FXML
    private TextField TXF_colonia;
    @FXML
    private TextField TXF_codigo_postal;
    @FXML
    private Label LB_mensaje;
    @FXML
    private Label LB_num_casa;
    @FXML
    private Label LB_codigo_postal;
    @FXML
    private Button BTN_buscar;
    private String id;
    private Double puntos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {//se inicia la pantalla con los botenes desabilitados y los cuadros
        //de texto tambien 
        
        this.BTN_guardar.setDisable(true);
        this.TXF_apellido_1.setDisable(true);
        this.TXF_apellido_2.setDisable(true);
        this.TXF_calle.setDisable(true);
        this.TXF_ciudad.setDisable(true);
        this.TXF_codigo_postal.setDisable(true);
        this.TXF_colonia.setDisable(true);
        this.TXF_estado.setDisable(true);
        this.TXF_nombre.setDisable(true);
        this.TXF_num_casa.setDisable(true);
        this.TXF_telefono.setDisable(true);
        
    }    

    @FXML
    private void salir(ActionEvent event) {
        //al precionar el boton salir se cierra la ventana
        ((Stage)AP_modificar_cliente.getScene().getWindow()).close();
        
    }

    @FXML
    private void guardar(ActionEvent event) {
        
        try{
            //se crea una nueva dirreccion y cliente (funcionan como aux)
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
            
            Direccion direccion = new Direccion(calle, no_casa, colonia, codigoPostal, ciudad, estado);
            Cliente cliente = new Cliente(id, nombres, apellidoPaterno, apellidoMaterno, puntos, direccion, telefono);
            
            //se llama al metodo de modificar para que le ralice el cambio al cliente 
            ControladorCliente.getInstance().modificarCliente(cliente);
            
            //se desabilitan los botenes nuevamente y se espera a que se vuelva a realizar una operacion
            this.BTN_guardar.setDisable(true);
            
            //despliega un mensaje de confirmacion que la accion a sido realizada con exito
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Éxito");
            alert.setContentText("Cliente modificado");
            alert.showAndWait();
            
            //dejamos en blanco nuevamente los cuadros de texto 
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
            
            //se bloquean los cuadros de texto para que no se pueda escribie en ellos
            this.TXF_apellido_1.setDisable(true);
            this.TXF_apellido_2.setDisable(true);
            this.TXF_calle.setDisable(true);
            this.TXF_ciudad.setDisable(true);
            this.TXF_codigo_postal.setDisable(true);
            this.TXF_colonia.setDisable(true);
            this.TXF_estado.setDisable(true);
            this.TXF_nombre.setDisable(true);
            this.TXF_num_casa.setDisable(true);
            this.TXF_telefono.setDisable(true);
            
        }catch(NumberFormatException e){//se despliega un mensaje en cado de que la conversion de datos falle
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Inténtelo de nuevo");
            alert.setContentText("Ingrese correctamente los datos");
            alert.showAndWait();
            
        }
        
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
                
                //se despliega en los cuadro de texto la informacion del cliente
                this.TXF_nombre.setText(cliente.getNombres());
                this.TXF_apellido_1.setText(cliente.getApellidoPaterno());
                this.TXF_apellido_2.setText(cliente.getApellidoMaterno());
                this.TXF_calle.setText(String.valueOf(cliente.getDireccion().getCalle()));
                this.TXF_ciudad.setText(cliente.getDireccion().getCiudad());
                this.TXF_codigo_postal.setText(String.valueOf(cliente.getDireccion().getCodigoPostal()));
                this.TXF_colonia.setText(cliente.getDireccion().getColonia());
                this.TXF_estado.setText(cliente.getDireccion().getEstado());
                this.TXF_num_casa.setText(cliente.getDireccion().getNo_casa());
                this.TXF_telefono.setText(String.valueOf(cliente.getTelefono()));
                id = cliente.getId();
                puntos = cliente.getPuntosObtenidos();
                
                //se abilitan los cuadros de texto para que se pueda escribir en ellos 
                this.BTN_guardar.setDisable(false);
                this.TXF_apellido_1.setDisable(false);
                this.TXF_apellido_2.setDisable(false);
                this.TXF_calle.setDisable(false);
                this.TXF_ciudad.setDisable(false);
                this.TXF_codigo_postal.setDisable(false);
                this.TXF_colonia.setDisable(false);
                this.TXF_estado.setDisable(false);
                this.TXF_nombre.setDisable(false);
                this.TXF_num_casa.setDisable(false);
                this.TXF_telefono.setDisable(false);
                 
            }else{//si el cliente no existe:
                
                //se depliega un mensaje de error que indica que el cliente no existe 
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Intentelo de nuevo");
                alert.setContentText("El cliente no existe");
                alert.showAndWait();
                
                //se desabilita el boton de guardar 
                this.BTN_guardar.setDisable(true);
                
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
                
                //se deabililitan los cuadros de texto
                this.TXF_apellido_1.setDisable(true);
                this.TXF_apellido_2.setDisable(true);
                this.TXF_calle.setDisable(true);
                this.TXF_ciudad.setDisable(true);
                this.TXF_codigo_postal.setDisable(true);
                this.TXF_colonia.setDisable(true);
                this.TXF_estado.setDisable(true);
                this.TXF_nombre.setDisable(true);
                this.TXF_num_casa.setDisable(true);
                this.TXF_telefono.setDisable(true);
                
            }
        }
        
        
    }
    
}
