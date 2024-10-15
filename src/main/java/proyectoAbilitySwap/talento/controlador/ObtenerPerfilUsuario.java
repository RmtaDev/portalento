package proyectoAbilitySwap.talento.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyectoAbilitySwap.talento.beans.Usuario;
import proyectoAbilitySwap.talento.servicio.UsuarioService;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

/**
 * Servlet implementation class ObtenerPerfilUsuario
 */
public class ObtenerPerfilUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("mylog");	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenerPerfilUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("GET ObtenerPerilUsuario");
		
		try {
			
			//request.getSession();//NO porque una sesión
			HttpSession httpSession =  request.getSession(false);
			if (httpSession!=null)
			{
				log.debug("El usuario tiene una sesión válida");
				int idusuario = (int) request.getAttribute("idusuario");
				UsuarioService usuarioService = new UsuarioService();
				Usuario usuario = usuarioService.leerUsuarioPorId(idusuario);
				Gson gson = new Gson();
				String usuarioJson = gson.toJson(usuario);
				response.getWriter().write(usuarioJson);
				response.setStatus(200);
				response.setContentType("application/json");
				log.debug("Petición con 200 correcta info usuario " + usuario.toString());
				
			} else {
				log.debug("El usuario NO tiene una sesión válida");
				response.setStatus(401);//NO AUTORIZADO
			}
			
			
		} catch (Exception e) {
			log.error("Error en ObtnerPerfil", e);
			response.setStatus(500);
			
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
