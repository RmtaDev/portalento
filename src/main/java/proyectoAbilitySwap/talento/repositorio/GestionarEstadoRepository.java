package proyectoAbilitySwap.talento.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import proyectoAbilitySwap.talento.beans.CrearIntercambio;

public class GestionarEstadoRepository {

	
	private static final String ACTUALIZAR_ESTADO_INTERCAMBIO = 
			"UPDATE `abilityswapbd`.`intercambios` SET `idintercambio` = ? WHERE `usuario_demandada` = ? AND `estado` = ?;";
	
	public boolean actualizarEstado(int idusuario, int idIntercambio, CrearIntercambio.EstadoIntercambio nuevoEstado) throws SQLException {
		Connection connection = Pool.getConnection();
		
		PreparedStatement ps = connection.prepareStatement(ACTUALIZAR_ESTADO_INTERCAMBIO);
		ps.setInt(1, idIntercambio);
		ps.setInt(2, idusuario);
		ps.setString(3, nuevoEstado.toString());
		
		
		
	
		int filasAfectadas = ps.executeUpdate();
		Pool.liberarRecursos(connection, ps, null);
		
		return filasAfectadas > 0;
		
	}
}
