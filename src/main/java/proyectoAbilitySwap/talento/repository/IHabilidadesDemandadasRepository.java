package proyectoAbilitySwap.talento.repository;

import java.sql.SQLException;
import java.util.List;

import proyectoAbilitySwap.talento.beans.HabilidadDemandada;


/**
 * Esta interfaz, describe qué operaciones
 * de base de datos realizamos con las habilidades
 * demandadas
 */
public interface IHabilidadesDemandadasRepository {

	void insertarHabilidadDemandada(String nombreHabilidad, int idUsuario, int idCategoria) throws SQLException;

	List<HabilidadDemandada> consultarTodasDemandadas(int idUsuario) throws SQLException;

	void borrarHabilidadDemandada(int idHabilidad) throws SQLException;

}
