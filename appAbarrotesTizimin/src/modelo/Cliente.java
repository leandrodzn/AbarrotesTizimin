/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import interfaces.IObservador;

/**
 *
 * @author leodz
 */
public class Cliente implements IObservador{
    private String id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Double puntosObtenidos;
    private Direccion direccion;
    private Long telefono;

    public Cliente(String nombres, String apellidoPaterno, String apellidoMaterno, Double puntosObtenidos, Direccion direccion, Long telefono) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.puntosObtenidos = puntosObtenidos;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    public Cliente(String nombres, String apellidoPaterno, String apellidoMaterno, Direccion direccion, Long telefono) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    public Cliente(String id, String nombres, String apellidoPaterno, String apellidoMaterno, Double puntosObtenidos, Direccion direccion, Long telefono) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.puntosObtenidos = puntosObtenidos;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Cliente(String id) {
        this.id = id;
    }
    

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Double getPuntosObtenidos() {
        return this.puntosObtenidos;
    }

    public void setPuntosObtenidos(Double puntosObtenidos) {
        this.puntosObtenidos = puntosObtenidos;
    }

    public Direccion getDireccion() {
        return this.direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Long getTelefono() {
        return this.telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
      
    @Override
    public void actualizar(){
        System.out.println("Mensaje para " + this.telefono + ":");
        System.out.println("Estimado " + this.apellidoPaterno + " " + this.apellidoMaterno + " " + this.nombres +" ID: " + this.id +  ", tiene " + this.puntosObtenidos + " puntos para usar en Abarrotes Tizimín");
    }
    
}
