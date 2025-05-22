package Modelo;

import Util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author LUIS
 */
public class VentaDao {
    private final Conexion cn = new Conexion();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;


    // Método para registrar una nueva venta
    public int registrarVenta(Venta venta) {
        // Asegúrate de que la tabla 'ventas' tenga las columnas adecuadas
        String sql = "INSERT INTO ventas (id_cliente, vendedor, total) VALUES (?, ?, ?)";
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, venta.getIdCliente());
            ps.setString(2, venta.getVendedor());
            ps.setDouble(3, venta.getTotal());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); // Devuelve el ID generado
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar venta: " + e.getMessage());
        }
        return -1; // Si falla
    }

    // Método para listar todas las ventas
    public List<Venta> listarVentas() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM ventas";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getInt("id"));
                venta.setIdCliente(rs.getInt("id_cliente"));
                venta.setVendedor(rs.getString("vendedor"));
                venta.setTotal(rs.getDouble("total"));
                venta.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                lista.add(venta);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar ventas: " + e);
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    private void cerrarRecursos() {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            System.out.println("Error cerrando ResultSet: " + e);
        }
        try {
            if (ps != null) ps.close();
        } catch (SQLException e) {
            System.out.println("Error cerrando PreparedStatement: " + e);
        }
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println("Error cerrando Connection: " + e);
        }
    }
}
