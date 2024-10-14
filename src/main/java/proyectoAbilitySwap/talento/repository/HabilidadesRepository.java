package proyectoAbilitySwap.talento.repository;

import java.util.List;

import proyectoAbilitySwap.talento.beans.HabilidadDemandada;
import proyectoAbilitySwap.talento.beans.HabilidadOfertada;

public class HabilidadesRepository implements IHabilidadesDemandadasRepository, IHabilidadesOfertadasRepository{

	@Override
	public void insertarHabilidadOfertada(String nombreHabilidad, int idUsuario, int idCategoria) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<HabilidadOfertada> consultarTodasOfertadas(int idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrarHabilidadOfertada(int idHabilidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertarHabilidadDemandada(String nombreHabilidad, int idUsuario, int idCategoria) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<HabilidadDemandada> consultarTodasDemandadas(int idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrarHabilidadDemandada(int idHabilidad) {
		// TODO Auto-generated method stub
		
	}




}
