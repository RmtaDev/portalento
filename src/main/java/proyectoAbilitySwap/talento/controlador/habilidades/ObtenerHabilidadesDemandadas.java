package proyectoAbilitySwap.talento.controlador.habilidades;


import jakarta.servlet.ServletException;
import org.apache.log4j.Logger;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyectoAbilitySwap.talento.beans.HabilidadDemandada;
import proyectoAbilitySwap.talento.beans.HabilidadOfertada;
import proyectoAbilitySwap.talento.servicio.HabilidadesService;

import java.io.IOException;
import java.util.List;


import com.google.gson.Gson;
import com.mysql.cj.log.Log;

/**
 * Servlet implementation class ObtenerHabilidadesDemandadas
 */
public class ObtenerHabilidadesDemandadas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("mylog");
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenerHabilidadesDemandadas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.debug("doGet HabilidadesDemandadas");
		
		
		 
		try {
			 HttpSession httpSession = request.getSession(false);
			 	
			 	if (httpSession != null) {
			 		
			 		
			 		int idUsuario = 3;//(int)httpSession.getAttribute("idusuario");
			 		HabilidadesService habilidadesService = new HabilidadesService();
			 		List<HabilidadDemandada> listaHabilidades = habilidadesService.consultarTodasDemandadas(idUsuario);
			 		log.debug("lista de habilidades recibidas " + listaHabilidades);
			 		Gson gson = new Gson();
					String listaJson = gson.toJson(listaHabilidades);
					response.getWriter().write(listaJson);
			 		response.setStatus(200);
			 		response.setContentType("application/json");
			 		
			 	} else {
			 		
			 		log.debug("No tiene sesión");
			 		response.setStatus(401);
			 		
			 	}
			
		} catch (Exception e) {
			log.error("Error en obtener habilidades ofertadas" , e);
			response.setStatus(500);
		}
		
	}

}
