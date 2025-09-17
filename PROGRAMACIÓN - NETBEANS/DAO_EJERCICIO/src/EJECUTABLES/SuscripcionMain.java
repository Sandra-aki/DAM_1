package EJECUTABLES;

import ACCESO_A_DATOS.JDBCSuscripcionDAO;
import java.time.LocalDate;

public class SuscripcionMain {

    public static void main(String[] args) {
        LocalDate myObj = LocalDate.now(); //Create a date Object
        System.out.println(myObj.toString());   //Display the current Date

        // Crear DAO
        JDBCSuscripcionDAO dao = new JDBCSuscripcionDAO();

        // ⚠️ IMPORTANTE: estos valores deben existir en la base de datos
        int idUsuario = 1;               // Debe existir en la tabla 'usuario'
        String nombreRedSocial = "Instagram"; // Debe existir en la tabla 'red_social'

    }
}