package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

public class JDBCTestMetadata {

    public static void main(String[] args) {
        Connection con = null;
        String cadena_conexion = "jdbc:mysql://localhost:3306/";
        String nombre_BBDD = "jdbctest";
        String usuario = "BBDDAdmin";
        String contrasenia = "bbddadmin";

        Scanner sc = new Scanner(System.in);
        int n = 0;

        try {
            con = DriverManager.getConnection(cadena_conexion + nombre_BBDD, usuario, contrasenia);
            String query = "SELECT * FROM COCHE";

            PreparedStatement pstm = con.prepareStatement(query);

            ResultSet rs = pstm.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();

            System.out.println("Metadata:");
            System.out.println(rsmd.toString());

            System.out.println("Nombre BBDD: " + rsmd.getCatalogName(1));
            System.out.println("Nombre Tabla: " + rsmd.getTableName(1));
            System.out.println("Num. Columnas: " + rsmd.getColumnCount());
            System.out.println("Columna 3: " + rsmd.getColumnName(3));
            System.out.println("Tipo Col. 3: " + rsmd.getColumnTypeName(3));

            pstm.close();
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error en la ejecucion del SQL");
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
