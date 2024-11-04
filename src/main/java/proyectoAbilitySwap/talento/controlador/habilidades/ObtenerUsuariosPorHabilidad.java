package proyectoAbilitySwap.talento.controlador.habilidades;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyectoAbilitySwap.talento.beans.Usuario;
import proyectoAbilitySwap.talento.servicio.HabilidadesService;

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
		log.debug("Peticion en el Servlet ObtenerUsuariosPorHabilidad");
		HabilidadesService habilidadesServiceAntonio = new HabilidadesService();// HabilidadesService seria el servicio de habilidades que esta haciendo Carlos
		String habilidad = request.getParameter("habilidad");
		HttpSession httpSession = request.getSession(false);
		Integer integerUsuario = (Integer) httpSession.getAttribute("idusuario");
		int idusuario = integerUsuario != null ? integerUsuario : 0;
		
		try {
			List<Usuario> listaUsuarios =  habilidadesServiceAntonio.listadoUsuariosPorHabilidad(habilidad, idusuario);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
