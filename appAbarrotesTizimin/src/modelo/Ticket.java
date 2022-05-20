/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author leodz
 */
public class Ticket {
    private Compra compra;
    private String nombreTienda = "Abarrotes Tizimín";
    private String fecha_hora;
    private String nombreCliente;
    private String idCliente;
    private Double puntosCompra;
    private Double importeProductos;
    private Double descuento;
    private Double importeFinal;
    private Double pago;
    private Double cambio;
    private Double puntosTotales;
    private ArrayList<Producto> productos = new ArrayList<>();

    public Ticket(Compra compra) {
        this.compra = compra;
        this.fecha_hora = compra.getFecha_hora();
        this.nombreCliente = compra.getCliente().getApellidoPaterno() + " " + compra.getCliente().getApellidoMaterno()+ " " + compra.getCliente().getNombres();
        this.idCliente = compra.getCliente().getId();
        this.importeProductos = compra.getImporteProductos();
        this.descuento = compra.getDescuento();
        this.importeFinal = compra.getImporteFinal();
        this.pago = compra.getPago();
        this.cambio = compra.getCambio();
        this.puntosCompra = compra.getPuntosGenerados();
        this.puntosTotales = compra.getCliente().getPuntosObtenidos();
        this.productos.addAll(compra.getProductos());
    }

    public Compra getCompra() {
        return this.compra;
    }

    
    public String getNombreTienda() {
        return this.nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public String getFecha_hora() {
        return this.fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Double getPuntosCompra() {
        return puntosCompra;
    }

    public void setPuntosCompra(Double puntosCompra) {
        this.puntosCompra = puntosCompra;
    }

    public Double getImporteProductos() {
        return importeProductos;
    }

    public void setImporteProductos(Double importeProductos) {
        this.importeProductos = importeProductos;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getImporteFinal() {
        return importeFinal;
    }

    public void setImporteFinal(Double importeFinal) {
        this.importeFinal = importeFinal;
    }

    

    public Double getPago() {
        return pago;
    }

    public void setPago(Double pago) {
        this.pago = pago;
    }

    public Double getCambio() {
        return cambio;
    }

    public void setCambio(Double cambio) {
        this.cambio = cambio;
    }

    public Double getPuntosTotales() {
        return puntosTotales;
    }

    public void setPuntosTotales(Double puntosTotales) {
        this.puntosTotales = puntosTotales;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public String getIdCliente() {
        return this.idCliente;
    }

    
    
}
