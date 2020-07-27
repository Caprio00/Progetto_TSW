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

@WebServlet("/ceckpassword")
public class CeckPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("utente");
        String password = request.getParameter("id");
        if(user!= null && password != null){
            UtenteDAO dao= new UtenteDAO();
            response.setContentType("application/text");
            if(dao.ceckPassword(user.getUsername(),password)){
                response.getWriter().append("false");
            }else{
                response.getWriter().append("true");
            }
        }
    }
}
