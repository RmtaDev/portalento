package proyectoAbilitySwap.talento.repositorio;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import proyectoAbilitySwap.talento.beans.Usuario;

public class UsuarioRepository {
	public static final String EXISTE_USUARIO = "SELECT * FROM abilityswapbd.usuarios WHERE usuario = ? AND password = ?";
	public static final String EXISTE_USUARIO_NUEVO = "SELECT 1 FROM abilityswapbd.usuarios WHERE usuario = ?";
	public static final String CONSULTAR_USUARIO_POR_ID = "SELECT * FROM abilityswapbd.usuarios WHERE id_usuario = ?";
	public static final String INSERTAR_USUARIO = "INSERT INTO `abilityswapbd`.`usuarios` (`usuario`, `password`, `nombre`, `apellidos`, `edad`, `genero`, `telefono`, `email`, `foto`, `rutaFoto`, `habla_sobre_ti`) VALUES (?,?,?,?,?,?,?,?,?,?,?);";


	public Usuario leerUsuario(Usuario usuario) throws SQLException {
		Usuario usuarioLeido = null;

		Connection connection = Pool.getConnection();
		PreparedStatement ps = connection.prepareStatement(EXISTE_USUARIO);
		ps.setString(1, usuario.getUsuario());
		ps.setString(2, usuario.getPassword());
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			int idusuario = rs.getInt(1);
			String nombreu = rs.getString("usuario");
			String pwdu = rs.getString("password");
			String rutafoto = rs.getString("rutafoto");

			usuarioLeido = new Usuario(idusuario, nombreu, pwdu, rutafoto);
		}

		Pool.liberarRecursos(connection, ps, rs);

		return usuarioLeido;
	}
	
	
	public Usuario leerUsuarioPorId(int id_usuario) throws SQLException {
		Usuario usuarioLeido = null;

		Connection connection = Pool.getConnection();
		PreparedStatement ps = connection.prepareStatement(CONSULTAR_USUARIO_POR_ID);
		ps.setInt(1, id_usuario);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			
			String nombreu = rs.getString("usuario");
			String rutafoto = rs.getString("rutafoto");
			String nombre = rs.getString("nombre");
			String apellidos = rs.getString("apellidos");
			int edad = rs.getInt("edad");
			String genero = rs.getString("genero");
			String telefono = rs.getString("telefono");
			String email = rs.getString("email");
			String sobreti = rs.getString("habla_sobre_ti");
			//nombre, apellidos, edad, genero ,telefono , email, habla_sobre_ti

			usuarioLeido = new Usuario(id_usuario, nombreu, null, nombre, apellidos, edad, genero, telefono, email, null, rutafoto, sobreti);
		}

		Pool.liberarRecursos(connection, ps, rs);

		return usuarioLeido;
	}

	/**
	 * Inserta un nuevo usuario en la bse de datos.
	 * 
	 * @param usuario El objeto Usuario que contiene los datos a insertar en la base
	 *                de datos.
	 * @return El ID del nuevo usuario insertado en la base de datos, o -1 si no se
	 *         insertó.
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
		ps.setInt(5, usuario.getEdad());
		ps.setString(6, usuario.getGenero());
		ps.setString(7, usuario.getTelefono());
		ps.setString(8, usuario.getEmail());
		ps.setBinaryStream(9, new ByteArrayInputStream(usuario.getFoto()), usuario.getFoto().length);
		ps.setString(10, usuario.getRutaFoto());
		ps.setString(11, usuario.getHablaSobreTi());


		// TODO set el resto de parámetros y MODIFICAR la consulta
		int nfilas = ps.executeUpdate();

		if (nfilas == 1) {
			ResultSet rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				idnuevo = rs.getInt(1);
			}
		}

		Pool.liberarRecursos(connection, ps, null);

		return idnuevo;
	}
	
	/**
	 * Verifica si un usuario ya existe en la base de datos basándose en su nombre de usuario.
	 * 
	 * Este método consulta la base de datos para determinar si un nombre de usuario ya está registrado,
	 * lo cual es útil para prevenir la creación de usuarios duplicados.
	 * 
	 * @param usuario Objeto Usuario que contiene el nombre de usuario a verificar.
	 * @return true si el usuario existe, false en caso contrario.
	 * @throws SQLException Si ocurre algún error de acceso a la base de datos o al ejecutar la consulta.
	 */
	public boolean existeUsuarioNuevo(Usuario usuario) throws SQLException{
		
		boolean usuarioExiste = false;
		Connection connection = Pool.getConnection();
		PreparedStatement ps = connection.prepareStatement(EXISTE_USUARIO_NUEVO);
		ps.setString(1, usuario.getUsuario());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			usuarioExiste = true;
		}
		
		Pool.liberarRecursos(connection, ps, rs);
		
		return usuarioExiste;
	}
}
