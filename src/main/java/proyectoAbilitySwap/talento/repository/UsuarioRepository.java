package proyectoAbilitySwap.talento.repository;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import proyectoAbilitySwap.talento.beans.Usuario;

public class UsuarioRepository {
	public static final String EXISTE_USUARIO = "SELECT * FROM abilityswapbd.usuarios WHERE usuario = ? AND password = ?";
	public static final String INSERTAR_USUARIO = "INSERT INTO `abilityswapbd`.`usuarios` (`usuario`, `password`, `foto`) VALUES (?,?,?);";

	public boolean existeUsuario(Usuario usuario) throws SQLException {
		boolean existe = false;

		Connection connection = Pool.getConnection();
		PreparedStatement ps = connection.prepareStatement(EXISTE_USUARIO);
		ps.setString(1, usuario.getUsuario());
		ps.setString(2, usuario.getPassword());
		ps.execute();
		existe = ps.getResultSet().next();

		Pool.liberarRecursos(connection, ps, null);

		return existe;
	}

	public int insertarUsuario(Usuario usuario) throws SQLException {
		int idnuevo = -1;
		Connection connection = Pool.getConnection();
		PreparedStatement ps = connection.prepareStatement(INSERTAR_USUARIO, Statement.RETURN_GENERATED_KEYS);
		
		
			ps.setString(1, usuario.getUsuario());
			ps.setString(2, usuario.getPassword());
			ps.setBinaryStream(3, new ByteArrayInputStream(usuario.getFoto()), usuario.getFoto().length);
			int nfilas = ps.executeUpdate();
			if (nfilas == 1) {
				ps.getGeneratedKeys().next();
				idnuevo = ps.getGeneratedKeys().getInt(1);
			}



			Pool.liberarRecursos(connection, ps, null);

		return idnuevo;
	}
}
