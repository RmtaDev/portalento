package proyectoAbilitySwap.talento.servicio;

import java.sql.SQLException;

import proyectoAbilitySwap.talento.beans.Usuario;
import proyectoAbilitySwap.talento.repository.UsuarioRepository;

public class UsuarioService {
	
	public boolean existeUsuario(Usuario usuario) throws SQLException {
		boolean existe = false;

		UsuarioRepository usuarioRepository = new UsuarioRepository();
		existe = usuarioRepository.existeUsuario(usuario);

		return existe;
	}
	
	public void insertarUsuario(Usuario usuario) throws SQLException {
		UsuarioRepository usuarioRepository = new UsuarioRepository();
		usuarioRepository.insertarUsuario(usuario);
	}
}
