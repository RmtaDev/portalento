package proyectoAbilitySwap.talento.servicio;

import java.sql.SQLException;

import proyectoAbilitySwap.talento.beans.Usuario;
import proyectoAbilitySwap.talento.repositorio.UsuarioRepository;

public class UsuarioService {

	public Usuario leerUsuario(Usuario usuario) throws SQLException {
		Usuario usuarioleido = null;

		UsuarioRepository usuarioRepository = new UsuarioRepository();
		usuarioleido = usuarioRepository.leerUsuario(usuario);

		return usuarioleido;
	}

	public Usuario leerUsuarioPorId(int idusuario) throws SQLException {
		Usuario usuarioleido = null;

		UsuarioRepository usuarioRepository = new UsuarioRepository();
		usuarioleido = usuarioRepository.leerUsuarioPorId(idusuario);

		return usuarioleido;
	}

	public int insertarUsuario(Usuario usuario) throws SQLException {
		int idnuevousuario = -1;

		UsuarioRepository usuarioRepository = new UsuarioRepository();
		idnuevousuario = usuarioRepository.insertarUsuario(usuario);

		return idnuevousuario;
	}

	public boolean existeUsuarioNuevo(Usuario usuario) throws SQLException {
		boolean usuarioNuevo = false;

		UsuarioRepository usuarioRepository = new UsuarioRepository();
		usuarioNuevo = usuarioRepository.existeUsuarioNuevo(usuario);

		return usuarioNuevo;
	}
}
