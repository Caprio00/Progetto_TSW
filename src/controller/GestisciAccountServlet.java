package controller;

import model.Ordine;
import model.OrdiniDAO;
import model.Utente;
import model.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/gestisciutenti")
public class GestisciAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente= (Utente) session.getAttribute("utente");
        if(utente != null && utente.isAdmin() == true){
            UtenteDAO odao =  new UtenteDAO();
            ArrayList<Utente> li = (ArrayList<Utente>) odao.doRetrieveAll();
            li.remove(odao.doRetrieveById(utente.getId()));
            request.setAttribute("utenti",li);
            request.getRequestDispatcher("/WEB-INF/jsp/gestisciutenti.jsp").forward(request,response);
        }else{
            throw new MyServletException("Sezione dedicata agli amministratori");
        }
    }
}
