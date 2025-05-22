package Modelo;
import Util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author LUIS
 */
public class ClienteDao {

    private final Conexion cn = new Conexion();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean RegistrarCliente(Cliente cl) {
        String sql = "INSERT INTO clientes (dni, nombre, telefono, direccion, razon_social) VALUES (?, ?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getRazonSocial());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            cerrarRecursos();
        }
    }

    public List<Cliente> ListarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setId(rs.getInt("id"));
                cl.setDni(rs.getString("dni"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getString("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setRazonSocial(rs.getString("razon_social"));
                cl.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                lista.add(cl);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally {
            cerrarRecursos();
        }
        return lista;
    }

    public boolean EliminarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
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
            cerrarRecursos();
        }
    }

    public boolean ModificarCliente(Cliente cl) {
        String sql = "UPDATE clientes SET dni=?, nombre=?, telefono=?, direccion=?, razon_social=? WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getRazonSocial());
            ps.setInt(6, cl.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            cerrarRecursos();
        }
    }

    public Cliente BuscarPorDni(String dni) {
        Cliente cl = new Cliente();
        String sql = "SELECT * FROM clientes WHERE dni = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            if (rs.next()) {
                cl.setId(rs.getInt("id"));
                cl.setDni(rs.getString("dni"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getString("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setRazonSocial(rs.getString("razon_social"));
                cl.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally {
            cerrarRecursos();
        }
        return cl;
    }

    public Cliente BuscarPorId(int id) {
        Cliente cl = new Cliente();
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                cl.setId(rs.getInt("id"));
                cl.setDni(rs.getString("dni"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getString("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setRazonSocial(rs.getString("razon_social"));
                cl.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally {
            cerrarRecursos();
        }
        return cl;
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
