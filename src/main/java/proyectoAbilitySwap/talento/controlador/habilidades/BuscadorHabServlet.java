package proyectoAbilitySwap.talento.controlador.habilidades;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoAbilitySwap.talento.beans.HabilidadOfertada;
import proyectoAbilitySwap.talento.servicio.BuscadorHabService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

/**
 * Servlet implementation class BuscadorHabServlet
 */
public class BuscadorHabServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("mylog");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscadorHabServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String consulta = request.getParameter("consulta");
		log.debug("Peticion GET en el servlet con prefijo " + consulta);
		BuscadorHabService habilidadesService = new BuscadorHabService();
		
		try {
			List<HabilidadOfertada> habilidades = habilidadesService.recuperarListadoHabilidades(consulta);
			Gson gson = new Gson();
			String listaJsonHabilidades = gson.toJson(habilidades);
			response.getWriter().write(listaJsonHabilidades);
			response.setStatus(200);
			response.setContentType("application/json");
			log.debug("La peticion fue bien " + listaJsonHabilidades);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Ha fallado exitosamente la base de datos",e);
			response.setStatus(500);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("la aplicacion ha fallado exitosamente", e);
			response.setStatus(500);
		}
	}

	

}
