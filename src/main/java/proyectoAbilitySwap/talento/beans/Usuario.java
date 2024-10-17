package proyectoAbilitySwap.talento.beans;

public class Usuario {
	private int idusuario;
	private String usuario;
	private String password;
	private String nombre;
	private String apellidos;
	private int edad;
	private String genero;
	private String telefono;
	private String email;
	private byte[] foto;
	private String rutaFoto;
	private String hablaSobreTi;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRutaFoto() {
		return rutaFoto;
	}

	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}

	public String getHablaSobreTi() {
		return hablaSobreTi;
	}

	public void setHablaSobreTi(String hablaSobreTi) {
		this.hablaSobreTi = hablaSobreTi;
	}

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

	public Usuario(int idusuario, String usuario, String password, String nombre, String apellidos, int edad,
			String genero, String telefono, String email, byte[] foto, String rutaFoto, String hablaSobreTi) {
		super();
		this.idusuario = idusuario;
		this.usuario = usuario;
		this.password = password;
		this.nombre = nombre;
		apellidos = apellidos;
		this.edad = edad;
		this.genero = genero;
		this.telefono = telefono;
		this.email = email;
		this.foto = foto;
		this.rutaFoto = rutaFoto;
		this.hablaSobreTi = hablaSobreTi;
	}

	public Usuario(int idusuario, String usuario, String password) {
		super();
		this.idusuario = idusuario;
		this.usuario = usuario;
		this.password = password;
	}

	
	public Usuario(int idusuario, String usuario, String password, String rutaFoto) {
		super();
		this.idusuario = idusuario;
		this.usuario = usuario;
		this.password = password;
		this.rutaFoto = rutaFoto;
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

	public Usuario(String usuario) {
		super();
		this.usuario = usuario;
	}

	

	public Usuario(int idusuario, String usuario, int edad, String genero, String rutaFoto) {
		super();
		this.idusuario = idusuario;
		this.usuario = usuario;
		this.edad = edad;
		this.genero = genero;
		this.rutaFoto = rutaFoto;
	}

	public Usuario() {
	}

	@Override
	public String toString() {
		return "Usuario [idusuario=" + idusuario + ", usuario=" + usuario + ", password=" + password + ", nombre="
				+ nombre + ", Apellidos=" + apellidos + ", edad=" + edad + ", genero=" + genero + ", telefono="
				+ telefono + ", email=" + email + ", rutaFoto=" + rutaFoto + ", hablaSobreTi=" + hablaSobreTi + "]";
	}
	
	
	
	
}