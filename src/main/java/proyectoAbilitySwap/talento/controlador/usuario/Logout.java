package proyectoAbilitySwap.talento.controlador.usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class Logout
 * servlet para cerrar la sesión
 */

public class Logout extends HttpServlet {
	
	private static Logger log = Logger.getLogger("mylog");
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("En Logout");
		HttpSession session =  request.getSession(false);
		if (session!=null)
		{
			log.debug("El usuario tiene sesión " +session.getId());
			session.invalidate();
			log.debug("Sesión invalidada");
			//le mandamos a login
			request.getRequestDispatcher("login.html").forward(request, response);
		} else {
			log.debug("El usuario NO tiene sesión. Le dirigimos a inicio ");
			//la mandamos a incio
			//request.getRequestDispatcher("login.html").forward(request, response);
			response.sendRedirect("inicio.html");
		}
		
	}


}
