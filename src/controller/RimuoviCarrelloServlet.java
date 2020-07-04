package controller;


import model.Carrello;
import model.Libro;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/RimuoviCarrelloServlet")
public class RimuoviCarrelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
            ArrayList<Libro> libri = carrello.getLibro();
            for (Libro l: libri) {
                if(l.getIsbn().equals(id)){
                    carrello.removeLibro(l);
                    break;
                }
            }
            carrello.aggiornaTotProdotti();
            request.getSession().setAttribute("carrello", carrello);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/carrello.jsp");
            dispatcher.forward(request,response);
        } catch(NumberFormatException er){
            throw  new MyServletException("Libro non trovato");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
