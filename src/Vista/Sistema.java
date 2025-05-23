package Vista;

import Modelo.*;
import Reportes.Excel;
import Reportes.Grafico;
import Reportes.PDF;
import Util.Conexion;
import Util.UtilImagenes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LUIS
 */
public class Sistema extends javax.swing.JFrame {

    // Ventanas secundarias para controlar que no se abran a la vez
    private VentanaCreditos ventanaCreditos = null;
    private VentanaManualUsuario ventanaManual = null;
    private VentanaPrimerosPasos ventanaPasos = null;
    private VentanaAnalisisInicial ventanaAnalisis = null;


    ProveedorDao proveedorDao = new ProveedorDao();
    ProductoDao productoDao = new ProductoDao();
    //Datos del usuario que inicio sesión
    private String usuarioNombre;
    private String usuarioRol;
    //Conexion a la base de datos
    private final Conexion cn = new Conexion();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    /**
     * Creates new form Sistema
     */
    public Sistema(String nombreUsuario, String rolUsuario) {
        initComponents();
        this.setLocationRelativeTo(null);



        this.usuarioNombre = nombreUsuario;
        this.usuarioRol = rolUsuario;
        //Ocultamos los campos de ID obtenidas de la base de datos y que se usan para la edición
        txtIDCliente.setVisible(false);
        txtIDProveedor.setVisible(false);
        txtIDUsuario.setVisible(false); //tabla 7
        txtIDEmpresa.setVisible(false); // tabla 6
        txtIDVentas.setVisible(false); // tabla 5
        txtIDProducto.setVisible(false); // tabla 4
        txtIDProveedor.setVisible(false); // tabla 3
        txtIDCliente.setVisible(false); // tabla 2
        //Tabla 1
        txtIDClienteVenta.setVisible(false); //  tabla 1
        txtTelefonoClienteVenta.setVisible(false); //
        txtDireccionClienteVenta.setVisible(false); //
        txtRazonClienteVenta.setVisible(false); //


        configurarEventos();  // Este evento se encarga de la configuración de eventos para las tablas en este momento lo usamos para cuando se haga click en la pestaña "Ventas" llame al boton que carga la lista 



        proveedorDao.llenarComboProveedores(cbxProveedorProducto);

        // Mostrar el rol en el JLabel
        txtVendedorACliente.setText("<html><div style='text-align: center;'>Bienvenido: " + usuarioNombre + "<br>(" + rolUsuario + ")</div></html>");




        // Aplicar restricciones si el usuario no es administrador
        aplicarPrivilegios();

        // Redimensionar imagen
        SwingUtilities.invokeLater(() -> {
            ImageIcon iconoOriginal = (ImageIcon) jLabelLogoT.getIcon();
            jLabelLogoT.setIcon(
                    UtilImagenes.redimensionarDesdeIcono(iconoOriginal, jLabelLogoT.getWidth(), jLabelLogoT.getHeight())
            );
        });
        
// Tooltips asociados al título visible de cada pestaña
        Map<String, String> tooltipsMap = new HashMap<>();
        tooltipsMap.put("Ventas", "Realizar una nueva venta");
        tooltipsMap.put("Clientes", "Gestión de clientes");
        tooltipsMap.put("Proveedores", "Administrar proveedores");
        tooltipsMap.put("Productos", "Registrar y modificar productos");
        tooltipsMap.put("Historial", "Consultar historial de ventas");
        tooltipsMap.put("Configuración", "Configuración del sistema");
        tooltipsMap.put("Usuarios", "Agregar Usuario");

        for (int i = 0; i < jTabbedPane1.getTabCount(); i++) {
            String titulo = jTabbedPane1.getTitleAt(i);
            String tooltip = tooltipsMap.get(titulo);
            if (tooltip != null) {
                jTabbedPane1.setToolTipTextAt(i, tooltip);
            }
        }


        // Llamar al método para obtener la primera empresa (id = 1) de momento es la unica que tenemos
        EmpresaDao empresaDao = new EmpresaDao();
        Empresa empresa = empresaDao.obtenerEmpresaPorId(1); // Obtenemos la empresa con id 1

        // Comprobar si la empresa fue encontrada y asignar los valores a los campos de texto
        if (empresa != null) {
            txtIDEmpresa.setText(String.valueOf(empresa.getId()));  // Mostrar el ID de la empresa
            txtDNIEmpresa.setText(empresa.getDniNif());
            txtNombreEmpresa.setText(empresa.getNombre());
            txtTelefonoEmpresa.setText(empresa.getTelefono());
            txtRazonEmpresa.setText(empresa.getRazonSocial());
            txtDireccionEmpresa.setText(empresa.getDireccion());
        } else {
            // Si no se encuentra la empresa, puedes manejar el caso (por ejemplo, mostrando un mensaje de error)
            JOptionPane.showMessageDialog(this, "No se encontró la empresa con ID 1.");
        }


    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnNuevaVenta = new javax.swing.JButton();
        btnListarClientes = new javax.swing.JButton();
        btnListarProveedores = new javax.swing.JButton();
        btnListarProductos = new javax.swing.JButton();
        btnListarVentas = new javax.swing.JButton();
        btnConfiguracion = new javax.swing.JButton();
        jLabelLogoT = new javax.swing.JLabel();
        txtVendedorACliente = new javax.swing.JLabel();
        btnListarUsuarios = new javax.swing.JButton();
        jLabelCabecera = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelNuevaVenta = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnEliminarVenta = new javax.swing.JButton();
        txtCodigoVenta = new javax.swing.JTextField();
        txtNombreVenta = new javax.swing.JTextField();
        txtCantidadVenta = new javax.swing.JTextField();
        txtPrecioVenta = new javax.swing.JTextField();
        txtStockVenta = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableVenta = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDniClienteVenta = new javax.swing.JTextField();
        txtNombreClienteVenta = new javax.swing.JTextField();
        btnGenerarVenta = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        labelTotalVenta = new javax.swing.JLabel();
        txtTelefonoClienteVenta = new javax.swing.JTextField();
        txtDireccionClienteVenta = new javax.swing.JTextField();
        txtRazonClienteVenta = new javax.swing.JTextField();
        txtIDClienteVenta = new javax.swing.JTextField();
        jLabelTituloNuevaVEnta = new javax.swing.JLabel();
        panelUsuarios = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtIDUsuario = new javax.swing.JTextField();
        cbxRolUsuario = new javax.swing.JComboBox<>();
        btnEliminarUsuario = new javax.swing.JButton();
        btnEditarUsuario = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableUsuarios = new javax.swing.JTable();
        btnGuardarUsuario = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        txtCorreoUsuario = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtContrasenaUsuario = new javax.swing.JTextField();
        jLabelTituloNuevaVEnta5 = new javax.swing.JLabel();
        panelClientes = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtDniCliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        txtDireccionCliente = new javax.swing.JTextField();
        txtRazonSocialCliente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCliente = new javax.swing.JTable();
        btnGuardarCliente = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        btnNuevoCliente = new javax.swing.JButton();
        txtIDCliente = new javax.swing.JTextField();
        jLabelTituloNuevaVEnta1 = new javax.swing.JLabel();
        panelProveedores = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableProveedor = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        txtDNIProveedor = new javax.swing.JTextField();
        txtNombreProveedor = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTelefonoProveedor = new javax.swing.JTextField();
        txtDireccionProveedor = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtRazonSProveedor = new javax.swing.JTextField();
        btnEditarProveedor = new javax.swing.JButton();
        btnGuardarProveedor = new javax.swing.JButton();
        btnEliminarProveedor = new javax.swing.JButton();
        btnNuevoProveedor = new javax.swing.JButton();
        txtIDProveedor = new javax.swing.JTextField();
        jLabelTituloNuevaVEnta2 = new javax.swing.JLabel();
        panelProductos = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableProducto = new javax.swing.JTable();
        txtCodigoProducto = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtDescripcionProducto = new javax.swing.JTextField();
        txtCantidadProducto = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtPrecioProducto = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        btnGuardarProducto = new javax.swing.JButton();
        btnEditarProducto = new javax.swing.JButton();
        btnNuevoProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        cbxProveedorProducto = new javax.swing.JComboBox<>();
        btnExcelProducto = new javax.swing.JButton();
        txtIDProducto = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        jLabelTituloNuevaVEnta3 = new javax.swing.JLabel();
        panelVentas = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableVentas = new javax.swing.JTable();
        btnPDFVentas = new javax.swing.JButton();
        txtIDVentas = new javax.swing.JTextField();
        jLabelTituloNuevaVEnta4 = new javax.swing.JLabel();
        panelConfiguracion = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtDNIEmpresa = new javax.swing.JTextField();
        txtDireccionEmpresa = new javax.swing.JTextField();
        txtNombreEmpresa = new javax.swing.JTextField();
        txtRazonEmpresa = new javax.swing.JTextField();
        txtTelefonoEmpresa = new javax.swing.JTextField();
        btnActualizarEmpresa = new javax.swing.JButton();
        txtIDEmpresa = new javax.swing.JTextField();
        jLabelTituloNuevaVEnta6 = new javax.swing.JLabel();
        btnGrafical = new javax.swing.JButton();
        miDateJCalendar = new com.toedter.calendar.JDateChooser();
        jLabel31 = new javax.swing.JLabel();
        botonCerrarSesion1 = new botoncerrarsesion.BotonCerrarSesion();
        relojPersonalizado1 = new relojpersonalizado.RelojPersonalizado();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemCerrarSesion = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemPrimerosPasos = new javax.swing.JMenuItem();
        jMenuItemManualUsuario = new javax.swing.JMenuItem();
        jMenuItemAnalisisInicial = new javax.swing.JMenuItem();
        jMenuItemCreditos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel Principal - TecnoLuis GestiónStock");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(88, 188, 103));

        btnNuevaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/metodo-de-pago40.png"))); // NOI18N
        btnNuevaVenta.setText("Nueva Venta");
        btnNuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaVentaActionPerformed(evt);
            }
        });

        btnListarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/clientes4035.png"))); // NOI18N
        btnListarClientes.setText("Clientes");
        btnListarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarClientesActionPerformed(evt);
            }
        });

        btnListarProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/proveedor.png"))); // NOI18N
        btnListarProveedores.setText("Proveedores");
        btnListarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarProveedoresActionPerformed(evt);
            }
        });

        btnListarProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/productos4035.png"))); // NOI18N
        btnListarProductos.setText("Productos");
        btnListarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarProductosActionPerformed(evt);
            }
        });

        btnListarVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ventas4035.png"))); // NOI18N
        btnListarVentas.setText("Ventas");
        btnListarVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarVentasActionPerformed(evt);
            }
        });

        btnConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/config.png"))); // NOI18N
        btnConfiguracion.setText("Config");
        btnConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionActionPerformed(evt);
            }
        });

        jLabelLogoT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/LogoTecnoL.png"))); // NOI18N

        txtVendedorACliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtVendedorACliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtVendedorACliente.setText("Vendedor");

        btnListarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Usuarios4035.png"))); // NOI18N
        btnListarUsuarios.setText("Usuarios");
        btnListarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarUsuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabelLogoT, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevaVenta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnListarClientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnListarProveedores, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnListarProductos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnListarVentas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConfiguracion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnListarUsuarios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtVendedorACliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabelLogoT, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(txtVendedorACliente)
                .addGap(18, 18, 18)
                .addComponent(btnNuevaVenta)
                .addGap(18, 18, 18)
                .addComponent(btnListarUsuarios)
                .addGap(18, 18, 18)
                .addComponent(btnListarClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnListarProveedores)
                .addGap(18, 18, 18)
                .addComponent(btnListarProductos)
                .addGap(18, 18, 18)
                .addComponent(btnListarVentas)
                .addGap(18, 18, 18)
                .addComponent(btnConfiguracion)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 730));

        jLabelCabecera.setBackground(new java.awt.Color(130, 205, 141));
        jLabelCabecera.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabelCabecera.setText("TecnoLuis - Sistema de Gestión Empresarial");
        getContentPane().add(jLabelCabecera, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 920, 70));

        jTabbedPane1.setBackground(new java.awt.Color(88, 188, 103));
        jTabbedPane1.setBorder(new javax.swing.border.MatteBorder(null));
        jTabbedPane1.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel1.setText("Código");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel3.setText("Stock Disponible");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setText("Cantidad");

        btnEliminarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar_1.png"))); // NOI18N
        btnEliminarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarVentaActionPerformed(evt);
            }
        });

        txtCodigoVenta.setToolTipText("Codigo producto");
        txtCodigoVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoVentaKeyPressed(evt);
            }
        });

        txtCantidadVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadVentaKeyPressed(evt);
            }
        });

        txtPrecioVenta.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel5.setText("Precio");

        tableVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE", "DESCRIPCIÓN", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ));
        jScrollPane1.setViewportView(tableVenta);
        if (tableVenta.getColumnModel().getColumnCount() > 0) {
            tableVenta.getColumnModel().getColumn(0).setPreferredWidth(10);
            tableVenta.getColumnModel().getColumn(1).setPreferredWidth(20);
            tableVenta.getColumnModel().getColumn(2).setPreferredWidth(100);
            tableVenta.getColumnModel().getColumn(3).setPreferredWidth(30);
            tableVenta.getColumnModel().getColumn(4).setPreferredWidth(30);
            tableVenta.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel6.setText("DNI");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel7.setText("NOMBRE");

        txtDniClienteVenta.setToolTipText("DNI cliente");
        txtDniClienteVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDniClienteVentaKeyPressed(evt);
            }
        });

        txtNombreClienteVenta.setEditable(false);

        btnGenerarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/print.png"))); // NOI18N
        btnGenerarVenta.setToolTipText("Presionar para registrar venta");
        btnGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVentaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/money.png"))); // NOI18N
        jLabel8.setText("Total a Pagar:");

        labelTotalVenta.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        jLabelTituloNuevaVEnta.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTituloNuevaVEnta.setForeground(new java.awt.Color(88, 188, 103));
        jLabelTituloNuevaVEnta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTituloNuevaVEnta.setText("Punto de Venta - Nueva Transacción");

        javax.swing.GroupLayout panelNuevaVentaLayout = new javax.swing.GroupLayout(panelNuevaVenta);
        panelNuevaVenta.setLayout(panelNuevaVentaLayout);
        panelNuevaVentaLayout.setHorizontalGroup(
            panelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevaVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelNuevaVentaLayout.createSequentialGroup()
                        .addGroup(panelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDniClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(panelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addComponent(txtTelefonoClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDireccionClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtRazonClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnGenerarVenta)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(panelNuevaVentaLayout.createSequentialGroup()
                        .addGroup(panelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(panelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelNuevaVentaLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(txtIDClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addGroup(panelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(panelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelNuevaVentaLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelNuevaVentaLayout.createSequentialGroup()
                                .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(txtStockVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
            .addGroup(panelNuevaVentaLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabelTituloNuevaVEnta, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelNuevaVentaLayout.setVerticalGroup(
            panelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevaVentaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTituloNuevaVEnta)
                .addGap(18, 18, 18)
                .addGroup(panelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(txtIDClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(jLabel3))
                    .addComponent(btnEliminarVenta, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStockVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNuevaVentaLayout.createSequentialGroup()
                        .addGroup(panelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(labelTotalVenta))
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNuevaVentaLayout.createSequentialGroup()
                        .addGroup(panelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnGenerarVenta)
                            .addGroup(panelNuevaVentaLayout.createSequentialGroup()
                                .addGroup(panelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDniClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombreClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTelefonoClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDireccionClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRazonClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(35, 35, 35))))
        );

        jTabbedPane1.addTab("Nueva Venta", panelNuevaVenta);

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel32.setText("Contraseña:");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel34.setText("Nombre:");

        cbxRolUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vendedor", "Administrador" }));

        btnEliminarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar_1.png"))); // NOI18N
        btnEliminarUsuario.setToolTipText("Eliminar usuario");
        btnEliminarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUsuarioActionPerformed(evt);
            }
        });

        btnEditarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/actualizar-datos4035.png"))); // NOI18N
        btnEditarUsuario.setToolTipText("Actualizar usuario");
        btnEditarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarUsuarioActionPerformed(evt);
            }
        });

        tableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "CORREO", "PASSWORD", "ROL"
            }
        ));
        tableUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableUsuariosMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tableUsuarios);
        if (tableUsuarios.getColumnModel().getColumnCount() > 0) {
            tableUsuarios.getColumnModel().getColumn(0).setPreferredWidth(10);
            tableUsuarios.getColumnModel().getColumn(1).setPreferredWidth(30);
            tableUsuarios.getColumnModel().getColumn(2).setPreferredWidth(80);
            tableUsuarios.getColumnModel().getColumn(3).setPreferredWidth(50);
            tableUsuarios.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        btnGuardarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/guardar-datos4035.png"))); // NOI18N
        btnGuardarUsuario.setToolTipText("Registrar usuario");
        btnGuardarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarUsuarioActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel37.setText("Rol:");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel33.setText("Correo:");

        jLabelTituloNuevaVEnta5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTituloNuevaVEnta5.setForeground(new java.awt.Color(88, 188, 103));
        jLabelTituloNuevaVEnta5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTituloNuevaVEnta5.setText("Gestión de Usuarios del Sistema");

        javax.swing.GroupLayout panelUsuariosLayout = new javax.swing.GroupLayout(panelUsuarios);
        panelUsuarios.setLayout(panelUsuariosLayout);
        panelUsuariosLayout.setHorizontalGroup(
            panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelUsuariosLayout.createSequentialGroup()
                        .addGroup(panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUsuariosLayout.createSequentialGroup()
                                .addGap(0, 135, Short.MAX_VALUE)
                                .addComponent(btnEditarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58))
                            .addGroup(panelUsuariosLayout.createSequentialGroup()
                                .addGroup(panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(panelUsuariosLayout.createSequentialGroup()
                                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                                            .addComponent(txtCorreoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panelUsuariosLayout.createSequentialGroup()
                                                .addGroup(panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(btnGuardarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addComponent(btnEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(cbxRolUsuario, 0, 150, Short.MAX_VALUE)
                                                    .addComponent(txtContrasenaUsuario))))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)))
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE))
                    .addGroup(panelUsuariosLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabelTituloNuevaVEnta5, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIDUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelUsuariosLayout.setVerticalGroup(
            panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsuariosLayout.createSequentialGroup()
                .addGroup(panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelUsuariosLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(txtIDUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelUsuariosLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabelTituloNuevaVEnta5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                    .addGroup(panelUsuariosLayout.createSequentialGroup()
                        .addGroup(panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCorreoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33))
                        .addGap(18, 18, 18)
                        .addGroup(panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(txtContrasenaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(cbxRolUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(107, 107, 107)
                        .addGroup(panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEditarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Usuarios", panelUsuarios);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel9.setText("DNI/NIF:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel10.setText("Nombre:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel11.setText("Teléfono:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel12.setText("Dirección:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel13.setText("Razón social:");

        tableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "DNI/NIF", "NOMBRE", "TELÉFONO", "DIRECCIÓN", "RAZÓN SOCIAL"
            }
        ));
        tableCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableCliente);
        if (tableCliente.getColumnModel().getColumnCount() > 0) {
            tableCliente.getColumnModel().getColumn(0).setPreferredWidth(10);
            tableCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
            tableCliente.getColumnModel().getColumn(3).setPreferredWidth(50);
            tableCliente.getColumnModel().getColumn(4).setPreferredWidth(50);
            tableCliente.getColumnModel().getColumn(5).setPreferredWidth(80);
        }

        btnGuardarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/guardar-datos4035.png"))); // NOI18N
        btnGuardarCliente.setToolTipText("Registrar cliente");
        btnGuardarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarClienteActionPerformed(evt);
            }
        });

        btnEditarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/actualizar-datos4035.png"))); // NOI18N
        btnEditarCliente.setToolTipText("Actualizar cliente");
        btnEditarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });

        btnEliminarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar_1.png"))); // NOI18N
        btnEliminarCliente.setToolTipText("Eliminar cliente");
        btnEliminarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        btnNuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo_1.png"))); // NOI18N
        btnNuevoCliente.setToolTipText("Nuevo cliente");
        btnNuevoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });

        jLabelTituloNuevaVEnta1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTituloNuevaVEnta1.setForeground(new java.awt.Color(88, 188, 103));
        jLabelTituloNuevaVEnta1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTituloNuevaVEnta1.setText("Gestión de Clientes Registrados");

        javax.swing.GroupLayout panelClientesLayout = new javax.swing.GroupLayout(panelClientes);
        panelClientes.setLayout(panelClientesLayout);
        panelClientesLayout.setHorizontalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClientesLayout.createSequentialGroup()
                .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelClientesLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEditarCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevoCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 70, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelClientesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRazonSocialCliente)
                            .addComponent(txtDniCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(txtNombreCliente)
                            .addComponent(txtTelefonoCliente)
                            .addComponent(txtDireccionCliente))
                        .addGap(23, 23, 23)))
                .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelClientesLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(txtIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClientesLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90))))
            .addGroup(panelClientesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabelTituloNuevaVEnta1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(570, Short.MAX_VALUE))
        );
        panelClientesLayout.setVerticalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClientesLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabelTituloNuevaVEnta1)
                .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelClientesLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(txtDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtRazonSocialCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEditarCliente)
                            .addComponent(btnGuardarCliente))
                        .addGap(23, 23, 23)
                        .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnEliminarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNuevoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClientesLayout.createSequentialGroup()
                        .addComponent(txtIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))))
        );

        jTabbedPane1.addTab("Clientes", panelClientes);

        tableProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "DNI/NIF", "NOMBRE", "TELÉFONO", "DIRECCIÓN", "RAZÓN SOCIAL"
            }
        ));
        tableProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProveedorMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableProveedor);
        if (tableProveedor.getColumnModel().getColumnCount() > 0) {
            tableProveedor.getColumnModel().getColumn(0).setPreferredWidth(10);
            tableProveedor.getColumnModel().getColumn(1).setPreferredWidth(50);
            tableProveedor.getColumnModel().getColumn(2).setPreferredWidth(100);
            tableProveedor.getColumnModel().getColumn(3).setPreferredWidth(50);
            tableProveedor.getColumnModel().getColumn(4).setPreferredWidth(50);
            tableProveedor.getColumnModel().getColumn(5).setPreferredWidth(80);
        }

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel14.setText("DNI/NIF:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel15.setText("Nombre:");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel16.setText("Teléfono:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel17.setText("Dirección:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel18.setText("Razón social:");

        btnEditarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/actualizar-datos4035.png"))); // NOI18N
        btnEditarProveedor.setToolTipText("Actualizar proveedor");
        btnEditarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProveedorActionPerformed(evt);
            }
        });

        btnGuardarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/guardar-datos4035.png"))); // NOI18N
        btnGuardarProveedor.setToolTipText("Registrar proveedor");
        btnGuardarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProveedorActionPerformed(evt);
            }
        });

        btnEliminarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar_1.png"))); // NOI18N
        btnEliminarProveedor.setToolTipText("Eliminar proveedor");
        btnEliminarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedorActionPerformed(evt);
            }
        });

        btnNuevoProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo_1.png"))); // NOI18N
        btnNuevoProveedor.setToolTipText("Nuevo proveedor");
        btnNuevoProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProveedorActionPerformed(evt);
            }
        });

        jLabelTituloNuevaVEnta2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTituloNuevaVEnta2.setForeground(new java.awt.Color(88, 188, 103));
        jLabelTituloNuevaVEnta2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTituloNuevaVEnta2.setText("Gestión de Proveedores");

        javax.swing.GroupLayout panelProveedoresLayout = new javax.swing.GroupLayout(panelProveedores);
        panelProveedores.setLayout(panelProveedoresLayout);
        panelProveedoresLayout.setHorizontalGroup(
            panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProveedoresLayout.createSequentialGroup()
                .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProveedoresLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIDProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelProveedoresLayout.createSequentialGroup()
                                .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnEliminarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnGuardarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                                .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelProveedoresLayout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(btnEditarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProveedoresLayout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(btnNuevoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(panelProveedoresLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRazonSProveedor)
                            .addComponent(txtDNIProveedor)
                            .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txtNombreProveedor)
                            .addComponent(txtDireccionProveedor))))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelProveedoresLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabelTituloNuevaVEnta2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelProveedoresLayout.setVerticalGroup(
            panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProveedoresLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabelTituloNuevaVEnta2)
                .addGap(47, 47, 47)
                .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProveedoresLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDireccionProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelProveedoresLayout.createSequentialGroup()
                                .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txtDNIProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtRazonSProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGuardarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(23, 23, 23)
                        .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNuevoProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                            .addComponent(btnEliminarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addComponent(txtIDProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(panelProveedoresLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("Proveedores", panelProveedores);

        tableProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "CÓDIGO", "NOMBRE", "DESCRIPCIÓN", "PROVEEDOR", "CANTIDAD", "PRECIO"
            }
        ));
        tableProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableProducto);
        if (tableProducto.getColumnModel().getColumnCount() > 0) {
            tableProducto.getColumnModel().getColumn(0).setPreferredWidth(10);
            tableProducto.getColumnModel().getColumn(1).setPreferredWidth(50);
            tableProducto.getColumnModel().getColumn(1).setHeaderValue("CÓDIGO");
            tableProducto.getColumnModel().getColumn(2).setPreferredWidth(30);
            tableProducto.getColumnModel().getColumn(3).setPreferredWidth(100);
            tableProducto.getColumnModel().getColumn(3).setHeaderValue("DESCRIPCIÓN");
            tableProducto.getColumnModel().getColumn(4).setPreferredWidth(80);
            tableProducto.getColumnModel().getColumn(5).setPreferredWidth(50);
            tableProducto.getColumnModel().getColumn(6).setPreferredWidth(50);
        }

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel19.setText("Código:");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel20.setText("Descripción:");

        txtDescripcionProducto.setAutoscrolls(false);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel21.setText("Cantidad:");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel22.setText("Precio:");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel23.setText("Proveedor:");

        btnGuardarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/guardar-datos4035.png"))); // NOI18N
        btnGuardarProducto.setToolTipText("Registrar producto");
        btnGuardarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProductoActionPerformed(evt);
            }
        });

        btnEditarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/actualizar-datos4035.png"))); // NOI18N
        btnEditarProducto.setToolTipText("Actualizar producto");
        btnEditarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProductoActionPerformed(evt);
            }
        });

        btnNuevoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo_1.png"))); // NOI18N
        btnNuevoProducto.setToolTipText("Nuevo producto");
        btnNuevoProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProductoActionPerformed(evt);
            }
        });

        btnEliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar_1.png"))); // NOI18N
        btnEliminarProducto.setToolTipText("Eliminar producto");
        btnEliminarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });

        btnExcelProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/excel4035.png"))); // NOI18N
        btnExcelProducto.setToolTipText("Crear un Excel con el stock");
        btnExcelProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcelProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelProductoActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel30.setText("Nombre:");

        jLabelTituloNuevaVEnta3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTituloNuevaVEnta3.setForeground(new java.awt.Color(88, 188, 103));
        jLabelTituloNuevaVEnta3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTituloNuevaVEnta3.setText("Inventario y Gestión de Productos");

        javax.swing.GroupLayout panelProductosLayout = new javax.swing.GroupLayout(panelProductos);
        panelProductos.setLayout(panelProductosLayout);
        panelProductosLayout.setHorizontalGroup(
            panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProductosLayout.createSequentialGroup()
                        .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelProductosLayout.createSequentialGroup()
                        .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelProductosLayout.createSequentialGroup()
                                .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnGuardarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                    .addComponent(btnEliminarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(28, 28, 28)
                                .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnEditarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnNuevoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExcelProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelProductosLayout.createSequentialGroup()
                                .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodigoProducto)
                                    .addComponent(txtNombreProducto)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelProductosLayout.createSequentialGroup()
                                .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23))
                                .addGap(18, 18, 18)
                                .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxProveedorProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPrecioProducto, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCantidadProducto)
                                    .addComponent(txtDescripcionProducto))))
                        .addGap(45, 45, 45)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProductosLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabelTituloNuevaVEnta3, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                .addComponent(txtIDProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203))
        );
        panelProductosLayout.setVerticalGroup(
            panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductosLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTituloNuevaVEnta3))
                .addGap(33, 33, 33)
                .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProductosLayout.createSequentialGroup()
                        .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(txtDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(cbxProveedorProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnExcelProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditarProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardarProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNuevoProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(btnEliminarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Productos", panelProductos);

        tableVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "CLIENTE", "VENDEDOR", "FECHA", "TOTAL"
            }
        ));
        tableVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableVentasMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tableVentas);
        if (tableVentas.getColumnModel().getColumnCount() > 0) {
            tableVentas.getColumnModel().getColumn(0).setPreferredWidth(20);
            tableVentas.getColumnModel().getColumn(1).setPreferredWidth(60);
            tableVentas.getColumnModel().getColumn(2).setPreferredWidth(50);
            tableVentas.getColumnModel().getColumn(3).setPreferredWidth(50);
            tableVentas.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        btnPDFVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pdf.png"))); // NOI18N
        btnPDFVentas.setToolTipText("Abrir pdf de venta fecha seleccionada");
        btnPDFVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFVentasActionPerformed(evt);
            }
        });

        jLabelTituloNuevaVEnta4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTituloNuevaVEnta4.setForeground(new java.awt.Color(88, 188, 103));
        jLabelTituloNuevaVEnta4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTituloNuevaVEnta4.setText("Inventario y Gestión de Productos");

        javax.swing.GroupLayout panelVentasLayout = new javax.swing.GroupLayout(panelVentas);
        panelVentas.setLayout(panelVentasLayout);
        panelVentasLayout.setHorizontalGroup(
            panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentasLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelVentasLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabelTituloNuevaVEnta4, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143)
                .addComponent(txtIDVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPDFVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        panelVentasLayout.setVerticalGroup(
            panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPDFVentas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelTituloNuevaVEnta4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIDVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                .addGap(45, 45, 45))
        );

        jTabbedPane1.addTab("Ventas", panelVentas);

        jLabel24.setText("DNI/NIF");

        jLabel25.setText("DIRECCIÓN");

        jLabel26.setText("NOMBRE");

        jLabel27.setText("RAZON SOCIAL");

        jLabel28.setText("TELÉFONO");

        btnActualizarEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/actualizar-datos4035.png"))); // NOI18N
        btnActualizarEmpresa.setText("ACTUALIZAR");
        btnActualizarEmpresa.setToolTipText("Actualizar datos de la empresa");
        btnActualizarEmpresa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarEmpresaActionPerformed(evt);
            }
        });

        jLabelTituloNuevaVEnta6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTituloNuevaVEnta6.setForeground(new java.awt.Color(88, 188, 103));
        jLabelTituloNuevaVEnta6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTituloNuevaVEnta6.setText("Configuración de los datos de la Empresa");

        javax.swing.GroupLayout panelConfiguracionLayout = new javax.swing.GroupLayout(panelConfiguracion);
        panelConfiguracion.setLayout(panelConfiguracionLayout);
        panelConfiguracionLayout.setHorizontalGroup(
            panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConfiguracionLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(txtIDEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112)
                .addComponent(jLabelTituloNuevaVEnta6, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelConfiguracionLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDNIEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRazonEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConfiguracionLayout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelConfiguracionLayout.createSequentialGroup()
                                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
                                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTelefonoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelConfiguracionLayout.createSequentialGroup()
                                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDireccionEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(panelConfiguracionLayout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(btnActualizarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelConfiguracionLayout.setVerticalGroup(
            panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfiguracionLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIDEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTituloNuevaVEnta6))
                .addGap(34, 34, 34)
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelConfiguracionLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDNIEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelConfiguracionLayout.createSequentialGroup()
                            .addComponent(jLabel28)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtTelefonoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelConfiguracionLayout.createSequentialGroup()
                            .addComponent(jLabel26)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(78, 78, 78)
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConfiguracionLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDireccionEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelConfiguracionLayout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRazonEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(66, 66, 66)
                .addComponent(btnActualizarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Configuración", panelConfiguracion);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 950, 570));

        btnGrafical.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/grafico-circular2_40.png"))); // NOI18N
        btnGrafical.setToolTipText("Crear grafico venta día seleciconado");
        btnGrafical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficalActionPerformed(evt);
            }
        });
        getContentPane().add(btnGrafical, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 80, 60, 50));

        miDateJCalendar.setToolTipText("Selecciona una fecha");
        getContentPane().add(miDateJCalendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 100, 250, -1));

        jLabel31.setText("Seleccionar:");
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 80, -1, -1));

        botonCerrarSesion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrarSesion1ActionPerformed(evt);
            }
        });
        getContentPane().add(botonCerrarSesion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 90, -1, -1));
        getContentPane().add(relojPersonalizado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 350, 50));

        jMenu1.setText("Inicio");

        jMenuItemCerrarSesion.setText("Cerra sesión");
        jMenuItemCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCerrarSesionActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemCerrarSesion);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");

        jMenuItemPrimerosPasos.setText("Primeros pasos");
        jMenuItemPrimerosPasos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrimerosPasosActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemPrimerosPasos);

        jMenuItemManualUsuario.setText("Manual Usuario");
        jMenuItemManualUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemManualUsuarioActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemManualUsuario);

        jMenuItemAnalisisInicial.setText("Análisis Inicial");
        jMenuItemAnalisisInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAnalisisInicialActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemAnalisisInicial);

        jMenuItemCreditos.setText("Creditos");
        jMenuItemCreditos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCreditosActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemCreditos);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarClienteActionPerformed
        //Obtenemos el ID del cliente para verificar si ya existe
        String idTexto = txtIDCliente.getText().trim();

        // Si ya hay un ID, significa que ese cliente ya existe
        if (!idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Este cliente ya existe. Si deseas modificarlo, usa el botón 'Editar'.",
                    "Cliente existente", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Botón para guardar cliente
        String dni = txtDniCliente.getText().trim();
        String nombre = txtNombreCliente.getText().trim();
        String telefono = txtTelefonoCliente.getText().trim();
        String direccion = txtDireccionCliente.getText().trim();
        String razonSocial = txtRazonSocialCliente.getText().trim();

        // Validación básica de campos vacíos
        if (dni.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || razonSocial.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validación de longitud exacta del DNI (9 caracteres)
        if (dni.length() != 9) {
            JOptionPane.showMessageDialog(this, "El DNI/NIF debe tener exactamente 9 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Validación de longitud máxima del teléfono
        if (telefono.length() > 20) {
            JOptionPane.showMessageDialog(this, "El teléfono no puede tener más de 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        Cliente cl = new Cliente();
        cl.setDni(dni);
        cl.setNombre(nombre);
        cl.setTelefono(telefono);
        cl.setDireccion(direccion);
        cl.setRazonSocial(razonSocial);

        ClienteDao clienteDao = new ClienteDao();
        boolean registrado = clienteDao.RegistrarCliente(cl);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "Cliente registrado correctamente.");
            // Limpia los campos si quieres
            limpiarCamposGuardarCliente();
            // Actualiza la tabla de clientes al terminar de guardar
            btnListarClientesActionPerformed(null); // Llama al método como si se hubiera hecho clic
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnGuardarClienteActionPerformed

    private void btnListarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarClientesActionPerformed
        // Botón para listar clientes
        // Buscar la pestaña por su título
        int index = jTabbedPane1.indexOfTab("Clientes");
        if (index != -1) {
            jTabbedPane1.setSelectedIndex(index);
        } else {
            JOptionPane.showMessageDialog(this, "La pestaña 'Clientes' no está disponible.");
            return;
        }

        // Botón para listar clientes
        ClienteDao clienteDao = new ClienteDao();
        List<Cliente> lista = clienteDao.ListarClientes();

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("DNI/NIF");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("TELÉFONO");
        modelo.addColumn("DIRECCIÓN");
        modelo.addColumn("RAZÓN SOCIAL");

        for (Cliente cl : lista) {
            Object[] fila = new Object[6];
            fila[0] = cl.getId();           // <-- ID en la primera columna
            fila[1] = cl.getDni();
            fila[2] = cl.getNombre();
            fila[3] = cl.getTelefono();
            fila[4] = cl.getDireccion();
            fila[5] = cl.getRazonSocial();
            modelo.addRow(fila);
        }

        tableCliente.setModel(modelo); // Actualiza la tabla con los clientes

    }//GEN-LAST:event_btnListarClientesActionPerformed

    private void tableClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClienteMouseClicked
        int fila = tableCliente.getSelectedRow();
        if (fila != -1) {
            txtIDCliente.setText(tableCliente.getValueAt(fila, 0).toString()); // Columna 0 = ID
            txtDniCliente.setText(tableCliente.getValueAt(fila, 1).toString());
            txtNombreCliente.setText(tableCliente.getValueAt(fila, 2).toString());
            txtTelefonoCliente.setText(tableCliente.getValueAt(fila, 3).toString());
            txtDireccionCliente.setText(tableCliente.getValueAt(fila, 4).toString());
            txtRazonSocialCliente.setText(tableCliente.getValueAt(fila, 5).toString());
            //  Deshabilita el botón Guardar para evitar duplicados
            btnGuardarCliente.setEnabled(false);
        }
    }//GEN-LAST:event_tableClienteMouseClicked

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        String idTexto = txtIDCliente.getText();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un cliente para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Deseas eliminar este cliente?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(idTexto);

            ClienteDao dao = new ClienteDao();
            boolean eliminado = dao.EliminarCliente(id);

            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.");
                btnListarClientesActionPerformed(null); // Llama al método como si se hubiera hecho clic
                limpiarCamposGuardarCliente(); // Limpia los campos
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed
        // Botón para editar cliente
        String idTexto = txtIDCliente.getText().trim();
        String dni = txtDniCliente.getText().trim();
        String nombre = txtNombreCliente.getText().trim();
        String telefono = txtTelefonoCliente.getText().trim();
        String direccion = txtDireccionCliente.getText().trim();
        String razonSocial = txtRazonSocialCliente.getText().trim();

        // Validaciones básicas
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un cliente para editar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (dni.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || razonSocial.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (dni.length() > 9) {
            JOptionPane.showMessageDialog(this, "El DNI/NIF no puede tener más de 9 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (telefono.length() > 20) {
            JOptionPane.showMessageDialog(this, "El teléfono no puede tener más de 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear objeto Cliente con ID incluido
        Cliente cl = new Cliente();
        cl.setId(Integer.parseInt(idTexto));
        cl.setDni(dni);
        cl.setNombre(nombre);
        cl.setTelefono(telefono);
        cl.setDireccion(direccion);
        cl.setRazonSocial(razonSocial);

        ClienteDao dao = new ClienteDao();
        boolean actualizado = dao.ModificarCliente(cl);

        if (actualizado) {
            JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente.");
            limpiarCamposGuardarCliente();
            btnListarClientesActionPerformed(null); // Refrescar tabla
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void btnGuardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProveedorActionPerformed
        //Obtenemos el ID del proveedor para verificar si ya existe
        String idTexto = txtIDProveedor.getText().trim();
        if (!idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Este proveedor ya existe. Si deseas modificarlo, usa el botón 'Editar'.",
                    "Proveedor existente", JOptionPane.WARNING_MESSAGE);
            return;
        }


        //Boton para guardar proveedor
        String dni = txtDNIProveedor.getText().trim();
        String nombre = txtNombreProveedor.getText().trim();
        String telefono = txtTelefonoProveedor.getText().trim();
        String direccion = txtDireccionProveedor.getText().trim();
        String razonSocial = txtRazonSProveedor.getText().trim();

        if (dni.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || razonSocial.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (dni.length() > 9) {
            JOptionPane.showMessageDialog(this, "El DNI/NIF no puede tener más de 9 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (telefono.length() > 20) {
            JOptionPane.showMessageDialog(this, "El teléfono no puede tener más de 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }



        // Crear objeto Proveedor
        Proveedor p = new Proveedor();
        p.setDni(dni);
        p.setNombre(nombre);
        p.setTelefono(telefono);
        p.setDireccion(direccion);
        p.setRazonSocial(razonSocial);

        ProveedorDao dao = new ProveedorDao();
        boolean registrado = dao.RegistrarProveedor(p);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "Proveedor registrado correctamente.");
            limpiarCamposProveedor();
            btnListarProveedoresActionPerformed(null); // Llama al listar para refrescar la tabla
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnGuardarProveedorActionPerformed

    private void btnListarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarProveedoresActionPerformed
        // Buscar la pestaña por su título de forma segura
        int index = jTabbedPane1.indexOfTab("Proveedores");
        if (index != -1) {
            jTabbedPane1.setSelectedIndex(index);
        } else {
            JOptionPane.showMessageDialog(this, "La pestaña 'Proveedores' no está disponible.");
            return;
        }

        // Obtener lista de proveedores y llenar la tabla
        ProveedorDao proDao = new ProveedorDao();
        List<Proveedor> lista = proDao.ListarProveedores();

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("DNI/NIF");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("TELÉFONO");
        modelo.addColumn("DIRECCIÓN");
        modelo.addColumn("RAZÓN SOCIAL");

        for (Proveedor p : lista) {
            Object[] fila = new Object[6];
            fila[0] = p.getId();
            fila[1] = p.getDni();
            fila[2] = p.getNombre();
            fila[3] = p.getTelefono();
            fila[4] = p.getDireccion();
            fila[5] = p.getRazonSocial();
            modelo.addRow(fila);
        }

        tableProveedor.setModel(modelo); // Cargar datos en la tabla
    }//GEN-LAST:event_btnListarProveedoresActionPerformed

    private void btnEditarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProveedorActionPerformed
        // Botón para editar proveedor seleccionado
        String idTexto = txtIDProveedor.getText();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un proveedor para editar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String dni = txtDNIProveedor.getText().trim();
        String nombre = txtNombreProveedor.getText().trim();
        String telefono = txtTelefonoProveedor.getText().trim();
        String direccion = txtDireccionProveedor.getText().trim();
        String razonSocial = txtRazonSProveedor.getText().trim();

        if (dni.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || razonSocial.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (dni.length() > 9) {
            JOptionPane.showMessageDialog(this, "El DNI/NIF no puede tener más de 9 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (telefono.length() > 20) {
            JOptionPane.showMessageDialog(this, "El teléfono no puede tener más de 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Proveedor p = new Proveedor();
        p.setId(Integer.parseInt(idTexto));
        p.setDni(dni);
        p.setNombre(nombre);
        p.setTelefono(telefono);
        p.setDireccion(direccion);
        p.setRazonSocial(razonSocial);

        ProveedorDao dao = new ProveedorDao();
        boolean modificado = dao.ModificarProveedor(p);

        if (modificado) {
            JOptionPane.showMessageDialog(this, "Proveedor actualizado correctamente.");
            limpiarCamposProveedor();
            btnListarProveedoresActionPerformed(null); // Actualiza tabla
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarProveedorActionPerformed

    private void btnEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedorActionPerformed
        String idTexto = txtIDProveedor.getText();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un proveedor para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Deseas eliminar este proveedor?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(idTexto);

            ProveedorDao dao = new ProveedorDao();
            boolean eliminado = dao.EliminarProveedor(id);

            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Proveedor eliminado correctamente.");
                limpiarCamposProveedor();
                btnListarProveedoresActionPerformed(null); // Refresca tabla
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarProveedorActionPerformed

    private void tableProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProveedorMouseClicked
        // Método para seleccionar un proveedor de la tabla
        int fila = tableProveedor.getSelectedRow();
        if (fila != -1) {
            txtIDProveedor.setText(tableProveedor.getValueAt(fila, 0).toString()); // Columna 0 = ID
            txtDNIProveedor.setText(tableProveedor.getValueAt(fila, 1).toString());
            txtNombreProveedor.setText(tableProveedor.getValueAt(fila, 2).toString());
            txtTelefonoProveedor.setText(tableProveedor.getValueAt(fila, 3).toString());
            txtDireccionProveedor.setText(tableProveedor.getValueAt(fila, 4).toString());
            txtRazonSProveedor.setText(tableProveedor.getValueAt(fila, 5).toString());
            // Deshabilita el botón Guardar para evitar duplicados
            btnGuardarProveedor.setEnabled(false);
        }
    }//GEN-LAST:event_tableProveedorMouseClicked

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
        limpiarCamposGuardarCliente();  // Limpia los campos
        btnGuardarCliente.setEnabled(true);// Habilita el botón Guardar
    }//GEN-LAST:event_btnNuevoClienteActionPerformed

    private void btnNuevoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProveedorActionPerformed
        limpiarCamposProveedor();  // Limpia los campos
        btnGuardarProveedor.setEnabled(true);// Habilita el botón Guardar
    }//GEN-LAST:event_btnNuevoProveedorActionPerformed

    private void btnListarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarProductosActionPerformed
        // Cambiar a la pestaña "Productos" de forma segura
        int index = jTabbedPane1.indexOfTab("Productos");
        if (index != -1) {
            jTabbedPane1.setSelectedIndex(index);
        } else {
            JOptionPane.showMessageDialog(this, "La pestaña 'Productos' no está disponible.");
            return;
        }

        // Llenar la tabla de productos
        ProductoDao dao = new ProductoDao();
        List<Producto> lista = dao.ListarProductos();

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("CÓDIGO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("DESCRIPCIÓN");
        modelo.addColumn("PROVEEDOR");
        modelo.addColumn("STOCK");
        modelo.addColumn("PRECIO");

        for (Producto p : lista) {
            Object[] fila = new Object[7];
            fila[0] = p.getId();
            fila[1] = p.getCodigo();
            fila[2] = p.getNombre();
            fila[3] = p.getDescripcion();
            fila[4] = p.getProveedorId(); // Si tienes el nombre, mejor mostrarlo
            fila[5] = p.getStock();
            fila[6] = p.getPrecio();
            modelo.addRow(fila);
        }

        tableProducto.setModel(modelo); // Mostrar datos
    }//GEN-LAST:event_btnListarProductosActionPerformed

    private void btnGuardarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProductoActionPerformed
        // TODO add your handling code here:
        String idTexto = txtIDProducto.getText().trim();
        if (!idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Este producto ya existe. Usa el botón 'Editar' para modificarlo.",
                    "Producto existente", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String codigo = txtCodigoProducto.getText().trim();
        String nombre = txtNombreProducto.getText().trim(); // nombre correcto
        String descripcion = txtDescripcionProducto.getText().trim(); // ahora es la descripción
        String cantidadTexto = txtCantidadProducto.getText().trim();
        String precioTexto = txtPrecioProducto.getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || cantidadTexto.isEmpty() || precioTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int stock;
        double precio;
        try {
            stock = Integer.parseInt(cantidadTexto);
            precio = Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero y el precio un número decimal.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombreProveedor = cbxProveedorProducto.getSelectedItem().toString();
        int idProveedor = proveedorDao.obtenerIdProveedorPorNombre(nombreProveedor);



        Producto p = new Producto();
        p.setCodigo(codigo);
        p.setNombre(nombre);
        p.setDescripcion(descripcion); // <-- nuevo
        p.setStock(stock);
        p.setPrecio(precio);
        p.setProveedorId(idProveedor);

        ProductoDao dao = new ProductoDao();
        boolean registrado = dao.RegistrarProducto(p);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "Producto registrado correctamente.");
            btnListarProductosActionPerformed(null); // Actualiza tabla
            limpiarCamposProducto();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarProductoActionPerformed

    private void btnEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProductoActionPerformed
        // TODO add your handling code here:
        String idTexto = txtIDProducto.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para editar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String codigo = txtCodigoProducto.getText().trim();
        String nombre = txtNombreProducto.getText().trim();
        String descripcion = txtDescripcionProducto.getText().trim();
        String stockStr = txtCantidadProducto.getText().trim();
        String precioStr = txtPrecioProducto.getText().trim();
        int proveedorId = cbxProveedorProducto.getSelectedIndex() + 1;

        if (codigo.isEmpty() || nombre.isEmpty() || stockStr.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            int stock = Integer.parseInt(stockStr);
            double precio = Double.parseDouble(precioStr);

            Producto p = new Producto();
            p.setId(id);
            p.setCodigo(codigo);
            p.setNombre(nombre);
            p.setDescripcion(descripcion);
            p.setProveedorId(proveedorId);
            p.setStock(stock);
            p.setPrecio(precio);

            ProductoDao dao = new ProductoDao();
            if (dao.ModificarProducto(p)) {
                JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.");
                btnListarProductosActionPerformed(null);
                limpiarCamposProducto();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Stock y precio deben ser numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarProductoActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        // TODO add your handling code here:
        String idTexto = txtIDProducto.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Deseas eliminar este producto?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(idTexto);
            ProductoDao dao = new ProductoDao();
            if (dao.EliminarProducto(id)) {
                JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.");
                btnListarProductosActionPerformed(null);
                limpiarCamposProducto();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProductoActionPerformed
        // TODO add your handling code here:
        limpiarCamposProducto();
        btnGuardarProducto.setEnabled(true);// Habilita el botón Guardar
    }//GEN-LAST:event_btnNuevoProductoActionPerformed


    private void tableProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductoMouseClicked
        // TODO add your handling code here:
        int fila = tableProducto.getSelectedRow();
        if (fila != -1) {
            txtIDProducto.setText(tableProducto.getValueAt(fila, 0).toString());
            txtCodigoProducto.setText(tableProducto.getValueAt(fila, 1).toString());
            txtNombreProducto.setText(tableProducto.getValueAt(fila, 2).toString());       // nombre
            txtDescripcionProducto.setText(tableProducto.getValueAt(fila, 3).toString());  // descripción
            cbxProveedorProducto.setSelectedIndex(Integer.parseInt(tableProducto.getValueAt(fila, 4).toString()) - 1);
            txtCantidadProducto.setText(tableProducto.getValueAt(fila, 5).toString());
            txtPrecioProducto.setText(tableProducto.getValueAt(fila, 6).toString());
            btnGuardarProducto.setEnabled(false); //  Desactiva botón Guardar si se seleccionó una fila
        }
    }//GEN-LAST:event_tableProductoMouseClicked

    private void btnExcelProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelProductoActionPerformed
        //Boton para crear el reporte de excel
        Excel.reporte();
    }//GEN-LAST:event_btnExcelProductoActionPerformed

    private void txtCodigoVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVentaKeyPressed
        // Boton para buscar el producto por código
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = txtCodigoVenta.getText().trim();

            if (!codigo.isEmpty()) {
                // Aquí haces la consulta a tu base de datos o tu clase DAO
                Producto producto = productoDao.buscarPorCodigo(codigo); // crea este método en tu DAO

                if (producto != null) {
                    txtNombreVenta.setText(producto.getDescripcion());
                    txtCantidadVenta.setText("1"); // valor por defecto
                    txtPrecioVenta.setText(String.valueOf(producto.getPrecio()));
                    txtStockVenta.setText(String.valueOf(producto.getStock()));
                    txtCantidadVenta.requestFocus(); // mover foco
                } else {
                    JOptionPane.showMessageDialog(this, "Producto no encontrado.");
                    txtNombreVenta.setText("");
                    txtPrecioVenta.setText("");
                    txtStockVenta.setText("");
                }
            }
        }
    }//GEN-LAST:event_txtCodigoVentaKeyPressed

    private void txtCantidadVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVentaKeyPressed
        // Comprobacion que la cantidado no puede ser mayor que el stock que tenemos

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = txtCodigoVenta.getText().trim();
            String cantidadTexto = txtCantidadVenta.getText().trim();

            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingresa un código de producto.");
                return;
            }

            if (cantidadTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingresa una cantidad.");
                return;
            }

            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                int cantidad = Integer.parseInt(cantidadTexto);

                con = cn.getConnection();
                String sql = "SELECT p.codigo, p.nombre, p.descripcion, p.precio, p.stock FROM productos p WHERE p.codigo = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, codigo);
                rs = ps.executeQuery();

                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");
                    double precio = rs.getDouble("precio");
                    int stock = rs.getInt("stock");

                    if (stock <= 0) {
                        JOptionPane.showMessageDialog(this, "Producto sin stock.");
                        return;
                    }

                    if (cantidad > stock) {
                        JOptionPane.showMessageDialog(this, "La cantidad no puede ser mayor que el stock disponible (" + stock + ").");
                        return;
                    }

                    double total = cantidad * precio;

                    // Agregar a la tabla
                    DefaultTableModel modelo = (DefaultTableModel) tableVenta.getModel();
                    modelo.addRow(new Object[]{
                            codigo,
                            nombre,
                            descripcion,
                            cantidad,
                            precio,
                            total
                    });
                    // Calcular total general
                    double totalVenta = 0.0;
                    for (int i = 0; i < tableVenta.getRowCount(); i++) {
                        Object valor = tableVenta.getValueAt(i, 5); // columna TOTAL
                        if (valor != null) {
                            totalVenta += Double.parseDouble(valor.toString());
                        }
                    }
                    labelTotalVenta.setText(String.format("%.2f", totalVenta)+"€");

                    // Limpiar campos
                    txtCodigoVenta.setText("");
                    txtNombreVenta.setText("");
                    txtPrecioVenta.setText("");
                    txtStockVenta.setText("");
                    txtCantidadVenta.setText("");
                    txtCodigoVenta.requestFocus();

                } else {
                    JOptionPane.showMessageDialog(this, "Producto no encontrado.");
                }

                con.close();

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al buscar producto: " + e.getMessage());
            } finally {
                try {
                    if (rs != null) rs.close();
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar ResultSet: " + ex.getMessage());
                }
                try {
                    if (ps != null) ps.close();
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar PreparedStatement: " + ex.getMessage());
                }
                try {
                    if (con != null) con.close();
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar Connection: " + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_txtCantidadVentaKeyPressed

    private void btnEliminarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarVentaActionPerformed
        // Boton para eliminar una fila de la tabla de ventas
        int filaSeleccionada = tableVenta.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una fila para eliminar.");
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) tableVenta.getModel();
        modelo.removeRow(filaSeleccionada);

        // Recalcular el total
        double totalVenta = 0.0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object valor = modelo.getValueAt(i, 5); // columna TOTAL
            if (valor != null) {
                totalVenta += Double.parseDouble(valor.toString());
            }
        }

        labelTotalVenta.setText(String.format("%.2f", totalVenta)+"€");

    }//GEN-LAST:event_btnEliminarVentaActionPerformed

    private void txtDniClienteVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniClienteVentaKeyPressed
        // Cuando se presiona Enter en el campo de DNI
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String dni = txtDniClienteVenta.getText().trim();

            if (dni.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un DNI.");
                return;
            }
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                con = cn.getConnection();
                String sql = "SELECT * FROM clientes WHERE dni = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, dni);
                rs = ps.executeQuery();

                if (rs.next()) {
                    // Mostrar los datos en los campos correspondientes
                    txtIDClienteVenta.setText(String.valueOf(rs.getInt("id")));
                    txtNombreClienteVenta.setText(rs.getString("nombre"));
                    txtTelefonoClienteVenta.setText(rs.getString("telefono"));
                    txtDireccionClienteVenta.setText(rs.getString("direccion"));
                    txtRazonClienteVenta.setText(rs.getString("razon_social"));
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
                    // Limpiar campos si no existe
                    txtIDClienteVenta.setText("");
                    txtNombreClienteVenta.setText("");
                    txtTelefonoClienteVenta.setText("");
                    txtDireccionClienteVenta.setText("");
                    txtRazonClienteVenta.setText("");
                }
                txtDniClienteVenta.requestFocus();// Mueve el foco al campo de dni

                con.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al buscar cliente: " + e.getMessage());
            } finally {
                try {
                    if (rs != null) rs.close();
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar ResultSet: " + ex.getMessage());
                }
                try {
                    if (ps != null) ps.close();
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar PreparedStatement: " + ex.getMessage());
                }
                try {
                    if (con != null) con.close();
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar Connection: " + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_txtDniClienteVentaKeyPressed

    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVentaActionPerformed
        //Generar, Registrar venta actual
        // Obtener datos de los campos
        String idClienteStr = txtIDClienteVenta.getText().trim();
        String totalStr = labelTotalVenta.getText().trim().replace("€", "").replace(",", ".");

        // Capturar datos del cliente antes de limpiar
        String dniCliente = txtDniClienteVenta.getText().trim();
        String nombreCliente = txtNombreClienteVenta.getText().trim();
        String telefonoCliente = txtTelefonoClienteVenta.getText().trim();
        String direccionCliente = txtDireccionClienteVenta.getText().trim();

        // Validaciones básicas
        if (idClienteStr.isEmpty() || usuarioNombre.isEmpty() || totalStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los datos del cliente y total de venta.");
            return;
        }

        try {
            int idCliente = Integer.parseInt(idClienteStr);
            double total = Double.parseDouble(totalStr);

            // Crear objeto Venta
            Venta venta = new Venta();
            venta.setIdCliente(idCliente);
            venta.setVendedor(usuarioNombre);
            venta.setTotal(total);

            // Registrar venta en la base de datos
            VentaDao ventaDao = new VentaDao();
            int idVenta = ventaDao.registrarVenta(venta);

            if (idVenta > 0) {
                JOptionPane.showMessageDialog(this, "Venta registrada con éxito. ID: " + idVenta);

                // Recorrer la tabla de ventas y registrar los detalles
                DefaultTableModel modelo = (DefaultTableModel) tableVenta.getModel();
                ProductoDao productoDao = new ProductoDao(); // Crear instancia de ProductoDao

                for (int i = 0; i < modelo.getRowCount(); i++) {
                    String codigo = modelo.getValueAt(i, 0).toString();  // Código del producto
                    int cantidad = Integer.parseInt(modelo.getValueAt(i, 3).toString());  // Cantidad
                    double precio = Double.parseDouble(modelo.getValueAt(i, 4).toString());  // Precio

                    // Obtener el ID del producto a partir del código
                    int idProducto = productoDao.obtenerIdProductoPorCodigo(codigo);

                    if (idProducto != -1) {
                        // Crear objeto Detalle y registrar en la base de datos
                        Detalle detalle = new Detalle();
                        detalle.setIdProducto(idProducto);
                        detalle.setCantidad(cantidad);
                        detalle.setPrecio(precio);
                        detalle.setIdVenta(idVenta);
                    // Llamada al método actualizarStock después de registrar el detalle de la venta
                        // Registrar el detalle de la venta
                        DetalleDao detalleDao = new DetalleDao();
                        boolean registrado = detalleDao.registrarDetalle(detalle);

                        if (!registrado) {
                            JOptionPane.showMessageDialog(this, "Error al registrar el detalle del producto con código: " + codigo);
                            return;
                        }

                        // Actualizar stock del producto
                        boolean stockActualizado = productoDao.actualizarStock(idProducto, cantidad);
                        if (!stockActualizado) {
                            JOptionPane.showMessageDialog(this, "Error al actualizar el stock del producto con código: " + codigo);
                            return;
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Producto con código " + codigo + " no encontrado.");
                        return;
                    }
                }

                // Generar PDF de la factura
                PDF pdf = new PDF();
                pdf.generarFacturaPdf(
                        idVenta, tableVenta,
                        txtDNIEmpresa, txtNombreEmpresa, txtTelefonoEmpresa,
                        txtDireccionEmpresa, txtRazonEmpresa,
                        dniCliente, nombreCliente, telefonoCliente,
                        direccionCliente,
                        totalStr
                );


                // Limpiar campos y tabla
                modelo.setRowCount(0);
                txtCodigoVenta.setText("");
                txtNombreVenta.setText("");
                txtPrecioVenta.setText("");
                txtStockVenta.setText("");
                txtCantidadVenta.setText("");
                txtIDClienteVenta.setText("");
//                txtVendedorACliente.setText("");
                txtNombreClienteVenta.setText("");
                txtDniClienteVenta.setText("");
                txtTelefonoClienteVenta.setText("");
                txtDireccionClienteVenta.setText("");
                txtRazonClienteVenta.setText("");
                labelTotalVenta.setText("0.00€");

            } else {
                JOptionPane.showMessageDialog(this, "No se pudo registrar la venta.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Formato de número inválido.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error inesperado al procesar la venta.");
        }
    }//GEN-LAST:event_btnGenerarVentaActionPerformed

    private void btnNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaVentaActionPerformed
        //jTabbedPane1.setSelectedIndex(0); // Cambia al panel de Ventas
        int index = jTabbedPane1.indexOfTab("Nueva Venta");
        if (index != -1) {
            jTabbedPane1.setSelectedIndex(index);
        } else {
            JOptionPane.showMessageDialog(this, "La pestaña 'Nueva Venta' no está disponible.");
        }
    }//GEN-LAST:event_btnNuevaVentaActionPerformed

    private void btnActualizarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarEmpresaActionPerformed
        // Botón para actualizar los datos de la empresa

        try {
            // Obtener datos desde los campos de texto
            int id = Integer.parseInt(txtIDEmpresa.getText().trim());
            String dni = txtDNIEmpresa.getText().trim();
            String nombre = txtNombreEmpresa.getText().trim();
            String telefono = txtTelefonoEmpresa.getText().trim();
            String razonSocial = txtRazonEmpresa.getText().trim();
            String direccion = txtDireccionEmpresa.getText().trim();

            // Validar que no estén vacíos (puedes agregar más validaciones si quieres)
            if (dni.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || razonSocial.isEmpty() || direccion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.");
                return;
            }

            // Crear objeto Empresa
            Empresa empresa = new Empresa(id, dni, nombre, telefono, razonSocial, direccion);

            // Actualizar en base de datos
            EmpresaDao dao = new EmpresaDao();
            boolean actualizado = dao.actualizarEmpresa(empresa);

            if (actualizado) {
                JOptionPane.showMessageDialog(this, "Empresa actualizada correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar la empresa.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error inesperado al actualizar la empresa.");
        }
    }//GEN-LAST:event_btnActualizarEmpresaActionPerformed

    //Boton para controlar eventos
    private void configurarEventos() {

        //Controla que cuando se haga click en el panel de ventas, se llame al método listar ventas
        jTabbedPane1.addChangeListener(evt -> {
            String titulo = jTabbedPane1.getTitleAt(jTabbedPane1.getSelectedIndex());

            switch (titulo) {
                case "Ventas":
                    btnListarVentasActionPerformed(null);
                    break;
                case "Clientes":
                    btnListarClientesActionPerformed(null);
                    break;
                case "Proveedores":
                    btnListarProveedoresActionPerformed(null);
                    break;
                case "Productos":
                    btnListarProductosActionPerformed(null);
                    break;
                case "Usuarios":
                    btnListarUsuariosActionPerformed(null);
                    break;
                // Puedes añadir más pestañas si quieres...
            }
        });
    }






    // Botón para listar ventas en la tabla y abrir el panel correspondiente
    private void btnListarVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarVentasActionPerformed
        // Buscar el índice de la pestaña "Ventas" por su título
        int indexVentas = jTabbedPane1.indexOfTab("Ventas");

        if (indexVentas != -1) {
            jTabbedPane1.setSelectedIndex(indexVentas);

            // Obtener el modelo de la tabla y limpiarlo
            DefaultTableModel modelo = (DefaultTableModel) tableVentas.getModel();
            modelo.setRowCount(0);

            // Obtener lista de ventas desde la base de datos
            VentaDao dao = new VentaDao();
            List<Venta> lista = dao.listarVentas();

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Insertar los datos en la tabla
            for (Venta v : lista) {
                Object[] fila = new Object[]{
                        v.getId(),
                        v.getIdCliente(),
                        v.getVendedor(),
                        v.getFecha().format(formato),
                        String.format("%.2f €", v.getTotal())
                };
                modelo.addRow(fila);
            }
        } else {
            // La pestaña no está disponible (posiblemente oculta por el rol del usuario)
            JOptionPane.showMessageDialog(this, "La pestaña 'Ventas' no está disponible para este usuario.");
        }
    }//GEN-LAST:event_btnListarVentasActionPerformed

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
        // Botón para abrir la configuración de la empresa
        //jTabbedPane1.setSelectedIndex(5);
        int index = jTabbedPane1.indexOfTab("Configuración");
        if (index != -1) {
            jTabbedPane1.setSelectedIndex(index);
        } else {
            JOptionPane.showMessageDialog(this, "La pestaña 'Configuración' no está disponible.");
        }
    }//GEN-LAST:event_btnConfiguracionActionPerformed

    private void btnPDFVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFVentasActionPerformed
        // TODO add your handling code here:
        try {
            int idVenta = Integer.parseInt(txtIDVentas.getText().trim());
            File carpeta = new File("src/Ventas");

            if (!carpeta.exists()) {
                JOptionPane.showMessageDialog(this, "La carpeta de PDF no existe.");
                return;
            }

            // Buscar archivo que empiece por "venta<ID>_"
            File[] archivos = carpeta.listFiles((dir, name) -> name.startsWith("venta" + idVenta + "_") && name.endsWith(".pdf"));

            if (archivos != null && archivos.length > 0) {
                // Asumir el más reciente por nombre (si hay más de uno)
                File archivoPDF = archivos[0];
                Desktop.getDesktop().open(archivoPDF);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el PDF para la venta con ID " + idVenta);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID de venta inválido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al abrir el PDF: " + e.getMessage());
        }

    }//GEN-LAST:event_btnPDFVentasActionPerformed

    private void tableVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableVentasMouseClicked
        //Al hacer click en una fila de la tabla de ventas, se selecciona el ID

        int fila = tableVentas.getSelectedRow();
        if (fila >= 0) {
            String id = tableVentas.getValueAt(fila, 0).toString(); // Columna 0 es ID
            txtIDVentas.setText(id);
        }
    }//GEN-LAST:event_tableVentasMouseClicked

    private void btnGraficalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficalActionPerformed
        //Boton para geberar grafico con la clase Grafico
        // Botón para generar gráfico con la clase Grafico
        Date fechaSeleccionada = miDateJCalendar.getDate();

        if (fechaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaStr = formato.format(fechaSeleccionada);

        Grafico grafico = new Grafico();
        boolean generado = grafico.generarGrafico(fechaStr);

        if (generado) {
            grafico.mostrarGrafico();
            System.out.println("Botón graficar presionado");
        } else {
            JOptionPane.showMessageDialog(this, "No hay ventas registradas en esa fecha", "Sin datos", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnGraficalActionPerformed

    private void btnListarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarUsuariosActionPerformed
        // Ir a la pestaña "Usuarios" de forma segura
        int index = jTabbedPane1.indexOfTab("Usuarios");
        if (index != -1) {
            jTabbedPane1.setSelectedIndex(index);
        } else {
            JOptionPane.showMessageDialog(this, "La pestaña 'Usuarios' no está disponible.");
            return;
        }

        // Cargar los datos de los usuarios
        UsuarioDao dao = new UsuarioDao();
        List<Usuario> lista = dao.ListarUsuarios();

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("CORREO");
        modelo.addColumn("PASSWORD");
        modelo.addColumn("ROL");

        for (Usuario u : lista) {
            modelo.addRow(new Object[]{
                    u.getId(), u.getNombre(), u.getCorreo(), u.getPass(), u.getRol()
            });
        }

        tableUsuarios.setModel(modelo); // Mostrar los datos en la tabla

    }//GEN-LAST:event_btnListarUsuariosActionPerformed

    private void btnEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUsuarioActionPerformed
        // TODO add your handling code here:

        String idTexto = txtIDUsuario.getText();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Deseas eliminar este usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(idTexto);
            UsuarioDao dao = new UsuarioDao();
            boolean eliminado = dao.EliminarUsuario(id);

            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
                limpiarCamposUsuarios();
                btnListarUsuariosActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarUsuarioActionPerformed

    private void btnEditarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarUsuarioActionPerformed
        // TODO add your handling code here:

        String idTexto = txtIDUsuario.getText().trim();
        String nombre = txtNombreUsuario.getText().trim();
        String correo = txtCorreoUsuario.getText().trim();
        String pass = txtContrasenaUsuario.getText().trim();
        String rol = cbxRolUsuario.getSelectedItem().toString();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario para editar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (nombre.isEmpty() || correo.isEmpty() || pass.isEmpty() || rol.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario u = new Usuario();
        u.setId(Integer.parseInt(idTexto));
        u.setNombre(nombre);
        u.setCorreo(correo);
        u.setPass(pass);
        u.setRol(rol);

        UsuarioDao dao = new UsuarioDao();
        boolean actualizado = dao.ModificarUsuario(u);

        if (actualizado) {
            JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.");
            limpiarCamposUsuarios();
            btnListarUsuariosActionPerformed(null);
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarUsuarioActionPerformed

    private void tableUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableUsuariosMouseClicked
        // TODO add your handling code here:
        int fila = tableUsuarios.getSelectedRow();
        if (fila != -1) {
            txtIDUsuario.setText(tableUsuarios.getValueAt(fila, 0).toString());
            txtNombreUsuario.setText(tableUsuarios.getValueAt(fila, 1).toString());
            txtCorreoUsuario.setText(tableUsuarios.getValueAt(fila, 2).toString());
            txtContrasenaUsuario.setText(tableUsuarios.getValueAt(fila, 3).toString());
            cbxRolUsuario.setSelectedItem(tableUsuarios.getValueAt(fila, 4).toString());

            btnGuardarUsuario.setEnabled(false); // Evita duplicados
        }


    }//GEN-LAST:event_tableUsuariosMouseClicked

    private void btnGuardarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarUsuarioActionPerformed
        // TODO add your handling code here:
        String nombre = txtNombreUsuario.getText().trim();
        String correo = txtCorreoUsuario.getText().trim();
        String pass = txtContrasenaUsuario.getText().trim(); //
        String rol = cbxRolUsuario.getSelectedItem().toString();

        if (nombre.isEmpty() || correo.isEmpty() || pass.isEmpty() || rol.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setCorreo(correo);
        u.setPass(pass);
        u.setRol(rol);

        UsuarioDao dao = new UsuarioDao();
        boolean registrado = dao.RegistrarUsuario(u);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "Usuario registrado correctamente.");
            limpiarCamposUsuarios();
            btnListarUsuariosActionPerformed(null);
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnGuardarUsuarioActionPerformed

    private void jMenuItemCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCerrarSesionActionPerformed
        // Boton para cerrar sesión
        // Cerrar la ventana actual (Sistema)
        this.dispose();

        // Volver a mostrar la ventana de Login
        Login login = new Login();
        login.setVisible(true);
        login.setLocationRelativeTo(null); // Centrar la ventana

    }//GEN-LAST:event_jMenuItemCerrarSesionActionPerformed

    private void jMenuItemPrimerosPasosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrimerosPasosActionPerformed
        // Botón para abrir la ventana "Primeros Pasos"
        if (ventanaCreditos != null && ventanaCreditos.isDisplayable()) {
            ventanaCreditos.dispose();
        }
        if (ventanaManual != null && ventanaManual.isDisplayable()) {
            ventanaManual.dispose();
        }

        if (ventanaAnalisis != null && ventanaAnalisis.isDisplayable()) {
            ventanaAnalisis.dispose();
        }

        // Abrir ventana Primeros Pasos si no está abierta
        if (ventanaPasos == null || !ventanaPasos.isDisplayable()) {
            ventanaPasos = new VentanaPrimerosPasos();
            ventanaPasos.setVisible(true);
            ventanaPasos.setLocationRelativeTo(this);
            ventanaPasos.toFront();
        } else {
            ventanaPasos.toFront();
        }
    }//GEN-LAST:event_jMenuItemPrimerosPasosActionPerformed

    private void jMenuItemManualUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemManualUsuarioActionPerformed
        // Boton para abrir la ventana manual de usuario
        // Crear e iniciar la ventana del manual
        // Cerrar otras ventanas si están abiertas
        if (ventanaCreditos != null && ventanaCreditos.isDisplayable()) {
            ventanaCreditos.dispose();
        }
        if (ventanaPasos != null && ventanaPasos.isDisplayable()) {
            ventanaPasos.dispose();
        }
        if (ventanaAnalisis != null && ventanaAnalisis.isDisplayable()) {
            ventanaAnalisis.dispose();
        }
        // Abrir ventana Manual de Usuario si no está abierta
        if (ventanaManual == null || !ventanaManual.isDisplayable()) {
            ventanaManual = new VentanaManualUsuario();
            ventanaManual.setVisible(true);
            ventanaManual.setLocationRelativeTo(this);
            ventanaManual.toFront();
        } else {
            ventanaManual.toFront();
        }
        // manual.setAlwaysOnTop(true); // opcional, si quieres forzar que quede siempre encima
    }//GEN-LAST:event_jMenuItemManualUsuarioActionPerformed

    private void jMenuItemCreditosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCreditosActionPerformed
        // Botón para abrir la ventana "Créditos"
        // Cerrar otras ventanas si están abiertas
        if (ventanaManual != null && ventanaManual.isDisplayable()) {
            ventanaManual.dispose();
        }
        if (ventanaPasos != null && ventanaPasos.isDisplayable()) {
            ventanaPasos.dispose();
        }
        if (ventanaAnalisis != null && ventanaAnalisis.isDisplayable()) {
            ventanaAnalisis.dispose();
        }
        // Abrir la ventana de créditos (solo si no está abierta)
        if (ventanaCreditos == null || !ventanaCreditos.isDisplayable()) {
            ventanaCreditos = new VentanaCreditos();
            ventanaCreditos.setVisible(true);
            //ventanaCreditos.setLocationRelativeTo(this);
            //ventanaCreditos.toFront();
        } else {
            //ventanaCreditos.toFront();
        }
    }//GEN-LAST:event_jMenuItemCreditosActionPerformed

    private void jMenuItemAnalisisInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAnalisisInicialActionPerformed
        // TODO add your handling code here:
        // Cerrar otras ventanas abiertas del menú Ayuda
        if (ventanaManual != null && ventanaManual.isDisplayable()) {
            ventanaManual.dispose();
        }
        if (ventanaPasos != null && ventanaPasos.isDisplayable()) {
            ventanaPasos.dispose();
        }
        if (ventanaCreditos != null && ventanaCreditos.isDisplayable()) {
            ventanaCreditos.dispose();
        }

        // Abrir la ventana de Análisis Inicial si no está abierta
        if (ventanaAnalisis == null || !ventanaAnalisis.isDisplayable()) {
            ventanaAnalisis = new VentanaAnalisisInicial();
            ventanaAnalisis.setVisible(true);
            ventanaAnalisis.setLocationRelativeTo(this);
            ventanaAnalisis.toFront();
        } else {
            ventanaAnalisis.toFront();
        }

    }//GEN-LAST:event_jMenuItemAnalisisInicialActionPerformed

    private void botonCerrarSesion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrarSesion1ActionPerformed
        // Cerrar la ventana actual (Sistema)
        this.dispose();

        // Abrir la ventana de Login
        Login login = new Login();
        login.setVisible(true);
        login.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }//GEN-LAST:event_botonCerrarSesion1ActionPerformed
    //Metodo para limpiar los campos de usuarios
    private void limpiarCamposUsuarios() {
        txtIDUsuario.setText("");
        txtNombreUsuario.setText("");
        txtCorreoUsuario.setText("");
        txtContrasenaUsuario.setText("");
        cbxRolUsuario.setSelectedIndex(0);
        btnGuardarUsuario.setEnabled(true);
    }

    //Metodo para limpiar los campos de productos
    private void limpiarCamposProducto() {
        txtIDProducto.setText("");
        txtCodigoProducto.setText("");
        txtNombreProducto.setText("");
        txtDescripcionProducto.setText("");
        txtCantidadProducto.setText("");
        txtPrecioProducto.setText("");
        cbxProveedorProducto.setSelectedIndex(0);
        txtCodigoProducto.requestFocus();
        btnGuardarProducto.setEnabled(true); // Habilita el botón Guardar
    }
    //Metodo para limpiar los campos de guardar proveedor
    private void limpiarCamposProveedor() {
        txtIDProveedor.setText("");
        txtDNIProveedor.setText("");
        txtNombreProveedor.setText("");
        txtTelefonoProveedor.setText("");
        txtDireccionProveedor.setText("");
        txtRazonSProveedor.setText("");

        // Opcional: poner el foco nuevamente en el primer campo
        txtDNIProveedor.requestFocus();
    }
    //Metodo para limpiar los campos de guardar cliente
    private void limpiarCamposGuardarCliente() {
        txtIDCliente.setText("");
        txtDniCliente.setText("");
        txtNombreCliente.setText("");
        txtTelefonoCliente.setText("");
        txtDireccionCliente.setText("");
        txtRazonSocialCliente.setText("");

        // Opcional: poner el foco nuevamente en el primer campo
        txtDniCliente.requestFocus();
    }

    //Metodo para crear privilegios a los usuarios
    private void aplicarPrivilegios() {
        if ("Vendedor".equalsIgnoreCase(usuarioRol)) {
            btnListarProveedores.setEnabled(false);
            btnListarProductos.setEnabled(false);
            btnConfiguracion.setEnabled(false); // opcional
            btnListarUsuarios.setEnabled(false);
            // También puedes ocultarlos: setVisible(false)

            // Le prohibimos las pestañas ya que no tiene privilegios:
            // Eliminar pestañas prohibidas directamente del jTabbedPane1
            jTabbedPane1.remove(panelProveedores);
            jTabbedPane1.remove(panelProductos);
            jTabbedPane1.remove(panelConfiguracion);
            jTabbedPane1.remove(panelUsuarios);
        }
    }







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
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sistema("Manolo Gafotas", "Vendedor").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private botoncerrarsesion.BotonCerrarSesion botonCerrarSesion1;
    private javax.swing.JButton btnActualizarEmpresa;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnEditarProducto;
    private javax.swing.JButton btnEditarProveedor;
    private javax.swing.JButton btnEditarUsuario;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnEliminarUsuario;
    private javax.swing.JButton btnEliminarVenta;
    private javax.swing.JButton btnExcelProducto;
    private javax.swing.JButton btnGenerarVenta;
    private javax.swing.JButton btnGrafical;
    private javax.swing.JButton btnGuardarCliente;
    private javax.swing.JButton btnGuardarProducto;
    private javax.swing.JButton btnGuardarProveedor;
    private javax.swing.JButton btnGuardarUsuario;
    private javax.swing.JButton btnListarClientes;
    private javax.swing.JButton btnListarProductos;
    private javax.swing.JButton btnListarProveedores;
    private javax.swing.JButton btnListarUsuarios;
    private javax.swing.JButton btnListarVentas;
    private javax.swing.JButton btnNuevaVenta;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JButton btnNuevoProducto;
    private javax.swing.JButton btnNuevoProveedor;
    private javax.swing.JButton btnPDFVentas;
    private javax.swing.JComboBox<String> cbxProveedorProducto;
    private javax.swing.JComboBox<String> cbxRolUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCabecera;
    private javax.swing.JLabel jLabelLogoT;
    private javax.swing.JLabel jLabelTituloNuevaVEnta;
    private javax.swing.JLabel jLabelTituloNuevaVEnta1;
    private javax.swing.JLabel jLabelTituloNuevaVEnta2;
    private javax.swing.JLabel jLabelTituloNuevaVEnta3;
    private javax.swing.JLabel jLabelTituloNuevaVEnta4;
    private javax.swing.JLabel jLabelTituloNuevaVEnta5;
    private javax.swing.JLabel jLabelTituloNuevaVEnta6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemAnalisisInicial;
    private javax.swing.JMenuItem jMenuItemCerrarSesion;
    private javax.swing.JMenuItem jMenuItemCreditos;
    private javax.swing.JMenuItem jMenuItemManualUsuario;
    private javax.swing.JMenuItem jMenuItemPrimerosPasos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelTotalVenta;
    private com.toedter.calendar.JDateChooser miDateJCalendar;
    private javax.swing.JPanel panelClientes;
    private javax.swing.JPanel panelConfiguracion;
    private javax.swing.JPanel panelNuevaVenta;
    private javax.swing.JPanel panelProductos;
    private javax.swing.JPanel panelProveedores;
    private javax.swing.JPanel panelUsuarios;
    private javax.swing.JPanel panelVentas;
    private relojpersonalizado.RelojPersonalizado relojPersonalizado1;
    private javax.swing.JTable tableCliente;
    private javax.swing.JTable tableProducto;
    private javax.swing.JTable tableProveedor;
    private javax.swing.JTable tableUsuarios;
    private javax.swing.JTable tableVenta;
    private javax.swing.JTable tableVentas;
    private javax.swing.JTextField txtCantidadProducto;
    private javax.swing.JTextField txtCantidadVenta;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextField txtCodigoVenta;
    private javax.swing.JTextField txtContrasenaUsuario;
    private javax.swing.JTextField txtCorreoUsuario;
    private javax.swing.JTextField txtDNIEmpresa;
    private javax.swing.JTextField txtDNIProveedor;
    private javax.swing.JTextField txtDescripcionProducto;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtDireccionClienteVenta;
    private javax.swing.JTextField txtDireccionEmpresa;
    private javax.swing.JTextField txtDireccionProveedor;
    private javax.swing.JTextField txtDniCliente;
    private javax.swing.JTextField txtDniClienteVenta;
    private javax.swing.JTextField txtIDCliente;
    private javax.swing.JTextField txtIDClienteVenta;
    private javax.swing.JTextField txtIDEmpresa;
    private javax.swing.JTextField txtIDProducto;
    private javax.swing.JTextField txtIDProveedor;
    private javax.swing.JTextField txtIDUsuario;
    private javax.swing.JTextField txtIDVentas;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreClienteVenta;
    private javax.swing.JTextField txtNombreEmpresa;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JTextField txtNombreVenta;
    private javax.swing.JTextField txtPrecioProducto;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtRazonClienteVenta;
    private javax.swing.JTextField txtRazonEmpresa;
    private javax.swing.JTextField txtRazonSProveedor;
    private javax.swing.JTextField txtRazonSocialCliente;
    private javax.swing.JTextField txtStockVenta;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtTelefonoClienteVenta;
    private javax.swing.JTextField txtTelefonoEmpresa;
    private javax.swing.JTextField txtTelefonoProveedor;
    private javax.swing.JLabel txtVendedorACliente;
    // End of variables declaration//GEN-END:variables
}
