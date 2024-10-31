package proyectoAbilitySwap.talento.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import proyectoAbilitySwap.talento.beans.HabilidadOfertada;

public class MensajeRepository {
	 private static final String BUSCAR_MENSAJE = "SELECT mensaje FROM abilityswapbd.mensajes";
	 private static final String ENVIAR_MENSAJE = "SELECT mensaje FROM abilityswapbd.mensajes";
		
		public List<Mensajeria> extraerMensjae(String consulta) throws SQLException {
			List<Mensajeria> listaMensajes = null;

			Connection connection = Pool.getConnection();
			PreparedStatement pstatement = connection.prepareStatement(BUSCAR_MENSAJE);

			if (consulta!= "") {
				pstatement.setString(1, consulta+"%");
			} else {
				pstatement.setString(1, "");
			}
			
			ResultSet rs = pstatement.executeQuery();
			listaMensajes = new ArrayList<Mensajeria>();
			while (rs.next()) {
				String mensaje = rs.getString("nombre");
				/*int id = rs.getInt("id_habilidad");
				int usuario = rs.getInt("usuario");
				int categoria = rs.getInt("id_categoria");*/
				Mensaje mensaje = new Mensaje(mensaje);
				listaMensajes.add(mensajes);
			}
			Pool.liberarRecursos(connection, pstatement, rs);

			return listaMensajes;
		}

}
