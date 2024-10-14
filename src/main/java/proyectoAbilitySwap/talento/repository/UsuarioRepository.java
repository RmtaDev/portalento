package proyectoAbilitySwap.talento.repository;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import proyectoAbilitySwap.talento.beans.Usuario;

public class UsuarioRepository {
	public static final String EXISTE_USUARIO = "SELECT * FROM abilityswapbd.usuarios WHERE usuario = ? AND password = ?";
	public static final String INSERTAR_USUARIO1= "INSERT INTO `abilityswapbd`.`usuarios` (`usuario`, `password`, `foto`) VALUES (?,?,?);";
	public static final String INSERTAR_USUARIO = "INSERT INTO `abilityswapbd`.`usuarios` (`usuario`, `password`, `nombre`, `apellidos`, `edad`, `genero`, `telefono`, `email`, `foto`, `rutaFoto`, `hablaSobreTi`) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
	
	private static Logger log = Logger.getLogger("mylog");



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
 * Inserta un nuevo usuario en la bse de datos.
 * 
 * @param usuario El objeto Usuario que contiene los datos a insertar en la base de datos.
 * @return El ID del nuevo usuario insertado en la base de datos, o -1 si no se insertó. 
 * @throws SQLException Si ocurre algún error de acceso a la base de datos.
 */
	public int insertarUsuario(Usuario usuario) throws SQLException {
		int idnuevo = -1;
		
		Connection connection = Pool.getConnection();
		PreparedStatement ps = connection.prepareStatement(INSERTAR_USUARIO, Statement.RETURN_GENERATED_KEYS);

		ps.setString(1, usuario.getUsuario());
		ps.setString(2, usuario.getPassword());
		ps.setString(3, usuario.getNombre());
		ps.setString(4, usuario.getApellidos());
		ps.setLong(5, usuario.getEdad());
		ps.setString(6, usuario.getGenero());
		ps.setString(7, usuario.getTelefono());
		ps.setString(8, usuario.getEmail());
		ps.setBinaryStream(9, new ByteArrayInputStream(usuario.getFoto()), usuario.getFoto().length);
		ps.setString(10, usuario.getRutaFoto());
		ps.setString(11, usuario.getHablaSobreTi());
		
		log.debug("### -> Ejecutando la consulta de inserción para el usuario: " + usuario.getUsuario());

		
		//TODO set el resto de parámetros y MODIFICAR la consulta
		int nfilas = ps.executeUpdate();
		
		if (nfilas == 1) {
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next())
			{
				idnuevo = rs.getInt(1);
				log.info("");
			}
		}

		Pool.liberarRecursos(connection, ps, null);

		return idnuevo;
	}
}
