package proyectoAbilitySwap.talento.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.google.gson.Gson;

import proyectoAbilitySwap.talento.beans.Usuario;
import proyectoAbilitySwap.talento.servicio.UsuarioService;
import proyectoAbilitySwap.talento.validacion.Validar;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			String infoUsuarioJson =  request.getReader().readLine();
			Gson gson = new Gson();
			Usuario usuario = gson.fromJson(infoUsuarioJson, Usuario.class);
			
			Validar validar = new Validar();
			if (validar.validarNombre(usuario.getUsuario()) && validar.validarPassword(usuario.getPassword()))
			{
				UsuarioService usuarioService = new UsuarioService();
				if (usuarioService.existeUsuario(usuario))
					{
						System.out.println("El usuario existe");
						response.setStatus(200);
					} else {
						System.out.println("El usuario NO existe");
						response.setStatus(403);
					}
			} else {
				//si no es válido, devolver 400
				System.out.println("Datos incorrectos");
				response.setStatus(400);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Hubo un error");
			response.setStatus(500);
		}
	}
}
