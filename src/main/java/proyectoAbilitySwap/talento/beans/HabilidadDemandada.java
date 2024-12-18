package proyectoAbilitySwap.talento.beans;

public class HabilidadDemandada {
	
	  private int idHabilidad;
	    private int usuario;
	    private String nombre;
	    private int idCategoria;
	    
		public int getIdHabilidad() {
			return idHabilidad;
		}
		
		public void setIdHabilidad(int idHabilidad) {
			this.idHabilidad = idHabilidad;
		}
		
		public int getUsuario() {
			return usuario;
		}
		
		public void setUsuario(int usuario) {
			this.usuario = usuario;
		}
		
		public String getNombre() {
			return nombre;
		}
		
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		public int getIdCategoria() {
			return idCategoria;
		}
		
		public void setIdCategoria(int idCategoria) {
			this.idCategoria = idCategoria;
		}

		public HabilidadDemandada(int idHabilidad, int usuario, String nombre, int idCategoria) {
			super();
			this.idHabilidad = idHabilidad;
			this.usuario = usuario;
			this.nombre = nombre;
			this.idCategoria = idCategoria;
		}

		@Override
		public String toString() {
			return "HabilidadDemandada [idHabilidad=" + idHabilidad + ", usuario=" + usuario + ", nombre=" + nombre
					+ ", idCategoria=" + idCategoria + "]";
		}
		
	    
	    
}
