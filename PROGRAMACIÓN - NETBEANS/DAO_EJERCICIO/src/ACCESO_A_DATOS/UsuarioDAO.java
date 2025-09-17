package ACCESO_A_DATOS;

import MODELO.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface UsuarioDAO {

    public abstract boolean alta(Usuario d) throws SQLException;

    public abstract boolean baja(Usuario d) throws SQLException;

    public abstract boolean cerrarConexion() throws SQLException;

    public abstract Usuario buscar(int id) throws SQLException;

    public abstract boolean modificar(Usuario d) throws SQLException;

    public abstract ArrayList<Usuario> obtenerSeguidores() throws SQLException;

    public abstract Map<String, Integer> gentePaisesNum() throws SQLException;

    public abstract Usuario[] obtenerUsuarioJovenYMayor() throws SQLException;

    public abstract void eliminarUsuarioJovenYMayor() throws SQLException;
}
