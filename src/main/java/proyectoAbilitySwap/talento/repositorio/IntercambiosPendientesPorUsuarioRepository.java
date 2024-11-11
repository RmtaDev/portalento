package proyectoAbilitySwap.talento.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class IntercambiosPendientesPorUsuarioRepository {

	public static final String NUMERO_INTERCAMBIOS_POR_USUARIO = "SELECT COUNT(idintercambio) AS intercambios_pendientes_por_usuario " +
																 "FROM intercambios " +
																 "WHERE usuario_demandada = ? AND estado = 'PENDIENTE'";
	
	private static Logger log = Logger.getLogger("mylog");
	
	public int consultarNumeroIntercambiosPendientesPorUsuario(int idusuario) throws SQLException {
		log.debug("Peticion en repositorio: consultarNumeroIntercambiosPendientesPorUsuario");
		int noIntercambiosPendientesPorUsuario = 0;

		Connection connection = Pool.getConnection();
		PreparedStatement ps = connection.prepareStatement(NUMERO_INTERCAMBIOS_POR_USUARIO);
		ps.setInt(1, idusuario);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		noIntercambiosPendientesPorUsuario = rs.getInt("intercambios_pendientes_por_usuario");

		Pool.liberarRecursos(connection, ps, rs);
		log.debug("El repositorio fue bien " + noIntercambiosPendientesPorUsuario);

		return noIntercambiosPendientesPorUsuario;
	}
}
