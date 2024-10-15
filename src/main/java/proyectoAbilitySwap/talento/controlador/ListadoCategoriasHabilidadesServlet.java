package proyectoAbilitySwap.talento.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoAbilitySwap.talento.beans.Categoria;
import proyectoAbilitySwap.talento.servicio.CategoriasService;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

/**
 * Servlet implementation class ListadoHabilidadesServlet
 */
public class ListadoCategoriasHabilidadesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("mylog");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoCategoriasHabilidadesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		log.debug("Peticion GET en el servlet");
		CategoriasService categoriasService = new CategoriasService();
		
		try {
			List<Categoria> categorias = categoriasService.recuperarListadoCategorias();
			Gson gson = new Gson();
			String listaJsonCategorias = gson.toJson(categorias);
			response.getWriter().write(listaJsonCategorias);
			response.setStatus(200);
			response.setContentType("application/json");
			log.debug("La peticion fue bien " + listaJsonCategorias);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Ha fallado exitosamente la base de datos",e);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("la aplicacion ha fallado exitosamente", e);
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
