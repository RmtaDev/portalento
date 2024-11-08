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
public class MensajeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MensajeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Log.debug("Peticion en el Servlet ObtenerMensajes");
		MensajeService mensajeService = new MensajeService();// HabilidadesService seria el servicio de habilidades que esta haciendo Carlos

		String mensaje = request.getParameter("mensaje");
		try {
			List<Mensaje> listamensajes =  mensajeService.listadoMensajes(mensaje);//definir ListadoUsuariosPorHabilidad en el servicio de habilidades
																									 		//habilidad seria la que haya hecho click el usuario que me la tendrian que pasar
			Gson gson = new Gson();
			String listaMensajes = gson.toJson(listaMensajes);
			response.getWriter().write(listaMensajes);
			response.setStatus(200);
			response.setContentType("application/json");
			Log.debug("La petición fue bien " + listaMensajes);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error("Ha habido un error de base de datos", e);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("Ha habido un error general", e);
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
