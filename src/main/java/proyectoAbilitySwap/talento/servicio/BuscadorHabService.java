package proyectoAbilitySwap.talento.servicio;

import java.sql.SQLException;
import java.util.List;

import proyectoAbilitySwap.talento.beans.Categoria;
import proyectoAbilitySwap.talento.beans.HabilidadOfertada;
import proyectoAbilitySwap.talento.repository.BuscadorHabRepository;
import proyectoAbilitySwap.talento.repository.CategoriasRepository;

public class BuscadorHabService {

	public List<HabilidadOfertada> recuperarListadoHabilidades () throws SQLException{
		List<HabilidadOfertada> listaHabilidades = null;
		BuscadorHabRepository habilidadesRepository = null;
			
			habilidadesRepository = new BuscadorHabRepository();
			listaHabilidades = habilidadesRepository.extraerHabilidad();
		
		return listaHabilidades;
	}
	
}
