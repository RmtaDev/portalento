package proyectoAbilitySwap.talento.servicio;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import proyectoAbilitySwap.talento.beans.HabilidadDemandada;
import proyectoAbilitySwap.talento.beans.HabilidadOfertada;
import proyectoAbilitySwap.talento.beans.Usuario;
import proyectoAbilitySwap.talento.repositorio.HabilidadesRepository;
import proyectoAbilitySwap.talento.repositorio.HabilidadesRepositoryAntonio;
import proyectoAbilitySwap.talento.repositorio.IHabilidadesDemandadasRepository;
import proyectoAbilitySwap.talento.repositorio.IHabilidadesOfertadasRepository;

public class HabilidadesService {

	private static Logger log = Logger.getLogger("mylog");

	public List<Usuario> listadoUsuariosPorHabilidad(String habilidad) throws SQLException {
		log.debug("Peticion en Servicio listadoUsuariosPorHabilidad");
		List<Usuario> listaUsuarios = null;
		HabilidadesRepositoryAntonio habilidadesRepositoryAntonio = null;// Habilidades repository seria el repositorio
																			// de habilidades que esta haciendo Carlos
		habilidadesRepositoryAntonio = new HabilidadesRepositoryAntonio();
		listaUsuarios = habilidadesRepositoryAntonio.consultarUsuariosPorHabilidad(habilidad);
		log.debug("El servicio fue bien " + listaUsuarios);

		return listaUsuarios;
	}

	public void insertarHabilidadDemandada(String nombreHabilidad, int idUsuario, int idCategoria) throws SQLException {

		HabilidadesRepository habilidadesDemandadasRepository = new HabilidadesRepository();

		habilidadesDemandadasRepository.insertarHabilidadDemandada(nombreHabilidad, idUsuario, idCategoria);

	}

	public List<HabilidadDemandada> consultarTodasDemandadas(int idUsuario) throws SQLException {

		List<HabilidadDemandada> habilidadDemandadas = null;

		HabilidadesRepository habilidadesDemandadasRepository = new HabilidadesRepository();

		habilidadDemandadas = habilidadesDemandadasRepository.consultarTodasDemandadas(idUsuario);

		return habilidadDemandadas;

	}

	public void borrarHabilidadDemandada(int idHabilidad) throws SQLException {

		HabilidadesRepository habilidadesDemandadasRepository = new HabilidadesRepository();

		habilidadesDemandadasRepository.borrarHabilidadDemandada(idHabilidad);

	}

	public void insertarHabilidadOfertada(String nombreHabilidad, int idUsuario, int idCategoria) throws SQLException {

		HabilidadesRepository habilidadesDemandadasRepository = new HabilidadesRepository();

		habilidadesDemandadasRepository.insertarHabilidadOfertada(nombreHabilidad, idUsuario, idCategoria);

	}

	public List<HabilidadOfertada> consultarTodasOfertadas(int idUsuario) throws SQLException {

		List<HabilidadOfertada> habilidadOfertadas = null;

		HabilidadesRepository habilidadesRepository = new HabilidadesRepository();
		habilidadOfertadas = habilidadesRepository.consultarTodasOfertadas(idUsuario);

		return habilidadOfertadas;
	}

	public void borrarHabilidadOfertada(int idHabilidad) throws SQLException {
		HabilidadesRepository habilidadesDemandadasRepository = new HabilidadesRepository();

		habilidadesDemandadasRepository.borrarHabilidadOfertada(idHabilidad);
	}

}
