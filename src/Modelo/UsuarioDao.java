package Modelo;

import Util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author LUIS
 */
public class UsuarioDao {
    private final Conexion cn = new Conexion();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    // REGISTRAR USUARIO
    public boolean RegistrarUsuario(Usuario u) {
        String sql = "INSERT INTO usuarios (nombre, correo, pass, rol) VALUES (?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, u.getPass());
            ps.setString(4, u.getRol());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(" Error al registrar usuario: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos();
        }
    }

    // LISTAR TODOS LOS USUARIOS
    public List<Usuario> ListarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setCorreo(rs.getString("correo"));
                u.setPass(rs.getString("pass"));
                u.setRol(rs.getString("rol"));
                lista.add(u);
            }
        } catch (SQLException e) {
            System.out.println(" Error al listar usuarios: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    // ELIMINAR USUARIO POR ID
    public boolean EliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(" Error al eliminar usuario: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos();
        }
    }

    // ACTUALIZAR USUARIO
    public boolean ModificarUsuario(Usuario u) {
        String sql = "UPDATE usuarios SET nombre = ?, correo = ?, pass = ?, rol = ? WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, u.getPass());
            ps.setString(4, u.getRol());
            ps.setInt(5, u.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(" Error al modificar usuario: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos();
        }
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
