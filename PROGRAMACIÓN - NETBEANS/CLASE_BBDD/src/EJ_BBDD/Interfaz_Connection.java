package EJ_BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface Interfaz_Connection {

    public static Connection conectar() {
        String cadena_conexion = "jdbc:mysql://localhost:3306/";
        String nombre_BBDD = "jdbctest";
        String usuario = "BBDDAdmin";
        String contrasenia = "bbddadmin";

        try {
//            Connection con = DriverManager.getConnection(cadena_conexion + nombre_BBDD, usuario, contrasenia);
//            return con;
            return DriverManager.getConnection(cadena_conexion + nombre_BBDD, usuario, contrasenia);
        } catch (SQLException ex) {
            System.out.println("Error al conectar con la base de datos");
            ex.printStackTrace();
            return null;
        }
    }

}
