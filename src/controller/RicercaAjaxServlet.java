
package controller;

import model.Libro;
import model.LibroDAO;
import org.json.JSONArray;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ricercaajax")
public class RicercaAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final LibroDAO libroDAO = new LibroDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONArray prodJson = new JSONArray();
		String query = request.getParameter("q");
		if (query != null) {
			List<Libro> libri = libroDAO.doRetrieveByNomeOrDescrizione(query+"*", 0, 10);
			for (Libro p : libri) {
				prodJson.put(p.getTitolo());
			}
		}
		response.setContentType("application/json");
		response.getWriter().append(prodJson.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
