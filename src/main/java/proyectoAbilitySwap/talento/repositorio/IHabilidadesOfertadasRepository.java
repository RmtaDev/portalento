package proyectoAbilitySwap.talento.repositorio;

import java.sql.SQLException;
import java.util.List;

import proyectoAbilitySwap.talento.beans.HabilidadOfertada;

/**
 * Esta interfaz, describe qué operaciones
 * de base de datos realizamos con las habilidades
 * ofertadas
 */
public interface IHabilidadesOfertadasRepository {

	void insertarHabilidadOfertada (String nombreHabilidad, int idUsuario, int idCategoria) throws SQLException;
	
	List<HabilidadOfertada> consultarTodasOfertadas (int idUsuario) throws SQLException;
	
	void borrarHabilidadOfertada (int idHabilidad) throws SQLException;
}
