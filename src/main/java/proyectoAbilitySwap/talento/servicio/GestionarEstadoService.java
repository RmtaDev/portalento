package proyectoAbilitySwap.talento.servicio;

import java.sql.SQLException;

import proyectoAbilitySwap.talento.beans.CrearIntercambio;
import proyectoAbilitySwap.talento.repositorio.GestionarEstadoRepository;

public class GestionarEstadoService {

	private GestionarEstadoRepository estadoRepository = new GestionarEstadoRepository();
	
	public boolean actualizarEstado(int idUsuario, int idIntercambio, CrearIntercambio.EstadoIntercambio nuevoEstado) throws SQLException {
		
		boolean estadoActual = estadoRepository.actualizarEstado(idUsuario, idIntercambio, nuevoEstado);
		
		return estadoActual;
	}
	
}
