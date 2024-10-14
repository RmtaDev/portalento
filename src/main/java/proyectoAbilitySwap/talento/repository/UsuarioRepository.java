package proyectoAbilitySwap.talento.repository;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import proyectoAbilitySwap.talento.beans.Usuario;

public class UsuarioRepository {
	public static final String EXISTE_USUARIO = "SELECT * FROM abilityswapbd.usuarios WHERE usuario = ? AND password = ?";
	public static final String INSERTAR_USUARIO = "INSERT INTO `abilityswapbd`.`usuarios` (`usuario`, `password`, `foto`) VALUES (?,?,?);";


	public Usuario leerUsuario(Usuario usuario) throws SQLException {
		Usuario usuarioLeido = null;

		Connection connection = Pool.getConnection();
		PreparedStatement ps = connection.prepareStatement(EXISTE_USUARIO);
		ps.setString(1, usuario.getUsuario());
		ps.setString(2, usuario.getPassword());
		ResultSet rs = ps.executeQuery();

		if (rs.next())
		{
			int idusuario = rs.getInt(1);
			String nombreu = rs.getString("usuario");
			String pwdu = rs.getString("password");
			String rutafoto = rs.getString("rutafoto");

			usuarioLeido = new Usuario(idusuario, nombreu, pwdu, rutafoto);
		}

		Pool.liberarRecursos(connection, ps, rs);

		return usuarioLeido;
	}
/**
 * 
 * @param usuario
 * @return
 * @throws SQLException
 */
	public int insertarUsuario(Usuario usuario) throws SQLException {
		int idnuevo = -1;
		Connection connection = Pool.getConnection();
		PreparedStatement ps = connection.prepareStatement(INSERTAR_USUARIO, Statement.RETURN_GENERATED_KEYS);

		ps.setString(1, usuario.getUsuario());
		ps.setString(2, usuario.getPassword());
		ps.setBinaryStream(3, new ByteArrayInputStream(usuario.getFoto()), usuario.getFoto().length);
		//TODO set el resto de parámetros y MODIFICAR la consulta
		int nfilas = ps.executeUpdate();
		if (nfilas == 1) {
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next())
			{
				idnuevo = rs.getInt(1);
			}
		}

		Pool.liberarRecursos(connection, ps, null);

		return idnuevo;
	}
}
