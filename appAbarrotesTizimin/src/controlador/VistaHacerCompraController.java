/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.Compra;
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author leodz
 */
public class VistaHacerCompraController implements Initializable {

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
    private ComboBox<Integer> cmboxUsarBonos;
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
    @FXML
    private Label labelTotalCompra;
    @FXML
    private Button btnVerClientes;
    @FXML
    private Button btnVerProducto;
    @FXML
    private Button btnQuitarCliente;
    @FXML
    private Label labelDescuento;
    @FXML
    private Label labelTotalLista;
    @FXML
    private Text txtCambio1;
    
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
        this.TxtIdCliente.setText(null);
        this.labelTotalCompra.setText("Total de la compra:");
        this.labelTotalLista.setText("Total de la lista:");
        this.btnQuitarCliente.setDisable(true);
        this.txtCambio.setText(null);
        this.txtPago.setText(null);
        this.labelDescuento.setText("Descuento:");
        this.labelTotalCompra.setText("Total de la compra:");
        this.txtCambio1.setText(null);
        clienteG = null;
        
        this.colCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.colID.setCellValueFactory(new PropertyValueFactory("id"));
        this.colProducto.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precioVenta"));
        
        ArrayList<Integer> Apuntos = new ArrayList<>();
        Apuntos.add(0);
        Apuntos.add(200);
        Apuntos.add(400);
        Apuntos.add(600);
        Apuntos.add(800);
        Apuntos.add(1000);
        ObservableList<Integer> puntos = FXCollections.observableArrayList(Apuntos);
        this.cmboxUsarBonos.setItems(puntos);
        
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
                    cliente = ControladorCliente.getInstance().regresarCliente(cliente);
                    clienteG = new Cliente(cliente.getId(), cliente.getNombres(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno(), cliente.getPuntosObtenidos(), cliente.getDireccion(), cliente.getTelefono());
                    this.labelIdCliente.setText("Nombre del cliente:");
                    String nombre = clienteG.getNombres() + " " + clienteG.getApellidoPaterno() + " " + clienteG.getApellidoMaterno();
                    this.TxtIdCliente.setText(nombre);
                    this.TxtIdCliente.setDisable(true);
                    this.btnQuitarCliente.setDisable(false);
                    
                              
                    
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
            stage.setTitle("Agregar cliente");
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
        if(clienteG != null){
            if(this.cmboxUsarBonos.getValue() != null){
                Double puntosCliente = clienteG.getPuntosObtenidos();
                Double posibilidad = puntosCliente - this.cmboxUsarBonos.getValue();
                Integer puntosSelec = this.cmboxUsarBonos.getValue(); 
                Double descuento = 0.;
                
                if(posibilidad >= 0){
                    descuento = ControladorCompra.getInstance().descuento(puntosSelec);
                    clienteG.setPuntosObtenidos(puntosCliente - puntosSelec);
                }else{
                    puntosSelec = 0;
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Aviso");
                        alert.setContentText("El cliente no cuenta con los puntos seleccionados para el descuento");
                        alert.showAndWait();  
                }
                this.labelDescuento.setText("Descuento: $" + descuento);
                Double totalCompra = ControladorCompra.getInstance().importeTotal(puntosSelec);
                this.labelTotalCompra.setText("Total de la compra: $" + totalCompra);
                Double puntosGenerados = ControladorCompra.getInstance().puntosGenerados();
                
                
                try{
                    if(this.txtPago.getText() == null || this.txtPago.getText().trim().isEmpty() || Double.parseDouble(this.txtPago.getText()) < totalCompra){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Inténtelo de nuevo");
                        alert.setContentText("Corriga el pago");
                        alert.showAndWait();
                    }else{
                        Double pago = Double.parseDouble(this.txtPago.getText());
                        Double cambio = pago - totalCompra;
                        String scambio = String.valueOf(cambio);
                        this.txtCambio1.setText("$" + scambio);
                        clienteG.setPuntosObtenidos(clienteG.getPuntosObtenidos() + puntosGenerados);
                        ControladorCliente.getInstance().modificarCliente(clienteG);
                        Compra compra = new Compra(ControladorCompra.getInstance().totalListaCompra(), descuento, totalCompra, pago, cambio, ControladorCompra.getInstance().getListaArticulos(), clienteG, puntosGenerados);
                        GeneradorTicket.getInstance().generarTicket(compra);
                        
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Compra exitosa");
                        alert.setContentText("Compra realizada con éxito");
                        alert.showAndWait();
                        
                        ControladorCompra.getInstance().limpiarListaCompra();
                        listaCompra.setAll(ControladorCompra.getInstance().getListaArticulos());
                        this.tableCompra.setItems(listaCompra);
                        
                        this.btnMenu.setDisable(false);
                        this.txtIngresarCantidad.setText(null);
                        this.txtIngresarProducto.setText(null);
                        this.btnCancelarCompra.setDisable(true);
                        this.btnPagar.setDisable(true);
                        this.labelIdCliente.setText("Ingrese el ID del cliente:");
                        this.TxtIdCliente.setDisable(false);
                        this.TxtIdCliente.setText(null);
                        this.labelTotalLista.setText("Total de la lista:");
                        this.btnQuitarCliente.setDisable(true);
                        this.txtCambio.setText(null);
                        this.txtPago.setText(null);
                        this.labelDescuento.setText("Descuento:");
                        this.labelTotalCompra.setText("Total de la compra:");
                        this.txtCambio1.setText(null);
                        clienteG = null;
                        
                        try {
                            File objetofile = new File ("Ticket.pdf");
                            Desktop.getDesktop().open(objetofile);
                        }catch (IOException ex) {
                            System.out.println("Archivo no encontrado");
                        }
                    }
                }
                catch(NumberFormatException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Inténtelo de nuevo");
                    alert.setContentText("Corriga el formato del pago");
                    alert.showAndWait();
                }
                
 
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Inténtelo de nuevo");
                alert.setContentText("Seleccione los bonos a utilizar");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Inténtelo de nuevo");
            alert.setContentText("Ingrese un cliente");
            alert.showAndWait();
        }
    }

    @FXML
    private void clickCancelarCompra(ActionEvent event) {
        ControladorCompra.getInstance().regresarArticulosInventario();
        listaCompra.setAll(ControladorCompra.getInstance().getListaArticulos());
        //listaCompra.clear();
        this.tableCompra.setItems(listaCompra);
        this.btnMenu.setDisable(false);
        this.btnCancelarCompra.setDisable(true);
        this.btnPagar.setDisable(true);
        
        this.labelIdCliente.setText("Ingrese el ID del cliente:");
        this.TxtIdCliente.setDisable(false);
        this.TxtIdCliente.setText(null);
        this.btnQuitarCliente.setDisable(true);
        this.labelTotalLista.setText("Total de la lista:");
        clienteG = null;
        this.txtIngresarProducto.setText(null);
        this.txtIngresarCantidad.setText(null);
        this.txtCambio.setText(null);
        this.txtPago.setText(null);
        this.labelDescuento.setText("Descuento:");
        this.labelTotalCompra.setText("Total de la compra:");
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Cancelación");
        alert.setContentText("La compra ha sido cancelada");
        alert.showAndWait();
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
                        alert2.setContentText("No existe cantidad suficiente");
                        alert2.showAndWait();
                        break;
                    
                    case 4: listaCompra.setAll(ControladorCompra.getInstance().getListaArticulos());
                        this.tableCompra.setItems(listaCompra);
                        this.btnMenu.setDisable(true);
                        this.btnCancelarCompra.setDisable(false);
                        this.btnPagar.setDisable(false);
                        Double total = ControladorCompra.getInstance().totalListaCompra();
                        this.labelTotalLista.setText("Total de la lista: $" + total);
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

    @FXML
    private void clickVerClientes(ActionEvent event) {
        try {
            
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Vista_lista_clientes.fxml"));
            Parent root=  loader.load();
            Vista_lista_clientesController controlador = loader.getController();
            Scene scene= new Scene(root);
            Stage stage = new Stage();
            
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getBounds();
            //stage.setMaximized(true);
            stage.setResizable(false);
            stage.setTitle("Lista de clientes");
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
    private void clickVerProducto(ActionEvent event) {
        try {
            
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Vista_elementos_inventario.fxml"));
            Parent root=  loader.load();
            Vista_elementos_inventarioController controlador = loader.getController();
            Scene scene= new Scene(root);
            Stage stage = new Stage();
            
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getBounds();
            //stage.setMaximized(true);
            stage.setResizable(false);
            stage.setTitle("Lista de productos");
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
    private void clickQuitarCliente(ActionEvent event) {
        this.labelIdCliente.setText("Ingrese el ID del cliente:");
        this.TxtIdCliente.setDisable(false);
        this.TxtIdCliente.setText(null);
        clienteG = null;
        this.btnQuitarCliente.setDisable(true);
    }
    
}
