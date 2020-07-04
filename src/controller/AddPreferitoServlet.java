package controller;

import model.Libro;
import model.Preferito;
import model.PreferitoDAO;
import model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/addpreferito")
public class AddPreferitoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("utente");
        String isbn = request.getParameter("id");
        if(user != null && user.isAdmin() == false) {
            PreferitoDAO dao = new PreferitoDAO();
            Preferito n = new Preferito();
            n.setUtente(user.getId());
            n.setLibro(isbn);
            dao.add(n);
            response.sendRedirect("preferiti");
        }else{
            throw new MyServletException("Errore!");
        }
    }
}
