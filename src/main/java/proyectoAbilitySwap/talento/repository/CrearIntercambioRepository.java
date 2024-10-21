package proyectoAbilitySwap.talento.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import proyectoAbilitySwap.talento.beans.CrearIntercambio;

public class CrearIntercambioRepository {

	public static final String INSERTAR_INTERCAMBIO = "INSERT INTO `abilityswapbd`.`intercambios` (`idintercambio`, `usuario_ofertada`, `usuario_demandada`, `habilidad_ofertada`, `habilidad_demandada`, `estado`) VALUES (?,?,?,?,?,?);";

	public int insertarIntercambio(CrearIntercambio crearIntercambio) throws SQLException {

		int idNuevoIntercambio = -1;

		Connection connection = Pool.getConnection();
		PreparedStatement ps = connection.prepareStatement(INSERTAR_INTERCAMBIO);

		/*
		ps.setInt(1, crearIntercambio.getUsuarioOfertada().getIdusuario());
		ps.setInt(2, crearIntercambio.getUsuarioDemandada().getIdusuario());
		ps.setInt(3, crearIntercambio.getHabilidadOfertada().getIdHabilidad());
		ps.setInt(4, crearIntercambio.getHabilidadDemandada().getIdHabilidad());
		ps.setString(5, crearIntercambio.getEstado());
*/
		ps.setInt(1, crearIntercambio.getUsuarioOfertada());
        ps.setInt(2, crearIntercambio.getUsuarioDemandada());
        ps.setInt(3, crearIntercambio.getHabilidadOfertada());
        ps.setInt(4, crearIntercambio.getHabilidadDemandada());
        ps.setString(5, crearIntercambio.getEstado());
		
		
		int filasAfectadas = ps.executeUpdate();

		if (filasAfectadas == 1) {
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				idNuevoIntercambio = rs.getInt(1);
			}
		}
		Pool.liberarRecursos(connection, ps, null);

		return idNuevoIntercambio;

	}

	 // Método main para hacer pruebas de inserción
    public static void main(String[] args) {
        try {
            // Crear un objeto CrearIntercambio con datos de prueba
            CrearIntercambio nuevoIntercambio = new CrearIntercambio(0, 0, 0, 0, 0, null);
            
            nuevoIntercambio.setUsuarioOfertada(1); // ID del usuario ofertante
            nuevoIntercambio.setUsuarioDemandada(2); // ID del usuario demandante
            nuevoIntercambio.setHabilidadOfertada(3); // ID de la habilidad ofertada
            nuevoIntercambio.setHabilidadDemandada(4); // ID de la habilidad demandada
            nuevoIntercambio.setEstado("PENDIENTE"); // Estado del intercambio

            // Crear una instancia del repositorio
            CrearIntercambioRepository intercambioRepository = new CrearIntercambioRepository();

            // Llamar al método de insertar e imprimir el ID del nuevo intercambio
            int idNuevoIntercambio = intercambioRepository.insertarIntercambio(nuevoIntercambio);

            if (idNuevoIntercambio != -1) {
                System.out.println("Intercambio creado exitosamente con ID: " + idNuevoIntercambio);
            } else {
                System.out.println("Error al crear el intercambio.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


