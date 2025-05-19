package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

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
            try { if (con != null) con.close(); } catch (SQLException e) { }
        }
        return usuario;
    }
}
