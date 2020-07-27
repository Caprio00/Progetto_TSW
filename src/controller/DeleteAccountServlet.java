package controller;

import model.LoginDAO;
import model.Utente;
import model.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/cancellaccount")
public class DeleteAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if(utente!= null){
            if(utente.isAdmin() == true){
                throw  new MyServletException("Gli admin non possono cancellare il profilo");
            }
            UtenteDAO dao = new UtenteDAO();
            dao.doDisableProfile(utente,true);
            request.getSession().removeAttribute("utente");
            LoginDAO loginDAO =  new LoginDAO();
            Cookie cookies[] = request.getCookies();
            if (cookies != null) {
                Cookie cookie = Arrays.stream(cookies).filter(c -> c.getName().equals("login")).findAny().orElse(null);
                if (cookie != null) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    String id = cookie.getValue().split("_")[0];
                    loginDAO.doDelete(id);
                }
            }
            request.getRequestDispatcher("/WEB-INF/jsp/deleteaccount.jsp").forward(request,response);
        }else{
            throw new MyServletException("Sezione dedicata agli utenti registrati");
        }
    }
}
