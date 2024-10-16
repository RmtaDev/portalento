package proyectoAbilitySwap.talento.servicio;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import  proyectoAbilitySwap.talento.beans.Usuario;
import proyectoAbilitySwap.talento.repository.HabilidadesRepositoryAntonio;

public class HabilidadesServiceAntonio {
	private static Logger log = Logger.getLogger("mylog");	

	
	public List<Usuario> listadoUsuariosPorHabilidad (String habilidad) throws SQLException{
		log.debug("Peticion en Servicio listadoUsuariosPorHabilidad");
		List<Usuario> listaUsuarios = null;
		HabilidadesRepositoryAntonio habilidadesRepositoryAntonio = null;//Habilidades repository seria el repositorio de habilidades que esta haciendo Carlos
		habilidadesRepositoryAntonio = new HabilidadesRepositoryAntonio();
		listaUsuarios = habilidadesRepositoryAntonio.consultarUsuariosPorHabilidad(habilidad);
		log.debug("El servicio fue bien " + listaUsuarios);
		
		return listaUsuarios;
	}

}
