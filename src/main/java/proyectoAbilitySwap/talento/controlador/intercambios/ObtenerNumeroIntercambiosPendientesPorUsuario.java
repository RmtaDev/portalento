package proyectoAbilitySwap.talento.controlador.intercambios;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyectoAbilitySwap.talento.beans.Usuario;
import proyectoAbilitySwap.talento.servicio.IntercambioService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

/**
 * Servlet implementation class ObtenerNumeroIntercambiosPendientesPorUsuario
 */
public class ObtenerNumeroIntercambiosPendientesPorUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("mylog");
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenerNumeroIntercambiosPendientesPorUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("Peticion en el Servlet ObtenerNumeroIntercambiosPendientesPorUsuario");
		IntercambioService intercambioService = new IntercambioService();
		HttpSession httpSession = request.getSession(false);
		int idusuario = (int) httpSession.getAttribute("idusuario");

		try {
			int noIntercambiosPendientes =  intercambioService.noIntercambiosPendientesPorUsuario(idusuario);
			String stringNoIntercambiosPendientes = Integer.toString(noIntercambiosPendientes);
			response.getWriter().write(stringNoIntercambiosPendientes);
			response.setStatus(200);
			response.setContentType("text/plain");
			log.debug("La petición fue bien " + stringNoIntercambiosPendientes);
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
