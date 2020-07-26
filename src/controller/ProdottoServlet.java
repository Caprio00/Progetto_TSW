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
import java.util.List;

@WebServlet("/libro")
public class ProdottoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String isbn = (String) request.getParameter("id");
            LibroDAO libroDAO = new LibroDAO();
            Libro libro = libroDAO.doRetrieveByIsbn(isbn);
            if(libro != null){
            request.setAttribute("libro",libro);
            HttpSession session = request.getSession();
            Utente utente = (Utente) session.getAttribute("utente");
            if(utente!= null){
                PreferitoDAO dao = new PreferitoDAO();
                Libro user = dao.doRetrieveByUserIdandIsbn(utente,libro);
                if(user != null){
                    request.setAttribute("preferiti",1);
                }
                if(utente.isAdmin() ==  false){
                    OrdiniDAO odao =  new OrdiniDAO();
                    Boolean b = odao.ceckIfExistbyIsbnAndUserID(libro.getIsbn(),utente.getId());
                    if(b) request.setAttribute("ebookbuy",b);
                }
            }
            }else {
                throw  new MyServletException("Libro non trovato");
            }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/prodotto.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}