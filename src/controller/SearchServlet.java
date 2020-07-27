package controller;

import model.Libro;
import model.LibroDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cerca")

public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LibroDAO d = new LibroDAO();
        List<Libro> libri = d.doRetrieveByNomeOrDescrizione(request.getParameter("q"), 0, 100);
        request.setAttribute("libri", libri);
        request.setAttribute("q",request.getParameter("q"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/search.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
