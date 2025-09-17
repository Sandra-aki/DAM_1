package EJECUTABLES;

import ACCESO_A_DATOS.JDBCUsuarioDAO;
import MODELO.Usuario;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class NewMain {

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
            System.out.println("Menu Gestion de Usuarios");
            System.out.println("1. Alta Usuario");
            System.out.println("2. Baja Usuario");
            System.out.println("3. Modificar Usuario");
            System.out.println("4. Mostrar Pais con + y - Usuarios");
            System.out.println("5. Mostrar y Eliminar Usuarios + Joven y + Mayor");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");

            if (!sc.hasNextInt()) {
                System.out.println("Entrada no válida. Ingrese un número.");
                sc.next();
                continue;
            }

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    Usuario nuevo = Usuario.crearUsuarioTeclado();
                    usuarioDAO.alta(nuevo);
                    System.out.println("Usuario dado de alta correctamente.");
                }
                case 2 -> {
                    System.out.print("Ingrese ID del usuario a eliminar: ");

                    ArrayList<Usuario> usuarios = usuarioDAO.listar();
                    System.out.println("\n--- Lista de Usuarios ---");
                    for (Usuario u : usuarios) {
                        System.out.println(u.myToString());
                    }

                    int id = sc.nextInt();
                    seleccionarIdUsuario(usuarios);

                    Usuario usuario = usuarioDAO.buscar(id);
                    if (usuario != null) {
                        usuarioDAO.baja(usuario);
                        System.out.println("Usuario eliminado correctamente.");
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                }
                case 3 -> {
                    System.out.print("Ingrese ID del usuario a modificar: ");

                    ArrayList<Usuario> usuarios = usuarioDAO.listar();
                    System.out.println("\n--- Lista de Usuarios ---");
                    for (Usuario u : usuarios) {
                        System.out.println(u.myToString());
                    }

                    int id = sc.nextInt();
                    seleccionarIdUsuario(usuarios);

                    Usuario usuario = usuarioDAO.buscar(id);
                    if (usuario != null) {
                        usuario.modificarUsuarioTeclado();
                        usuarioDAO.modificar(usuario);
                        System.out.println("Usuario modificado correctamente.");
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                }
                case 4 -> {
                    Map<String, Integer> paises = usuarioDAO.gentePaisesNum();
                    System.out.println("Pais con mas y menos usuarios");
                    for (Map.Entry<String, Integer> entry : paises.entrySet()) {
                        String pais = entry.getKey();       // Clave (nombre del país)
                        int numeroUsuarios = entry.getValue(); // Valor (cantidad de usuarios)
                        System.out.println(pais + ": " + numeroUsuarios + " usuarios");
                    }
                }
                case 5 -> {
                    usuarioDAO.eliminarUsuarioJovenYMayor();
                }
                case 6 -> {
                    System.out.println("Saliendo...");
                    usuarioDAO.cerrarConexion();
                    sc.close();
                }
                default ->
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    public static int seleccionarIdUsuario(ArrayList<Usuario> aL) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < aL.size(); i++) {
            System.out.println(i + 1 + ".- " + aL.get(i).toString());
        }
        int selec = sc.nextInt();
        return aL.get(selec - 1).getId();
    }
}
