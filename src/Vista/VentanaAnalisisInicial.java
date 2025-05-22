/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import javax.swing.*;

/**
 *
 * @author LUIS
 */
public class VentanaAnalisisInicial extends javax.swing.JFrame {

    /**
     * Creates new form VentanaAnalisisInicial
     */
    public VentanaAnalisisInicial() {
        initComponents();

        setResizable(false);

        // Centrar ventana y evitar que cierre el programa
        setLocationRelativeTo(null); // Centrado en pantalla (o puedes usar setLocationRelativeTo(Sistema.this))
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana

        jEditorPane1.setContentType("text/html");

        jEditorPane1.setText(
                "<html>"
                        + "<body style='font-family:sans-serif; font-size: 12px;'>"
                        + "<h1>Análisis Inicial – TecnoLuis GestiónStock</h1>"

                        + "<h2>1. Contexto y justificación</h2>"
                        + "<p>En la actualidad, muchas pequeñas y medianas empresas necesitan herramientas accesibles y funcionales para llevar un control eficiente de sus operaciones comerciales. Muchas veces, la gestión de ventas, clientes, productos, proveedores y reportes se realiza de manera manual o con múltiples programas dispersos, lo que genera errores, pérdida de información o falta de integración.</p>"
                        + "<p><strong>TecnoLuis GestiónStock</strong> nace como respuesta a esta necesidad, proporcionando una solución unificada, intuitiva y extensible para la gestión completa de inventario y ventas desde una sola aplicación.</p>"

                        + "<h2>2. Objetivo general</h2>"
                        + "<p>Desarrollar una aplicación de escritorio en Java que facilite la gestión de ventas, productos, usuarios, clientes, proveedores y generación de reportes, integrando funcionalidades clave como facturación en PDF, exportación a Excel y reportes visuales mediante gráficos.</p>"

                        + "<h2>3. Objetivos específicos</h2>"
                        + "<ul>"
                        + "<li>Permitir el registro, edición y eliminación de productos, clientes, proveedores y usuarios.</li>"
                        + "<li>Automatizar el proceso de venta y generar facturas en formato PDF.</li>"
                        + "<li>Exportar datos a archivos Excel para análisis externo.</li>"
                        + "<li>Proporcionar reportes personalizados con JasperReports.</li>"
                        + "<li>Implementar validaciones, roles de usuario (Administrador y Vendedor) y medidas básicas de seguridad.</li>"
                        + "<li>Facilitar el acceso al manual del sistema, tanto en formato HTML como PDF.</li>"
                        + "</ul>"

                        + "<h2>4. Usuarios del sistema</h2>"
                        + "<p>El sistema está diseñado para ser utilizado por el personal de una empresa comercial, diferenciando entre dos tipos de usuarios:</p>"
                        + "<ul>"
                        + "<li><strong>Administrador</strong>: Tiene acceso completo a todas las funcionalidades del sistema, incluyendo configuración empresarial y gestión de usuarios.</li>"
                        + "<li><strong>Vendedor</strong>: Acceso limitado, centrado en la realización de ventas y consulta de información.</li>"
                        + "</ul>"

                        + "<h2>5. Tecnologías utilizadas</h2>"
                        + "<ul>"
                        + "<li>Lenguaje: Java SE</li>"
                        + "<li>Interfaz gráfica: Swing</li>"
                        + "<li>Base de datos: MySQL</li>"
                        + "<li>Reportes PDF: iText</li>"
                        + "<li>Exportación Excel: Apache POI</li>"
                        + "<li>Gráficos y reportes visuales: JasperReports, JFreeChart</li>"
                        + "<li>IDE utilizado: NetBeans</li>"
                        + "<li>Empaquetado: Ant + Launch4j</li>"
                        + "</ul>"

                        + "<h2>6. Estructura general del sistema</h2>"
                        + "<p>El sistema se divide en módulos funcionales accesibles desde un menú lateral:</p>"
                        + "<ul>"
                        + "<li><strong>Nueva Venta</strong>: Punto de venta con validación de stock, asignación de cliente y generación de factura.</li>"
                        + "<li><strong>Usuarios</strong>: Gestión de credenciales y roles, solo para administradores.</li>"
                        + "<li><strong>Clientes y Proveedores</strong>: Administración completa de datos de contacto y razón social.</li>"
                        + "<li><strong>Productos</strong>: Registro y actualización de inventario con generación de reportes Excel.</li>"
                        + "<li><strong>Ventas</strong>: Historial de transacciones con acceso a facturas generadas.</li>"
                        + "<li><strong>Configuración</strong>: Datos fiscales de la empresa para reflejar en facturas.</li>"
                        + "<li><strong>Reportes y gráficas</strong>: Exportación y visualización de datos estadísticos.</li>"
                        + "<li><strong>Ayuda y Manual de usuario</strong>: Integración de documentación accesible desde el sistema y en PDF.</li>"
                        + "</ul>"

                        + "<h2>7. Alcance del sistema</h2>"
                        + "<p>El sistema TecnoLuis GestiónStock cubre completamente las necesidades básicas de una tienda o empresa que requiere controlar inventario, gestionar ventas y mantener registros organizados de clientes y proveedores.</p>"
                        + "<p><strong>No incluye</strong>, por ahora, gestión de compras, contabilidad avanzada ni conexión web o remota. Sin embargo, su arquitectura modular permite futuras ampliaciones.</p>"

                        + "<h2>8. Justificación técnica</h2>"
                        + "<p>Java fue seleccionado por su portabilidad y soporte para desarrollo de aplicaciones de escritorio. Swing permite construir interfaces gráficas con alto grado de personalización. El uso de MySQL facilita la persistencia de datos, mientras que librerías externas como iText y POI aportan capacidad avanzada para generar documentos y reportes, cubriendo necesidades reales de una empresa.</p>"

                        + "</body>"
                        + "</html>"
        );
        jEditorPane1.setEditable(false);
        jScrollPane1.setViewportView(jEditorPane1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Análisis Inicial del Proyecto - TecnoLuis GestiónStock");

        jScrollPane1.setViewportView(jEditorPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1150, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaAnalisisInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaAnalisisInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaAnalisisInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaAnalisisInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaAnalisisInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
