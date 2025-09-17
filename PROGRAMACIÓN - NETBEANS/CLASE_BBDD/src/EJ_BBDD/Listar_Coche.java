package EJ_BBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Listar_Coche {

    public static void main(String[] args) {
        /*
        Ejercicio para mañana:
        Igual que hicimos con el insert:
            - Crear un update para el coche según un parámetro que queramos (menú con los coches de la BBDD).
            - Eliminar el coche que queramos (delete)
        
        Necesito:
            - Metodo public static int listarCoche (Connection con) : 
                - Un menu con el listado de los coches que se van a mandar al Update.
                - Devuelve un int (que es la pk del coche)
                - Connection con: Crear interfaz con metodo para el:
                    Connection con = null;
                    · String cadena_conexion = "jdbc:mysql://localhost:3306/";
                    · String nombre_BBDD = "jdbctest";
                    · String usuario = "BBDDAdmin";
                    · String contrasenia = "bbddadmin";
         */

        Connection con = Interfaz_Connection.conectar();
        if (con != null) {
            int cocheSeleccionado = listarCoche(con);
            System.out.println("El coche es: " + cocheSeleccionado);
        }
    }

    public static int listarCoche(Connection con) {
        Scanner sc = new Scanner(System.in);
        int id = -1;

        try {
            String query = "SELECT ID, MARCA, KM, ETIQUETA, USADO FROM COCHE";
            PreparedStatement pstm = con.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            System.out.println("Lista de coches disponibles: (-1 para no seleccionar)");
            System.out.println("ID\tMarca\t\tKm\tEtiqueta\tUsado");

            while (rs.next()) {
                System.out.println(rs.getInt("ID") + "\t" + rs.getString("MARCA") + "\t"
                        + rs.getFloat("KM") + "\t" + rs.getString("ETIQUETA") + "\t" + rs.getBoolean("USADO"));
            }

            System.out.print("\nIngrese el ID del coche que desea seleccionar: ");
            id = sc.nextInt();

            pstm.close();
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error al listar los coches");
            ex.printStackTrace();
        }

        return id; // Devuelve el ID del coche seleccionado
    }
}
