/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import modelo.Cliente;

/**
 *
 * @author leodz
 */
public interface IClientesDAO {
    public void agregarCliente(Cliente cliente);
    public void modificarCliente(Cliente cliente);
    public void eliminarCliente(Cliente cliente);
    public ArrayList<Cliente> getListaClientes();
    
}
