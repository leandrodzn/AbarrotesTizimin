/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import interfaces.IPersistenciaDatos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.Direccion;
import modelo.Producto;

/**
 *
 * @author leodz
 */
public class PersistorDatos implements IPersistenciaDatos{
    private static PersistorDatos instance;
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private ArrayList<Producto> listaProductos = new ArrayList<>();
    
    /**
     *
     * @return
     */
    public static PersistorDatos getInstance(){
        if (instance == null)
            instance = new PersistorDatos();
        
        return instance;
    }
    
    @Override
    public void guardarClientes(ArrayList<Cliente> clientes){
        listaClientes.clear();
        listaClientes.addAll(clientes);
        
        try (PrintWriter writer = new PrintWriter(new File("BaseDatosClientes.csv"))) {

                StringBuilder sb = new StringBuilder();
    
                for (int i = 0; i < listaClientes.size(); i++) {
                    sb.append(listaClientes.get(i).getId());
                    sb.append(',');
                    sb.append(listaClientes.get(i).getNombres());
                    sb.append(',');
                    sb.append(listaClientes.get(i).getApellidoPaterno());
                    sb.append(',');
                    sb.append(listaClientes.get(i).getApellidoMaterno());
                    sb.append(',');
                    sb.append(listaClientes.get(i).getPuntosObtenidos());
                    //direccion compuesta
                    sb.append(',');
                    sb.append(listaClientes.get(i).getDireccion().getCalle());
                    sb.append(',');
                    sb.append(listaClientes.get(i).getDireccion().getNo_casa());
                    sb.append(',');
                    sb.append(listaClientes.get(i).getDireccion().getColonia());
                    sb.append(',');
                    sb.append(listaClientes.get(i).getDireccion().getCodigoPostal());
                    sb.append(',');
                    sb.append(listaClientes.get(i).getDireccion().getCiudad());
                    sb.append(',');
                    sb.append(listaClientes.get(i).getDireccion().getEstado());
                    //termina direccion
                    sb.append(',');
                    sb.append(listaClientes.get(i).getTelefono());
                    sb.append('\n');
                }
    
                writer.write(sb.toString());
                writer.close();

                System.out.println("Archivo 'BaseDatosClientes.csv' generado");
    
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
         
    }
    
    @Override
    public ArrayList<Cliente> cargarClientes(){
        BufferedReader bufferLectura = null;
            try {
                // Abrir el .csv en buffer de lectura
                bufferLectura = new BufferedReader(new FileReader("BaseDatosClientes.csv")); //Especificar nombre de archivo de entrada
                
                // Leer una linea del archivo
                String linea = bufferLectura.readLine();
                
                while (linea != null) {
                    // Separar la linea leída con el separador definido previamente
                    String[] campos = linea.split(","); 
                    String id = campos[0];
                    String nombres = campos[1];
                    String aPaterno = campos[2];
                    String aMaterno = campos[3];
                    Double puntos = Double.parseDouble(campos[4]);
                    //Direccion
                    Integer calle = Integer.parseInt(campos[5]);
                    String no_casa = campos[6];
                    String colonia = campos[7];
                    Integer cp = Integer.parseInt(campos[8]);
                    String ciudad = campos[9];
                    String estado= campos[10];
                    //termina direccion
                    Long telefono = Long.parseLong(campos[11]);
                    
                    
                    Direccion dir = new Direccion(calle, no_casa, colonia, cp, ciudad, estado);
                    listaClientes.add(new Cliente(id, nombres, aPaterno, aMaterno, puntos, dir, telefono));
                    //System.out.println(Arrays.toString(campos));
                    
                    // Volver a leer otra línea del fichero
                    linea = bufferLectura.readLine();
                }
                System.out.println("Archivo 'BaseDatosClientes.csv' cargado");
            }catch (IOException e) {
                e.printStackTrace();
            }finally {
                // Cierro el buffer de lectura
                if (bufferLectura != null) {
                    try {
                        bufferLectura.close();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return listaClientes;
    }
    
    @Override
    public void guardarProductos(ArrayList<Producto> productos){
        listaProductos.clear();
        listaProductos.addAll(productos);
        
        try (PrintWriter writer = new PrintWriter(new File("BaseDatosProductos.csv"))) {

                StringBuilder sb = new StringBuilder();
    
                for (int i = 0; i < listaProductos.size(); i++) {
                    sb.append(listaProductos.get(i).getId());
                    sb.append(',');
                    sb.append(listaProductos.get(i).getNombre());
                    sb.append(',');
                    sb.append(listaProductos.get(i).getCantidad());
                    sb.append(',');
                    sb.append(listaProductos.get(i).getPrecioProveedor());
                    sb.append(',');
                    sb.append(listaProductos.get(i).getPrecioVenta());
                    sb.append(',');
                    sb.append(listaProductos.get(i).getPuntosOtorga());
                    sb.append('\n');
                }
    
                writer.write(sb.toString());
                writer.close();

                System.out.println("Archivo 'BaseDatosProductos.csv' generado");
    
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
         
    }
   
    @Override
    public ArrayList<Producto> cargarProductos(){
        BufferedReader bufferLectura = null;
            try {
                // Abrir el .csv en buffer de lectura
                bufferLectura = new BufferedReader(new FileReader("BaseDatosProductos.csv")); //Especificar nombre de archivo de entrada
                
                // Leer una linea del archivo
                String linea = bufferLectura.readLine();
                
                while (linea != null) {
                    // Separar la linea leída con el separador definido previamente
                    String[] campos = linea.split(","); 
                    String id = campos[0];
                    String nombre = campos[1];
                    Double cantidad = Double.parseDouble(campos[2]);
                    Double precioProveedor = Double.parseDouble(campos[3]);
                    Double precioVenta = Double.parseDouble(campos[4]);
                    Integer puntosOtorga = Integer.parseInt(campos[5]);
                
                    listaProductos.add(new Producto(nombre, id, cantidad, precioProveedor, precioVenta, puntosOtorga));
                    //System.out.println(Arrays.toString(campos));
                    
                    // Volver a leer otra línea del fichero
                    linea = bufferLectura.readLine();
                }
                System.out.println("Archivo 'BaseDatosProductos.csv' cargado");
            }catch (IOException e) {
                e.printStackTrace();
            }finally {
                // Cierro el buffer de lectura
                if (bufferLectura != null) {
                    try {
                        bufferLectura.close();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return listaProductos;
    }
    

}
