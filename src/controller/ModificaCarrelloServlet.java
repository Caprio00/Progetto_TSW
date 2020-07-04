package controller;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ModificaCarrello")
public class ModificaCarrelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String id = request.getParameter("id");
            int quantita = Integer.parseInt(request.getParameter("quantita"));
            int disponibili = 0;
            if(quantita<= 0){
                quantita =1;
            }
        int prezzoTotaleProdotto = 0;
        Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
        try {
           ArrayList<Libro> libri = carrello.getLibro();
           for (Libro l : libri){
                if(l.getIsbn().equals(id)){
                    if(quantita>l.getNumero_disponibili()){
                        if(! l.getTipo().equals("ebook")){
                            quantita=l.getNumero_disponibili();
                        }
                    }
                    l.setQuantitaCarrello(quantita);
                    carrello.aggiornaTotProdotti();
                    prezzoTotaleProdotto = l.getQuantitaCarrello()*l.getPrezzo();
                    disponibili = l.getNumero_disponibili();
                }
            }
           carrello.setListlibro(libri);
           request.getSession().setAttribute("carrello", carrello);
            response.setContentType("text/plain");
            response.getWriter().write("" + carrello.convertiEuro(prezzoTotaleProdotto) + " " + carrello.convertiEuro(carrello.getTotaleNetto()) + " " + carrello.convertiEuro(carrello.getIva())  + " " + carrello.convertiEuro(carrello.getTotale()) + " " + carrello.convertiEuro(carrello.getCostoSpedizione()) + " " + carrello.convertiEuro(carrello.getTotaleLordo()) + " " + carrello.getTotprodotti() + " " + disponibili);
        }
        catch(NumberFormatException er){
            throw  new MyServletException("Libro non trovato");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}