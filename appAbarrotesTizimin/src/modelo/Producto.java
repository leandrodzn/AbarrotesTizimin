/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author leodz
 */
public class Producto {
    private String nombre;
    private String id;
    private Double cantidad;
    private Double precioProveedor;
    private Double precioVenta;
    private Integer puntosOtorga;
    
    public Producto(String id){
        this.id = id;
    }

    public Producto(String nombre, String id, Double cantidad, Double precioProveedor, Double precioVenta, Integer puntosOtorga) {
        this.nombre = nombre;
        this.id = id;
        this.cantidad = cantidad;
        this.precioProveedor = precioProveedor;
        this.precioVenta = precioVenta;
        this.puntosOtorga = puntosOtorga;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioProveedor() {
        return this.precioProveedor;
    }

    public void setPrecioProveedor(Double precioProveedor) {
        this.precioProveedor = precioProveedor;
    }

    public Double getPrecioVenta() {
        return this.precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getPuntosOtorga() {
        return this.puntosOtorga;
    }

    public void setPuntosOtorga(Integer puntosOtorga) {
        this.puntosOtorga = puntosOtorga;
    }

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", id=" + id + ", cantidad=" + cantidad + ", precioProveedor=" + precioProveedor + ", precioVenta=" + precioVenta + ", puntosOtorga=" + puntosOtorga + '}';
    }
    
    
}
