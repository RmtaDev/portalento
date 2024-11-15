package proyectoAbilitySwap.talento.controlador.mensajes;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyectoAbilitySwap.talento.beans.HabilidadOfertada;
import proyectoAbilitySwap.talento.beans.Mensaje;
import proyectoAbilitySwap.talento.servicio.HabilidadesService;
import proyectoAbilitySwap.talento.servicio.MensajeService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

/**
 * Servlet implementation class InsertarMensaje
 */
public class InsertarMensaje extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("mylog");
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarMensaje() {
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
		log.debug("Peticion en el Servlet InsertarMensaje");
		
		try {
			String mensajePeticionJson =  request.getReader().readLine();
			Gson gson = new Gson();
			Mensaje mensaje = gson.fromJson(mensajePeticionJson, Mensaje.class);

			int idintercambio = mensaje.getIdintercambio();
			
			HttpSession httpSession = request.getSession(false);
			int idusuario = (int) httpSession.getAttribute("idusuario");
			int emisor = idusuario;
			int receptor = mensaje.getReceptor();
			LocalDateTime fecha_hora = LocalDateTime.parse(mensaje.getFechaHora(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
			String texto = mensaje.getTexto();
			
			MensajeService mensajeService = new MensajeService();
			Mensaje mensajeRespuesta = mensajeService.insertarMensaje(idintercambio, emisor, receptor, fecha_hora, texto);

	        String mensajeResputestaJson = gson.toJson(mensajeRespuesta);
            response.setStatus(201); // 201 Created, el mensaje fue creado
            response.setContentType("application/json");
            response.getWriter().write(mensajeResputestaJson);
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Hubo un error");
			response.setStatus(500);
			log.error("500 Hubo un error", e);
		}

	}
}
