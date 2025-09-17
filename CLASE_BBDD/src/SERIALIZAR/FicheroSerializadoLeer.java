package SERIALIZAR;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FicheroSerializadoLeer {

    public static void main(String[] args) {
        File f = new File("./ejemploSerializado.obj");

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        ArrayList<N> a1 = new ArrayList();
        
        try {
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);

            System.out.println(ois.available());
            System.out.println("Comenzando la lectura del fichero...");

            while (true) {//cuando es false, salta una excepción (lo de la clase)
                N aux = (N) ois.readObject();
                a1.add(aux);
            }

        } catch (EOFException e) {//End Of File Exception
            System.out.println("Finalizada la lectura del fichero...");
//            e.printStackTrace();
        } catch (FileNotFoundException ex) {
            System.out.println("Error al encontrar el fichero FNF.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error al encontrar el fichero IOE.");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FicheroSerializadoLeer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {//necesitas hacer otro try-catch porque te puede dar otro error al cerrar el fichero tras su escritura.
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero.");
                e.printStackTrace();
                System.out.println(e.toString());
                System.out.println("Finalizando el programa.");
            }
        }

        for (N n : a1) {
            System.out.println(n);
        }
    }

}
