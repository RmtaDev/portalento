package proyectoAbilitySwap.talento.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoAbilitySwap.talento.beans.Usuario;
import talento.dni.Dni;
import talento.dni.servicio.DniService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

/**
 * Servlet implementation class ObtenerUsuariosPorHabilidad
 */
public class ObtenerUsuariosPorHabilidad extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("mylog");	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenerUsuariosPorHabilidad() {
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
		log.debug("Peticion en el Servlet ServletListadoDni");
		HabilidadesService habilidadesService = new HabilidadesService();// HabilidadesService seria el servicio de habilidades que esta haciendo Carlos

		try {
			List<Usuario> listaUsuarios =  habilidadesService.listadoUsuariosPorHabilidad(habilidad);//definir ListadoUsuariosPorHabilidad en el servicio de habilidades
																									 //habilidad seria la que haya hecho click el usuario que me la tendrian que pasar
			Gson gson = new Gson();
			String listaJsonUsuarios = gson.toJson(listaUsuarios);
			response.getWriter().write(listaJsonUsuarios);
			response.setStatus(200);
			response.setContentType("application/json");
			log.debug("La petición fue bien " + listaJsonUsuarios);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Ha habido un error de base de datos", e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Ha habido un error general", e);
		}
	}
}
