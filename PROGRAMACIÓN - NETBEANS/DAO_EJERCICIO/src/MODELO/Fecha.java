package MODELO;

public class Fecha implements Comparable<Fecha> {

    private int dia;
    private int mes;
    private int anio;

    // Constructor normal
    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    // Constructor String: "dd/MM/yyyy"
    public Fecha(String fecha) {
        String[] partes = fecha.split("-");
        this.dia = Integer.valueOf(partes[0]);
        this.mes = Integer.valueOf(partes[1]);
        this.anio = Integer.valueOf(partes[2]);
    }

    @Override
    public int compareTo(Fecha otra) {
        if (otra == null) {
            throw new NullPointerException("null");
        }
        if (otra == this) {
            return 0;
        }
        if (this.anio != otra.anio) {
            return Integer.compare(this.anio, otra.anio);
        }
        if (this.mes != otra.mes) {
            return Integer.compare(this.mes, otra.mes);
        }
        return Integer.compare(this.dia, otra.dia);
    }

//    @Override
//    public int compareTo(Fecha otra) {
//        if (this.anio != otra.anio) {
//            return Integer.compare(this.anio, otra.anio);
//        }
//        if (this.mes != otra.mes) {
//            return Integer.compare(this.mes, otra.mes);
//        }
//        return Integer.compare(this.dia, otra.dia);
//    }
    
    @Override
    public String toString() {
        return "Fecha{" + "dia=" + dia + ", mes=" + mes + ", anio=" + anio + '}';
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }
}
