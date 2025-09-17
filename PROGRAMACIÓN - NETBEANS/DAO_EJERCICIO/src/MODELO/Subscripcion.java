package MODELO;

import java.util.Date;

public class Subscripcion {

    private int usrId;
    private String nomRS;
    private Date fechaIncorp;

    @Override
    public String toString() {
        return "Suscripcion{" + "usrId=" + usrId + ", nomRS=" + nomRS + ", fechaIncorp=" + fechaIncorp + '}';
    }

    public Subscripcion(int usrId, String nomRS, Date fechaIncorp) {
        this.usrId = usrId;
        this.nomRS = nomRS;
        this.fechaIncorp = fechaIncorp;
    }

    public Subscripcion(String nomRS, Date fechaIncorp) {
        this.nomRS = nomRS;
        this.fechaIncorp = fechaIncorp;
    }

    public int getUsrId() {
        return usrId;
    }

    public void setUsrId(int usrId) {
        this.usrId = usrId;
    }

    public String getNomRS() {
        return nomRS;
    }

    public void setNomRS(String nomRS) {
        this.nomRS = nomRS;
    }

    public Date getFechaIncorp() {
        return fechaIncorp;
    }

    public void setFechaIncorp(Date fechaIncorp) {
        this.fechaIncorp = fechaIncorp;
    }

}
/*
Clase Subscripcion:
    - id_usuario (usrId)
    - id_redSocial (nomRS)
    - fecha_suscripcion

Subscripcion DAO:
6 metodos:
    + boolean suscribir(usr, rs); - > lanza Exception personalizada -> throws Duplicado
        al suscribir se hace select en el registro, si devuelve 0, INSERT y return true
        si es algo distinto a 0, es que ya estaba suscrito, se manda un sout con un mensaje
        de error con un Este usuario ya estaba suscrito desde (fecha) y se hace el throws de duplicado    
    + boolean desuscribir(usr, rs);
    + boolean desuscribir_todo(usr);
    + Obtener redsociales usuario -> <RS> de Usuarios:
        - m1(usr) -> Devuelve arrayList de RS
    + Obtener Usuarios red social -> <Usuarios> de RS
        - m2(rs) -> Devuelve arrayList de Usuarios
    + Obtener suscripciones -> ():
        - no entra nada y develve toda la tabla -> HashMap<RS,AL<Usr>>
 */
