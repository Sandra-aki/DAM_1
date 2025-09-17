package DAO.MODELO;

public class RedSocial {

    private String nombre;
    private String url;
    private byte[] logo;

    public RedSocial(String nombre, String url, byte[] logo) {
        this.nombre = nombre;
        this.url = url;
        this.logo = logo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrl() {
        return url;
    }

    public byte[] getLogo() {
        return logo;
    }
}
