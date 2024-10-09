package proyectoAbilitySwap.talento.validacion;

public class Validar {

	public boolean validarNombre(String nombre) {
		boolean valido = false;
			valido = ((null!=nombre)&&(nombre.length()>=4)&&(nombre.length()<=100));
		return valido;
	}

	public boolean validarPassword(String password) {
		boolean valido = false;
			valido = ((null!=password)&&(password.length()>=4)&&(password.length()<=50));
		return valido;
	}
		
	public boolean validarPasswordRegistro(String password1, String password2) {
		boolean valido = false;
			valido = ((password1!=null)&&(password1.equals(password2)));
		return valido;
	}
}
