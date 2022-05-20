/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import modelo.Cliente;
import modelo.Producto;

/**
 *
 * @author leodz
 */
public interface IPersistenciaDatos {
    public void guardarClientes(ArrayList<Cliente> clientes);
    public ArrayList<Cliente> cargarClientes();
    public void guardarProductos(ArrayList<Producto> productos);
    public ArrayList<Producto> cargarProductos();
}
