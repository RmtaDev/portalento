package proyectoAbilitySwap.talento.validacion;

import proyectoAbilitySwap.talento.beans.Usuario;

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
	    
	    public static boolean validarUsuario (Usuario usuario)
	    {
	    	boolean usuariovalido = false;
	    	
	    	//TODO llamar a los métodos de validar password, nombre...etc
	    	/**
	    	 * // Usa la clase de validación
		if (!ValidarAltaNuevoUsuario.validarNombreUsuario(usuario)) {
			//request.setAttribute("error", "El nombre de usuario debe tener al menos 5 caracteres.");
			System.out.println("error, elnombre de ususarui deber tener al menos 5 caracteres");
			
			//request.getRequestDispatcher("/error.html").forward(request, response);
			return;
		}

		if (!ValidarAltaNuevoUsuario.validarPassword(password)) {
			//request.setAttribute("error", "La contraseña debe tener al menos 8 caracteres y un número.");
			System.out.println("error, La contraseña debe tener al menos 8 caracteres y un nombre.");
			
			//request.getRequestDispatcher("/error.html").forward(request, response);
			return;
		}

		if (!ValidarAltaNuevoUsuario.validarConfirmacionPassword(password, confirmPassword)) {
			//request.setAttribute("error", "Las contraseñas no coinciden.");
			System.out.println("error, Las contraseñas no coinciden");
			
			//request.getRequestDispatcher("/error.html").forward(request, response);
			return;
		}

	    	 */
	    	
	    	return usuariovalido;
	    }

}
