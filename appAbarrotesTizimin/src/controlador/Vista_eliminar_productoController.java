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
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Vista_eliminar_productoController implements Initializable {

    @FXML
    private TextField TXF_Id;
    @FXML
    private Button BTN_salir;
    @FXML
    private Label LB_nombre;
    @FXML
    private Label LB_no_encontrado;
    @FXML
    private Label LB_cantidad;
    @FXML
    private Label LB_precio_provedor;
    @FXML
    private Label LB_precio_venta;
    @FXML
    private Label LB_puntos;
    @FXML
    private Button BTN_buscar;
    @FXML
    private Button BTN_eliminar;
    @FXML
    private AnchorPane AP_eliminar_producto;
    
    private String idProducto;

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
        
        ((Stage)AP_eliminar_producto.getScene().getWindow()).close();
        
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
                if(ControladorProducto.getInstance().existeProducto(producto)){//Si el cliente no existe, lo busca por ID
                    producto = ControladorProducto.getInstance().regresarProducto(producto);
                    
                    this.LB_nombre.setText(producto.getNombre());
                    this.LB_precio_provedor.setText(String.valueOf(producto.getPrecioProveedor()));
                    this.LB_precio_venta.setText(String.valueOf(producto.getPrecioVenta()));
                    this.LB_puntos.setText(String.valueOf(producto.getPuntosOtorga()));
                    this.LB_cantidad.setText(String.valueOf(producto.getCantidad()));
                    this.idProducto = producto.getId();
                    this.BTN_eliminar.setDisable(false);
                    
                }else{ //Si el cliente ya existe no se crea otra con la misma información
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Inténtelo de nuevo");
                    alert.setContentText("El producto no existe");
                    alert.showAndWait();
                    
                    this.BTN_eliminar.setDisable(true);
                    
                    this.LB_nombre.setText(null);
                    this.LB_precio_provedor.setText(null);
                    this.LB_precio_venta.setText(null);
                    this.LB_puntos.setText(null);
                    this.LB_cantidad.setText(null);
                    this.idProducto = null;
                }
            }
        
    }

    @FXML
    private void eliminar(ActionEvent event) {
        Producto producto = new Producto(idProducto);
        ControladorProducto.getInstance().eliminarProducto(ControladorProducto.getInstance().regresarProducto(producto));
        this.BTN_eliminar.setDisable(true);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Éxito");
        alert.setContentText("Producto eliminado");
        alert.showAndWait();
        
        this.TXF_Id.setText(null);
        
        this.LB_nombre.setText(null);
        this.LB_precio_provedor.setText(null);
        this.LB_precio_venta.setText(null);
        this.LB_puntos.setText(null);
        this.LB_cantidad.setText(null);
        this.idProducto = null;
    }
    
}
