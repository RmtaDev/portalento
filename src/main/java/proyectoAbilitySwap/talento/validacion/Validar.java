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
		
	public boolean validarConfirmacionPassword(String password, String confirmPassword) {

		boolean resultado = password != null && password.equals(confirmPassword);

		return resultado;
	}
}
