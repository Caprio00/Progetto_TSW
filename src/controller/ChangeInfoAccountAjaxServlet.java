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

@WebServlet("/changeinfo")
public class ChangeInfoAccountAjaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("utente");
        String nome = request.getParameter("id");
        String cognome = request.getParameter("id1");
        String email = request.getParameter("id2");
        String username = request.getParameter("id3");
        if(user!= null){
            String error = "Sono stati trovati i seguenti errori:\n\n";
            UtenteDAO dao= new UtenteDAO();
            response.setContentType("application/text");
            if(nome.length() == 0){
                error=error + "Il nome non può essere vuoto\n";
            }else if(!nome.matches("[a-zA-Z]+")){
                error=error + "Il nome deve contenere solo caratteri\n";
            }
            if(cognome.length() ==0){
                error=error + "Il cognome non può essere vuoto\n";
            }else if(!cognome.matches("[a-zA-Z]+")){
                error=error + "Il cognome deve contenere solo caratteri\n";
            }
            Utente us = dao.doRetrieveByUsername(username);
            if (username.length() == 0) {
                error=error + "L'username non può essere vuoto\n";
            }else if(us != null && !user.getUsername().equals(us.getUsername())){
                error=error + "L'username é già stato usato\n";
            }
            if(email.length()==0){
                error=error + "L'email non può essere vuota<br>";
            }else if(dao.doRetrieveByEmail(email) == true && !user.getEmail().equals(email)){
                error=error + "L'email é già stata usata da un altro account<br>";
            }else if(!email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$")){
                error=error + "L'email é mal formata, l'email deve contere una @ e un . ad esempio: \"example@email.com\"<br>";
            }
            if(error.length() > 44){
                response.getWriter().append(error);
            }else{
                user.setNome(nome);
                user.setCognome(cognome);
                user.setEmail(email);
                user.setUsername(username);
                dao.doUpdateUserInfo(user);
                session.setAttribute("utente",user);
                response.getWriter().append("true");
            }
        }
    }
}
