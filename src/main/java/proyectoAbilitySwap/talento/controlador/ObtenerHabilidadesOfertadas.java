package proyectoAbilitySwap.talento.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyectoAbilitySwap.talento.beans.HabilidadOfertada;
import proyectoAbilitySwap.talento.beans.Usuario;
import proyectoAbilitySwap.talento.service.HabilidadesService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

/**
 * Servlet implementation class ObtenerHabilidadesOfertadas
 */
public class ObtenerHabilidadesOfertadas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("mylog"); // para hacer log . dejar mensajes

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ObtenerHabilidadesOfertadas() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.debug("doGet HabilidadesOfertadas");
		try {
			 //HttpSession httpSession = request.getSession(false);
			 	/*
			 	if (httpSession != null) {
			 		*/
			 		log.debug("Tiene sesión");
			 		int idUsuario = 3;//(int)httpSession.getAttribute("idusuario");
			 		HabilidadesService habilidadesService = new HabilidadesService();
			 		List<HabilidadOfertada> listaHabilidades = habilidadesService.consultarTodasOfertadas(idUsuario);
			 		log.debug("lista de habilidades recibidas " + listaHabilidades);
			 		Gson gson = new Gson();
					String listaJson = gson.toJson(listaHabilidades);
					response.getWriter().write(listaJson);
			 		response.setStatus(200);
			 		response.setContentType("application/json");
			 		/*
			 	} else {
			 		
			 		log.debug("No tiene sesión");
			 		response.setStatus(401);
			 		
			 	}
			*/
		} catch (Exception e) {
			log.error("Error en obtener habilidades ofertadas" , e);
			response.setStatus(500);
		}
		
		
		 	
		
	}

}
