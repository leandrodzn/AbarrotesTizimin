/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Vista_elementos_inventarioController implements Initializable {

    @FXML
    private AnchorPane AP_ver_Inventario;
    @FXML
    private TableView<Producto> TB_ver_inventario;
    @FXML
    private TableColumn<?, ?> CLM_id;
    @FXML
    private TableColumn<?, ?> CLM_nombre;
    @FXML
    private TableColumn<?, ?> CLM_cantidad;
    @FXML
    private TableColumn<?, ?> CLM_puntos;
    @FXML
    private TableColumn<?, ?> CLM_precio_proveedor;
    @FXML
    private TableColumn<?, ?> CLM_jprecio_venta;
    
    protected ObservableList<Producto> listaProductos = FXCollections.observableArrayList(ControladorProducto.getInstance().getListaProductos());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.CLM_id.setCellValueFactory(new PropertyValueFactory("id"));
        this.CLM_nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.CLM_cantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.CLM_puntos.setCellValueFactory(new PropertyValueFactory("puntosOtorga"));
        this.CLM_precio_proveedor.setCellValueFactory(new PropertyValueFactory("precioProveedor"));
        this.CLM_jprecio_venta.setCellValueFactory(new PropertyValueFactory("precioVenta"));
       
       listaProductos.setAll(ControladorProducto.getInstance().getListaProductos());
        this.TB_ver_inventario.setItems(listaProductos);
    }    
    
}
