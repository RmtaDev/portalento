package proyectoAbilitySwap.talento.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import proyectoAbilitySwap.talento.beans.Usuario;

public class HabilidadesRepositoryAntonio {

	public static final String USUARIOS_POR_HABILIDAD = "SELECT usuarios.usuario " +
														"FROM abilityswapbd.usuarios INNER JOIN abilityswapbd.habilidades_ofertadas ON habilidades_ofertadas.usuario = usuarios.id_usuario " +
														"WHERE habilidades_ofertadas.nombre = ?";
	
	private static Logger log = Logger.getLogger("mylog");	



	public List<Usuario> consultarUsuariosPorHabilidad(String habilidad) throws SQLException {
		log.debug("Peticion en repositorio: consultarUsuariosPorHabilidad");
		List<Usuario> listaUsuarios = null;

		Connection connection = Pool.getConnection();
		PreparedStatement ps = connection.prepareStatement(USUARIOS_POR_HABILIDAD);
		ps.setString(1, habilidad);
		ResultSet rs = ps.executeQuery();		
		listaUsuarios = new ArrayList<Usuario>();

		while (rs.next())
		{
			String string_usuario = rs.getString("usuario");

			Usuario usuario = new Usuario(string_usuario);
			listaUsuarios.add(usuario);
		}

		Pool.liberarRecursos(connection, ps, rs);
		log.debug("El repositorio fue bien " + listaUsuarios);

		return listaUsuarios;
}
}
