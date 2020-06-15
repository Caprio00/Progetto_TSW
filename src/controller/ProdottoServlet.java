package controller;

import model.Categoria;
import model.CategoriaDAO;
import model.Libro;
import model.LibroDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/libro")
public class ProdottoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String isbn = (String) request.getParameter("id");

        try {
            LibroDAO libroDAO = new LibroDAO();
            Libro libro = libroDAO.doRetrieveByIsbn(isbn);
            request.setAttribute("libro",libro);
        }
           catch(NumberFormatException er){
                throw  new MyServletException("Libro non trovato");
           }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/prodotto.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}