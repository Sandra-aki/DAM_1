package MODELO;

public class RedSocial {

    private String nombre;
    private String url;
    private String fechaLanzamiento;

    public RedSocial(String nombre, String url, String fechaLanzamiento) {
        this.nombre = nombre;
        this.url = url;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    @Override
    public String toString() {
        return "RedSocial{" + "nombre=" + nombre + ", url=" + url + ", fechaLanzamiento=" + fechaLanzamiento + '}';
    }

}
//Sacar el pais con mas y menos usuarios. Su metodo escupe un mapa -> Con pais y 
/*
Sacar el país con más usuarios y con menos usuarios: se mete en el jdbcUsuario (método gentePaisesNum(): va a sacar un mapa con el país con más usuarios y el país con menos usuarios)
Sacar el usuario más joven y el más mayor de una red social.
Sacar por pantalla a la persona más joven y a la más mayor de cada rrss y eliminarla.

SELECT pais, COUNT(*) AS numero
FROM usuario
GROUP BY pais
HAVING COUNT() = (SELECT MAX(nmax) FROM (SELECT COUNT() AS nmax FROM usuario GROUP BY pais) AS numax)
   OR COUNT() = (SELECT MIN(nmin) FROM (SELECT COUNT() AS nmin FROM usuario GROUP BY pais) AS numin)
ORDER BY numero DESC;*/
