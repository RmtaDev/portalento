package proyectoAbilitySwap.talento.controlador;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import proyectoAbilitySwap.talento.beans.Usuario;
import proyectoAbilitySwap.talento.servicio.UsuarioService;
import proyectoAbilitySwap.talento.validacion.ValidarAltaNuevoUsuario;

/**
 * Servlet implementation class AltaNuevoUsuario
 */

@MultipartConfig // para recibir info de formulario y ficheros
public class AltaNuevoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AltaNuevoUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String usuario = request.getParameter("usuario");// tiene que coincidir con el atributo name del formulario
		String password = request.getParameter("password");
		Part filePart = request.getPart("fichero");
		
		String confirmPassword = request.getParameter("confirmPassword");

		// Usa la clase de validación
		if (!ValidarAltaNuevoUsuario.validarNombreUsuario(usuario)) {
			request.setAttribute("error", "El nombre de usuario debe tener al menos 5 caracteres.");
			request.getRequestDispatcher("/error.html").forward(request, response);
			return;
		}

		if (!ValidarAltaNuevoUsuario.validarPassword(password)) {
			request.setAttribute("error", "La contraseña debe tener al menos 8 caracteres y un número.");
			request.getRequestDispatcher("/error.html").forward(request, response);
			return;
		}

		if (!ValidarAltaNuevoUsuario.validarConfirmacionPassword(password, confirmPassword)) {
			request.setAttribute("error", "Las contraseñas no coinciden.");
			request.getRequestDispatcher("/error.html").forward(request, response);
			return;
		}

		byte[] foto = filePart.getInputStream().readAllBytes();
		String fileName = filePart.getSubmittedFileName();
		System.out.println("Fichero subido = " + fileName);

		Usuario nuevoUsuario = new Usuario(0, usuario, password, foto);

		UsuarioService usuarioService = new UsuarioService();

		try {
			usuarioService.insertarUsuario(nuevoUsuario);
			/*una linea*/request.getRequestDispatcher("/bienvenida.html").forward(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			/*dos linea*/request.setAttribute("error", "Error al registrar el usuario. Inténtalo de nuevo.");
			
			// redirecciona a la página error para mostrar al usuario, informandole sobre el problema que ocurrio
			/*tres linea*/request.getRequestDispatcher("/error.html").forward(request, response);
		}
	}
}
