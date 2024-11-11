package proyectoAbilitySwap.talento.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import proyectoAbilitySwap.talento.beans.Mensaje;

public class MensajeRepository {
	 private static final String INSERTAR_MENSAJE = "INSERT INTO mensajes (idintercambio, emisor, receptor, fecha_hora, texto) VALUES (?, ?, ?, ?, ?)";
	 private static final String MENSAJES_POR_INTERCAMBIO = "SELECT * FROM mensajes WHERE idintercambio = ? ORDER BY fecha_hora";
		
	 public Mensaje insertarMensaje(int idintercambio, int emisor, int receptor, LocalDateTime fecha_hora, String texto) throws SQLException {
	 	Mensaje mensaje = null;
		Connection connection = Pool.getConnection();
		PreparedStatement ps = connection.prepareStatement(INSERTAR_MENSAJE, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, idintercambio);
		ps.setInt(2, emisor);
		ps.setInt(3, receptor);
		ps.setObject(4, fecha_hora);
		ps.setString(5, texto);

		int filasInsertadas = ps.executeUpdate();

		if (filasInsertadas == 1) {
			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				int idnuevo = rs.getInt(1);
				String string_fecha_hora = fecha_hora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
				mensaje = new Mensaje(idnuevo, idintercambio, emisor, receptor, string_fecha_hora, texto);
			}
		}

		Pool.liberarRecursos(connection, ps, null);
		return mensaje;
	}

	public List<Mensaje> ConsultarMensajesPorIntercambio(int idintercambio) throws SQLException {
		List<Mensaje> listaMensajes = null;
		Connection connection = Pool.getConnection();
		PreparedStatement pstatement = connection.prepareStatement(MENSAJES_POR_INTERCAMBIO);
		pstatement.setInt(1, idintercambio);
		ResultSet rs = pstatement.executeQuery();
		listaMensajes = new ArrayList<Mensaje>();

		while (rs.next()) {
			int idmensaje = rs.getInt("idmensaje");
			int emisor = rs.getInt("emisor");
			int receptor = rs.getInt("receptor");
			LocalDateTime fecha_hora = rs.getObject("fecha_hora", LocalDateTime.class);
			String string_fecha_hora = fecha_hora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
			String texto = rs.getString("texto");
			Mensaje mensaje = new Mensaje(idmensaje, idintercambio, emisor, receptor, string_fecha_hora, texto);
			listaMensajes.add(mensaje);
		}
		Pool.liberarRecursos(connection, pstatement, rs);
		return listaMensajes;
	}
}