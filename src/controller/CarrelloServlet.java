package controller;

import model.Carrello;
import model.Libro;
import model.LibroDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/carrello")
public class CarrelloServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrello carrello= null;
        if(request.getParameter("id")!=null) {
            if (session.getAttribute("carrello") == null) {
                carrello = new Carrello();
            } else {
                carrello = (Carrello) session.getAttribute("carrello");
            }
            LibroDAO libroDAO = new LibroDAO();
            Libro libro = libroDAO.doRetrieveByIsbn(request.getParameter("id"));
            carrello.setLibro(libro);
            session.setAttribute("carrello", carrello);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/carrello.jsp");
        dispatcher.forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}
