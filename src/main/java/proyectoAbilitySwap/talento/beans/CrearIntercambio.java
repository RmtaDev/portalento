package proyectoAbilitySwap.talento.beans;

public class CrearIntercambio {

	private int idIntercambio;
	private Usuario usuarioOfertada;
	private Usuario usuarioDemandada;
	private HabilidadOfertada habilidadOfertada;
	private HabilidadDemandada habilidadDemandada;
	private String estado;
	
	
	public CrearIntercambio(int idIntercambio, Usuario usuarioOfertada, Usuario usuarioDemandada,
			HabilidadOfertada habilidadOfertada, HabilidadDemandada habilidadDemandada, String estado) {
		super();
		this.idIntercambio = idIntercambio;
		this.usuarioOfertada = usuarioOfertada;
		this.usuarioDemandada = usuarioDemandada;
		this.habilidadOfertada = habilidadOfertada;
		this.habilidadDemandada = habilidadDemandada;
		this.estado = estado;
	}


	public int getIdIntercambio() {
		return idIntercambio;
	}


	public void setIdIntercambio(int idIntercambio) {
		this.idIntercambio = idIntercambio;
	}


	public Usuario getUsuarioOfertada() {
		return usuarioOfertada;
	}


	public void setUsuarioOfertada(Usuario usuarioOfertada) {
		this.usuarioOfertada = usuarioOfertada;
	}


	public Usuario getUsuarioDemandada() {
		return usuarioDemandada;
	}


	public void setUsuarioDemandada(Usuario usuarioDemandada) {
		this.usuarioDemandada = usuarioDemandada;
	}


	public HabilidadOfertada getHabilidadOfertada() {
		return habilidadOfertada;
	}


	public void setHabilidadOfertada(HabilidadOfertada habilidadOfertada) {
		this.habilidadOfertada = habilidadOfertada;
	}


	public HabilidadDemandada getHabilidadDemandada() {
		return habilidadDemandada;
	}


	public void setHabilidadDemandada(HabilidadDemandada habilidadDemandada) {
		this.habilidadDemandada = habilidadDemandada;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}
