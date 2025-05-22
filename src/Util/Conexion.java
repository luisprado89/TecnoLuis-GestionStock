package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author LUIS
 */
public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/tecnoluisdb";
    private static final String USER = "root";
    private static final String PASSWORD = "abc123.";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos");
            e.printStackTrace();
            return null;
        }
    }
}
