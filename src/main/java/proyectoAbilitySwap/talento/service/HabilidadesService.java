package proyectoAbilitySwap.talento.service;

import java.sql.SQLException;
import java.util.List;

import proyectoAbilitySwap.talento.beans.HabilidadDemandada;
import proyectoAbilitySwap.talento.beans.HabilidadOfertada;
import proyectoAbilitySwap.talento.repository.HabilidadesRepository;
import proyectoAbilitySwap.talento.repository.IHabilidadesDemandadasRepository;
import proyectoAbilitySwap.talento.repository.IHabilidadesOfertadasRepository;

public class HabilidadesService {
	
	
	public void insertarHabilidadDemandada(String nombreHabilidad, int idUsuario, int idCategoria) throws SQLException{
		
		HabilidadesRepository habilidadesDemandadasRepository = new HabilidadesRepository();
		
		habilidadesDemandadasRepository.insertarHabilidadDemandada(nombreHabilidad, idUsuario, idCategoria);

		
	}

	public List<HabilidadDemandada> consultarTodasDemandadas(int idUsuario) throws SQLException{
		
		

		List<HabilidadDemandada> habilidadDemandadas  = null;
		
		HabilidadesRepository habilidadesDemandadasRepository = new HabilidadesRepository();
		
			habilidadDemandadas =  habilidadesDemandadasRepository.consultarTodasDemandadas(idUsuario);

		
		
		return habilidadDemandadas;
		
	}

	public void borrarHabilidadDemandada(int idHabilidad) throws SQLException{
		
		HabilidadesRepository habilidadesDemandadasRepository = new HabilidadesRepository();
		
		habilidadesDemandadasRepository.borrarHabilidadDemandada(idHabilidad);

		
		
	}
	
	public void insertarHabilidadOfertada (String nombreHabilidad, int idUsuario, int idCategoria) throws SQLException{
		
		HabilidadesRepository habilidadesDemandadasRepository = new HabilidadesRepository();
		
		habilidadesDemandadasRepository.insertarHabilidadOfertada(nombreHabilidad, idUsuario, idCategoria);
		
	}
	
	public List<HabilidadOfertada> consultarTodasOfertadas (int idUsuario) throws SQLException {
		
		List<HabilidadOfertada> habilidadOfertadas = null;
		
		HabilidadesRepository habilidadesRepository = new HabilidadesRepository();
		habilidadOfertadas= habilidadesRepository.consultarTodasOfertadas(idUsuario);
		
		
		
		return habilidadOfertadas;
	}
	
	public void borrarHabilidadOfertada (int idHabilidad) throws SQLException{
	HabilidadesRepository habilidadesDemandadasRepository = new HabilidadesRepository();
		
		habilidadesDemandadasRepository.borrarHabilidadOfertada(idHabilidad);
	}

}
