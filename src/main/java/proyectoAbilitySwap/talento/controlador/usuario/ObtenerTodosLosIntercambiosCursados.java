package proyectoAbilitySwap.talento.controlador.usuario;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyectoAbilitySwap.talento.beans.IntercambioJoined;
import proyectoAbilitySwap.talento.servicio.ObtenerTodosLosIntercambiosCursadosService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

/**
 * Servlet implementation class ObtenerTodosLosIntercambiosCursados
 */
public class ObtenerTodosLosIntercambiosCursados extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger("mylog");

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenerTodosLosIntercambiosCursados() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Petición en el Servlet ObtenerTodosLosIntercambiosCursados");
        ObtenerTodosLosIntercambiosCursadosService intercambiosService = new ObtenerTodosLosIntercambiosCursadosService();
        HttpSession httpSession = request.getSession(false);
        int idusuario = (int) httpSession.getAttribute("idusuario");
        try {
            List<IntercambioJoined> listaIntercambios = intercambiosService. IntercambiosCursadosPorUsuario(idusuario);
            Gson gson = new Gson();
            String listaIntercambiosJSON = gson.toJson(listaIntercambios);
            response.getWriter().write(listaIntercambiosJSON);
            response.setContentType("application/json");
            response.setStatus(200);
            log.debug("La petición fue exitosa. Lista de intercambios: " + listaIntercambiosJSON);
        } catch (SQLException e) {
            log.error("Error de base de datos", e);
            response.setStatus(404);
            response.getWriter().write("Error de base de datos");
        } catch (Exception e) {
            log.error("Error general", e);
            response.setStatus(500);
            response.getWriter().write("Error general en el servidor");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	doGet(request, response);
    }
}
