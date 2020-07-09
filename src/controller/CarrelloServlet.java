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
            if(carrello.getLibro().size() == 0){
                carrello.setLibro(libro);
                session.setAttribute("carrello", carrello);
                response.sendRedirect("carrello");
            }else{
                for(int i=0;i<carrello.getLibro().size();i++){
                    if(carrello.getLibro().get(i).getIsbn().equals(libro.getIsbn()) && carrello.getLibro().get(i).getTipo().equals(libro.getTipo()) == true && libro.getTipo().equals("ebook") == true){
                       throw new MyServletException("Errore");
                    }
                }
                    carrello.setLibro(libro);
                    session.setAttribute("carrello", carrello);
                    response.sendRedirect("carrello");
                    return;
            }

        }else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/carrello.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}
