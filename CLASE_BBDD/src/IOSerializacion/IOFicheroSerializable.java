package IOSerializacion;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class IOFicheroSerializable {

    /*
    Hacer una clase que se llame IOFicheroSerializable, donde meter file y meter los metodos para leer y escribir fichero.
    Main lee el contenido de fichero Personas va a eliminar las personas menores de persona. (Leer el array de personas, ve los menores, y los elimina del fichero.)
    Sin append (Para sobreecribir el fichero.)
     */

 /*
    Atributos:
    private static File f = RUTA_DEF;
    private static fis = null;
    private static dis = null;
    private static fos = null;
    private static oos = null;
    
    Metodos:
    public boolean escribir(ArrayList<Objetos>,boolean){ // devuelve boolean que dice si ok, o no ok
    }
    public ArrayList<Objetos> leer(){ // No recibe nada
    }
    
    //Si no se puede hacer ArrayList<Objetos>, hacer ArrayList<Persona>

     */
    private static final File RUTA_DEF = new File("./ficheroDefecto.data");
    private static File f = RUTA_DEF;
    private static FileInputStream fis = null;
    private static ObjectInputStream ois = null;
    private static FileOutputStream fos = null;
    private static ObjectOutputStream oos = null;

    // Método para escribir en el archivo
    public boolean escribir(ArrayList<Persona> aL, boolean b) {
        try {
            fos = new FileOutputStream(f, b);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(aL);
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Error al acceder el Fichero");
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                System.out.println("Error a la hora de cerrar los ficheros");
                System.out.println(e.toString());
                e.printStackTrace();
            }
        }
    }

    // Método para leer desde el archivo
    public ArrayList<Persona> leer() {
        ArrayList<Persona> aL = new ArrayList<>();
        try {
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            aL = (ArrayList<Persona>) ois.readObject();

        } catch (EOFException ex) {
            System.out.println("Finalizado lectura de Fichero...");
        } catch (FileNotFoundException ex) {
            System.out.println("Error, no se encuentra fichero");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error IO");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                System.out.println("Error a la hora de cerrar los ficheros");
                System.out.println(e.toString());
                e.printStackTrace();
            }
        }
        return aL;
    }

}
