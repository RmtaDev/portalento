package proyectoAbilitySwap.talento.controlador;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoAbilitySwap.talento.service.HabilidadesService;

public class InsertarHabilidadesDemandadas extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public InsertarHabilidadesDemandadas() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Recoger los parámetros enviados desde el frontend (idUsuario, habilidadDemandada, idCategoria)
        String habilidadDemandada = request.getParameter("habilidadDemandada");
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));

        // 2. Llamar al servicio para procesar la lógica
        HabilidadesService habilidadesService = new HabilidadesService();
        try {
            // 3. Realizar la operación de inserción usando el servicio
            habilidadesService.insertarHabilidadDemandada(habilidadDemandada, idUsuario, idCategoria);

            // 4. Enviar una respuesta de éxito al frontend
            response.setStatus(201); // 201 Created, la habilidad demandada fue creada
        } catch (SQLException e) {
            e.printStackTrace();
            // 5. Enviar una respuesta de error al frontend
            response.setStatus(500); // Error en el servidor
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

