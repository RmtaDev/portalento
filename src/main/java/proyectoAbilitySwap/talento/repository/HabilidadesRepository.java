package proyectoAbilitySwap.talento.repository;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import proyectoAbilitySwap.talento.beans.HabilidadDemandada;
import proyectoAbilitySwap.talento.beans.HabilidadOfertada;

public class HabilidadesRepository implements IHabilidadesDemandadasRepository, IHabilidadesOfertadasRepository {

	public static final String INSERTAR_HABILIDAD_OFERTADA = "INSERT INTO habilidades_ofertadas (usuario, nombre, id_categoria) VALUES (?, ?, ?)";
	public static final String INSERTAR_HABILIDAD_DEMANDA = "INSERT INTO habilidades_demandadas (usuario, nombre, id_categoria) VALUES (?, ?, ?)";
	public static final String CONSULTAR_HABILIDAD_DEMANDA = "SELECT * FROM abilityswapbd.habilidades_demandadas WHERE usuario = ?";
	public static final String CONSULTAR_HABILIDAD_OFERTADA = "SELECT * FROM abilityswapbd.habilidades_ofertadas WHERE usuario = ?";
	public static final String BORRAR_HABILIDAD_OFERTADA = "DELETE FROM `abilityswapbd`.`habilidades_ofertadas` WHERE (`id_habilidad` = ?)";
	public static final String BORRAR_HABILIDAD_DEMANDA = "DELETE FROM `abilityswapbd`.`habilidades_demandadas` WHERE (`id_habilidad` = ?)";

	@Override
	public void insertarHabilidadOfertada(String nombreHabilidad, int idUsuario, int idCategoria) throws SQLException {
		Connection connection = obtenerConexionlocal();
		PreparedStatement ps = connection.prepareStatement(INSERTAR_HABILIDAD_OFERTADA);

		ps.setInt(1, idUsuario);
		ps.setString(2, nombreHabilidad);
		ps.setInt(3, idCategoria);

		int filasInsertadas = ps.executeUpdate();

		liberarRecursos(connection, ps, null);
		return;
	}

	@Override
	public List<HabilidadOfertada> consultarTodasOfertadas(int idUsuario) throws SQLException {
		List<HabilidadOfertada> listaHabilidadOfertada = null;
		Connection connection = obtenerConexionlocal();
		PreparedStatement ps = connection.prepareStatement(CONSULTAR_HABILIDAD_OFERTADA);
		ps.setInt(1, idUsuario);

		ResultSet rs = ps.executeQuery();
		listaHabilidadOfertada = new ArrayList<HabilidadOfertada>();

		while (rs.next()) {
			int id_habilidad = rs.getInt("id_habilidad");
			int usuario = rs.getInt("usuario");
			String nombre = rs.getString("nombre");
			int id_categoria = rs.getInt("id_categoria");
			// Ya tengo todos los atributos que representan a una oferta, puedo crear el
			// objeto Oferta con ellos
			HabilidadOfertada habilidadOfertada = new HabilidadOfertada(id_habilidad, usuario, nombre, id_categoria);
			listaHabilidadOfertada.add(habilidadOfertada);
		}

		liberarRecursos(connection, ps, rs);
		return listaHabilidadOfertada;
	}

	@Override
	public void borrarHabilidadOfertada(int idHabilidad) throws SQLException {
		Connection connection = obtenerConexionlocal();
		PreparedStatement ps = connection.prepareStatement(BORRAR_HABILIDAD_OFERTADA);

		ps.setInt(1, idHabilidad);
		ps.executeUpdate();

		liberarRecursos(connection, ps, null);
	}

	@Override
	public void insertarHabilidadDemandada(String nombreHabilidad, int idUsuario, int idCategoria) throws SQLException {
		Connection connection = obtenerConexionlocal();
		PreparedStatement ps = connection.prepareStatement(INSERTAR_HABILIDAD_DEMANDA);

		ps.setInt(1, idUsuario);
		ps.setString(2, nombreHabilidad);
		ps.setInt(3, idCategoria);

		int filasInsertadas = ps.executeUpdate();
		liberarRecursos(connection, ps, null);
	}

	@Override
	public List<HabilidadDemandada> consultarTodasDemandadas(int idUsuario) throws SQLException {
		List<HabilidadDemandada> listaHabilidadDemandada = null;
		Connection connection = obtenerConexionlocal();
		PreparedStatement ps = connection.prepareStatement(CONSULTAR_HABILIDAD_DEMANDA);
		ps.setInt(1, idUsuario);

		ResultSet rs = ps.executeQuery();
		listaHabilidadDemandada = new ArrayList<HabilidadDemandada>();

		while (rs.next()) {
			int id_habilidad = rs.getInt("id_habilidad");
			int usuario = rs.getInt("usuario");
			String nombre = rs.getString("nombre");
			int id_categoria = rs.getInt("id_categoria");
			HabilidadDemandada habilidadDemandada = new HabilidadDemandada(id_habilidad, usuario, nombre, id_categoria);
			listaHabilidadDemandada.add(habilidadDemandada);
		}

		liberarRecursos(connection, ps, rs);
		return listaHabilidadDemandada;
	}

	@Override
	public void borrarHabilidadDemandada(int idHabilidad) throws SQLException {
		Connection connection = obtenerConexionlocal();
		PreparedStatement ps = connection.prepareStatement(BORRAR_HABILIDAD_DEMANDA);

		ps.setInt(1, idHabilidad);
		ps.executeUpdate();

		liberarRecursos(connection, ps, null);
	}

	public static Connection obtenerConexionlocal() {
		Driver driver = null;
		Connection connection = null;

		try {
			driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/abilityswapbd", "root", "user0");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}

	public static void liberarRecursos(Connection conexion, Statement st, ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.err.println("Error al liberar el ResultSet");
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				System.err.println("Error al liberar el Statement");
			}
		}

		if (conexion != null) {
			try {
				conexion.close();
			} catch (SQLException e) {
				System.err.println("Error al liberar la conexion");
			}
		}
	}

	public static void main(String[] args) {
		HabilidadesRepository habilidadesRepo = new HabilidadesRepository();

		// Parámetros para insertar una habilidad demandada
		String nombreHabilidadDemandada = "Latín";
		int idUsuarioDemandada = 3;
		int idCategoriaDemandada = 2;

		// Parámetros para insertar una habilidad ofertada
		String nombreHabilidadOfertada = "Desarrollo Web";
		int idUsuarioOfertada = 3;
		int idCategoriaOfertada = 1;

		try {
			// Inserción de habilidad demandada
			habilidadesRepo.insertarHabilidadDemandada(nombreHabilidadDemandada, idUsuarioDemandada,
					idCategoriaDemandada);
			System.out.println("Inserción exitosa de habilidad demandada en la base de datos.");

			// Inserción de habilidad ofertada
			habilidadesRepo.insertarHabilidadOfertada(nombreHabilidadOfertada, idUsuarioOfertada, idCategoriaOfertada);
			System.out.println("Inserción exitosa de habilidad ofertada en la base de datos.");

			// Consulta de todas las habilidades demandadas de un usuario
			System.out.println("Consultando todas las habilidades demandadas del usuario con ID " + idUsuarioDemandada);
			List<HabilidadDemandada> habilidadesDemandadas = habilidadesRepo
					.consultarTodasDemandadas(idUsuarioDemandada);
			for (HabilidadDemandada habilidad : habilidadesDemandadas) {
				System.out.println("Habilidad Demandada: " + habilidad.getNombre());
			}

			// Consulta de todas las habilidades ofertadas de un usuario
			System.out.println("Consultando todas las habilidades ofertadas del usuario con ID " + idUsuarioOfertada);
			List<HabilidadOfertada> habilidadesOfertadas = habilidadesRepo.consultarTodasOfertadas(idUsuarioOfertada);
			for (HabilidadOfertada habilidad : habilidadesOfertadas) {
				System.out.println("Habilidad Ofertada: " + habilidad.getNombre());
			}

			// Borrado de una habilidad demandada por ID
			System.out.println("Borrando habilidad demandada con ID 1");
			habilidadesRepo.borrarHabilidadDemandada(1);
			System.out.println("Habilidad demandada borrada exitosamente.");

			// Borrado de una habilidad ofertada por ID
			System.out.println("Borrando habilidad ofertada con ID 1");
			habilidadesRepo.borrarHabilidadOfertada(1);
			System.out.println("Habilidad ofertada borrada exitosamente.");

		} catch (SQLException e) {
			System.err.println("Error en la operación con la base de datos: " + e.getMessage());
		}
	}
}