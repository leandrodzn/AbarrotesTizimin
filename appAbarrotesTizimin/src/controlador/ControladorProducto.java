/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import interfaces.IProductosDAO;
import java.util.ArrayList;
import modelo.Producto;

/**
 *
 * @author leodz
 */
public class ControladorProducto implements IProductosDAO{
    private static ControladorProducto instance;
    private static ArrayList<Producto> listaProductos = new ArrayList<>();
    private PersistorDatos persistencia = PersistorDatos.getInstance();
    
    public static ControladorProducto getInstance(){
        if(instance == null)
            instance = new ControladorProducto();
        
        return instance;
    }
    
    @Override
    public void agregarProducto(Producto producto){
        listaProductos.add(producto);
        persistencia.guardarProductos(listaProductos);
    }
    
    @Override
    public void modificarProducto(Producto producto){
        for(int i = 0; i < listaProductos.size(); i++){
            if(producto.getId().equals(listaProductos.get(i).getId()))
                listaProductos.set(i, producto);
        }
        persistencia.guardarProductos(listaProductos);
    }
    
    @Override
    public void eliminarProducto(Producto producto){
        listaProductos.remove(producto);
        persistencia.guardarProductos(listaProductos);
    }
    
    @Override
    public ArrayList<Producto> getListaProductos(){
        return ControladorProducto.listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        ControladorProducto.listaProductos.clear();
        ControladorProducto.listaProductos.addAll(listaProductos);
    }
    

    public boolean existeSuficiente(Producto producto, Double peticion){
        
        int resultado = Double.compare(peticion, producto.getCantidad());
        if(resultado <= 0)
            return true;
        else
            return false;
    }
    
    public boolean existeProducto(Producto producto){
        boolean existe = false;
        for(int i = 0; i < listaProductos.size(); i++){
            if(producto.getId().equals(listaProductos.get(i).getId())){
                existe = true;
            }
        }
        return existe;
    }
    
    public Producto regresarProducto(Producto producto){
        Producto encontrado = null;
        for(int i = 0; i < listaProductos.size(); i++){
            if(producto.getId().equals(listaProductos.get(i).getId())){
                encontrado = listaProductos.get(i);
            }
        }
        return encontrado;
    }
    
    public Double regresarCantidad(Producto producto){
        Double cantidad = 0.;
        for(int i = 0; i < listaProductos.size(); i++){
            if(producto.getId().equals(listaProductos.get(i).getId())){
                cantidad = listaProductos.get(i).getCantidad();
            }
        }
        return cantidad;
    }
    
    
}
