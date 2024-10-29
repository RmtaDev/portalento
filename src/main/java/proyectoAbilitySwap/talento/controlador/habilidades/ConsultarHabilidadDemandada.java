package proyectoAbilitySwap.talento.controlador.habilidades;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import proyectoAbilitySwap.talento.beans.HabilidadDemandada;
import proyectoAbilitySwap.talento.servicio.HabilidadesService;

import java.io.IOException;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Catch;

import com.google.gson.Gson;

/**
 * Servlet implementation class ConsultarHabilidadDemandada
 */
public class ConsultarHabilidadDemandada extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultarHabilidadDemandada() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.comprobar la sesión 1.1. Si no tienes sesión ---> no autorizado
		 * 
		 * 
		 * 
		 * 1.2. Si tiene sesión ---> al punto 2
		 * 
		 * 2. Tiene sesión -> obtengo el id del usuario
		 * 
		 * 3. Llamo al servicio para obtener el listado de ese usuario.
		 * habilidadesDemandadas
		 * 
		 * 4. Devuelvo la lista con un 200 al formao json
		 * 
		 * 5. Todo tiene que ir envuelto en un try - catch. Si surge una excepción en el
		 * catch hay que devolver 500
		 * 
		 * ------pasar esto a JaVA ---------
		 *
		 */
		// 1. Comprobar la sesión
		HttpSession session = request.getSession(false); // No crear nueva sesión si no existe

		try {

			if (session == null || session.getAttribute("idusuario") == null) {

				// 1.1. Si no tienes sesión ---> no autorizado
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
			} else {
				// 1.2. Si tiene sesión ---> al punto 2

				// 2. Tiene sesión -> obtengo el id del usuario
				int idUsuario = (int) session.getAttribute("idusuario");

				// 3. Llamo al servicio para obtener el listado de ese usuario.
				// habilidadesDemandadas
				HabilidadesService habilidadesService = new HabilidadesService();
				List<HabilidadDemandada> listaHabilidades = habilidadesService.consultarTodasDemandadas(idUsuario);

				// 4. Devuelvo la lista con un 200 en formato JSON
				Gson gson = new Gson();
				String json = gson.toJson(listaHabilidades);

				// Respuesta
				response.setContentType("application/json");
				response.setStatus(HttpServletResponse.SC_OK); // 200 OK
				response.getWriter().write(json);
			}
		} catch (Exception e) {
			// 5. Si surge una excepción en el catch hay que devolver 500

			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Internal Server Error
		}
	}
}