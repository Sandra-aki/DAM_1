package SERIALIZAR;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
//import java.io.OutputStream;

public class MyOOS extends ObjectOutputStream {

    public MyOOS(FileOutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {//la cabecera es similar a las columnas. La definicion de los atributos.
//        // do not write a header, but reset:
//        // this line added after another question
//        // showed a problem with the original
//        reset();
        System.out.println("No escribo cabecera");
    }
}
//    public MyOOS(OutputStream out) throws IOException {
//        super(out);
//    }
