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
public class Vista_agregar_productoController implements Initializable {

    @FXML
    private Label LB_nombre;
    @FXML
    private Label LB_Id;
    @FXML
    private Label LB_cantidad;
    @FXML
    private Label LB_precio_provedor;
    @FXML
    private Label LB_precio_venta;
    @FXML
    private Label LB_puntos;
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
    private AnchorPane AP_agregar_producto;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void salir(ActionEvent event) {
        
        ((Stage)AP_agregar_producto.getScene().getWindow()).close();
    }

    @FXML
    private void guardar(ActionEvent event) {
        if(this.TXF_Id.getText() == null || this.TXF_Id.getText().trim().isEmpty() || this.TXF_cantidad.getText() == null || this.TXF_cantidad.getText().trim().isEmpty() || this.TXF_nombre.getText() == null || this.TXF_nombre.getText().trim().isEmpty() || this.TXF_precio_provedor.getText() == null || this.TXF_precio_provedor.getText().trim().isEmpty() || this.TXF_prcio_venta.getText() == null || this.TXF_prcio_venta.getText().trim().isEmpty() || this.TXF_puntos.getText() == null || this.TXF_puntos.getText().trim().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Inténtelo de nuevo");
                alert.setContentText("Llene los cuadro de texto");
                alert.showAndWait();
        }else{
                try{
                    String id = this.TXF_Id.getText();
                    String nombre = this.TXF_nombre.getText();
                    Double cantidad = Double.parseDouble(this.TXF_cantidad.getText());
                    Double precioProveedor = Double.parseDouble(this.TXF_precio_provedor.getText());
                    Double precioVenta = Double.parseDouble(this.TXF_prcio_venta.getText());
                    Integer puntosOtorga = Integer.parseInt(this.TXF_puntos.getText());
                    
                    if(cantidad < 0 || precioProveedor < 0 || precioVenta < 0 || puntosOtorga < 0){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Inténtelo de nuevo");
                        alert.setContentText("Los valores no pueden ser menores que 0");
                        alert.showAndWait();
                    }else{
                        if(!(ControladorProducto.getInstance().existeProducto(new Producto(id)))){
                            Producto producto = new Producto(nombre, id, cantidad, precioProveedor, precioVenta, puntosOtorga);
                            ControladorProducto.getInstance().agregarProducto(producto);

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setHeaderText(null);
                            alert.setTitle("Éxito");
                            alert.setContentText("Producto agregado al inventario");
                            alert.showAndWait();

                            this.TXF_cantidad.setText(null);
                            this.TXF_nombre.setText(null);
                            this.TXF_prcio_venta.setText(null);
                            this.TXF_precio_provedor.setText(null);
                            this.TXF_puntos.setText(null);
                            this.TXF_Id.setText(null);
                        }else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText(null);
                            alert.setTitle("Inténtelo de nuevo");
                            alert.setContentText("El ID del producto ya existe");
                            alert.showAndWait();
                        }
                    }
                    
                }catch(NumberFormatException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Inténtelo de nuevo");
                    alert.setContentText("Ingrese correctamente los datos");
                    alert.showAndWait();
                }
                
        }
    }
    
}
