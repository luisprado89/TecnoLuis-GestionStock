package Reportes;

import Modelo.VentaDao;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.swing.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PDF {

    public void generarFacturaPdf(int idVenta, JTable tableVenta,
                                  JTextField txtDNIEmpresa, JTextField txtNombreEmpresa, JTextField txtTelefonoEmpresa,
                                  JTextField txtDireccionEmpresa, JTextField txtRazonEmpresa,
                                  String dniCliente, String nombreCliente, String telefonoCliente,
                                  String direccionCliente,
                                  String total) {

        try {
            String fechaStr = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

            // Asegurarse de que el directorio exista
            File carpeta = new File("src/Ventas");
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            File file = new File(carpeta, "venta" + idVenta + "_" + fechaStr + ".pdf");
            FileOutputStream archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            // Logo
            Image img = Image.getInstance("src/img/logo_pdf.png");
            img.scaleToFit(80, 80);
            img.setAlignment(Element.ALIGN_LEFT);

            // Fuente negrita
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);

            // Fecha y Factura
            Paragraph fecha = new Paragraph();
            fecha.add(new Chunk("Factura: " + idVenta + "\nFecha: " + fechaStr + "\n\n", negrita));
            fecha.setAlignment(Element.ALIGN_RIGHT);

            // Tabla encabezado (empresa)
            PdfPTable encabezado = new PdfPTable(2);
            encabezado.setWidthPercentage(100);
            encabezado.setWidths(new float[]{40f, 60f});
            encabezado.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            PdfPCell logoCell = new PdfPCell(img);
            logoCell.setBorder(0);
            encabezado.addCell(logoCell);

            String empresaInfo = "DNI/NIF: " + txtDNIEmpresa.getText() + "\n"
                    + "Nombre: " + txtNombreEmpresa.getText() + "\n"
                    + "Teléfono: " + txtTelefonoEmpresa.getText() + "\n"
                    + "Dirección: " + txtDireccionEmpresa.getText() + "\n"
                    + "Razón: " + txtRazonEmpresa.getText();
            PdfPCell empresaCell = new PdfPCell(new Phrase(empresaInfo, negrita));
            empresaCell.setBorder(0);
            encabezado.addCell(empresaCell);

            doc.add(encabezado);
            doc.add(fecha);

            // Datos del cliente
            Paragraph cliente = new Paragraph("Datos del Cliente", negrita);
            cliente.setSpacingBefore(10);
            cliente.setSpacingAfter(5);
            doc.add(cliente);

            PdfPTable tablaCliente = new PdfPTable(4);
            tablaCliente.setWidthPercentage(100);
            tablaCliente.setWidths(new float[]{25f, 25f, 25f, 25f});

            tablaCliente.addCell(celda("DNI", negrita));
            tablaCliente.addCell(celda("Nombre", negrita));
            tablaCliente.addCell(celda("Teléfono", negrita));
            tablaCliente.addCell(celda("Dirección", negrita));

            tablaCliente.addCell(dniCliente);
            tablaCliente.addCell(nombreCliente);
            tablaCliente.addCell(telefonoCliente);
            tablaCliente.addCell(direccionCliente);

            doc.add(tablaCliente);

            // Tabla de productos
            Paragraph productos = new Paragraph("Productos", negrita);
            productos.setSpacingBefore(10);
            productos.setSpacingAfter(5);
            doc.add(productos);

            PdfPTable tablaProductos = new PdfPTable(4);
            tablaProductos.setWidthPercentage(100);
            tablaProductos.setWidths(new float[]{20f, 40f, 20f, 20f});

            tablaProductos.addCell(celda("Cant.", negrita, BaseColor.LIGHT_GRAY));
            tablaProductos.addCell(celda("Nombre", negrita, BaseColor.LIGHT_GRAY));
            tablaProductos.addCell(celda("Precio U.", negrita, BaseColor.LIGHT_GRAY));
            tablaProductos.addCell(celda("Precio T.", negrita, BaseColor.LIGHT_GRAY));

            for (int i = 0; i < tableVenta.getRowCount(); i++) {
                tablaProductos.addCell(tableVenta.getValueAt(i, 3).toString()); // Cantidad
                tablaProductos.addCell(tableVenta.getValueAt(i, 1).toString()); // Nombre
                tablaProductos.addCell(tableVenta.getValueAt(i, 4).toString()); // Precio Unitario
                tablaProductos.addCell(tableVenta.getValueAt(i, 5).toString()); // Precio Total
            }

            doc.add(tablaProductos);

            // Total
            Paragraph totalP = new Paragraph("Total a Pagar: " + total + " €", negrita);
            totalP.setAlignment(Element.ALIGN_RIGHT);
            totalP.setSpacingBefore(10);
            doc.add(totalP);

            // Firma
            Paragraph firma = new Paragraph("\nCancelación y Firma\n\n----------------------------", negrita);
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);

            // Mensaje final
            Paragraph mensaje = new Paragraph("Gracias por su compra", negrita);
            mensaje.setAlignment(Element.ALIGN_CENTER);
            mensaje.setSpacingBefore(10);
            doc.add(mensaje);

            doc.close();
            archivo.close();

            Desktop.getDesktop().open(file);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar PDF: " + e.getMessage());
        }
    }

    private PdfPCell celda(String texto, Font fuente) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, fuente));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private PdfPCell celda(String texto, Font fuente, BaseColor fondo) {
        PdfPCell cell = celda(texto, fuente);
        cell.setBackgroundColor(fondo);
        return cell;
    }
}
