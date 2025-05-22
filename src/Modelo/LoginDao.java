package Modelo;

import Util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author LUIS
 */
public class LoginDao {
    private final Conexion cn = new Conexion();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;


    public Usuario login(String correo, String pass) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND pass = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setPass(rs.getString("pass"));
                usuario.setRol(rs.getString("rol"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al validar login: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }
        return usuario;
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
