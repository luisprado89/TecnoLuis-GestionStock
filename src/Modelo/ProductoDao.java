package Modelo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Conexion cn = new Conexion();

    public boolean RegistrarProducto(Producto p) {
        String sql = "INSERT INTO productos (codigo, nombre, descripcion, proveedor_id, stock, precio) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getDescripcion());
            ps.setInt(4, p.getProveedorId());
            ps.setInt(5, p.getStock());
            ps.setDouble(6, p.getPrecio());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar producto: " + e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.toString());
            }
        }
    }

    public List<Producto> ListarProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setCodigo(rs.getString("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion")); // NUEVO
                p.setProveedorId(rs.getInt("proveedor_id"));
                p.setStock(rs.getInt("stock"));
                p.setPrecio(rs.getDouble("precio"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.toString());
            }
        }
        return lista;
    }

    public boolean EliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.toString());
            }
        }
    }

    public boolean ModificarProducto(Producto p) {
        String sql = "UPDATE productos SET codigo=?, nombre=?, descripcion=?, proveedor_id=?, stock=?, precio=? WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getDescripcion()); // NUEVO
            ps.setInt(4, p.getProveedorId());
            ps.setInt(5, p.getStock());
            ps.setDouble(6, p.getPrecio());
            ps.setInt(7, p.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al modificar producto: " + e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.toString());
            }
        }
    }

    public Producto buscarPorCodigo(String codigo) {
        Producto producto = null;
        String sql = "SELECT * FROM productos WHERE codigo = ?";

        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setCodigo(rs.getString("codigo"));
                producto.setDescripcion(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producto;
    }
    // Método para obtener el ID del producto por su código para insertar en detalle
    public int obtenerIdProductoPorCodigo(String codigo) {
        String sql = "SELECT id FROM productos WHERE codigo = ?";
        try (Connection con = new Conexion().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ID del producto: " + e.toString());
        }
        return -1;
    }
    // Método para actualizar el stock del producto después de una venta
    public boolean actualizarStock(int idProducto, int cantidadVendida) {
        String sql = "UPDATE productos SET stock = stock - ? WHERE id = ?";
        try (Connection con = new Conexion().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cantidadVendida);
            ps.setInt(2, idProducto);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
