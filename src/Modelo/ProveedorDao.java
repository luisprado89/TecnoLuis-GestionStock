package Modelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDao {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Conexion cn = new Conexion();

    public boolean RegistrarProveedor(Proveedor p) {
        String sql = "INSERT INTO proveedor (dni, nombre, telefono, direccion, razon_social) VALUES (?, ?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getDni());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getDireccion());
            ps.setString(5, p.getRazonSocial());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public List<Proveedor> ListarProveedores() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedor";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setId(rs.getInt("id"));
                p.setDni(rs.getString("dni"));
                p.setNombre(rs.getString("nombre"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccion(rs.getString("direccion"));
                p.setRazonSocial(rs.getString("razon_social"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return lista;
    }

    public boolean EliminarProveedor(int id) {
        String sql = "DELETE FROM proveedor WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public boolean ModificarProveedor(Proveedor p) {
        String sql = "UPDATE proveedor SET dni=?, nombre=?, telefono=?, direccion=?, razon_social=? WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getDni());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getDireccion());
            ps.setString(5, p.getRazonSocial());
            ps.setInt(6, p.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    // Método para buscar el nombre del proveedor
    public void llenarComboProveedores(JComboBox<String> combo) {
        String sql = "SELECT nombre FROM proveedor";
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                combo.addItem(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            System.out.println("Error al cargar nombres de proveedores: " + e.toString());
        }
    }

    // Método para obtener el ID del proveedor por su nombre encotrado en el combo
    public int obtenerIdProveedorPorNombre(String nombre) {
        String sql = "SELECT id FROM proveedor WHERE nombre = ?";
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener ID del proveedor: " + e.toString());
        }
        return -1; // ID no encontrado
    }

}
