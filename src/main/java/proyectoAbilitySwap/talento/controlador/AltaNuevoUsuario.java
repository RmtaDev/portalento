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

		log.debug("### -> Solicitud en camino: el Servlet AltaNuevoUsuario está en acción.");

		String usuario = request.getParameter("usuario");// tiene que coincidir con el atributo name del formulario
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String edad = request.getParameter("edad");
		int edadPersona = Integer.parseInt(edad);
		String genero = request.getParameter("genero");
		String telefono = request.getParameter("telefono");
		String email = request.getParameter("email");
		String rutaFoto = request.getParameter("rutaFoto");
		String hablaSobreTi = request.getParameter("hablaSobreTi");
		Part filePart = request.getPart("foto");

		log.info("### -> Datos recibidos: Usuario = " + usuario + ", Email = " + email);

		try {
			byte[] foto = filePart.getInputStream().readAllBytes();
			log.debug("###-> foto recibida con tamaño: " + foto.length + " bytes ");

			String rutaFotoPerfil = EscuchaInicioFinAltaUsuario.RUTAS_FOTO + File.separator + new Date().getTime();
			Path fichero = Path.of(rutaFotoPerfil);

			Files.copy(filePart.getInputStream(), fichero, StandardCopyOption.REPLACE_EXISTING);
			log.debug("### -> Foto guradada en: " + rutaFotoPerfil);

			Usuario nuevoUsuario = new Usuario(0, usuario, password, nombre, apellidos, edadPersona, genero, telefono, email,
					foto, rutaFoto, hablaSobreTi);
			
			Validar validar = new Validar();
			if (validar.validarNombre(usuario) && validar.validarPassword(password)
					&& validar.validarConfirmacionPassword(password, confirmPassword)) {

				log.debug("### -> Validacion de usuario exitosa");

				String fileName = filePart.getSubmittedFileName();

				log.debug("### -> Nombre del fichero: " + fileName);

				UsuarioService usuarioService = new UsuarioService();

				int idUsuario = usuarioService.insertarUsuario(nuevoUsuario);
				// TODO devolvemos 200:
				// crear la sesión, obtener el id del usuario
				HttpSession session = request.getSession(true);
				// y guardarla en la sesión
				session.setAttribute("idUsuario", idUsuario);

				response.setStatus(200);

				log.debug("### -> Usuario insrteado correctamente con ID: " + idUsuario);

			} else {
				// TODO: declarar log y comentar todo

				response.setStatus(400);// error en la petición
				log.warn("### error en la validacion de usuario: " + usuario);
			}
		} catch (SQLException e) {
			
			if (e instanceof java.sql.SQLIntegrityConstraintViolationException)
			{
				log.error("### -> Error SQL al insertar el usuario " + usuario, e);
				response.setStatus(404);
			} else {
				log.error("### -> Error SQL al insertar el usuario " + usuario, e);
				e.printStackTrace();
				// TODO devolver 500
				response.setStatus(500);
				
			}

			
		} catch (Exception e) {
			log.error("### -> Error inesperado al procesar la solicitud", e);
		}

	}
}
