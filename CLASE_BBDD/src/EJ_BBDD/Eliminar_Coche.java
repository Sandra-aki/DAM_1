package EJ_BBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Eliminar_Coche {

    public static void main(String[] args) {
        Connection con = Interfaz_Connection.conectar();
        if (con == null) {
            return;
        }

        // Seleccionar coche con el método existente
        int cocheId = Listar_Coche.listarCoche(con);
        if (cocheId == -1) {
            System.out.println("No se seleccionó ningún coche.");
            return;
        }

        try {
            String query = "DELETE FROM COCHE WHERE ID = ?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1, cocheId);

            int filasAfectadas = pstm.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Coche eliminado correctamente.");
            } else {
                System.out.println("No se encontró un coche con ese ID.");
            }

            pstm.close();
            con.close();

        } catch (SQLException ex) {
            System.out.println("Error al eliminar el coche.");
            ex.printStackTrace();
        }
    }
}
