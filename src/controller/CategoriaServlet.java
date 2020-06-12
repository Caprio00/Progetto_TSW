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
        String maxlimiti = request.getParameter("n");
        if(maxlimiti == null){
            maxlimiti = "10";
            request.setAttribute("n",10);
        }
        request.setAttribute("n",maxlimiti);
        int maxlimitint = Integer.parseInt(maxlimiti);
        List<Libro> libri = null;
        Categoria categoriaAssociata = null;
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/view-categorie.jsp");
        int numlibri = libroDAO.countByCategoria(Integer.parseInt(id));
        if(numlibri<maxlimitint){
            request.setAttribute("totlibri", 1);
        }else {
            int totlibriindex = (int) Math.ceil((double) numlibri/maxlimitint);
            request.setAttribute("totlibri",totlibriindex);
        }
        int l=-1;
        try{
            if(page == null){
                libri = libroDAO.doRetrieveByCategoria(Integer.parseInt(id),0,maxlimitint);
                categoriaAssociata=cat.doRetriveById(Integer.parseInt(id));
                request.setAttribute("limit","1");
                if(numlibri<maxlimitint){
                    request.setAttribute("next", "-1");
                }
            }else{
                l = Integer.parseInt(page)*maxlimitint;
                if(l<=0){
                    throw new MyServletException("Non ci sono libri presenti in questa pagina");
                }
                libri = libroDAO.doRetrieveByCategoria(Integer.parseInt(id),l-maxlimitint,maxlimitint);
                if(libri.size() == 0){
                    throw new MyServletException("Non ci sono libri presenti in questa pagina");
                }
                categoriaAssociata=cat.doRetriveById(Integer.parseInt(id));
                request.setAttribute("limit",page);
                int limite= l + 1;
                if(limite > numlibri){
                    request.setAttribute("next", "-1");
                }
            }
            if(categoriaAssociata == null){
               throw new MyServletException("Categoria non trovata");
            }else{
                request.setAttribute("libri", libri);
                request.setAttribute("categoria",categoriaAssociata);
            }
        } catch (NumberFormatException er){
            throw new MyServletException("Categoria non trovata");
        }

        requestDispatcher.forward(request, response);

    }


}
