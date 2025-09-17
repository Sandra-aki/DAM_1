package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTestMain {

    public static void main(String[] args) {
        /*
        EN LA BBDD:
        
        SGBDAdmin - sgbdadmin
        BBDDAdmin - bbddadmin
         */

//        try {//Para cargar los drivers manualmente
//            Class.forName("con.mysql.cj.jdbc.Driver");
//        } catch (Exception e) {
//            System.out.println("asdasdasdads");
//            e.printStackTrace();
//        }
        Connection con = null;
        String cadena_conexion = "jdbc:mysql://localhost:3306/";
        String nombre_BBDD = "jdbctest";
        String usuario = "BBDDAdmin";
        String contrasenia = "bbddadmin";

        try {
            con = DriverManager.getConnection(cadena_conexion + nombre_BBDD, usuario, contrasenia);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();//cerrar la conexion
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
