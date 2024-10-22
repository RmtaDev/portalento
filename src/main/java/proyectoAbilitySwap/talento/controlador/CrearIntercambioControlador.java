package proyectoAbilitySwap.talento.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoAbilitySwap.talento.beans.CrearIntercambio;
import proyectoAbilitySwap.talento.beans.CrearIntercambio.EstadoIntercambio;
import proyectoAbilitySwap.talento.servicio.CrearIntercambioService;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class CrearIntercambio
 */
public class CrearIntercambioControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("mylog");
	private CrearIntercambioService crearIntercambioService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearIntercambioControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		log.debug("### -> Solicitud en camino: el Servlet CrearIntercambio está en acción.");
		int usuarioOfertada = Integer.parseInt(request.getParameter("usuarioOfertada"));
		int usuarioDemandada = Integer.parseInt(request.getParameter("usuarioDemandada"));
		int habilidadOfertada = Integer.parseInt(request.getParameter("habilidadOfertada"));
		int habilidadDemandada = Integer.parseInt(request.getParameter("habilidadDemandada"));
		EstadoIntercambio estado = EstadoIntercambio.valueOf(request.getParameter("estado").toUpperCase());
		
		log.info("### -> Datos recibidos: Usuario Ofertada = " + usuarioOfertada + ", Usuario Demandada = " + usuarioDemandada + ", Habilidad Ofertada = " + habilidadOfertada + ", Habilidad Demandada = " + habilidadDemandada + ", estado = " + estado);
		
		CrearIntercambio intercambio = new CrearIntercambio(0, usuarioOfertada, usuarioDemandada, habilidadOfertada, habilidadDemandada, estado);
		
		try {
			int idIntercambio = crearIntercambioService.insertarIntercambio(intercambio);
			response.setStatus(201);
			
		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(500);
		}
		
		//doGet(request, response);
	}

}
