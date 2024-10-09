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
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String edad = request.getParameter("edad");
		int year = Integer.parseInt(edad);
		String genero = request.getParameter("genero");
		String telefono = request.getParameter("telefono");
		
		String email = request.getParameter("email");
		String rutaFoto = request.getParameter("rutaFoto");
		String hablaSobreTi = request.getParameter("hablaSobreTi");
		
		Part filePart = request.getPart("fichero");
		
		
		String confirmPassword = request.getParameter("confirmPassword");

		// Usa la clase de validación
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

		byte[] foto = filePart.getInputStream().readAllBytes();
		String fileName = filePart.getSubmittedFileName();
		System.out.println("Fichero subido = " + fileName);

		Usuario nuevoUsuario = new Usuario(0, usuario, password, nombre,  apellidos, year, genero, telefono, email,foto, rutaFoto, hablaSobreTi);

		UsuarioService usuarioService = new UsuarioService();

		try {
			usuarioService.insertarUsuario(nuevoUsuario);
			/*una linea*/request.getRequestDispatcher("/bienvenida.html").forward(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			/*dos linearequest.setAttribute("error", "Error al registrar el usuario. Inténtalo de nuevo.");
			*/
			System.out.println("error, error al registrar el usuario. intentelo de nuevo");
			
			// redirecciona a la página error para mostrar al usuario, informandole sobre el problema que ocurrio
			/*tres linearequest.getRequestDispatcher("/error.html").forward(request, response);
			*/
		}
	}
}
