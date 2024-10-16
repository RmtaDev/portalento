package proyectoAbilitySwap.talento.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import proyectoAbilitySwap.talento.beans.HabilidadOfertada;

public class BuscadorHabRepository {
    private static final String BUSCAR_HABILIDAD_POR_NOMBRE = "SELECT * FROM abilityswapbd.habilidades_ofertadas WHERE nombre LIKE ?";
	
	public List<HabilidadOfertada> extraerHabilidad() throws SQLException {
		List<HabilidadOfertada> listaHabilidades = null;

		Connection connection = Pool.getConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(BUSCAR_HABILIDAD_POR_NOMBRE);
		listaHabilidades = new ArrayList<HabilidadOfertada>();
		while (rs.next()) {
			String habilidad = rs.getString("nombre");
			int id = rs.getInt("id_habilidad");
			int usuario = rs.getInt("usuario");
			int categoria = rs.getInt("id_categoria");
			HabilidadOfertada habilidades = new HabilidadOfertada(usuario, id, habilidad, categoria);
			listaHabilidades.add(habilidades);
		}
		Pool.liberarRecursos(connection, statement, rs);

		return listaHabilidades;
	}

}
