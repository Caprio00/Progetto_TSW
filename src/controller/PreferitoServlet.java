package controller;

import model.Libro;
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
@WebServlet("/preferiti")
public class PreferitoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("utente");
        if(user != null && user.isAdmin() == false) {
            PreferitoDAO dao = new PreferitoDAO();
            List<Libro> libri = dao.doRetrieveByUserId(user);
            request.setAttribute("libri", libri);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/preferiti.jsp");
            requestDispatcher.forward(request, response);
        }else{
            throw new MyServletException("Impossibile accedere alla pagina, perfavore esegui prima il login");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {doPost(request,response);
    }
}
