package proyectoAbilitySwap.talento.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import proyectoAbilitySwap.talento.repository.Pool;

public class CrearIntercambioRepository {
	public static final String INSERTAR_INTERCAMBIO = "INSERT INTO `abilityswapbd`.`intercambios` (`idintercambio`, `usuario_ofertada`, `usuario_demandada`, `habilidad_ofertada`, `habilidad_demandada`, `estado`) VALUES (?,?,?,?,?,?);";

	public int insertarIntercambio(CrearIntercambio crearIntercambio) throws SQLException {
		
		int idNuevo = -1;
		
		Connection connection = Pool.getConnection();
		PreparedStatement ps = connection.prepareStatement(INSERTAR_INTERCAMBIO);
		
		
		return idNuevo;
	}
}
