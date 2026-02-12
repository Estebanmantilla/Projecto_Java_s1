
package CONTROLADOR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public Connection conexion() {
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tecnostore_db", "root", "1234");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        }
        return c;
        
    }
}