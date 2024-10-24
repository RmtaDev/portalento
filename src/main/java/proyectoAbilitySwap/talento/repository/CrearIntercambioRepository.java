package proyectoAbilitySwap.talento.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import proyectoAbilitySwap.talento.beans.CrearIntercambio;

public class CrearIntercambioRepository {

	public static final String INSERTAR_INTERCAMBIO = "INSERT INTO `abilityswapbd`.`intercambios` ( `usuario_ofertada`, `usuario_demandada`, `habilidad_ofertada`, `habilidad_demandada`, `estado`) VALUES (?,?,?,?,?);";

	public int insertarIntercambio(CrearIntercambio crearIntercambio) throws SQLException {

		int idNuevoIntercambio = -1;

		Connection connection = Pool.getConnection();
		PreparedStatement ps = connection.prepareStatement(INSERTAR_INTERCAMBIO, Statement.RETURN_GENERATED_KEYS);

		
		ps.setInt(1, crearIntercambio.getUsuarioOfertada());
        ps.setInt(2, crearIntercambio.getUsuarioDemandada());
        ps.setInt(3, crearIntercambio.getHabilidadOfertada());
        ps.setInt(4, crearIntercambio.getHabilidadDemandada());
        ps.setString (5, crearIntercambio.getEstado().toString());
		
		
		int filasAfectadas = ps.executeUpdate();

		if (filasAfectadas == 1) {
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				idNuevoIntercambio = rs.getInt(1);
			}
		}
		Pool.liberarRecursos(connection, ps, null);

		return idNuevoIntercambio;

	}

}


