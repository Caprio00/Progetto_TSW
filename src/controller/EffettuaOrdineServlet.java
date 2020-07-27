package controller;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import model.*;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Instant;

@WebServlet("/effettuaordine")
public class EffettuaOrdineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrdiniDAO dao = new OrdiniDAO();
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        Utente utente = (Utente) session.getAttribute("utente");
        LibroDAO ldao = new LibroDAO();
        Libro ltemp;
        if(utente!= null && utente.isAdmin() == false){
            if(carrello != null && carrello.getTotprodotti() != 0){
                Ordine o = new Ordine();
                o.setLibri(carrello.getLibro());
                o.setOraordine("" + Instant.now().getEpochSecond());
                o.setQuantita(carrello.getTotprodotti());
                o.setTotale(carrello.getTotaleLordo());
                dao.doSave(o,utente);
                for(int i=0;i<carrello.getLibro().size();i++){
                    ltemp = ldao.doRetrieveByIsbn(carrello.getLibro().get(i).getIsbn());
                    if(ltemp.getTipo().equals("cartaceo")) {
                        if(carrello.getLibro().get(i).getQuantitaCarrello() <= ltemp.getNumero_disponibili()) {
                            ltemp.setNumero_disponibili(ltemp.getNumero_disponibili() - carrello.getLibro().get(i).getQuantitaCarrello());
                            ltemp.setAcquisti(ltemp.getAcquisti() + carrello.getLibro().get(i).getQuantitaCarrello());
                        }
                        else
                            throw new MyServletException("La quantitá inserita nel carrello eccede quella disponibile");
                    ldao.doUpdate(ltemp);
                    }
                }
                session.setAttribute("carrello",null);
                response.sendRedirect("ordini");
            }else{
                throw new MyServletException("Non hai prodotti nel carrello");
            }
        }else{
            throw new MyServletException("Non sei loggato o sei un amministratore");
        }
    }

}
