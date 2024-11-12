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
	private int id_habilidad_demandada;
	private String nombre_habilidad_demandada;
	private int id_usuario_demandada;
	private String nombre_usuario_demandada;
	private int id_habilidad_ofertada;
	private String nombre_habilidad_ofertada;			
	private EstadoIntercambio estado;
	
	
	public IntercambioJoined(int idIntercambio, int id_usuario_ofertada, String nombre_usuario_ofertada,
							 int id_habilidad_demandada, String nombre_habilidad_demandada, int id_usuario_demandada,
							 String nombre_usuario_demandada, int id_habilidad_ofertada, String nombre_habilidad_ofertada,
							 EstadoIntercambio estado) {
		super();
		this.idIntercambio = idIntercambio;
		this.id_usuario_ofertada = id_usuario_ofertada;
		this.nombre_usuario_ofertada = nombre_usuario_ofertada;
		this.id_habilidad_demandada = id_habilidad_demandada;
		this.nombre_habilidad_demandada = nombre_habilidad_demandada;
		this.id_usuario_demandada = id_usuario_demandada;
		this.nombre_usuario_demandada = nombre_usuario_demandada;
		this.id_habilidad_ofertada = id_habilidad_ofertada;
		this.nombre_habilidad_ofertada = nombre_habilidad_ofertada;
		this.estado = estado;
	}
	
	

}
