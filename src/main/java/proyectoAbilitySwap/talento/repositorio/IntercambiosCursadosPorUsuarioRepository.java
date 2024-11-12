package proyectoAbilitySwap.talento.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import proyectoAbilitySwap.talento.beans.IntercambioJoined;
import proyectoAbilitySwap.talento.beans.IntercambioJoined.EstadoIntercambio;


public class IntercambiosCursadosPorUsuarioRepository {
	
	public static final String INTERCAMBIOS_POR_USUARIO = "SELECT i.idintercambio, " +
														  "		  i.usuario_ofertada AS id_usuario_ofertada, " +
		       											  "		  uo.usuario AS nombre_usuario_ofertada, " +
		       											  "		  i.habilidad_demandada AS id_habilidad_demandada, " +
		       											  "		  hd.nombre AS nombre_habilidad_demandada, " +
		       											  "		  i.usuario_demandada AS id_usuario_demandada, " +
		       											  "		  ud.usuario AS nombre_usuario_demandada, " +
		       											  "		  i.habilidad_ofertada AS id_habilidad_ofertada, " +
		       											  "		  ho.nombre AS nombre_habilidad_ofertada, " +
		       											  "		  i.estado " +
		       											  "FROM intercambios i " +
		       											  "		JOIN usuarios uo ON i.usuario_ofertada = uo.id_usuario " +
		       											  "		JOIN usuarios ud ON i.usuario_demandada = ud.id_usuario " +
		       											  "		JOIN habilidades_ofertadas ho ON i.habilidad_ofertada = ho.id_habilidad " +
		       											  "		JOIN habilidades_demandadas hd ON i.habilidad_demandada = hd.id_habilidad " +
		       											  "WHERE (i.usuario_demandada=?) OR (i.usuario_ofertada=?)";
			
	private static Logger log = Logger.getLogger("mylog");
	
	public List<IntercambioJoined> consultarNumeroIntercambiosCursadosPorUsuario(int idusuario) throws SQLException {
		List<IntercambioJoined> listaIntercambios = null;
		log.debug("Peticion en repositorio: consultarNumeroIntercambiosCursadosPorUsuario");
		Connection connection = Pool.getConnection();
		PreparedStatement ps = connection.prepareStatement(INTERCAMBIOS_POR_USUARIO);
		ps.setInt(1, idusuario);
		ps.setInt(2, idusuario);
		ResultSet rs = ps.executeQuery();		
		listaIntercambios = new ArrayList<IntercambioJoined>();
		
		while (rs.next()) {
			int idIntercambio = rs.getInt("idintercambio");
			int id_usuario_ofertada = rs.getInt("id_usuario_ofertada");
			String nombre_usuario_ofertada = rs.getString("nombre_usuario_ofertada");
			int id_habilidad_demandada = rs.getInt("id_habilidad_demandada");
			String nombre_habilidad_demandada = rs.getString("nombre_habilidad_demandada");
			int id_usuario_demandada = rs.getInt("id_usuario_demandada");
			String nombre_usuario_demandada = rs.getString("nombre_usuario_demandada");
			int id_habilidad_ofertada = rs.getInt("id_habilidad_ofertada");
			String nombre_habilidad_ofertada = rs.getString("nombre_habilidad_ofertada");			
			EstadoIntercambio estado = EstadoIntercambio.valueOf(rs.getString("estado"));
			
			IntercambioJoined intercambioJoined = new IntercambioJoined(idIntercambio, id_usuario_ofertada, nombre_usuario_ofertada,
																		id_habilidad_demandada, nombre_habilidad_demandada, 
																		id_usuario_demandada, nombre_usuario_demandada,
																		id_habilidad_ofertada, nombre_habilidad_ofertada, estado);
			listaIntercambios.add(intercambioJoined);
		}
								
		Pool.liberarRecursos(connection, ps, rs);
		log.debug("El repositorio fue bien " + listaIntercambios);

		return listaIntercambios;
	}
																         
}
