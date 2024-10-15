package proyectoAbilitySwap.talento.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import proyectoAbilitySwap.talento.beans.Categoria;

public class CategoriasRepository {

		public static final String EXISTE_CATEGORIA = "SELECT * FROM abilityswapbd.categorias;";
		
		public List<Categoria> extraerCategoria() throws SQLException {
			List<Categoria> listaCategorias = null;

			Connection connection = Pool.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(EXISTE_CATEGORIA);
			listaCategorias = new ArrayList<Categoria>();
			while (rs.next()) {
				String categoria = rs.getString("nombre");
				int id = rs.getInt("id_categoria");
				Categoria categorias = new Categoria(categoria, id);
				listaCategorias.add(categorias);
			}
			Pool.liberarRecursos(connection, statement, rs);

			return listaCategorias;
		}
		
	}

