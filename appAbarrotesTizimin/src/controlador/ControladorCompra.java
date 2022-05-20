/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import java.util.ArrayList;
import modelo.Compra;
import modelo.Producto;

/**
 *
 * @author leodz
 */
public class ControladorCompra{
    private static ControladorCompra instance;
    private ArrayList<Compra> listaCompras = new ArrayList<>();
    private ArrayList<Producto> listaArticulos = new ArrayList<>();
    
    public static ControladorCompra getInstance(){
        if(instance == null)
            instance = new ControladorCompra();
        
        return instance;
    }

    public ArrayList<Producto> getListaArticulos() {
        return this.listaArticulos;
    }   
    
    public void limpiarListaCompra(){
        this.listaArticulos.clear();
    }
    
    public int agregarArticuloCompra(Producto producto, Double peticion){
        int mensaje = 0; //no se pudo realizar
        boolean suficiente = true;
        boolean existe = false;
        boolean agregado = false;
        
        
            if(ControladorProducto.getInstance().existeProducto(producto)){//si existe
                existe = true;
                Producto productoInventario = ControladorProducto.getInstance().regresarProducto(producto);
                
                System.out.println("Producto en existencia:");
                System.out.println(productoInventario.toString());
                
                if(ControladorProducto.getInstance().existeSuficiente(productoInventario, peticion)){//si tiene suficiente
                    suficiente = true;
                    System.out.println("Producto actualizado:");
                    productoInventario.setCantidad(productoInventario.getCantidad() - peticion);
                    System.out.println(productoInventario.toString());
                    ControladorProducto.getInstance().modificarProducto(productoInventario);
                    
                    producto.setCantidad(1.);
                    producto = ControladorProducto.getInstance().regresarProducto(producto);
                    System.out.println("Producto en el inventario actualizado:");
                    System.out.println(producto.toString());
                    
                    System.out.println("Producto en lista de compra:");
                    Producto productoCompra = new Producto(productoInventario.getNombre(), productoInventario.getId(), peticion, productoInventario.getPrecioProveedor(), productoInventario.getPrecioVenta(), productoInventario.getPuntosOtorga());
                    System.out.println(productoCompra.toString());
                    
                    this.listaArticulos.add(productoCompra);
                    
                    System.out.println("Cantidad en el inventario:");
                    Double cantidad = ControladorProducto.getInstance().regresarCantidad(producto);
                    System.out.println(cantidad);
                    
                }else{
                    suficiente = false;
                    
                }
            }else{
                existe = false;
            }
        
        
        if(!existe)
            mensaje = 1; //no existe producto
        else if(existe & !suficiente)
            mensaje = 2; //no suficiente
        else if(existe & suficiente)
            mensaje = 4; //se agrego
        
        return mensaje;
    }
    
    public void regresarArticulosInventario(){
        for(int i = 0; i < listaArticulos.size(); i++){
            System.out.println("");
            System.out.println("/n/nProducto recuperado de la lista de compra:");
            System.out.println(listaArticulos.get(i).toString());
            
            
            String id = listaArticulos.get(i).getId();
            System.out.println("/n/nProducto auxiliar:");
            Producto producto = new Producto(id);
            System.out.println(producto.toString());
            
            System.out.println("Cantidad en el inventario:");
            Double cantidad = ControladorProducto.getInstance().regresarCantidad(producto);
            System.out.println(cantidad);
            
            producto = listaArticulos.get(i);
            System.out.println(producto.toString());
            
            Double newCantidad = producto.getCantidad() + cantidad;
            producto.setCantidad(newCantidad);
            System.out.println("Producto modificado:");
            System.out.println(producto.toString());
            ControladorProducto.getInstance().modificarProducto(producto);
        }
        
        limpiarListaCompra();
    }
    
    public Double totalListaCompra(){
        Double total = 0.;
        for(int i = 0; i < listaArticulos.size(); i++){
            total = total + (listaArticulos.get(i).getCantidad() * listaArticulos.get(i).getPrecioVenta());
        }
        return total;
    }
    
    public Double puntosGenerados(){
        Double puntos = 0.;
        for(int i = 0; i < listaArticulos.size(); i++){
            puntos = puntos + (listaArticulos.get(i).getCantidad() * listaArticulos.get(i).getPuntosOtorga());
        }
        return puntos;
    }
    
    public Double descuento(Integer puntos){
        Double descuento = 0.;
        Double totalLista = totalListaCompra();
        switch(puntos){
            case 0:
                descuento = totalLista * 0;
                break;
            case 200:
                descuento = totalLista * 0.03;
                break;
            case 400:
                descuento = totalLista * 0.06;
                break;
            case 600:
                descuento = totalLista * 0.09;
                break;
            case 800:
                descuento = totalLista * 0.12;
                break;
            case 1000:
                descuento = totalLista * 0.15;
                break;
        }
        return descuento;
    }
    
    public Double importeTotal(Integer puntos){
        Double importeTotal = totalListaCompra() - descuento(puntos);
        return importeTotal;
    }
    
}
