package DAO;

import DAO.MODELO.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UsuarioDAO {

    public abstract boolean alta(Usuario d) throws SQLException;

    public abstract boolean baja(Usuario d) throws SQLException;

    public abstract Usuario buscar(int id) throws SQLException;

    public abstract boolean modificar(Usuario d) throws SQLException;

    public abstract ArrayList<Usuario> obtenerSeguidores() throws SQLException;
}
