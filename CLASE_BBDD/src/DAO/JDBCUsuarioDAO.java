package DAO;

import DAO.MODELO.Usuario;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUsuarioDAO implements UsuarioDAO {

    static String cadena_conexion = "jdbc:mysql://localhost:3306/";
    static String nombre_BBDD = "jdbctest";
    static String usuario = "BBDDAdmin";
    static String contrasenia = "bbddadmin";

    public static Connection conectar() {
        try {
//            Connection con = DriverManager.getConnection(cadena_conexion + nombre_BBDD, usuario, contrasenia);
//            return con;
            return DriverManager.getConnection(cadena_conexion + nombre_BBDD, usuario, contrasenia);
        } catch (SQLException ex) {
            System.out.println("Error al conectar con la base de datos");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean alta(Usuario usr) throws SQLException {
        String sql = "INSERT INTO Usuario (nombre, pais, fechaNac) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(cadena_conexion, usuario, contrasenia); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usr.getNombre());
            ps.setString(2, usr.getPais());
            ps.setString(3, usr.getFechaNacimiento());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean baja(Usuario usr) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(cadena_conexion, usuario, contrasenia); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usr.getId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Usuario buscar(int id) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(cadena_conexion, usuario, contrasenia); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("pais"),
                        rs.getString("fechaNac")
                );
            }
        }
        return null;
    }

    @Override
    public boolean modificar(Usuario usr) throws SQLException {
        String sql = "UPDATE Usuario SET nombre = ?, pais = ?, fechaNac = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(cadena_conexion, usuario, contrasenia); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usr.getNombre());
            ps.setString(2, usr.getPais());
            ps.setString(3, usr.getFechaNacimiento());
            ps.setInt(4, usr.getId());

            return ps.executeUpdate() > 0;
        }
    }

    public ArrayList<Usuario> listar() throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";

        try (Connection conn = DriverManager.getConnection(cadena_conexion, usuario, contrasenia); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("pais"),
                        rs.getString("fechaNac")
                ));
            }
        }
        return usuarios;
    }

    @Override
    public ArrayList<Usuario> obtenerSeguidores() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
