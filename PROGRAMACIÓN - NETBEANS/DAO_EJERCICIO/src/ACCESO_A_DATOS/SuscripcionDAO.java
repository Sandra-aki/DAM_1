package ACCESO_A_DATOS;

import MODELO.RedSocial;
import MODELO.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface SuscripcionDAO {

    public boolean suscribir(int usrId, String nomRS) throws DuplicadoException;

    public boolean desuscribir(int usrId, String nomRS) throws SQLException;

    public boolean desuscribirTodo(int usrId) throws SQLException;

    public ArrayList<RedSocial> obtenerRedesSocialesUsuario(int usrId) throws SQLException;

    public ArrayList<Usuario> obtenerUsuariosRedSocial(String nomRS) throws SQLException;

    public HashMap<RedSocial, ArrayList<Usuario>> obtenerSuscripciones() throws SQLException;
}

/*
Suscripcion DAO:
6 metodos:
    + boolean suscribir(usr, rs); - > lanza Exception personalizada -> throws Duplicado
        al suscribir se hace select en el registro, si devuelve 0, INSERT y return true
        si es algo distinto a 0, es que ya estaba suscrito, se manda un sout con un mensaje
        de error con un Este usuario ya estaba suscrito desde (fecha) y se hace el throws de duplicado    
    + boolean desuscribir(usr, rs);
    + boolean desuscribir_todo(usr);
    + Obtener redsociales usuario -> <RS> de Usuarios:
        - m1(usr) -> Devuelve arrayList de RS
    + Obtener Usuarios red social -> <Usuarios> de RS
        - m2(rs) -> Devuelve arrayList de Usuarios
    + Obtener suscripciones -> ():
        - no entra nada y develve toda la tabla -> HashMap<RS,AL<Usr>>
    + Obtener

    Partiendo del metodo que te devuelve todas las suscripciones, 
    sacar de cada redSocial el usuario mas nuevo y mas viejo (con fechaIncorp). //Hay que comparar fechas
    Coger esos usuarios y sumarles 100 a su UID.
    //Crear clase fecha, constructor fecha es String. Clase fecha podemos distanciar fechas, y CompareTo
 */
