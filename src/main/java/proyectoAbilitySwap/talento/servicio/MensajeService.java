package proyectoAbilitySwap.talento.servicio;

import java.util.List;

import proyectoAbilitySwap.talento.beans.HabilidadOfertada;
import proyectoAbilitySwap.talento.beans.Mensaje;
import proyectoAbilitySwap.talento.repositorio.BuscadorHabRepository;
import proyectoAbilitySwap.talento.repositorio.MensajeRepository;

public class MensajeService {
	List<Mensaje> listaMensajes = null;
	MensajeRepository habilidadesRepository = null;
		
		habilidadesRepository = new BuscadorHabRepository();
		listaHabilidades = habilidadesRepository.extraerHabilidad(consulta);
	
	return listaHabilidades;
}

}
