package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
//import java.util.ArrayList;

public class JDBCTestEscribir {

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

        Coche c1 = new Coche(01, "Mercedes", 120.12f, 'A', true);

        try {
            con = DriverManager.getConnection(cadena_conexion + nombre_BBDD, usuario, contrasenia);

            String query = "INSERT INTO COCHE (ID,MARCA,KM,ETIQUETA,USADO) VALUES(null,?,?,?,?)";//interrogantes significa parametros

            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, c1.getMarca());
            pstm.setFloat(2, c1.getKm());
            pstm.setString(3, String.valueOf(c1.getEtiqueta()));
            pstm.setBoolean(4, c1.isUsado());

            int n = pstm.executeUpdate();
            System.out.println(n + "Registros insertados.");

            pstm.close();

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
