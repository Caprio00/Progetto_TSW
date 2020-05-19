package controller;

import model.Categoria;
import model.CategoriaDAO;
import model.LibroDAO;
import model.Libro;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/categoria")
public class CategoriaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LibroDAO libroDAO = new LibroDAO();
        CategoriaDAO cat = new CategoriaDAO();
        String id = request.getParameter("id");
        List<Libro> libri = libroDAO.doRetrieveByCategoria(Integer.parseInt(id),0,10);
        Categoria categpriaAssociata=cat.doRetriveById(Integer.parseInt(id));
        request.setAttribute("libri", libri);
        request.setAttribute("categoria",categpriaAssociata);
        request.setAttribute("limit","1");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/view-categorie.jsp");
        requestDispatcher.forward(request, response);



    }
}
