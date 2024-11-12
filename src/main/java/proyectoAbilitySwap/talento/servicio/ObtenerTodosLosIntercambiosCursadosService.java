package proyectoAbilitySwap.talento.servicio;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import proyectoAbilitySwap.talento.beans.CrearIntercambio;

import proyectoAbilitySwap.talento.repositorio.IntercambiosCursadosPorUsuarioRepository;


public class ObtenerTodosLosIntercambiosCursadosService {
	
private static Logger log = Logger.getLogger("mylog");
	
	public List<CrearIntercambio> IntercambiosCursadosPorUsuario(int idusuario) throws SQLException {
		log.debug("Peticion en Servicio noIntercambiosCursadosPorUsuario");
		List<CrearIntercambio> listaIntercambios = null;
		IntercambiosCursadosPorUsuarioRepository intercambiosCursadosPorUsuarioRepository = null;
		intercambiosCursadosPorUsuarioRepository = new IntercambiosCursadosPorUsuarioRepository();
		
		listaIntercambios = intercambiosCursadosPorUsuarioRepository.consultarNumeroIntercambiosCursadosPorUsuario(idusuario);
		//vamos por aquí
		log.debug("El servicio fue bien " + listaIntercambios);

		return listaIntercambios;
	}
}
