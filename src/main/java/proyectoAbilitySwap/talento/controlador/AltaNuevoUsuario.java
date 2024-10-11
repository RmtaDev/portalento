package proyectoAbilitySwap.talento.controlador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import proyectoAbilitySwap.talento.beans.Usuario;
import proyectoAbilitySwap.talento.servicio.UsuarioService;
import proyectoAbilitySwap.talento.validacion.Validar;

/**
 * Servlet implementation class AltaNuevoUsuario
 */

@MultipartConfig // para recibir info de formulario y ficheros
public class AltaNuevoUsuario extends HttpServlet {
	private static Logger log = Logger.getLogger("mylog");
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
		
		log.debug("### -> Peticion en el Servlet Login");
		
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
		byte[] foto = filePart.getInputStream().readAllBytes();
		
		
		String rutaFotoPerfil = EscuchaInicioFinAltaUsuario.RUTAS_FOTO +File.separator + new Date().getTime();
		
		Path fichero = Path.of(rutaFotoPerfil);
		Files.copy(filePart.getInputStream(), fichero, StandardCopyOption.REPLACE_EXISTING);
		String confirmPassword = request.getParameter("confirmPassword");
		
		Usuario nuevoUsuario = new Usuario(0, usuario, password, nombre,  apellidos, year, genero, telefono, email,foto, rutaFoto, hablaSobreTi);
		Validar validar = new Validar();
		if (validar.validarNombre(usuario) && validar.validarPassword(password) && validar.validarConfirmacionPassword(password, confirmPassword)){
			
			
			String fileName = filePart.getSubmittedFileName();
			System.out.println("Fichero subido = " + fileName);

			

			UsuarioService usuarioService = new UsuarioService();

			try {
				int idUsuario = usuarioService.insertarUsuario(nuevoUsuario);
				//TODO devolvemos 200:
				//crear la sesión, obtener el id del usuario
				HttpSession session = request.getSession(true);
				//y guardarla en la sesión
				session.setAttribute("idUsuario", idUsuario);
				
				response.setStatus(200);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
				//TODO devolver 500
				response.setStatus(500);
			}
			
		} else {
			//TODO: declarar log y comentar todo
			response.setStatus(400);//error en la petición
		}

		
		
	}
}
