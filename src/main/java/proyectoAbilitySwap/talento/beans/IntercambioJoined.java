package proyectoAbilitySwap.talento.beans;

import proyectoAbilitySwap.talento.beans.CrearIntercambio.EstadoIntercambio;

public class IntercambioJoined {

	public enum EstadoIntercambio {
        PENDIENTE,
        RECHAZADO,
        ACEPTADO;
    }

	private int idIntercambio;
	private int id_usuario_ofertada;
	private String nombre_usuario_ofertada;
	private String ruta_foto_ofertada;
	private int id_habilidad_demandada;
	private String nombre_habilidad_demandada;
	private int id_usuario_demandada;
	private String nombre_usuario_demandada;
	private String ruta_foto_demandada;
	private int id_habilidad_ofertada;
	private String nombre_habilidad_ofertada;			
	private EstadoIntercambio estado;
	
	
	
	
	
	
	public IntercambioJoined(int idIntercambio, int id_usuario_ofertada, String nombre_usuario_ofertada,
			String ruta_foto_ofertada, int id_habilidad_demandada, String nombre_habilidad_demandada, 
			int id_usuario_demandada, String nombre_usuario_demandada, String ruta_foto_demandada,
			int id_habilidad_ofertada, String nombre_habilidad_ofertada, EstadoIntercambio estado) {
		super();
		this.idIntercambio = idIntercambio;
		this.id_usuario_ofertada = id_usuario_ofertada;
		this.nombre_usuario_ofertada = nombre_usuario_ofertada;
		this.ruta_foto_ofertada = ruta_foto_ofertada;
		this.id_habilidad_demandada = id_habilidad_demandada;
		this.nombre_habilidad_demandada = nombre_habilidad_demandada;
		this.id_usuario_demandada = id_usuario_demandada;
		this.nombre_usuario_demandada = nombre_usuario_demandada;
		this.ruta_foto_demandada = ruta_foto_demandada;
		this.id_habilidad_ofertada = id_habilidad_ofertada;
		this.nombre_habilidad_ofertada = nombre_habilidad_ofertada;
		this.estado = estado;
	}
	public int getIdIntercambio() {
		return idIntercambio;
	}
	public void setIdIntercambio(int idIntercambio) {
		this.idIntercambio = idIntercambio;
	}
	public int getId_usuario_ofertada() {
		return id_usuario_ofertada;
	}
	public void setId_usuario_ofertada(int id_usuario_ofertada) {
		this.id_usuario_ofertada = id_usuario_ofertada;
	}
	public String getNombre_usuario_ofertada() {
		return nombre_usuario_ofertada;
	}
	public void setNombre_usuario_ofertada(String nombre_usuario_ofertada) {
		this.nombre_usuario_ofertada = nombre_usuario_ofertada;
	}
	public String getRuta_foto_ofertada() {
		return ruta_foto_ofertada;
	}
	public void setRuta_foto_ofertada(String ruta_foto_ofertada) {
		this.ruta_foto_ofertada = ruta_foto_ofertada;
	}
	public int getId_habilidad_demandada() {
		return id_habilidad_demandada;
	}
	public void setId_habilidad_demandada(int id_habilidad_demandada) {
		this.id_habilidad_demandada = id_habilidad_demandada;
	}
	public String getNombre_habilidad_demandada() {
		return nombre_habilidad_demandada;
	}
	public void setNombre_habilidad_demandada(String nombre_habilidad_demandada) {
		this.nombre_habilidad_demandada = nombre_habilidad_demandada;
	}
	public int getId_usuario_demandada() {
		return id_usuario_demandada;
	}
	public void setId_usuario_demandada(int id_usuario_demandada) {
		this.id_usuario_demandada = id_usuario_demandada;
	}
	public String getNombre_usuario_demandada() {
		return nombre_usuario_demandada;
	}
	public void setNombre_usuario_demandada(String nombre_usuario_demandada) {
		this.nombre_usuario_demandada = nombre_usuario_demandada;
	}
	public String getRuta_foto_demandada() {
		return ruta_foto_demandada;
	}
	public void setRuta_foto_demandada(String ruta_foto_demandada) {
		this.ruta_foto_demandada = ruta_foto_demandada;
	}
	public int getId_habilidad_ofertada() {
		return id_habilidad_ofertada;
	}
	public void setId_habilidad_ofertada(int id_habilidad_ofertada) {
		this.id_habilidad_ofertada = id_habilidad_ofertada;
	}
	public String getNombre_habilidad_ofertada() {
		return nombre_habilidad_ofertada;
	}
	public void setNombre_habilidad_ofertada(String nombre_habilidad_ofertada) {
		this.nombre_habilidad_ofertada = nombre_habilidad_ofertada;
	}
	public EstadoIntercambio getEstado() {
		return estado;
	}
	public void setEstado(EstadoIntercambio estado) {
		this.estado = estado;
	}
	
	
	
	

}
