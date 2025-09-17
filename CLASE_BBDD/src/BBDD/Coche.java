package BBDD;

public class Coche {

    private int id;
    private String marca;
    private float km;
    private char etiqueta;
    private boolean usado;

    public Coche(int id, String marca, float km, char etiqueta, boolean usado) {
        this.id = id;
        this.marca = marca;
        this.km = km;
        this.etiqueta = etiqueta;
        this.usado = usado;
    }

    public Coche(String marca, float km, char etiqueta, boolean usado) {
        this.marca = marca;
        this.km = km;
        this.etiqueta = etiqueta;
        this.usado = usado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getKm() {
        return km;
    }

    public void setKm(float km) {
        this.km = km;
    }

    public char getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(char etiqueta) {
        this.etiqueta = etiqueta;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    @Override
    public String toString() {
        return "Coche{" + "id=" + id + ", marca=" + marca + ", km=" + km + ", etiqueta=" + etiqueta + ", usado=" + usado + '}';
    }

}
