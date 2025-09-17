package EJ_BBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Update_Marca_Coche implements Interfaz_Connection {

    public static void main(String[] args) {
        Connection con = Interfaz_Connection.conectar();
        if (con == null) {
            return;
        }

        Scanner sc = new Scanner(System.in);

        // Seleccionar coche
        int cocheId = Listar_Coche.listarCoche(con);
        if (cocheId == -1) {
            System.out.println("No se selecciono ningun coche.");
            return;
        }

        System.out.print("Ingrese la nueva marca: ");
        String nuevaMarca = sc.nextLine();

        try {
            String query = "UPDATE COCHE SET MARCA = ? WHERE ID = ?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, nuevaMarca);
            pstm.setInt(2, cocheId);

            int filasAfectadas = pstm.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Coche actualizado correctamente.");
            } else {
                System.out.println("No se encontro el coche.");
            }

            pstm.close();
            con.close();

        } catch (SQLException ex) {
            System.out.println("Error al actualizar el coche.");
            ex.printStackTrace();
        }
    }
}
