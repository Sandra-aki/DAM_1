package EJECUTABLES;

import MODELO.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Filtros {

    private static final String CADENA_CONEXION = "jdbc:mysql://localhost:3306/";
    private static final String NOMBRE_BBDD = "z_opos20";
    private static final String USUARIO = "BBDDAdmin";
    private static final String CONTRASENIA = "bbddadmin";

    public static void main(String[] args) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(CADENA_CONEXION + NOMBRE_BBDD, USUARIO, CONTRASENIA);
        } catch (SQLException ex) {
            System.out.println("Error al conectar con la base de datos");
            ex.printStackTrace();
        }

        ArrayList<Usuario> sol = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String nombre, usrId;

        System.out.println("Introduce valores para cada filtro, enter si quieres vacio");
        System.out.println("Nombre: ");
        nombre = sc.nextLine();
        System.out.println("Id del usuario: ");
        usrId = sc.nextLine();

        String query = "SELECT * FROM usuario WHERE 1=1";

        if (nombre != null && !nombre.equals("")) {
            query = query + " AND nombre LIKE ?";
        }
        if (usrId != null && !usrId.equals("")) {
            query = query + " AND usrId = ?";
        }

        try {
            PreparedStatement ptsm = con.prepareStatement(query);
            int cont = 1;

            if (nombre != null && !nombre.equals("")) {
                cont++;
                ptsm.setString(cont, nombre);
            }
            if (usrId != null && !usrId.equals("")) {
                cont++;
                ptsm.setString(cont, usrId);
            }
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                sol.add(new Usuario(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        for (Usuario u : sol) {
            System.out.println(u);
        }
    }
}
