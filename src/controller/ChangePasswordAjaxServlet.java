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

@WebServlet("/changepassword")
public class ChangePasswordAjaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("utente");
        String expassword = request.getParameter("id");
        String password = request.getParameter("id1");
        String passwordconfirm = request.getParameter("id2");
        if(user!= null){
            String error = "Sono stati trovati i seguenti errori:\n\n";
            UtenteDAO dao= new UtenteDAO();
            response.setContentType("application/text");
            if(dao.ceckPassword(user.getUsername(),expassword) == false) {
                error = error + "La password corrente non é corretta!\n";
            }
            if(password.length() ==0){
                error=error + "La password non può essere vuota\n";
            }else if(!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$")){
                error=error + "La password é mal formata.<br>&nbsp;&nbsp;&nbsp;La password deve essere lunga 8 caratteri e al massimo 32\n&nbsp;&nbsp;&nbsp;Deve contenere una lettere maiuscola e una minuscola\n&nbsp;&nbsp;&nbsp;Deve contenere un numero\n";
            }else if(!password.equals(passwordconfirm)){
                error=error + "La password e la conferma password sono diverse<br>";
            }
            if(error.length() > 44){
                response.getWriter().append(error);
            }else{
                dao.doUpdatePassword(user.getUsername(),password);
                response.getWriter().append("true");
            }
        }
    }
}
