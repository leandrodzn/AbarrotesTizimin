/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.InputMismatchException;
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
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Modificar_productoController implements Initializable {

    @FXML
    private AnchorPane AP_modificar_producto;
    @FXML
    private TextField TXF_nombre;
    @FXML
    private TextField TXF_Id;
    @FXML
    private TextField TXF_cantidad;
    @FXML
    private TextField TXF_precio_provedor;
    @FXML
    private TextField TXF_prcio_venta;
    @FXML
    private TextField TXF_puntos;
    @FXML
    private Button BTN_salir;
    @FXML
    private Button BTN_guardar;
    @FXML
    private Label LB_id_producto;
    @FXML
    private Button BTN_buscar;

    private String idProducto;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.BTN_guardar.setDisable(true);
        this.TXF_cantidad.setDisable(true);
        this.TXF_nombre.setDisable(true);
        this.TXF_prcio_venta.setDisable(true);
        this.TXF_precio_provedor.setDisable(true);
        this.TXF_puntos.setDisable(true);
    }    

    @FXML
    private void salir(ActionEvent event) {
        
        ((Stage)AP_modificar_producto.getScene().getWindow()).close();
        
    }

    @FXML
    private void guardar(ActionEvent event) {
        try{
            String nombre = this.TXF_nombre.getText();
            Double cantidad = Double.parseDouble(this.TXF_cantidad.getText());
            Double precioProveedor = Double.parseDouble(this.TXF_precio_provedor.getText());
            Double precioVenta = Double.parseDouble(this.TXF_prcio_venta.getText());
            Integer puntosOtorga = Integer.parseInt(this.TXF_puntos.getText());
            
            Producto producto = new Producto(nombre, idProducto, cantidad, precioProveedor, precioVenta, puntosOtorga);
            ControladorProducto.getInstance().modificarProducto(producto);
            
            this.BTN_guardar.setDisable(true);
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Éxito");
            alert.setContentText("Producto modificado");
            alert.showAndWait();

            this.TXF_cantidad.setText(null);
            this.TXF_nombre.setText(null);
            this.TXF_prcio_venta.setText(null);
            this.TXF_precio_provedor.setText(null);
            this.TXF_puntos.setText(null);
            this.idProducto = null;
            
            this.TXF_cantidad.setDisable(true);
            this.TXF_nombre.setDisable(true);
            this.TXF_prcio_venta.setDisable(true);
            this.TXF_precio_provedor.setDisable(true);
            this.TXF_puntos.setDisable(true);
                    
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Inténtelo de nuevo");
            alert.setContentText("Ingrese correctamente los datos");
            alert.showAndWait();
        }
        
    }

    @FXML
    private void buscar(ActionEvent event) {
    //Lectura de los campos de texto
            String ID = this.TXF_Id.getText();
            
            //Por si algún campo de texto está vacio
            if(this.TXF_Id.getText() == null || this.TXF_Id.getText().trim().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Inténtelo de nuevo");
                alert.setContentText("Llene el cuadro de texto");
                alert.showAndWait();
            }else{
                
                Producto producto = new Producto(ID);
                if(ControladorProducto.getInstance().existeProducto(producto)){
                    producto = ControladorProducto.getInstance().regresarProducto(producto);
                    
                    this.TXF_cantidad.setText(String.valueOf(producto.getCantidad()));
                    this.TXF_nombre.setText(producto.getNombre());
                    this.TXF_prcio_venta.setText(String.valueOf(producto.getPrecioVenta()));
                    this.TXF_precio_provedor.setText(String.valueOf(producto.getPrecioProveedor()));
                    this.TXF_puntos.setText(String.valueOf(producto.getPuntosOtorga()));
                    this.idProducto = producto.getId();
                    
                    this.BTN_guardar.setDisable(false);
                    
                    this.TXF_cantidad.setDisable(false);
                    this.TXF_nombre.setDisable(false);
                    this.TXF_prcio_venta.setDisable(false);
                    this.TXF_precio_provedor.setDisable(false);
                    this.TXF_puntos.setDisable(false);
                    
                }else{ 
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Inténtelo de nuevo");
                    alert.setContentText("El producto no existe");
                    alert.showAndWait();
                    
                    this.BTN_guardar.setDisable(true);

                    this.TXF_cantidad.setText(null);
                    this.TXF_nombre.setText(null);
                    this.TXF_prcio_venta.setText(null);
                    this.TXF_precio_provedor.setText(null);
                    this.TXF_puntos.setText(null);
                    this.idProducto = null;
                    
                    this.TXF_cantidad.setDisable(true);
                    this.TXF_nombre.setDisable(true);
                    this.TXF_prcio_venta.setDisable(true);
                    this.TXF_precio_provedor.setDisable(true);
                    this.TXF_puntos.setDisable(true);
                }
            }
            
    }
    
}
