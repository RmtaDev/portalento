package proyectoAbilitySwap.talento.controlador.filtro;

import jakarta.servlet.Filter;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * Servlet Filter implementation class FiltroInicio
 */
@WebFilter("/Login")
public class FiltroLoginPerformance extends HttpFilter implements Filter {

	private static Logger log = Logger.getLogger("mylog");

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public FiltroLoginPerformance() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		log.debug("Accediendo al Login");
		// si tiene sesion, le mando a la página de perfil
		long t1 = System.currentTimeMillis();
		chain.doFilter(request, response);
		long t2 = System.currentTimeMillis();
		log.debug("A la vuelta del Login");
		long tiempoEmpleadoMs = t2-t1;
		log.debug("El proceso de login ha tardado " + tiempoEmpleadoMs + " milisegundos");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
