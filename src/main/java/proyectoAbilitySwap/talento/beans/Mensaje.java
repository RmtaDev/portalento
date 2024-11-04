package proyectoAbilitySwap.talento.beans;

import java.util.Date;

public class Mensaje {
    private int idmensajes;
    private String mensaje;
    private int idintercambio;
    private Date fecha;
    private int idusuario;

    public Mensaje() {}

    public Mensaje(int idmensajes, String mensaje, int idintercambio, Date fecha, int idusuario) {
        this.idmensajes = idmensajes;
        this.mensaje = mensaje;
        this.idintercambio = idintercambio;
        this.fecha = fecha;
        this.idusuario = idusuario;
    }

    public int getIdmensajes() {
        return idmensajes;
    }

    public void setIdmensajes(int idmensajes) {
        this.idmensajes = idmensajes;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getIdintercambio() {
        return idintercambio;
    }

    public void setIdintercambio(int idintercambio) {
        this.idintercambio = idintercambio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
    @Override
    public String toString() {
        return "Mensaje [idmensajes=" + idmensajes + ", mensaje=" + mensaje + ", idintercambio=" + idintercambio
                + ", fecha=" + fecha + ", idusuario=" + idusuario + "]";
    }

}

