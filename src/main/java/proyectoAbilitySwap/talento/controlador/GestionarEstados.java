package proyectoAbilitySwap.talento.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyectoAbilitySwap.talento.beans.CrearIntercambio.EstadoIntercambio;
import proyectoAbilitySwap.talento.servicio.GestionarEstadoService;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class GestionarEstados
 */
@WebServlet("/GestionarEstados")
public class GestionarEstados extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("mylog");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionarEstados() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		log.debug("Petición recibida en método POST para cambiar estado de intercambio");
		
		// Recuperamos la session del usuario
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("idusuario") == null) {
			
			log.warn("### No hay sesión activa o usuario no autenticado.");
			response.setStatus(401);
			
		} else {
			// Recuperamos el id de la sesión
			Integer idUsuarioSesión = (Integer) session.getAttribute("idusuario");
			log.debug("ID de usuario obtenido de la sesión: " + idUsuarioSesión);
			
			Gson gson = new Gson();
			JsonObject estadoJSON = gson.fromJson(request.getReader(), JsonObject.class);

			GestionarEstadoService estadoService = new GestionarEstadoService();
			try {

				int idIntercambio = estadoJSON.get("idIntercambio").getAsInt();
				String nuevoEstadoParam = estadoJSON.get("nuevoEstado").getAsString();
				// Validamos el estado reciido
				EstadoIntercambio nuevoEstado = null;
				
				if ("ACEPTADO".equals(nuevoEstadoParam)) {
					
					nuevoEstado = EstadoIntercambio.ACEPTADO;
					
				} else if ("RECHAZADO".equals(nuevoEstadoParam)) {
					
					nuevoEstado = EstadoIntercambio.RECHAZADO;
				}
				if (nuevoEstado != null) {
					
					log.debug("Intentando actualizar el estado del intercambio.");
					boolean actualizado = estadoService.actualizarEstado(idUsuarioSesión, idIntercambio, nuevoEstado);
					
					if (actualizado) {
						response.setStatus(200);
						log.info("Estado del intercambio con ID " + idIntercambio + " actualizado correctamente a " + nuevoEstado);
					} else {
						log.warn("No se encontró el intercambio con ID " + idIntercambio + " para el usuario " + idUsuarioSesión);
						response.setStatus(404);
					}
				} else {
					log.error("Estado no válido: " + nuevoEstadoParam);
					response.setStatus(400);
				}
			} catch (SQLException e) {
				log.error("Error en la base de datos al actualizar el estado del intercambio.", e);
				response.setStatus(500);
			} catch (Exception e) {
				log.error("Error inesperado al procesar la solicitud de actualización de estado.", e);
				response.setStatus(400);
			}
		}
	}
}
