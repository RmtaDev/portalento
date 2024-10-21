package proyectoAbilitySwap.talento.beans;

public class CrearIntercambio {

	private int idIntercambio;
	private int usuarioOfertada;
	private int usuarioDemandada;
	private int habilidadOfertada;
	private int habilidadDemandada;
	private String estado;
	private EstadoIntercambio estda;

	public CrearIntercambio(int idIntercambio, int usuarioOfertada, int usuarioDemandada, int habilidadOfertada,
			int habilidadDemandada, String estado) {
		super();
		this.idIntercambio = idIntercambio;
		this.usuarioOfertada = usuarioOfertada;
		this.usuarioDemandada = usuarioDemandada;
		this.habilidadOfertada = habilidadOfertada;
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

	public int getHabilidadOfertada() {
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}