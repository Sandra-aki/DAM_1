package EJECUTABLES;

import ACCESO_A_DATOS.JDBCUsuarioDAO;
import MODELO.Usuario;
import java.util.Scanner;

public class Ejecutable {

    public static void main(String[] args) {
        /*
        Clase Usuario necesita dos metodos: Static CrearUsuarioTeclado(); y No static modificarUsuarioTeclado();
        Crear Menu:
        1- Alta Usuario: 
            - Crear Usuario (Metodo static en Usuario con Scanners -> CrearUsuarioTeclado(); sin id (eso se lo da la bbdd))
            - Alta
        2- Baja Usuario: 
            - listar();
            - Seleccionar ID del usuario (Capturar con Scanner el id).
            - buscar() -> busca/recibe el id y escupe Usuario
            - baja() -> Con el usuario que te da buscar.
        3- Modificar Usuario:
            - listar();
            - Seleccionar ID del usuario (Capturar con Scanner el id).
            - buscar() -> busca/recibe el id y escupe Usuario
            - Usuario.modificarUsuarioTeclado();
            - Update -> metodo modificar();
        4- Salir:
            - Cierra conexion
        
        Crear ArrayList de Usuario: AL<Usuario> aL = listar();
         */
 /*
        El menú va a ser como el menú de banco:
        1.- Alta
        2.- Baja
        3.- Modificar
        4.- Salir
        
        Se va a crear un AR<usuarios> al = listar(); (vamos a usarlo en bajas y en modificar)
        
        En alta de usuario:
        Crear usuario: método estático de usuario (crearUsuarioTeclado) -> Se hace el alta.
        
        En baja de usuario: listar(); -> Se captura con scanner el id -> buscar(); -> baja usuario.
        
        En modificar usuario: listar(); -> se captura con scanner el id -> modificar usuario por teclado();
        -> buscar el usuario(): coge el id +.modificar teclado(modusuario();
        -> hacer el update del usuario();
         */

        JDBCUsuarioDAO usuarioDAO = new JDBCUsuarioDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("--- Menú Gestión de Usuarios ---");
            System.out.println("1. Alta Usuario");
            System.out.println("2. Baja Usuario");
            System.out.println("3. Modificar Usuario");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    Usuario nuevo = Usuario.crearUsuarioTeclado();
                    usuarioDAO.alta(nuevo);
                }
                case 2 -> {
                    System.out.print("Ingrese ID del usuario a eliminar: ");
                    int id = sc.nextInt();
                    Usuario usuario = usuarioDAO.buscar(id);
                    if (usuario != null) {
                        usuarioDAO.baja(usuario);
                    }
                }
                case 3 -> {
                    System.out.print("Ingrese ID del usuario a modificar: ");
                    int id = sc.nextInt();
                    Usuario usuario = usuarioDAO.buscar(id);
                    if (usuario != null) {
                        usuario.modificarUsuarioTeclado();
                        usuarioDAO.modificar(usuario);
                    }
                }
                case 4 -> {
                    System.out.println("Cerrando conexión y saliendo del programa...");
                    usuarioDAO.cerrarConexion();
                    sc.close();
                    return;
                }
                default ->
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

        }
    }
}
//JDBCUsuarioDAO exe = null;
//
//        try {
//            exe = new JDBCUsuarioDAO();
//            ArrayList<Usuario> usuarios = exe.obtenerUsuarios();
//
//            Usuario test = new Usuario(98, "Ivan", "ap1", "ap2", "ESP", 888888888, "2023/10/20", "ads@gmail.com");
//        } catch (Exception e) {
//
//        }
//    }
