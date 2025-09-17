package ACCESO_A_DATOS;

import MODELO.RedSocial;
import MODELO.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class JDBCSuscripcionDAO implements SuscripcionDAO {

    private static final String CADENA_CONEXION = "jdbc:mysql://localhost:3306/";
    private static final String NOMBRE_BBDD = "z_opos20";
    private static final String USUARIO = "BBDDAdmin";
    private static final String CONTRASENIA = "bbddadmin";

    private Connection con;

    // Constructor: Inicializa la conexión con la base de datos
    public JDBCSuscripcionDAO() {
        try {
            this.con = DriverManager.getConnection(CADENA_CONEXION + NOMBRE_BBDD, USUARIO, CONTRASENIA);
        } catch (SQLException ex) {
            System.out.println("Error al conectar con la base de datos");
            ex.printStackTrace();
        }
    }

    @Override
    public boolean suscribir(int usrId, String nomRS) throws DuplicadoException {
        String query = "SELECT fechaIncorp FROM suscripciones WHERE usrId = ? AND nomRS = ?";
        String sql = "INSERT INTO suscripciones (usrId, nomRS, fechaIncorp) VALUES (?, ?, SYSDATE)";

        try (PreparedStatement psExiste = con.prepareStatement(query)) {
            psExiste.setInt(1, usrId);
            psExiste.setString(2, nomRS);
            ResultSet rs = psExiste.executeQuery();

            if (rs.next()) {
                throw new DuplicadoException("El usuario ya se suscribio el: " + rs.getDate("fechaIncorp"));
            }

            try (PreparedStatement psInsert = con.prepareStatement(sql)) {
                psInsert.setInt(1, usrId);
                psInsert.setString(2, nomRS);
                return psInsert.executeUpdate() > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean desuscribir(int usrId, String nomRS) throws SQLException {
        String query = "DELETE FROM suscripciones WHERE usrId = ? AND nomRS = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, usrId);
            ps.setString(2, nomRS);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean desuscribirTodo(int usrId) {
        String query = "DELETE FROM suscripciones WHERE usrId = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, usrId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<RedSocial> obtenerRedesSocialesUsuario(int usrId) {
        String query = "SELECT nomRS FROM suscripcion WHERE usrId = ?";
        ArrayList<RedSocial> redes = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, usrId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                redes.add(new RedSocial(rs.getString("red_social"), rs.getString("url"), rs.getString("fechaLanzamiento")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return redes;
    }

    @Override
    public ArrayList<Usuario> obtenerUsuariosRedSocial(String nomRS) {
        String query = "SELECT u.id, u.nombre, u.pais, u.fechaNac FROM usuario u JOIN suscripcion s ON u.id = s.usuario_id WHERE s.nomRS = ?;";
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, nomRS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("pais"), rs.getString("fecha")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public HashMap<RedSocial, ArrayList<Usuario>> obtenerSuscripciones() {
        String query = "SELECT u.id, u.nombre, u.pais, u.fechaNac, "
                + "rs.nombre, rs.url , rs.fechaLanzamiento"
                + "FROM usuario u "
                + "INNER JOIN suscripcion s ON u.id = s.usuario_id "
                + "INNER JOIN red_social rs ON s.nomRS = rs.nombre;";

        HashMap<RedSocial, ArrayList<Usuario>> mapa = new HashMap<>();

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RedSocial redSocial = new RedSocial(
                        rs.getString("nombre"),
                        rs.getString("url"),
                        rs.getString("fechaLanzamiento")
                );

                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("pais"),
                        rs.getString("fechaNac")
                );
                if (!mapa.containsKey(redSocial)) {
                    mapa.put(redSocial, new ArrayList<>());
                }
                mapa.get(redSocial).add(usuario);

//                mapa.putIfAbsent(redSocial, new ArrayList<>());
//                mapa.get(redSocial).add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return mapa;
    }

    public Usuario[] usuarioMasYMenosReciente() {
        Usuario[] sol = new Usuario[2]; // Array para guardar dos usuarios (el mas y menos reciente)

        String sql = """
        (SELECT * FROM usuario ORDER BY fechaIncorp ASC LIMIT 1)
        UNION
        (SELECT * FROM usuario ORDER BY fechIncorp DESC LIMIT 1);
    """;
//        ArrayList<String> aL = new ArrayList<String>();
//        HashMap<String,RedSocial> hM = new HashMap();

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            int i = 0;
            while (rs.next()) {
                // Creamos el usuario con los datos de la consulta
                sol[i] = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("pais"),
                        rs.getString("fechaNac")
                );
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // En caso de error, mostramos el mensaje
        }

        return sol; // Retornamos el array con los dos usuarios (mas y menos reciente)
    }
}
