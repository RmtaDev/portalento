package proyectoAbilitySwap.talento.controlador;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyectoAbilitySwap.talento.beans.CrearIntercambio;
import proyectoAbilitySwap.talento.beans.CrearIntercambio.EstadoIntercambio;
import proyectoAbilitySwap.talento.servicio.CrearIntercambioService;

/**
 * Servlet implementation class CrearIntercambio
 */
public class CrearIntercambioControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("mylog");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CrearIntercambioControlador() {
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
			throws ServletException, IOException, NumberFormatException {

		String usuarioDemandadaParam = request.getParameter("usuarioDemandada");
		String habilidadOfertadaParam = request.getParameter("habilidadOfertada");
		String habilidadDemandadaParam = request.getParameter("habilidadDemandada");
		String estadoParam = request.getParameter("estado");

		log.debug("### Solicitud recibida en CreaIntercambioControlador.");

		HttpSession sesion = request.getSession(false);

		if (sesion == null) {
			log.warn("### No hay sesión activa. el usuario no esta auntenticado. ");
			response.setStatus(401);

		} else {
			Integer idUsuarioOfertada = (Integer) sesion.getAttribute("idusuario");

			if (idUsuarioOfertada == null) {
				log.warn("### Usuario ofertada no encontrado en la session. ");
				response.setStatus(400);

			} else {

				if (usuarioDemandadaParam == null || habilidadOfertadaParam == null || habilidadDemandadaParam == null
						|| usuarioDemandadaParam.isEmpty() || habilidadOfertadaParam.isEmpty()
						|| habilidadDemandadaParam.isEmpty()) {
					log.error("### Error: uno o mas paramteros son nulos.");
					response.setStatus(400);
					
				}else {
				try {
					int usuarioDemandada = Integer.parseInt(usuarioDemandadaParam);
					int habilidadOfertada = Integer.parseInt(habilidadOfertadaParam);
					int habilidadDemandada = Integer.parseInt(habilidadDemandadaParam);

					EstadoIntercambio estado = EstadoIntercambio.PENDIENTE;
					
					if (estadoParam != null && !estadoParam.isEmpty()) {
						try {
						estado = EstadoIntercambio.valueOf(estadoParam.toUpperCase());
						} catch(IllegalArgumentException e){
							log.warn("### Valor de estado no válido. Se asignará 'PENDIENTE' por defecto");
						}
					}

					log.info("### -> Datos recibidos: Usuario Ofertada = " + idUsuarioOfertada + ", Usuario Demandada = "
							+ usuarioDemandada + ", Habilidad Ofertada = " + habilidadOfertada
							+ ", Habilidad Demandada = " + habilidadDemandada + ", estado = " + estado);

					CrearIntercambio intercambio = new CrearIntercambio(0, idUsuarioOfertada, usuarioDemandada,
							habilidadOfertada, habilidadDemandada, estado);

					CrearIntercambioService crearIntercambioService = new CrearIntercambioService();

					int idIntercambio = crearIntercambioService.insertarIntercambio(intercambio);
					response.setStatus(201);
					log.info("### El intercambio nuevo ha sido insertado correctamente: " + idIntercambio);

				}catch (NumberFormatException e) {
					log.error("Error al convertir uno de los parametros a número:" + e.getMessage());
					response.setStatus(400);
				} catch (IllegalArgumentException e) {

					log.error("Error en los parametros recibidos: " + e.getMessage());
					response.setStatus(400);

				} catch (SQLException e) {

					log.error("Error al insertar el intercambio en la base de daos: " + e.getMessage(), e);
					e.printStackTrace();
					response.setStatus(500);
				}
			}
			}
		}
	}
}
