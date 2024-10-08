package proyectoAbilitySwap.talento.beans;

public class Usuario {
	private int idusuario;
	private String usuario;
	private String password;
	private byte[] foto;

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario(int idusuario, String usuario, String password) {
		super();
		this.idusuario = idusuario;
		this.usuario = usuario;
		this.password = password;
	}

	public Usuario(int idusuario, String usuario, String password, byte[] foto) {
		super();
		this.idusuario = idusuario;
		this.usuario = usuario;
		this.password = password;
		this.foto = foto;
	}

	public Usuario(byte[] foto) {
		super();
		this.foto = foto;
	}

	public Usuario() {
	}
}
