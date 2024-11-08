package proyectoAbilitySwap.talento.beans;

public class CrearIntercambio {

	private int idIntercambio;
	private int usuarioOfertada;
	private int usuarioDemandada;
	private Integer habilidadOfertada;
	private int habilidadDemandada;
	private EstadoIntercambio estado;

	public CrearIntercambio(int idIntercambio, int usuarioOfertada, int usuarioDemandada, Integer habilidadOfertada,
			int habilidadDemandada, EstadoIntercambio estado) {
		super();
		this.idIntercambio = idIntercambio;
		this.usuarioOfertada = usuarioOfertada;
		this.usuarioDemandada = usuarioDemandada;
		this.habilidadOfertada = habilidadOfertada;
		this.habilidadDemandada = habilidadDemandada;
		this.estado = estado;
	}
	
	
	public CrearIntercambio(int idIntercambio, int usuarioOfertada, int usuarioDemandada, int habilidadDemandada,
			EstadoIntercambio estado) {
		super();
		this.idIntercambio = idIntercambio;
		this.usuarioOfertada = usuarioOfertada;
		this.usuarioDemandada = usuarioDemandada;
		this.habilidadOfertada = null;
		this.habilidadDemandada = habilidadDemandada;
		this.estado = estado;
	}


	public enum EstadoIntercambio {
        PENDIENTE,
        RECHAZADO,
        ACEPTADO;
    }

	public int getIdIntercambio() {
		return idIntercambio;
	}

	public void setIdIntercambio(int idIntercambio) {
		this.idIntercambio = idIntercambio;
	}

	public int getUsuarioOfertada() {
		return usuarioOfertada;
	}

	public void setUsuarioOfertada(int usuarioOfertada) {
		this.usuarioOfertada = usuarioOfertada;
	}

	public int getUsuarioDemandada() {
		return usuarioDemandada;
	}

	public void setUsuarioDemandada(int usuarioDemandada) {
		this.usuarioDemandada = usuarioDemandada;
	}

	public Integer getHabilidadOfertada() {
		return habilidadOfertada;
	}

	public void setHabilidadOfertada(int habilidadOfertada) {
		this.habilidadOfertada = habilidadOfertada;
	}

	public int getHabilidadDemandada() {
		return habilidadDemandada;
	}

	public void setHabilidadDemandada(int habilidadDemandada) {
		this.habilidadDemandada = habilidadDemandada;
	}

	public EstadoIntercambio getEstado() {
		return estado;
	}

	public void setEstado(EstadoIntercambio estado) {
		this.estado = estado;
	}

}
