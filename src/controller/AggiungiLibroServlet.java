package controller;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/editlibro")

public class AggiungiLibroServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if(utente != null && utente.isAdmin() == true){
            String isbn = request.getParameter("id");
            if(isbn != null){
                request.setAttribute("titolo","Modifica libro");
                LibroDAO dao = new LibroDAO();
                Libro libro = dao.doRetrieveByIsbn(isbn);
                request.setAttribute("libro",libro);
            }else{
                request.setAttribute("titolo","Aggiugni libro");
            }
            CategoriaDAO dao = new CategoriaDAO();
            List<Categoria> list = dao.doRetrieveAll();
            request.setAttribute("categorie",list);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/addlibro.jsp");
            dispatcher.forward(request,response);
        }else{
            throw new MyServletException("Sezione dedicata ai soli amministratori, perfavore prima fai il login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {doGet(request,response);}
}
