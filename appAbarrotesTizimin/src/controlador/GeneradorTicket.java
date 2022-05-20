/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import modelo.Compra;
import modelo.Ticket;

/**
 *
 * @author leodz
 */
public class GeneradorTicket {
    private static GeneradorTicket instance;
    private Ticket ticketReciente;
    
    public static GeneradorTicket getInstance(){
        if(instance == null)
            instance = new GeneradorTicket();
        
        return instance;
    }
    
    public void generarTicket(Compra compra){
        ticketReciente = new Ticket(compra);
        
        try {
            File file = new File("Ticket.pdf");
            PdfWriter pdfWriter = new PdfWriter(file);
 
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
 
            Document document = new Document(pdfDocument);

            StringBuilder sb = new StringBuilder();
            sb.append("Fecha y hora: " + ticketReciente.getFecha_hora());
            sb.append('\n');
            sb.append("Cliente: " + ticketReciente.getNombreCliente() + " ID: " + ticketReciente.getIdCliente());
            sb.append('\n');
            
            StringBuilder sb2 = new StringBuilder();
            sb.append('\n' + "Artículos en la compra: " + '\n');

            
            for(int i = 0; i < ticketReciente.getProductos().size(); i ++){
                sb2.append("Nombre: " + ticketReciente.getProductos().get(i).getNombre());
                sb2.append(" | ");
                sb2.append("Cantidad: " + ticketReciente.getProductos().get(i).getCantidad());
                sb2.append(" | ");
                sb2.append("Precio: $" + ticketReciente.getProductos().get(i).getPrecioVenta() + " c/u" + '\n');
            }
            
            StringBuilder sb3 = new StringBuilder();
            sb3.append('\n' + "Importe total de los productos: $" + ticketReciente.getImporteProductos() + '\n');
            sb3.append("Descuento en la compra: $" + ticketReciente.getDescuento() + '\n');
            sb3.append("Importe total de compra: $" + ticketReciente.getImporteFinal() + '\n');
            sb3.append("Pago: $" + ticketReciente.getPago() + '\n');
            sb3.append("Cambio: $" + ticketReciente.getCambio() + '\n');
            sb3.append("Puntos generados por compra: " + ticketReciente.getPuntosCompra() + '\n');
            sb3.append("Puntos totales del cliente: " + ticketReciente.getPuntosTotales() + '\n');
            
            //se agrega a parrafos
            Paragraph paragraph = new Paragraph( ticketReciente.getNombreTienda() + '\n');
            Paragraph paragraph1 = new Paragraph("Ticket de compra:");
            Paragraph paragraph2 = new Paragraph(sb.toString());
            Paragraph paragraph3 = new Paragraph(sb2.toString());
            Paragraph paragraph4 = new Paragraph(sb3.toString());
            
            //se agrega al documento
            document.add(paragraph);
            document.add(paragraph1);
            document.add(paragraph2);
            document.add(paragraph3);
            document.add(paragraph4);

            document.close();
            pdfWriter.close();
 
            System.out.println("Archivo 'ticket.pdf' generado en la raíz.\n");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        

    }

    public Ticket getTicketReciente() {
        return this.ticketReciente;
    }
    
}
