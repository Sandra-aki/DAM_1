package ACCESO_A_DATOS;

import MODELO.Usuario;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class JDBCUsuarioDAO implements UsuarioDAO {
//    public static Connection conectar() {
//        try {
////            Connection con = DriverManager.getConnection(cadena_conexion + nombre_BBDD, usuario, contrasenia);
////            return con;
//            return DriverManager.getConnection(cadena_conexion + nombre_BBDD, usuario, contrasenia);
//        } catch (SQLException ex) {
//            System.out.println("Error al conectar con la base de datos");
//            ex.printStackTrace();
//            return null;
//        }
//    }
    // Constructor que inicializa la conexión

    private static final String CADENA_CONEXION = "jdbc:mysql://localhost:3306/";
    private static final String NOMBRE_BBDD = "z_opos20";
    private static final String USUARIO = "BBDDAdmin";
    private static final String CONTRASENIA = "bbddadmin";

    private static Connection con;

    // Constructor: Inicializa la conexión con la base de datos
    public JDBCUsuarioDAO() {
        try {
            this.con = DriverManager.getConnection(CADENA_CONEXION + NOMBRE_BBDD, USUARIO, CONTRASENIA);
        } catch (SQLException ex) {
            System.out.println("Error al conectar con la base de datos");
            ex.printStackTrace();
        }
    }

    @Override
    public boolean alta(Usuario usr) {
        String sql = "INSERT INTO Usuario (nombre, pais, fechaNac) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usr.getNombre());
            ps.setString(2, usr.getPais());
            ps.setString(3, usr.getFechaNacimiento());

            return ps.executeUpdate() > 0; // Devuelve true si se insertó al menos un registro
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean baja(Usuario usr) {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, usr.getId());

            return ps.executeUpdate() > 0; // Devuelve true si se eliminó un registro
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Usuario buscar(int id) {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean modificar(Usuario usr) {
        String sql = "UPDATE Usuario SET nombre = ?, pais = ?, fechaNac = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usr.getNombre());
            ps.setString(2, usr.getPais());
            ps.setString(3, usr.getFechaNacimiento());
            ps.setInt(4, usr.getId());

            return ps.executeUpdate() > 0; // Devuelve true si se actualizó al menos un registro
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Usuario> obtenerSeguidores() {
        ArrayList<Usuario> seguidores = new ArrayList<>();
        String sql = "SELECT u.id, u.nombre, u.pais, u.fechaNac FROM Usuario u "
                + "JOIN Seguidores s ON u.id = s.id_seguidor";

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                seguidores.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("pais"),
                        rs.getString("fechaNac")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seguidores;
    }

    public ArrayList<Usuario> listar() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("pais"),
                        rs.getString("fechaNac")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public boolean cerrarConexion() {
        try {
            if (con != null) {
                con.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Map<String, Integer> gentePaisesNum() {
        Map<String, Integer> sol = new HashMap<>(); // Creamos un HashMap para guardar el país y la cantidad de usuarios

        // Consulta SQL para encontrar el país con más y menos usuarios
        String sql = """
        SELECT pais, COUNT(*) AS numero
        FROM usuario
        GROUP BY pais
        HAVING COUNT(*) = (SELECT MAX(nmax) FROM (SELECT COUNT(*) AS nmax FROM usuario GROUP BY pais) AS numax)
           OR COUNT(*) = (SELECT MIN(nmin) FROM (SELECT COUNT(*) AS nmin FROM usuario GROUP BY pais) AS numin)
        ORDER BY numero DESC;
    """;

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // Guardamos en el mapa el país y la cantidad de usuarios
                sol.put(rs.getString("pais"), rs.getInt("numero"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sol;
    }

    public Usuario[] obtenerUsuarioJovenYMayor() {
        Usuario[] sol = new Usuario[2]; // Array para guardar dos usuarios (el más joven y el más mayor)

        // Consulta SQL para encontrar al usuario más joven (fecha más reciente) y al más mayor (fecha más antigua)
        String sql = """
        (SELECT * FROM usuario ORDER BY fechaNac ASC LIMIT 1) -- El más mayor (fecha de nacimiento más antigua)
        UNION
        (SELECT * FROM usuario ORDER BY fechaNac DESC LIMIT 1); -- El más joven (fecha de nacimiento más reciente)
    """;

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

        return sol; // Retornamos el array con los dos usuarios (más mayor y más joven)
    }

    public void eliminarUsuarioJovenYMayor() {
        Usuario[] sol = obtenerUsuarioJovenYMayor(); // Llamamos al método que obtiene los usuarios

        if (sol[0] != null) { // Si el usuario más mayor existe
            baja(sol[0]); // Lo eliminamos
            System.out.println("Usuario más mayor eliminado: " + sol[0].getNombre());
        }

        if (sol[1] != null) { // Si el usuario más joven existe
            baja(sol[1]); // Lo eliminamos
            System.out.println("Usuario más joven eliminado: " + sol[1].getNombre());
        }
    }

    public static void filtros(String cod_empleado, String oficio, String departamento) {
        try {
            String query = "select emp_no,apellido,oficio,salario,dep_no from empleados where 1=1";
            if (cod_empleado != null && !cod_empleado.equals("")) {
                query = query + "AND emp_no = ?";
            }
            if (oficio != null && !oficio.equals("")) {
                query = query + "AND oficio LIKE ?";
            }
            if (departamento != null && !departamento.equals("")) {
                query = query + "AND dep_no = ?";
            }

            PreparedStatement pstm = con.prepareStatement(query);

            //Sets
            int contadorSets = 0;

            if (cod_empleado != null && !cod_empleado.equals("")) {
                contadorSets++;
                pstm.setString(contadorSets, cod_empleado);
            }
            if (oficio != null && !oficio.equals("")) {
                contadorSets++;
                pstm.setString(contadorSets, "%" + oficio + "%");
            }
            if (departamento != null && !departamento.equals("")) {
                contadorSets++;
                pstm.setString(contadorSets, departamento);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
//    public ArrayList<Usuario> obtenerUsuariosExtremos(String redSocial) {
//        String sql = """
//        (SELECT u.* FROM Usuario u
//        JOIN Suscripcion s ON u.id = s.id
//        WHERE s.nomRS = ? 
//        AND u.fechaNac = (SELECT MIN(fechaNac) FROM Usuario WHERE usrId IN 
//            (SELECT usrId FROM Suscripcion WHERE nomRS = ?)))
//        UNION
//        (SELECT u.* FROM Usuario u
//        JOIN Suscripcion s ON u.id = s.id
//        WHERE s.nomRS = ? 
//        AND u.fechaNac = (SELECT MAX(fechaNac) FROM Usuario WHERE usrId IN 
//            (SELECT usrId FROM Suscripcion WHERE nomRS = ?)));
//    """;
//
//        ArrayList<Usuario> usuarios = new ArrayList<>();
//        try (PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setString(1, redSocial);
//            ps.setString(2, redSocial);
//            ps.setString(3, redSocial);
//            ps.setString(4, redSocial);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                usuarios.add(new Usuario(
//                        rs.getInt("id"),
//                        rs.getString("nombre"),
//                        rs.getString("pais"),
//                        rs.getString("fechaNac")
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return usuarios;
//    }
//
//    public Map<String, Integer> gentePaisesNum() {
//        String sql = """
//        (SELECT pais, 
//         COUNT(*) as total 
//                     FROM Usuario 
//                     GROUP BY pais 
//                     ORDER BY total DESC LIMIT 1
//                     )
//        UNION
//        (SELECT pais, 
//         COUNT(*) as total 
//                     FROM Usuario 
//                     GROUP BY pais 
//                     ORDER BY total ASC LIMIT 1
//                     );
//    """;
//
//        Map<String, Integer> resultado = new HashMap<>();
//
//        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
//
//            while (rs.next()) {
//                resultado.put(rs.getString("pais"), rs.getInt("total"));//put
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return resultado;
//    }
