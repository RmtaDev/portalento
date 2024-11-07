package proyectoAbilitySwap.talento.servicio;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import proyectoAbilitySwap.talento.beans.Usuario;
import proyectoAbilitySwap.talento.repositorio.HabilidadesRepositoryAntonio;
import proyectoAbilitySwap.talento.repositorio.IntercambiosPendientesPorUsuarioRepository;

public class NumeroIntercambiosPendientesPorUsuarioService {

	private static Logger log = Logger.getLogger("mylog");
	
	public int noIntercambiosPendientesPorUsuario(int idusuario) throws SQLException {
		log.debug("Peticion en Servicio noIntercambiosPendientesPorUsuario");
		int noIntercambiosPendientes = 0;
		
		IntercambiosPendientesPorUsuarioRepository intercambiosPendientesPorUsuarioReposityory = new IntercambiosPendientesPorUsuarioRepository();
		
		noIntercambiosPendientes = intercambiosPendientesPorUsuarioReposityory.consultarNumeroIntercambiosPendientesPorUsuario(idusuario);
		log.debug("El servicio fue bien " + noIntercambiosPendientes);

		return noIntercambiosPendientes;
	}

}
