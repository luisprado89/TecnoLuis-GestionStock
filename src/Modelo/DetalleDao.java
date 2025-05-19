package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DetalleDao {
    private Conexion cn = new Conexion();

    public boolean registrarDetalle(Detalle detalle) {
        String sql = "INSERT INTO detalle (id_producto, cantidad, precio, id_venta) VALUES (?, ?, ?, ?)";
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, detalle.getIdProducto());
            ps.setInt(2, detalle.getCantidad());
            ps.setDouble(3, detalle.getPrecio());
            ps.setInt(4, detalle.getIdVenta());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar detalle: " + e.toString());
            return false;
        }
    }
}
