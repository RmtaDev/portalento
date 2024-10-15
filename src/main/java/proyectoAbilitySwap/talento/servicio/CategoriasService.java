package proyectoAbilitySwap.talento.servicio;

import java.sql.SQLException;
import java.util.List;

import proyectoAbilitySwap.talento.beans.Categoria;
import proyectoAbilitySwap.talento.repository.CategoriasRepository;

public class CategoriasService {

	public List<Categoria> recuperarListadoCategorias () throws SQLException{
		List<Categoria> listaCategorias = null;
		CategoriasRepository categoriasRepository = null;
			
			categoriasRepository = new CategoriasRepository();
			listaCategorias = categoriasRepository.extraerCategoria();
		
		return listaCategorias;
	}
	
}

