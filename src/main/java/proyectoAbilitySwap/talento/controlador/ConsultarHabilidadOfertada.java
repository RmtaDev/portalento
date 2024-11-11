package proyectoAbilitySwap.talento.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyectoAbilitySwap.talento.beans.HabilidadDemandada;
import proyectoAbilitySwap.talento.beans.HabilidadOfertada;
import proyectoAbilitySwap.talento.servicio.HabilidadesService;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

/**
 * Servlet implementation class ConsultarHabilidadOfertada
 */
public class ConsultarHabilidadOfertada extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarHabilidadOfertada() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1. Comprobar la sesión
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("idusuario") == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
                return;
            }

            // 2. Obtener el idUsuario
            int idUsuario = (int) session.getAttribute("idusuario");

            // 3. Llamar al servicio para obtener las habilidades ofertadas del usuario
            HabilidadesService habilidadesService = new HabilidadesService();
            List<HabilidadOfertada> listaHabilidades = habilidadesService.consultarTodasOfertadas(idUsuario);

            // 4. Convertir a JSON y enviar la respuesta
            String json = new Gson().toJson(listaHabilidades);
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK); // 200 OK
            response.getWriter().write(json);

        } catch (Exception e) {
            // 5. Si hay error, devolver 500 Internal Server Error
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }
}
