package FICHEROS_BINARIOS;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Leer {

    public static void main(String[] args) {
        File f = new File("./ejemplobinario.data");
        FileInputStream fis = null;
        DataInputStream dis = null;

        ArrayList<N> al = new ArrayList();

        try {
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);

            while (dis.available() > 0) {
                boolean b_aux = dis.readBoolean();
                char c_aux = dis.readChar();
                int n_aux = dis.readInt();
                double d_aux = dis.readDouble();
                String s_aux = dis.readUTF();

                N aux = new N(b_aux, c_aux, n_aux, d_aux, s_aux);

                al.add(aux);
            }

            for (N n : al) {
                System.out.println(n);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error, no se encuentra fichero");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                System.out.println("Error a la hora de cerrar los ficheros");
                System.out.println(e.toString());
                e.printStackTrace();
            }
        }
    }
}
