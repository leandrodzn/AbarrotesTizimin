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
import modelo.Cliente;

/**
 * FXML Controller class
 *
 * @author leodz
 */
public class Vista_lista_clientesController implements Initializable {

    @FXML
    private AnchorPane AP_lista_clientes;
    @FXML
    private TableView<Cliente> TB_lista_clientes;
    @FXML
    private TableColumn<?, ?> CLM_id;
    @FXML
    private TableColumn<?, ?> CLM_nombre;
    @FXML
    private TableColumn<?, ?> CLM_direccion;
    @FXML
    private TableColumn<?, ?> CLM_telefono;
    @FXML
    private TableColumn<?, ?> CLM_puntos;
    
    protected ObservableList<Cliente> listaClientes = FXCollections.observableArrayList(ControladorCliente.getInstance().getListaClientes());
    @FXML
    private TableColumn<?, ?> CLM_apellido_1;
    @FXML
    private TableColumn<?, ?> CLM_apellido_2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.CLM_id.setCellValueFactory(new PropertyValueFactory("id"));
        this.CLM_nombre.setCellValueFactory(new PropertyValueFactory("nombres"));
        this.CLM_apellido_1.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        this.CLM_apellido_2.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        this.CLM_direccion.setCellValueFactory(new PropertyValueFactory("direccion"));
        this.CLM_puntos.setCellValueFactory(new PropertyValueFactory("puntosObtenidos"));
        this.CLM_telefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        
        listaClientes.setAll(ControladorCliente.getInstance().getListaClientes());
        this.TB_lista_clientes.setItems(listaClientes);
    }    
    
}
