package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCTest {

    public static void main(String[] args) {
        /*
        EN LA BBDD:
        
        SGBDAdmin - sgbdadmin
        BBDDAdmin - bbddadmin
         */


        Connection con = null;
        String cadena_conexion = "jdbc:mysql://localhost:3306/";
        String nombre_BBDD = "jdbctest";
        String usuario = "BBDDAdmin";
        String contrasenia = "bbddadmin";

        ArrayList<Coche> alCoche = new ArrayList<>();
        alCoche.add(new Coche(01, "Mercedes", 120.12f, 'A', true));
        alCoche.add(new Coche(02, "BMW", 160.14f, 'B', false));

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
//        try {//Para cargar los drivers manualmente
//            Class.forName("con.mysql.cj.jdbc.Driver");
//        } catch (Exception e) {
//            System.out.println("asdasdasdads");
//            e.printStackTrace();
//        }
