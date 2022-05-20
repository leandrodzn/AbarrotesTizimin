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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import modelo.Cliente;
import modelo.Direccion;
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author leodz
 */
public class VistaRealizarCompraController implements Initializable {

    @FXML
    private Label labelIdCliente;
    @FXML
    private TextField TxtIdCliente;
    @FXML
    private Button btnBuscarCliente;
    @FXML
    private Button btnAgregarCliente;
    @FXML
    private Label labelIngresarProducto;
    @FXML
    private TextField txtIngresarProducto;
    @FXML
    private Label labelIngresarCantidad;
    @FXML
    private TextField txtIngresarCantidad;
    @FXML
    private Label labelUsarBonos;
    @FXML
    private Button btnPagar;
    @FXML
    private Label labelPago;
    @FXML
    private Label labelCambio;
    @FXML
    private TextField txtPago;
    @FXML
    private ComboBox<?> cmboxUsarBonos;
    @FXML
    private Label labelRealizarCompra;
    @FXML
    private Button btnCancelarCompra;
    @FXML
    private Text txtCambio;
    @FXML
    private TableView<Producto> tableCompra;
    @FXML
    private TableColumn<?, ?> colID;
    @FXML
    private TableColumn<?, ?> colProducto;
    @FXML
    private TableColumn<?, ?> colCantidad;
    @FXML
    private TableColumn<?, ?> colPrecio;
    @FXML
    private Button btnMenu;
    @FXML
    private Button btnAgregarProducto;
    
    protected ObservableList<Producto> listaCompra = FXCollections.observableArrayList(ControladorCompra.getInstance().getListaArticulos());
    
    private Cliente clienteG;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.btnCancelarCompra.setDisable(true);
        this.btnPagar.setDisable(true);
        this.labelIdCliente.setText("Ingrese el ID del cliente:");
        this.TxtIdCliente.setDisable(false);
        clienteG = null;
        
        this.colCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.colID.setCellValueFactory(new PropertyValueFactory("id"));
        this.colProducto.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precioVenta"));
        
    }    

    @FXML
    private void clickBuscarCliente(ActionEvent event) {
            
            //Por si algún campo de texto está vacio
            if(this.TxtIdCliente.getText() == null || this.TxtIdCliente.getText().trim().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Inténtelo de nuevo");
                alert.setContentText("Llene el cuadro de texto del id del cliente");
                alert.showAndWait();
            }else{
                
                String id = this.TxtIdCliente.getText();
                Cliente cliente = new Cliente(id);
                if(ControladorCliente.getInstance().existeCliente(cliente)){//Si el cliente no existe, lo busca por ID
                    clienteG = ControladorCliente.getInstance().regresarCliente(cliente);
                    this.labelIdCliente.setText("Nombre:");
                    String nombre = clienteG.getNombres();
                    this.TxtIdCliente.setText(nombre);
                    this.TxtIdCliente.setDisable(true);
                    
                }else{ //Si el cliente ya existe no se crea otra con la misma información
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Inténtelo de nuevo");
                    alert.setContentText("El cliente no existe, pruebe agregándolo");
                    alert.showAndWait();
                }
                    
            }
        
    }

    @FXML
    private void clickAgregarCliente(ActionEvent event) {
        try {
            
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Vista_agregar_cliente.fxml"));
            Parent root=  loader.load();
            Vista_agregar_clienteController controlador = loader.getController();
            Scene scene= new Scene(root);
            Stage stage = new Stage();
            
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getBounds();
            stage.setResizable(false);
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
    private void clickPagar(ActionEvent event) {
    }

    @FXML
    private void clickCancelarCompra(ActionEvent event) {
            ControladorCompra.getInstance().regresarArticulosInventario();
            listaCompra.setAll(ControladorCompra.getInstance().getListaArticulos());
            //listaCompra.clear();
            this.tableCompra.setItems(listaCompra);
            this.btnMenu.setDisable(false);
            
            
    }

    @FXML
    private void clickMenu(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Main.class.getResource("/vista/VistaMenuPrincipal.fxml"));
            
            ControladorVistaMenuPrincipal controlador = loader.getController();
            
            Pane ventana = (Pane) loader.load();

            Scene scene = new Scene(ventana);
            Stage stage = new Stage();
            
            stage.setTitle("Menú principal");
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.btnMenu.getScene().getWindow();
            myStage.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void clickAgregarProducto(ActionEvent event) {
        if(this.txtIngresarProducto.getText() == null || this.txtIngresarProducto.getText().trim().isEmpty() || this.txtIngresarCantidad.getText() == null || this.txtIngresarCantidad.getText().trim().isEmpty()){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Inténtelo de nuevo");
            alert.setContentText("Llene los cuadro de texto de producto y cantidad");
            alert.showAndWait();
            
        }else{
            
            try{
                String id = this.txtIngresarProducto.getText();
                Double cantidad = Double.parseDouble(this.txtIngresarCantidad.getText());
                
                Producto producto = new Producto(id);
                int respuesta = ControladorCompra.getInstance().agregarArticuloCompra(producto, cantidad);
                
                switch(respuesta){
                
                    case 0:Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Inténtelo de nuevo");
                        alert.setContentText("No se pudo agregar el producto");
                        alert.showAndWait();
                        break;
                    
                    case 1: 
                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                        alert1.setHeaderText(null);
                        alert1.setTitle("Inténtelo de nuevo");
                        alert1.setContentText("No existe el producto");
                        alert1.showAndWait();
                        break;
                    
                    case 2: Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setHeaderText(null);
                        alert2.setTitle("Inténtelo de nuevo");
                        alert2.setContentText("No existen cantidad suficiente");
                        alert2.showAndWait();
                        break;
                    
                    case 4: listaCompra.setAll(ControladorCompra.getInstance().getListaArticulos());
                            this.tableCompra.setItems(listaCompra);
                            this.btnMenu.setDisable(true);
                        break;
                    
                }
            
            }catch(NumberFormatException e){//se despliega un mensaje en cado de que la conversion de datos falle

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Inténtelo de nuevo");
                alert.setContentText("Ingrese correctamente la cantidad");
                alert.showAndWait();

            }
            
        }
        
    }
    
}
