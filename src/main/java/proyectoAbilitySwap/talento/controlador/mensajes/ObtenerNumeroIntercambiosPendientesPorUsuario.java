package proyectoAbilitySwap.talento.controlador.mensajes;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyectoAbilitySwap.talento.servicio.NumeroIntercambiosPendientesPorUsuarioService;

import java.io.IOException;

import org.apache.log4j.Logger;

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
		NumeroIntercambiosPendientesPorUsuarioService intercambiosPendientesPorUsuarioService = new NumeroIntercambiosPendientesPorUsuarioService();
		HttpSession httpSession = request.getSession(false);
		Integer integerUsuario = (Integer) httpSession.getAttribute("idusuario");
		int idusuario = integerUsuario != null ? integerUsuario : 0;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
