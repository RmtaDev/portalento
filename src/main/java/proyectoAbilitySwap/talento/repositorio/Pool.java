package proyectoAbilitySwap.talento.repositorio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Pool {

	private static final String fuente = "java:comp/env/jdbc/pool";
	private static DataSource fuenteDatos = null;

	
	private static DataSource iniciarFuente() {
		DataSource ds = null;

		try {
			InitialContext ct = new InitialContext();
			ds = (DataSource) ct.lookup(fuente);
		} catch (Exception e) {
			System.err.println("Error al acceder a los recursos del context.xml para configurar el Pool de la base de datos");
		}
		return ds;
	}
	// patron singleton
	private Pool() {
		fuenteDatos = iniciarFuente();
	}
	
	static
	{
		new Pool();
	}

	

	public static Connection getConnection() {
		Connection conexion = null;
		try {
			conexion = fuenteDatos.getConnection();
		} catch (Exception e) {
			System.err.println("Imposible obtener conexión a la base de datos");
		}
		return conexion;
	}

	public static void liberarRecursos(Connection conexion, Statement st, ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
			}

			catch (SQLException e) {
				System.err.println("Error al liberar el ResultSet");
			}
		}
		if (st != null) {
			try {
				st.close();
			}

			catch (SQLException e) {
				System.err.println("Error al liberar el Statement");
			}
		}

		if (conexion != null) {
			try {
				conexion.close();
			}

			catch (SQLException e) {
				System.err.println("Error al liberar la conexion");
			}
		}
	}

}
