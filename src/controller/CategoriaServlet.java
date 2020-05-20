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
        String page = request.getParameter("page");
        List<Libro> libri = null;
        Categoria categoriaAssociata = null;
        int l=-1;
        if(page == null){
            libri = libroDAO.doRetrieveByCategoria(Integer.parseInt(id),0,10);
            categoriaAssociata=cat.doRetriveById(Integer.parseInt(id));
            request.setAttribute("limit","1");
        }else{
            l = Integer.parseInt(page)*10;
            libri = libroDAO.doRetrieveByCategoria(Integer.parseInt(id),l-10,10);
            categoriaAssociata=cat.doRetriveById(Integer.parseInt(id));
            request.setAttribute("limit",page);
        }
        if( l!=-1){
            if(libroDAO.doRetrieveByCategoria(Integer.parseInt(id),l,10).isEmpty())
                request.setAttribute("next", "-1");
        }else{
            if(libroDAO.doRetrieveByCategoria(Integer.parseInt(id),10,10).isEmpty())
                request.setAttribute("next", "-1");
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/view-categorie.jsp");

        if(categoriaAssociata == null){
            requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/sectionnotfound.jsp");
        }else{
            request.setAttribute("libri", libri);
            request.setAttribute("categoria",categoriaAssociata);
        }

        requestDispatcher.forward(request, response);

    }


}
