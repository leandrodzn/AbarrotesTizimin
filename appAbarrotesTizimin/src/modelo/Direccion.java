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
public class Direccion {
    private Integer calle;
    private String no_casa;
    private String colonia;
    private Integer codigoPostal;
    private String ciudad;
    private String estado;

    public Direccion(Integer calle, String no_casa, String colonia, Integer codigoPostal, String ciudad, String estado) {
        this.calle = calle;
        this.no_casa = no_casa;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.estado = estado;
    }

    public Integer getCalle() {
        return this.calle;
    }

    public void setCalle(Integer calle) {
        this.calle = calle;
    }

    public String getNo_casa() {
        return this.no_casa;
    }

    public void setNo_casa(String no_casa) {
        this.no_casa = no_casa;
    }

    public String getColonia() {
        return this.colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public Integer getCodigoPostal() {
        return this.codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return estado + ", " + ciudad  + ", colonia: " + colonia + " calle: " + calle + ", no_casa: " + no_casa +  ", C.P. " + codigoPostal ;
    }
    
    
    
}
