package conversorLibrasPesetas;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/convertir")
public class librasPesetasYViceversa extends jakarta.servlet.http.HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cantidadStr = request.getParameter("cantidad");
        String tipoConversion = request.getParameter("conversion");
        double cantidad = Double.parseDouble(cantidadStr);
        double resultado = 0;

        if ("libra".equals(tipoConversion)) {
            resultado = cantidad * 0.86;
        } else if ("peseta".equals(tipoConversion)) {
            resultado = cantidad * 166.386;
        }

        String resultadoStr = "El resultado de la conversión es: " + resultado;

        response.setContentType("text/plain");
        response.getWriter().write(resultadoStr);
    }
}


