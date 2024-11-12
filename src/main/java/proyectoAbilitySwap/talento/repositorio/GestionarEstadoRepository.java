package proyectoAbilitySwap.talento.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import proyectoAbilitySwap.talento.beans.CrearIntercambio;

public class GestionarEstadoRepository {

	
	private static final String ACTUALIZAR_ESTADO_INTERCAMBIO = 
			"UPDATE `abilityswapbd`.`intercambios` SET `estado` = ? WHERE `idintercambio` = ? AND `usuario_demandada` = ?;";
	
	public boolean actualizarEstado(int idusuario, int idIntercambio, CrearIntercambio.EstadoIntercambio nuevoEstado) throws SQLException {
		Connection connection = Pool.getConnection();
		
		PreparedStatement ps = connection.prepareStatement(ACTUALIZAR_ESTADO_INTERCAMBIO);
		ps.setString(1, nuevoEstado.toString());
		ps.setInt(2, idIntercambio);
		ps.setInt(3, idusuario);
		
		int filasAfectadas = ps.executeUpdate();
		Pool.liberarRecursos(connection, ps, null);
		
		return filasAfectadas > 0;
		
	}
}
