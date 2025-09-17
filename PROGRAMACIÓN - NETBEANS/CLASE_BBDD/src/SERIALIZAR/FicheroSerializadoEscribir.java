package SERIALIZAR;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FicheroSerializadoEscribir {

    public static void main(String[] args) {
        N n1 = new N();
        N n2 = new N(false, 'V', 555, 9.99, "Adios");
        File f = new File("./ejemploSerializado.obj");

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        MyOOS myoos = null;

        try {

            if (!f.exists()) {
                fos = new FileOutputStream(f, true);//al ponerlo en true, da error IOE
                oos = new ObjectOutputStream(fos);
                oos.writeObject(n1);
                oos.writeObject(n2);
            } else {
                fos = new FileOutputStream(f, true);//al ponerlo en true, da error IOE
                myoos = new MyOOS(fos);
                myoos.writeObject(n1);
                myoos.writeObject(n2);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos != null) {
                    oos.close();
                }
                if (myoos != null) {
                    myoos.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero.");
                e.printStackTrace();
            }
            System.out.println("Finalizando el programa.");
        }
    }
}
