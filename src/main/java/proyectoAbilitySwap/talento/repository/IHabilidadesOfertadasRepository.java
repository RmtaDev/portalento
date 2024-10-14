package proyectoAbilitySwap.talento.repository;

import java.util.List;

import proyectoAbilitySwap.talento.beans.HabilidadOfertada;

/**
 * Esta interfaz, describe qué operaciones
 * de base de datos realizamos con las habilidades
 * ofertadas
 */
public interface IHabilidadesOfertadasRepository {

	void insertarHabilidadOfertada (String nombreHabilidad, int idUsuario, int idCategoria);
	
	List<HabilidadOfertada> consultarTodasOfertadas (int idUsuario);
	
	void borrarHabilidadOfertada (int idHabilidad);
}
