package Reportes;

import java.awt.Desktop;
import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import Util.Conexion;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author LUIS
 */

public class Excel {

    public static void reporte() {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Productos");

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Cambiado: usar LogoTL.jpg
            InputStream is = Excel.class.getResourceAsStream("/Img/LogoTL.jpg");
            if (is == null) {
                throw new FileNotFoundException("No se encontró el logo en: /Img/LogoTL.jpg");
            }

            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
            is.close();

            CreationHelper help = book.getCreationHelper();
            Drawing<?> draw = sheet.createDrawingPatriarch();
            ClientAnchor anchor = help.createClientAnchor();

            Row filaLogo = sheet.createRow(1);
            filaLogo.setHeightInPoints(100);
            filaLogo.createCell(0).setCellValue(" ");
            anchor.setCol1(0);
            anchor.setRow1(1);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(3, 3);

            // Título
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);

            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("Reporte de Productos");
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 4));

            // Cabecera
            String[] cabecera = {"Código", "Nombre", "Descripción", "Proveedor", "Existencia", "Precio"};

            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);

            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);

            Row filaEncabezados = sheet.createRow(4);
            for (int i = 0; i < cabecera.length; i++) {
                Cell celda = filaEncabezados.createCell(i);
                celda.setCellStyle(headerStyle);
                celda.setCellValue(cabecera[i]);
            }

            // Datos
            Conexion cn = new Conexion();
            con = cn.getConnection();

            String sql = """
                SELECT p.codigo, p.nombre, p.descripcion, pr.nombre AS proveedor, p.stock, p.precio
                FROM productos p
                JOIN proveedor pr ON p.proveedor_id = pr.id
            """;

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);

            int numFilaDatos = 5;
            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);
                for (int i = 0; i < cabecera.length; i++) {
                    Cell celda = filaDatos.createCell(i);
                    celda.setCellStyle(datosEstilo);
                    celda.setCellValue(rs.getString(i + 1));
                }
                numFilaDatos++;
            }

            for (int i = 0; i < cabecera.length; i++) {
                sheet.autoSizeColumn(i);
            }

            sheet.setZoom(150);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String fechaHora = LocalDateTime.now().format(formatter);

            String fileName = "productos_" + fechaHora;
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/" + fileName + ".xlsx");

            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                book.write(fileOut);
            }

            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "Reporte generado correctamente");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Logo no encontrado. Verifica la ruta: src/Img/LogoTL.jpg");
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Excel.class.getName()).log(Level.WARNING, "Error al cerrar recursos", ex);
            }
        }
    }
}
