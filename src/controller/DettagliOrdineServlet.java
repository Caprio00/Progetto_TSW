package controller;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
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

@WebServlet("/dettagliordine")
public class DettagliOrdineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if(utente!= null && utente.isAdmin() == false){
            String id = request.getParameter("id");
            OrdiniDAO dao = new OrdiniDAO();
            Ordine ordine = dao.doRetrievebyUserIdAndOra(id,utente.getId());
            if(ordine == null) throw new MyServletException("Errore");
            request.setAttribute("ordine",ordine);
            request.getRequestDispatcher("WEB-INF/jsp/dettagliordine.jsp").forward(request,response);
        }else if(utente!= null && utente.isAdmin() == true){
            String id = request.getParameter("id");
            String userid= request.getParameter("userid");
            int useridn;
            try {
                 useridn = Integer.parseInt(userid);
            }catch (NumberFormatException er){
                throw new MyServletException("Utente non trovato");
            }
            OrdiniDAO dao = new OrdiniDAO();
            Ordine ordine = dao.doRetrievebyUserIdAndOra(id,useridn);
            if(ordine == null) throw new MyServletException("Errore");
            request.setAttribute("ordine",ordine);
            request.getRequestDispatcher("WEB-INF/jsp/dettagliordine.jsp").forward(request,response);
        }else{
            throw new MyServletException("Sezione dedicata agli utenti registrati");
        }
    }
}
