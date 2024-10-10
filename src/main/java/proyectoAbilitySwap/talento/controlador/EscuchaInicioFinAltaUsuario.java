package proyectoAbilitySwap.talento.controlador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class EscuchaInicioFinAltaUsuario
 *
 */
public class EscuchaInicioFinAltaUsuario implements ServletContextListener {
	public final static String RUTAS_FOTO = "fotosperfil";

    /**
     * Default constructor. 
     */
    public EscuchaInicioFinAltaUsuario() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
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
				System.out.println("ERROR AL CREA LA CARPETA");
			}
		} else {
			System.out.println("LA CARPETA YA EXISTE ");
			
		}
    }
	
}
