package proyectoAbilitySwap.talento.controlador;

import jakarta.servlet.Filter;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.apache.log4j.Logger;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * Servlet Filter implementation class FiltroInicio
 */
@WebFilter("/inicio.html")
public class FiltroInicio extends HttpFilter implements Filter {
       
	private static Logger log = Logger.getLogger("mylog");
    /**
     * @see HttpFilter#HttpFilter()
     */
    public FiltroInicio() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		log.debug("Accediendo a la página de inicio.html");
		//si tiene sesion, le mando a la página de perfil
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpSession httpSession =  httpServletRequest.getSession(false);
		if (httpSession!=null)
		{
			log.debug("El usuario ya tiene sesión. Le mandamos a perfil");
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			httpServletResponse.sendRedirect("perfil.html");
		} else {
			//si no, le dejo seguir
			log.debug("El usuario NO tiene sesión. Le dejo pasar a inicio.html");
			chain.doFilter(request, response);
			log.debug("Devolviendo la página de inicio.html");
	
		}
			}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
