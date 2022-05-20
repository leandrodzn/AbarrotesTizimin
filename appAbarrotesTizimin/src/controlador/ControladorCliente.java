/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import interfaces.IClientesDAO;
import java.util.ArrayList;
import java.util.Random;
import modelo.Cliente;
import interfaces.IObservable;

/**
 *
 * @author leodz
 */
public class ControladorCliente implements IObservable, IClientesDAO{
    
    private static ControladorCliente instancia;
    private static ArrayList<Cliente> listaClientes = new ArrayList<>();
    private PersistorDatos persistencia = PersistorDatos.getInstance();

    
    public static ControladorCliente getInstance(){
        
        if(instancia == null)
            instancia = new ControladorCliente();
        
        return instancia;
        
    }
    
   /* public ControladorCliente(ArrayList<Cliente> clientes){
        this.listaClientes.clear();
        this.listaClientes.addAll(clientes);
    }*/

    
    @Override
    public ArrayList<Cliente> getListaClientes() {
        return this.listaClientes;
    }
    
    @Override
    public void agregarCliente(Cliente cliente){
        cliente.setId(generarId());
        listaClientes.add(cliente);
        persistencia.guardarClientes(listaClientes);
    }
    
    @Override
    public void modificarCliente(Cliente cliente){
        for(int i = 0; i < listaClientes.size(); i++){
            if(cliente.getId().equals(listaClientes.get(i).getId()))
                listaClientes.set(i, cliente);
        }
        persistencia.guardarClientes(listaClientes);
    }
    
    @Override
    public void eliminarCliente(Cliente cliente){
        listaClientes.remove(cliente);
        persistencia.guardarClientes(listaClientes);
    }
    
    public void setListaClientes(ArrayList<Cliente> listaClientes){
        
        ControladorCliente.listaClientes.clear();
        ControladorCliente.listaClientes.addAll(listaClientes);
        
    }
    
    public String generarId(){
        boolean existe;
        String id = numeroRandom();
        do{
            existe = false;
            for(int i=0; i < listaClientes.size();i++){
                if(id.equals(listaClientes.get(i).getId())){
                    existe = true;
                }
            }
            if(existe){
                id = numeroRandom();
            }
        }while(existe);
        return id;
    }
    
    private String numeroRandom(){
        Random numero = new Random();
        Integer n = numero.nextInt(9999999-1000000+1) + 1000000;
        String ns = String.valueOf(n);
        return ns;
    }
    
    @Override
    public void notificarTodos(){
        for (Cliente cliente : listaClientes) {
            cliente.actualizar();
        }
    }
    
    public boolean existeCliente(Cliente cliente){ 
        boolean existencia=false;
        
        for(int i=0; i<listaClientes.size(); i++){
            if(cliente.getId().equals(listaClientes.get(i).getId())){
                existencia = true;
            }
        }
        
        return existencia;
    }
    
    public boolean existeClienteNom(Cliente cliente){   
        boolean existencia=true;
        
        for(int i=0; i<listaClientes.size(); i++){
            if(cliente.getNombres().equals(listaClientes.get(i).getNombres()) || cliente.getApellidoPaterno().equals(listaClientes.get(i).getApellidoPaterno()) || cliente.getApellidoMaterno().equals(listaClientes.get(i).getApellidoMaterno())){
                existencia = false;
            }
        }
        
        return existencia;
    }
    
    public Cliente regresarCliente(Cliente cliente){
        
        Cliente encontrado = null;
        
        for(int i=0; i<listaClientes.size(); i++){
            
            if(cliente.getId().equals(listaClientes.get(i).getId())){
                encontrado = listaClientes.get(i);
            }
        }
        
        return encontrado;
    }
   
    
}
