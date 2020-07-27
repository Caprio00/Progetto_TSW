package controller;

import model.Utente;
import model.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/setutente")
public class SetUtenteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if(utente!= null && utente.isAdmin() == true){
            UtenteDAO dao = new UtenteDAO();
            String idutente = request.getParameter("id");
            String flag = request.getParameter("id1");
            int id = 0;
            try {
                id = Integer.parseInt(idutente);
            }catch (NumberFormatException er){
                throw new MyServletException("Utente non trovato!");
            }
            Utente user = dao.doRetrieveById(id);
            if(user ==  null) throw new MyServletException("Utente non trovato!");
            if(flag.equals("0")){
                dao.doSetAdmin(user,false);
            }else if(flag.equals("1")){
                dao.doSetAdmin(user,true);
            }else{
                throw new MyServletException("Errore parametri");
            }
            response.sendRedirect("gestisciutenti");
        }else{
            throw new MyServletException("Sezione dedicata agli admin!");
        }
    }
}
