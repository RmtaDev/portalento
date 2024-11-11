package proyectoAbilitySwap.talento.controlador.mensajes;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoAbilitySwap.talento.beans.HabilidadOfertada;
import proyectoAbilitySwap.talento.beans.Mensaje;
import proyectoAbilitySwap.talento.beans.Usuario;
import proyectoAbilitySwap.talento.servicio.BuscadorHabService;
import proyectoAbilitySwap.talento.servicio.HabilidadesService;
import proyectoAbilitySwap.talento.servicio.MensajeService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mysql.cj.log.Log;

/**
 * Servlet implementation class MensajeServlet
 */
public class ConsultarMensajes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("mylog");
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarMensajes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("Peticion en el Servlet ConsultarMensajesServlet");
		MensajeService mensajeService = new MensajeService();

		int idintercambio = Integer.parseInt(request.getParameter("idintercambio"));
		try {
			List<Mensaje> listaMensajes =  mensajeService.ConsultarMensajesPorIntercambio(idintercambio);
			Gson gson = new Gson();
			String listaMensajesJSON = gson.toJson(listaMensajes);
			response.getWriter().write(listaMensajesJSON);
			response.setStatus(200);
			response.setContentType("application/json");
			log.debug("La petición fue bien " + listaMensajesJSON);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Ha habido un error de base de datos", e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Ha habido un error general", e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
