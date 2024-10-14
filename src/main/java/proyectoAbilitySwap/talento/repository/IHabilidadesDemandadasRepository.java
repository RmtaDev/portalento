package proyectoAbilitySwap.talento.repository;

import java.util.List;

import proyectoAbilitySwap.talento.beans.HabilidadDemandada;


/**
 * Esta interfaz, describe qué operaciones
 * de base de datos realizamos con las habilidades
 * demandadas
 */
public interface IHabilidadesDemandadasRepository {

	void insertarHabilidadDemandada(String nombreHabilidad, int idUsuario, int idCategoria);

	List<HabilidadDemandada> consultarTodasDemandadas(int idUsuario);

	void borrarHabilidadDemandada(int idHabilidad);

}
