package proyectoAbilitySwap.talento.controlador.habilidades;

import java.io.IOException;
import java.sql.SQLException;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyectoAbilitySwap.talento.beans.HabilidadDemandada;
import proyectoAbilitySwap.talento.servicio.HabilidadesService;

public class InsertarHabilidadesDemandadas extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public InsertarHabilidadesDemandadas() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//TODO 1 mejorar porque los datos deberían venir en un JSON en el BODY por ser POST
    	//TODO 2 el id de usuario no se envía desde el cliente. Lo sacamos de la sesión (comprobar primero)
    	HttpSession session = request.getSession(false);
    	int idUsuario = 0;
    	if (session!=null)
    	{
    		idUsuario = (int)session.getAttribute("idusuario");
    		String habilidadDemandada = request.getParameter("habilidadDemandada");
            int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));

            // 2. Llamar al servicio para procesar la lógica
            HabilidadesService habilidadesService = new HabilidadesService();
            try {
                // 3. Realizar la operación de inserción usando el servicio
                HabilidadDemandada hd = habilidadesService.insertarHabilidadDemandada(habilidadDemandada, idUsuario, idCategoria);
                Gson gson = new Gson();
                String jsonhd = gson.toJson(hd);
                // 4. Enviar una respuesta de éxito al frontend
                response.setStatus(201); // 201 Created, la habilidad demandada fue creada
                response.setContentType("application/json");
                response.getWriter().write(jsonhd);
            } catch (SQLException e) {
                e.printStackTrace();
                // 5. Enviar una respuesta de error al frontend
                response.setStatus(500); // Error en el servidor
            }
    	} else {
    		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
    	}
    	
    	
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lógica opcional para manejar GET si es necesario, de lo contrario puedes dejarlo vacío o redirigir a POST
        response.setStatus(405); // 405 Method Not Allowed si no permites GET para inserciones
    }
}

