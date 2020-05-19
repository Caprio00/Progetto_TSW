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
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/changepage")

public class IndexNextBackPageServlet extends HttpServlet {

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            LibroDAO libroDAO = new LibroDAO();
            String limit= request.getParameter("page");
            int l = Integer.parseInt(limit);
            l=l*10;
            List<Libro> prodotti=null;
            prodotti = libroDAO.doRetrieveAll(l-10,10);
            if(libroDAO.doRetrieveAll(l,10).isEmpty()){
                request.setAttribute("next", "-1");
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
            request.setAttribute("prodotti", prodotti);
            request.setAttribute("limit", limit);
            requestDispatcher.forward(request, response);
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

}
