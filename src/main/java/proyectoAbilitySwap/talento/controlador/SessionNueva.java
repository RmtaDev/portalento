package proyectoAbilitySwap.talento.controlador;

import org.apache.log4j.Logger;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionNueva
 *
 */
public class SessionNueva implements HttpSessionListener {

	private static Logger log = Logger.getLogger("mylog");

	/**
	 * Default constructor.
	 */
	public SessionNueva() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent se) {
		log.debug("NUEVA SESIÓN");
		se.getSession();
		// Este saco, lo tengo desde el principio
		ServletContext contexto = se.getSession().getServletContext();
		// voy contando las sesiones que se van creadnp
		if (contexto.getAttribute("numsesiones") != null) {
			log.debug("Ya tengo alguna sesión");
			int numsesiones = (int) contexto.getAttribute("numsesiones");
			numsesiones = numsesiones + 1;
			contexto.setAttribute("numsesiones", numsesiones);
		} else {
			log.debug("NO tengo ninguna sesión - primera creada");
			contexto.setAttribute("numsesiones", 1);

		}

	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		log.debug("Sesión Destruida");

		ServletContext contexto = se.getSession().getServletContext();
		int numsesiones = (int) contexto.getAttribute("numsesiones");
		numsesiones = numsesiones - 1;
		contexto.setAttribute("numsesiones", numsesiones);

	}

}
