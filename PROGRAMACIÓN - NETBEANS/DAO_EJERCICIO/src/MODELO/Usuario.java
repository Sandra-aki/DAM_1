package MODELO;

import java.util.Scanner;

public class Usuario {

    private int id;
    private String nombre;
    private String pais;
    private String fechaNacimiento;

    public Usuario(int id, String nombre, String pais, String fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Usuario(String nombre, String pais, String fechaNacimiento) {
        this.nombre = nombre;
        this.pais = pais;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public static Usuario crearUsuarioTeclado() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese pais (3 LETRAS): ");
        String pais = sc.nextLine();
        System.out.print("Ingrese fecha de nacimiento (YYYY-MM-DD): ");
        String fechaNacimiento = sc.nextLine();

        return new Usuario(nombre, pais, fechaNacimiento);
    }

    public void modificarUsuarioTeclado() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nuevo nombre (" + this.nombre + "): ");
        String nuevoNombre = sc.nextLine();
        if (!nuevoNombre.isEmpty()) { // Verifica si el nuevo nombre NO está vacío
            this.nombre = nuevoNombre; // Si no está vacío, actualiza el nombre del usuario
        }

        System.out.print("Nuevo pais (" + this.pais + "): ");
        String nuevoPais = sc.nextLine();
        if (!nuevoPais.isEmpty()) {// Verifica si el nuevo pais NO está vacío
            this.pais = nuevoPais; // Si no está vacío, actualiza
        }

        System.out.print("Nueva fecha de nacimiento (" + this.fechaNacimiento + "): ");
        String nuevaFecha = sc.nextLine();
        if (!nuevaFecha.isEmpty()) {// Verifica si la nueva fecha NO está vacío
            this.fechaNacimiento = nuevaFecha; // Si no está vacío, actualiza
        }
    }

    public String myToString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Pais: " + pais + " | Fecha Nac: " + fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", pais=" + pais + ", fechaNacimiento=" + fechaNacimiento + '}';
    }
}
