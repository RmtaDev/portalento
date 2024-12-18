package proyectoAbilitySwap.talento.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import proyectoAbilitySwap.talento.beans.HabilidadOfertada;

public class BuscadorHabRepository {
    private static final String BUSCAR_HABILIDAD_POR_NOMBRE = "SELECT DISTINCT nombre FROM abilityswapbd.habilidades_ofertadas WHERE nombre LIKE ?";
	
	public List<HabilidadOfertada> extraerHabilidad(String consulta) throws SQLException {
		List<HabilidadOfertada> listaHabilidades = null;

		Connection connection = Pool.getConnection();
		PreparedStatement pstatement = connection.prepareStatement(BUSCAR_HABILIDAD_POR_NOMBRE);

		if (consulta!= "") {
			pstatement.setString(1, consulta+"%");
		} else {
			pstatement.setString(1, "");
		}
		
		ResultSet rs = pstatement.executeQuery();
		listaHabilidades = new ArrayList<HabilidadOfertada>();
		while (rs.next()) {
			String habilidad = rs.getString("nombre");
			/*int id = rs.getInt("id_habilidad");
			int usuario = rs.getInt("usuario");
			int categoria = rs.getInt("id_categoria");*/
			HabilidadOfertada habilidades = new HabilidadOfertada(habilidad);
			listaHabilidades.add(habilidades);
		}
		Pool.liberarRecursos(connection, pstatement, rs);

		return listaHabilidades;
	}

}
