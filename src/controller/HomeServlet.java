package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Categoria;
import model.CategoriaDAO;
import model.Libro;
import model.LibroDAO;

@WebServlet(name = "HomeServlet", urlPatterns="", loadOnStartup=1)
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void init() throws ServletException {
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		List<Categoria> categorie = categoriaDAO.doRetrieveAll();
		getServletContext().setAttribute("categorie", categorie);
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LibroDAO libroDAO = new LibroDAO();
		List<Libro> prodotti = libroDAO.doRetrieveAll(0, 10);
		request.setAttribute("limit","1");
		request.setAttribute("prodotti", prodotti);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
