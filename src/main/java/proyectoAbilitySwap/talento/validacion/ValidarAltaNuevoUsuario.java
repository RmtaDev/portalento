package proyectoAbilitySwap.talento.validacion;

public class ValidarAltaNuevoUsuario {

	    // Método para validar el nombre de usuario
	    public static boolean validarNombreUsuario(String usuario) {
	    	boolean resultado = usuario != null && usuario.length() >= 5;
	        // El nombre debe tener al menos 5 caracteres
	        return resultado;
	    }

	    // Método para validar la contraseña
	    public static boolean validarPassword(String password) {
	    	
	    	 // Asegúrate de que la contraseña no sea nula y tenga al menos 8 caracteres
	    	boolean resultado = password != null && password.length() >= 8 && password.matches(".*\\d.*");
	    	
	    	return resultado;
	    }

	    // Método para validar la coincidencia de contraseñas 
	    //Si las contraseñas son idénticas y no nulas, retorna true.
	    public static boolean validarConfirmacionPassword(String password, String confirmPassword) {
	    	
	        boolean resultado = password != null && password.equals(confirmPassword);
	        
	    	return resultado;
	    }

}
