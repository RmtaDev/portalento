package proyectoAbilitySwap.talento.servicio;

import java.sql.SQLException;

import proyectoAbilitySwap.talento.beans.CrearIntercambio;
import proyectoAbilitySwap.talento.repository.CrearIntercambioRepository;

public class CrearIntercambioService {

	public int insertarIntercambio(CrearIntercambio crearIntercambio) throws SQLException {

		int idNuevoIntercambio = -1;
		CrearIntercambioRepository crearIntercambioRepository = new CrearIntercambioRepository();
		
		idNuevoIntercambio = crearIntercambioRepository.insertarIntercambio(crearIntercambio);
		
		return idNuevoIntercambio;
	}
}
