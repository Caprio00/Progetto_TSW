package controller;

import model.Categoria;
import model.CategoriaDAO;
import model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/editcategoria")
public class AggiungiCategoriaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if(utente != null && utente.isAdmin() == true){
            String errore = "";
            String id = request.getParameter("id");
            CategoriaDAO dao =  new CategoriaDAO();
            String titolo = request.getParameter("titolo");
            String descrizione =  request.getParameter("descrizione");
            int idnum = -1;
            try {
                if(id != null) {
                    idnum = Integer.parseInt(id);
                }
            }catch(NumberFormatException ex){
                throw new MyServletException("La categoria da modificare non esiste");
            }
            if(titolo == null || titolo.length() == 0){
                errore += "Il campo titolo é vuoto<br>";
            }else if(id!= null){
                Categoria d = dao.doRetriveByNome(titolo);
                if(d!= null &&  d.getId() != idnum){
                errore += "Una categoria con questo nome esiste gia!<br>";
                }
            }else if (id== null && dao.doRetriveByNome(titolo) != null){
                errore += "Una categoria con questo nome esiste gia!<br>";
            }
            if(descrizione == null || descrizione.length() == 0){
                errore += "Il campo descrizione é vuoto<br>";
            }
            Categoria c =  null;
            if(errore != ""){
                if(id != null){
                    request.setAttribute("titolo","Modifica categoria");
                    request.setAttribute("categoria",dao.doRetriveById(idnum));
                }else{
                    request.setAttribute("titolo","Aggiungi categoria");
                }
                request.setAttribute("errore",errore);
                request.getRequestDispatcher("WEB-INF/jsp/addcategoria.jsp").forward(request,response);
            }else{
            if(id != null){
                c = dao.doRetriveById(idnum);
                c.setNome(titolo);
                c.setDescrizione(descrizione);
                dao.doUpdate(c);
            }else{
                c = new Categoria();
                c.setDescrizione(descrizione);
                c.setNome(titolo);
                dao.doSave(c);
            }
            List<Categoria> categorie = dao.doRetrieveAll();
            getServletContext().setAttribute("categorie", categorie);
            response.sendRedirect("categoria?id="+c.getId());
            }
        }else{
            throw new MyServletException("Sezione dedicata ai soli amministratori, perfavore prima fai il login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if(utente != null && utente.isAdmin() == true){
            String id = request.getParameter("id");
            CategoriaDAO dao =  new CategoriaDAO();
            if(id != null){
                request.setAttribute("titolo","Modifica categoria");
                int idnum = -1;
                try {
                    idnum = Integer.parseInt(id);
                }catch(NumberFormatException ex){
                    throw new MyServletException("La categora da modificare non esiste");
                }
                Categoria c = dao.doRetriveById(idnum);
                request.setAttribute("categoria",c);
            }else{
                request.setAttribute("titolo","Aggiungi categoria");
            }
            request.getRequestDispatcher("WEB-INF/jsp/addcategoria.jsp").forward(request,response);
        }else{
            throw new MyServletException("Sezione dedicata ai soli amministratori, perfavore prima fai il login");
        }
    }
}
