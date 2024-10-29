package proyectoAbilitySwap.talento.controlador.usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyectoAbilitySwap.talento.controlador.EscuchaInicioFinApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ObtenerFoto
 */
public class ObtenerFoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("mylog");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ObtenerFoto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		InputStream is = null;
		int leido = 0;
		long total_leidos = 0;
		byte[] buffer_lectura_escritura = null;
		String idfoto = null;
		String rutaFoto = null;

		try {
			log.debug("Obteniendo foto");
			
			HttpSession sesion =  request.getSession(false);
			if (sesion!=null)
			{
				log.debug("Petición con sesión válida");
				// OBTENGO LA RUTA DE LA FOTO EN EL SERVIDOR
				idfoto = request.getParameter("idfoto");
				rutaFoto = EscuchaInicioFinApp.RUTAS_FOTO + File.separator + idfoto;

				is = new FileInputStream(rutaFoto);// FICHERO DE ENTRADA / LA IMAGEN
				log.debug("Leyendo fichero ... " + rutaFoto);

				buffer_lectura_escritura = new byte[1024];
				OutputStream os = response.getOutputStream();// FICHERO DE SALIDA / BODY RESPUESTA
				response.setContentType("img/jpeg");

				// VOY VOLCANDO LA ENTRADA (INPUTSTREAM) EN LA SALIDA (OUTPUTSTREAM)
				while ((leido = is.read(buffer_lectura_escritura)) != -1) {
					os.write(buffer_lectura_escritura, 0, leido);
					total_leidos = total_leidos + leido;
				}
				log.debug("fichero copia completada");

				response.setHeader("Content-Length", Long.toString(total_leidos));
				response.setStatus(200);

				is.close();
				os.flush();
			} else {
				log.debug("Petición sin sesión válida");
				response.setStatus(401);
			}
			
		
		} catch (Exception e) {
			log.error("Fallo leyendo la foto del perfil ", e);
			response.setStatus(500);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
