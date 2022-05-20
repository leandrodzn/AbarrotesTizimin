/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author leodz
 */
public class Compra {
    private String fecha_hora;
    private Double importeProductos;
    private Double descuento;
    private Double importeFinal;
    private Double pago;
    private Double cambio;
    private ArrayList<Producto> productos;
    private Cliente cliente;
    private Double puntosGenerados;

    public Compra(Double iProductos, Double descuento, Double importeFinal, Double pago, Double cambio, ArrayList<Producto> productos, Cliente cliente, Double puntosGenerados) {
        this.fecha_hora = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        this.importeProductos = iProductos;
        this.descuento = descuento;
        this.importeFinal = importeFinal;
        this.pago = pago;
        this.cambio = cambio;
        this.productos = new ArrayList<>(productos);
        this.cliente = cliente;
        this.puntosGenerados = puntosGenerados;
    }

    public String getFecha_hora() {
        return this.fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public Double getImporteProductos() {
        return this.importeProductos;
    }

    public void setImporteProductos(Double importeProductos) {
        this.importeProductos = importeProductos;
    }

    public Double getDescuento() {
        return this.descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }
    
    
    
    public Double getImporteFinal() {
        return this.importeFinal;
    }

    public void setImporteFinal(Double importeFinal) {
        this.importeFinal = importeFinal;
    }

    public Double getPago() {
        return this.pago;
    }

    public void setPago(Double pago) {
        this.pago = pago;
    }

    public Double getCambio() {
        return this.cambio;
    }

    public void setCambio(Double cambio) {
        this.cambio = cambio;
    }

    public ArrayList<Producto> getProductos() {
        return this.productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getPuntosGenerados() {
        return this.puntosGenerados;
    }

    public void setPuntosGenerados(Double puntosGenerados) {
        this.puntosGenerados = puntosGenerados;
    }
    
    
}
