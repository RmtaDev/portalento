package proyectoAbilitySwap.talento.controlador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.log4j.Logger;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class EscuchaInicioFinAltaUsuario
 *
 */
public class EscuchaInicioFinApp implements ServletContextListener {
	public final static String RUTAS_FOTO = "fotosperfil";
	
	private static Logger log = Logger.getLogger("mylog");	

    /**
     * Default constructor. 
     */
    public EscuchaInicioFinApp() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	log.debug("APLICACIÓN LEVANTADA... ABILITY SWAP");
     	// TODO Auto-generated method stub
      	 File carpetaFotos = new File(RUTAS_FOTO);
      	 
      	 if (!carpetaFotos.exists()) {
   			// creamos la carpeta
      		 Path path = Path.of(RUTAS_FOTO);
      		 try {
   				Files.createDirectories(path);
 
   			} catch (IOException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   				log.error("ERROR AL CREA LA CARPETA", e);
   			}
   		} else {
   			log.debug("LA CARPETA YA EXISTE ");
   			
   		}
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	log.debug("APLICACIÓN TERMINADA... bye ABILITY SWAP");
    }
	
}
