package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author LUIS
 */
public class EmpresaDao {

    // Método para obtener la conexión a la base de datos
    private Connection getConnection() throws SQLException {
        // Aquí debes poner tu código para conectar a la base de datos, por ejemplo:
        String url = "jdbc:mysql://localhost:3306/tecnoluisdb"; // URL de la base de datos
        String usuario = "root"; // Usuario de la base de datos
        String contrasena = "abc123."; // Contraseña de la base de datos
        return DriverManager.getConnection(url, usuario, contrasena);
    }

    // Crear una nueva empresa en la base de datos
    public int insertarEmpresa(Empresa empresa) {
        String sql = "INSERT INTO empresa (dni_nif, nombre, telefono, razon_social, direccion) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, empresa.getDniNif());
            ps.setString(2, empresa.getNombre());
            ps.setString(3, empresa.getTelefono());
            ps.setString(4, empresa.getRazonSocial());
            ps.setString(5, empresa.getDireccion());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                // Obtener el ID de la nueva empresa insertada
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); // Retornar el ID generado
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Si no se pudo insertar, devolver -1
    }

    // Obtener una empresa por su ID
    public Empresa obtenerEmpresaPorId(int id) {
        String sql = "SELECT * FROM empresa WHERE id = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Empresa(
                        rs.getInt("id"),
                        rs.getString("dni_nif"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("razon_social"),
                        rs.getString("direccion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si no encuentra la empresa, devolver null
    }

    // Obtener todas las empresas
    public List<Empresa> obtenerTodasLasEmpresas() {
        List<Empresa> empresas = new ArrayList<>();
        String sql = "SELECT * FROM empresa";
        try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Empresa empresa = new Empresa(
                        rs.getInt("id"),
                        rs.getString("dni_nif"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("razon_social"),
                        rs.getString("direccion")
                );
                empresas.add(empresa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empresas;
    }

    // Actualizar los datos de una empresa
    public boolean actualizarEmpresa(Empresa empresa) {
        String sql = "UPDATE empresa SET dni_nif = ?, nombre = ?, telefono = ?, razon_social = ?, direccion = ? WHERE id = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, empresa.getDniNif());
            ps.setString(2, empresa.getNombre());
            ps.setString(3, empresa.getTelefono());
            ps.setString(4, empresa.getRazonSocial());
            ps.setString(5, empresa.getDireccion());
            ps.setInt(6, empresa.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; // Si se actualizó, retorna true
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Si no se pudo actualizar, retorna false
    }

    // Eliminar una empresa por su ID
    public boolean eliminarEmpresa(int id) {
        String sql = "DELETE FROM empresa WHERE id = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; // Si se eliminó, retorna true
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Si no se pudo eliminar, retorna false
    }
}
