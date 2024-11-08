package proyectoAbilitySwap.talento.servicio;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import proyectoAbilitySwap.talento.beans.Mensaje;
import proyectoAbilitySwap.talento.repositorio.MensajeRepository;

public class MensajeService {

	public Mensaje insertarMensaje(int idintercambio, int emisor, int receptor, LocalDateTime fecha_hora, String texto) throws SQLException {
		Mensaje mensaje = null;
		MensajeRepository mensajeRepository = null;
			
		mensajeRepository = new MensajeRepository();
			mensaje = mensajeRepository.insertarMensaje(idintercambio, emisor, receptor, fecha_hora, texto);
		
		return mensaje;
	}
	
	public List<Mensaje> ConsultarMensajesPorIntercambio(int idintercambio) throws SQLException{
		List<Mensaje> listaMensajes = null;
		MensajeRepository mensajeRepository = null;
			
		mensajeRepository = new MensajeRepository();
			listaMensajes = mensajeRepository.ConsultarMensajesPorIntercambio(idintercambio);
		
		return listaMensajes;
	}
}
