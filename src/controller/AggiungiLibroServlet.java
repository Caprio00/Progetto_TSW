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
import java.security.cert.CertificateRevokedException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/editlibro")

public class AggiungiLibroServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if(utente != null && utente.isAdmin() == true){
            CategoriaDAO cdao = new CategoriaDAO();
        String isbn = request.getParameter("id");
            Libro libro = null;
            List<Categoria> list = cdao.doRetrieveAll();
        if(isbn != null){
            request.setAttribute("titolo","Modifica libro");
            LibroDAO dao = new LibroDAO();
             libro = dao.doRetrieveByIsbn(isbn);
             if(libro ==null){
                 throw new MyServletException("Libro non trovato");
             }
            request.setAttribute("libro",libro);
        int f = list.size();
            Iterator<Categoria> iter1 = list.iterator();
            Categoria current = null;
            while (iter1.hasNext()) {
                current = iter1.next();
                for(int i=0;i<libro.getCategorie().size();i++){
                    if(current.getId() == libro.getCategorie().get(i).getId()){
                        iter1.remove();
                        break;
                    }
                }
            }
            request.setAttribute("cecked",libro.getCategorie());
        }else{
            request.setAttribute("titolo","Aggiugni libro");
        }
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
