package controller;

import model.Login;
import model.LoginDAO;
import model.Utente;
import model.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession login = request.getSession();
        RequestDispatcher requestDispatcher;
        if(login.getAttribute("utente")!= null){
            requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/profilo.jsp");
        }
        else{
         requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
        }
        requestDispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        UtenteDAO dao = new UtenteDAO();
        if(username == null){
            String error = "";
            username = request.getParameter("usernamesubmit");
            String password = request.getParameter("passwordsubmit");
            String passwordconfirm = request.getParameter("passwordsubmitconfirm");
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            String sesso = request.getParameter("sesso");
            String email = request.getParameter("email");
            Boolean ceck = true;
            if(nome.length() == 0){
                error=error + "Il nome non può essere vuoto<br>";
                ceck=false;
            }else if(!nome.matches("/[a-zA-Z]+/g")){
                error=error + "Il nome deve contenere solo caratteri<br>";
                ceck=false;
            }
            if(cognome.length() ==0){
                error=error + "Il cognome non può essere vuoto<br>";
                ceck=false;
            }else if(!cognome.matches("/[a-zA-Z]+/g")){
                error=error + "Il cognome deve contenere solo caratteri<br>";
                ceck=false;
            }
            Utente us = dao.doRetrieveByUsername(username);
            if (username.length() == 0) {
                error=error + "L'username non può essere vuoto<br>";
                ceck=false;
            }else if(us != null){
                error=error + "L'username é già stato usato<br>";
                ceck=false;
            }
            if(email.length()==0){
                error=error + "L'email non può essere vuota<br>";
                ceck=false;
            }else if(dao.doRetrieveByEmail(email) == true){
                error=error + "L'email é già stata usata da un altro account<br>";
                ceck=false;
            }else if(!email.matches("/^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$/")){
                error=error + "L'email é mal formata, l'email deve contere una @ e un . ad esempio: \"example@email.com\"<br>";
                ceck=false;
            }
            if(password.length() ==0){
                error=error + "La password non può essere vuota<br>";
                ceck=false;
            }else if(!password.matches("/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$/g")){
                error=error + "La password é mal formata.<br>&nbsp;&nbsp;&nbsp;La password deve essere lunga 8 caratteri e al massimo 32<br>&nbsp;&nbsp;&nbsp;Deve contenere una lettere maiuscola e una minuscola<br>&nbsp;&nbsp;&nbsp;Deve contenere un numero<br>";
                ceck=false;
            }else if(!password.equals(passwordconfirm)){
                error=error + "La password e la conferma password sono diverse<br>";
                ceck=false;
            }
            if(password.length() ==0){
                error=error + "La conferma password non può essere vuota<br>";
                ceck=false;
            }
            if(!(sesso.equals("Maschio")||sesso.equals("Femmina"))){
                error=error + "Sesso non valido<br>";
                ceck=false;
            }
            if(ceck== true){
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
                error= "Sono stati trovati i seguenti errori: <br><br>" + error;
                request.setAttribute("fomrerror",error);
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
                dispatcher.forward(request,response);
            }
        }else{
            String password = request.getParameter("password");
            String ricordaAccesso = request.getParameter("remember");
            Utente utente= dao.doRetrieveByUsernamePassword(username,password);
            HttpSession session = request.getSession();
            session.setAttribute("utente",utente);
            if(utente != null){
                if(ricordaAccesso!= null && ricordaAccesso.equals("yes")){
                    Login login = new Login();
                    login.setIdutente(utente.getId());
                    login.setToken(UUID.randomUUID().toString());
                    login.setTime(Timestamp.from(Instant.now()));

                    LoginDAO loginDAO =  new LoginDAO();
                    loginDAO.doSave(login);

                    Cookie cookie = new Cookie("login", login.getId() + "_" + login.getToken());
                    cookie.setMaxAge(30 * 24 * 60 * 60); // 30 giorni
                    response.addCookie(cookie);
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/profilo");
                dispatcher.forward(request, response);
            }
            else {
                request.setAttribute("errorserverlogin", "Username o password errati!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
                dispatcher.forward(request, response);
            }
        }

    }
}
