package FICHEROS_BINARIOS;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Guardar {

    public static void main(String[] args) {
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {
            fos = new FileOutputStream("./ejemplobinario.data", false);
            dos = new DataOutputStream(fos);

            N n1 = new N();
            N n2 = new N(false, 'B', 20, 18.32, "Adios");

            dos.writeBoolean(n1.isB());
            dos.writeChar(n1.getC());
            dos.writeInt(n1.getN());
            dos.writeDouble(n1.getD());
            dos.writeUTF(n1.getS());

            dos.writeBoolean(n2.isB());
            dos.writeChar(n2.getC());
            dos.writeInt(n2.getN());
            dos.writeDouble(n2.getD());
            dos.writeUTF(n2.getS());

        } catch (FileNotFoundException e) {
            System.out.println("Error al abrir el Fichero");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
