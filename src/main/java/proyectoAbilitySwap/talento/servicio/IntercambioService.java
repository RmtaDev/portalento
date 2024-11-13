package proyectoAbilitySwap.talento.servicio;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import proyectoAbilitySwap.talento.beans.IntercambioJoined;
import proyectoAbilitySwap.talento.repositorio.IntercambioRepository;

public class IntercambioService {

	private static Logger log = Logger.getLogger("mylog");

	public List<IntercambioJoined> intercambiosCursadosPorUsuario(int idusuario) throws SQLException {
		log.debug("Peticion en Servicio noIntercambiosCursadosPorUsuario");
		List<IntercambioJoined> listaIntercambios = null;
		IntercambioRepository intercambiosRepository = null;
		intercambiosRepository = new IntercambioRepository();

		listaIntercambios = intercambiosRepository
				.consultarNumeroIntercambiosCursadosPorUsuario(idusuario);
		// vamos por aquí
		log.debug("El servicio fue bien " + listaIntercambios);

		return listaIntercambios;
	}


	public int noIntercambiosPendientesPorUsuario(int idusuario) throws SQLException {
		log.debug("Peticion en Servicio noIntercambiosPendientesPorUsuario");
		int noIntercambiosPendientes = 0;

		IntercambioRepository intercambioReposityory = new IntercambioRepository();

		noIntercambiosPendientes = intercambioReposityory
				.consultarNumeroIntercambiosPendientesPorUsuario(idusuario);
		log.debug("El servicio fue bien " + noIntercambiosPendientes);

		return noIntercambiosPendientes;
	}

}
