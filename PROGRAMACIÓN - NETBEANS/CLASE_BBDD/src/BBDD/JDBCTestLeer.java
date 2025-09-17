package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JDBCTestLeer {

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
            String query = "SELECT * FROM COCHE WHERE USADO = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            System.out.println("Seleccione 1 para sacar los coches nuevos, 2 para los coches usados:");

            String opc = sc.nextLine();
            boolean usado;
            if (opc.equals("1")) {
                usado = true;
            } else {
                usado = false;
            }

            pstm.setBoolean(1, usado);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                System.out.println("ID \tMarca \tkm \tEtiqueta\tUsado");
                System.out.println(rs.getInt("ID") + "\t" + rs.getString(2) + "\t" + rs.getDouble("km") + "\t" + rs.getString(4) + "\t" + rs.getInt(5));
                n++;
            }

            System.out.println(n + "Registros insertados.");
            pstm.close();

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
