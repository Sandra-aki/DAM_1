package ACCESO_A_DATOS;

public class DuplicadoException extends Exception {

    public DuplicadoException(String error_msg) {
        super(error_msg);
        System.out.println("Error: Duplicado.");

        /*
        al suscribir se hace select en el registro, si devuelve 0, INSERT y return true
        si es algo distinto a 0, es que ya estaba suscrito, se manda un sout con un mensaje
        de error con un Este usuario ya estaba suscrito desde (fecha) y se hace el throws de duplicado
         */
    }
}
