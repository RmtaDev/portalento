package proyectoAbilitySwap.talento.controlador.habilidades;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoAbilitySwap.talento.servicio.HabilidadesService;

public class BorrarHabilidadesOfertadas extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public BorrarHabilidadesOfertadas() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//TODO DEBERÍAMOS COMPROBAR QUE
    	//1. TIENE SESIÓN 
    	//2. EL USUARIO DE LA SESIÓN TIENE ASOCIADA ESE ID HABILIDAD
    	//ESTO protegería que un usuario distinto borre habilidades de otros
    	//lo suyo sería modificar la intrstucción de borrado para añadirle la restricción del id de usuario
    	//PENDIENTE. Caso especial
    	try {
            // 1. Recoge el parámetro "idHabilidad" que envía el cliente (por ejemplo, desde un formulario)
            String habilidadParam = request.getParameter("idHabilidad");

            // Si no se envía "idHabilidad", respondemos con un error 
            if (habilidadParam == null || habilidadParam.isEmpty()) {
                response.setStatus(400); // Código de error: solicitud incorrecta
                response.getWriter().write("El parámetro idHabilidad es necesario.");
                return; // Finaliza aquí si hay error
            }

            // Convertimos el parámetro a un número entero 
            int borrarHabilidad = Integer.parseInt(habilidadParam);

            // 2. Llamamos al servicio (una clase que se encarga de la lógica de negocio) para borrar la habilidad ofertada
            HabilidadesService habilidadesService = new HabilidadesService();
            habilidadesService.borrarHabilidadOfertada(borrarHabilidad);

            // 3. Si todo sale bien, respondemos con un código 204 que significa "se ha eliminado correctamente"
            response.setStatus(204); // Operación ok (no se necesita devolver más datos)
            
        } catch (NumberFormatException e) {
            // Si el parámetro "idHabilidad" no se puede convertir a número, respondemos con un error
        	
            response.setStatus(400); // Código de error: solicitud incorrecta
            response.getWriter().write("Formato inválido para idHabilidad.");
            
        } catch (SQLException e) {
            // Si ocurre un error de base de datos, devolvemos un error de servidor
        	
            e.printStackTrace(); // Imprime el error para que el desarrollador lo vea
            
            response.setStatus(500); // Código de error: problema en el servidor
            
            response.getWriter().write("Error al borrar la habilidad ofertada.");
        }
    }
}
