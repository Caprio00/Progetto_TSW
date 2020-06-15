package controller;

import model.Utente;
import model.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String ricordaAccesso = request.getParameter("remember");
        String nome = request.getParameter("nome");
        UtenteDAO dao = new UtenteDAO();
        if(nome != null){
            String cognome = request.getParameter("cognome");
            String sesso = request.getParameter("sesso");
            String email = request.getParameter("email");
            Utente utente = new Utente();
            utente.setAdmin(false);
            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setUsername(username);
            utente.setPassword(password);
            utente.setSesso(sesso);
            utente.setEmail(email);
            dao.doSave(utente);
            throw new MyServletException("Utente aggiunto");
        }else{
            Utente utente= dao.doRetrieveByUsernamePassword(username,password);
            HttpSession session = request.getSession();
            session.setAttribute("utente",utente);
            if(utente != null)
            throw new MyServletException("Utente trovato");
            else
                throw new MyServletException("Utente non trovato");

        }

    }
}
