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

@WebServlet("/rimuovicarrello")
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
            response.setContentType("text/plain");
            response.getWriter().write ("ok" + " " + carrello.convertiEuro(carrello.getTotaleNetto()) + " " + carrello.convertiEuro(carrello.getIva())  + " " + carrello.convertiEuro(carrello.getTotale()) + " " + carrello.convertiEuro(carrello.getCostoSpedizione()) + " " + carrello.convertiEuro(carrello.getTotaleLordo()) + " " + carrello.getTotprodotti());
        } catch(NumberFormatException er){
            throw  new MyServletException("Libro non trovato");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
