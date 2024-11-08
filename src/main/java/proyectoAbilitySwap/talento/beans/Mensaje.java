package proyectoAbilitySwap.talento.beans;

import java.time.LocalDateTime;

public class Mensaje {
    private int idmensaje;
    private int idintercambio;
    private int emisor;
    private int receptor;
    private LocalDateTime fecha_hora;
    private String texto;

    public Mensaje() {}


    // Constructor con parámetros
    public Mensaje(int idmensaje, int idintercambio, int emisor, int receptor, LocalDateTime fecha_hora, String texto) {
        this.idmensaje = idmensaje;

    public Mensaje(int idmensaje, String mensaje, int idintercambio, LocalDateTime fecha, int idusuario) {
        this.idmensaje = idmensaje;
        this.idintercambio = idintercambio;
        this.emisor = emisor;
        this.receptor = receptor;
        this.fecha_hora = fecha_hora;
        this.texto = texto;

    }


    
    // Getters y Setters
    public int getIdmensaje() {
        return idmensaje;
    }

    public void setIdmensaje(int idmensajes) {
        this.idmensaje = idmensaje;
    }

    public int getIdintercambio() {
        return idintercambio;
    }

    public void setIdintercambio(int idintercambio) {
        this.idintercambio = idintercambio;
    }

    public int getEmisor() {
        return emisor;
    }

    public void setEmisor(int emisor) {
        this.emisor = emisor;
    }

    public int getReceptor() {
        return receptor;
    }

    public void setReceptor(int emisor) {
        this.receptor = receptor;
    }
    
    public LocalDateTime getFechaHora() {
        return fecha_hora;
    }

    public void setFechaHora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }
    
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Mensaje [idmensaje=" + idmensaje + ", idintercambio=" + idintercambio + 
        		", emisor=" + emisor + ", receptor=" + receptor + 
        		", fecha_hora=" + fecha_hora + ", texto=" + texto + "]";
    }
}