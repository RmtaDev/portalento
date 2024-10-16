package proyectoAbilitySwap.talento.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import proyectoAbilitySwap.talento.beans.Categoria;
import proyectoAbilitySwap.talento.beans.HabilidadOfertada;

public class BuscadorHabRepository {
	public static final String EXISTE_HABILIDAD_OFERTADA = "SELECT * FROM abilityswapbd.habilidades_ofertadas;";
	
	public List<HabilidadOfertada> extraerHabilidad() throws SQLException {
		List<HabilidadOfertada> listaHabilidades = null;

		Connection connection = Pool.getConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(EXISTE_HABILIDAD_OFERTADA);
		listaHabilidades = new ArrayList<HabilidadOfertada>();
		while (rs.next()) {
			String habilidad = rs.getString("nombre");
			int id = rs.getInt("id_categoria");
			Categoria categorias = new Categoria(habilidad, id);
			listaHabilidades.add(habilidades);
		}
		Pool.liberarRecursos(connection, statement, rs);

		return listaHabilidades;
	}

}
