package proyectoAbilitySwap.talento.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import proyectoAbilitySwap.talento.beans.CrearIntercambio;
import proyectoAbilitySwap.talento.beans.CrearIntercambio.EstadoIntercambio;


public class IntercambiosCursadosPorUsuarioRepository {
	
	public static final String NUMERO_INTERCAMBIOS_CURSADOS_POR_USUARIO = "SELECT * " +
																		  "FROM intercambios " + 
																		  "WHERE (usuario_demandada=?) OR (usuario_ofertada=?)"
																		  ;
	
	private static Logger log = Logger.getLogger("mylog");
	
	public List<CrearIntercambio> consultarNumeroIntercambiosCursadosPorUsuario(int idusuario) throws SQLException {
		List<CrearIntercambio> listaIntercambios = null;
		log.debug("Peticion en repositorio: consultarNumeroIntercambiosCursadosPorUsuario");
		Connection connection = Pool.getConnection();
		PreparedStatement ps = connection.prepareStatement(NUMERO_INTERCAMBIOS_CURSADOS_POR_USUARIO);
		ps.setInt(1, idusuario);
		ps.setInt(2, idusuario);
		ResultSet rs = ps.executeQuery();		
		listaIntercambios = new ArrayList<CrearIntercambio>();
		
		while (rs.next()) {
			int idIntercambio = rs.getInt("idintercambio");
			int usuarioOfertada = rs.getInt("usuario_ofertada");
			int usuarioDemandada = rs.getInt("usuario_demandada");
			int habilidadOfertada = rs.getInt("habilidad_ofertada");
			int habilidadDemandada = rs.getInt("habilidad_demandada");
			EstadoIntercambio estado = EstadoIntercambio.valueOf(rs.getString("estado"));
			
			CrearIntercambio intercambio = new CrearIntercambio(idIntercambio, usuarioOfertada, usuarioDemandada, habilidadOfertada, habilidadDemandada, estado);
			listaIntercambios.add(intercambio);
		}
								
		Pool.liberarRecursos(connection, ps, rs);
		log.debug("El repositorio fue bien " + listaIntercambios);

		return listaIntercambios;
	}
																         
}
