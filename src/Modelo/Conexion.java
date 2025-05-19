package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private Connection con;

    public Connection getConnection() {
        try {
            // URL de conexión a la base de datos
            String url = "jdbc:mysql://localhost:3306/tecnoluisdb?serverTimezone=UTC";
            String user = "root";
            String password = "abc123.";

            // Intentar conectar
            con = DriverManager.getConnection(url, user, password);
            return con;

        } catch (SQLException e) {
            System.out.println("❌ Error al conectar con la base de datos: " + e.getMessage());
        }

        return null;
    }
}
