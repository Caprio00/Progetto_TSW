package controller;

import model.Ordine;
import model.OrdiniDAO;
import model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/gestisciordini")
public class GestisciOrdiniServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente= (Utente) session.getAttribute("utente");
        if(utente != null && utente.isAdmin() == true){
            OrdiniDAO odao =  new OrdiniDAO();
            ArrayList<Ordine> li = odao.doRetrieveAll();
            request.setAttribute("ordini",li);
            request.getRequestDispatcher("/WEB-INF/jsp/gestisciordini.jsp").forward(request,response);
        }else{
            throw new MyServletException("Sezione dedicata agli amministratori");
        }
    }
}
