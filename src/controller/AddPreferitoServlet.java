package controller;

import model.LibroDAO;
import model.Preferito;
import model.PreferitoDAO;
import model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/addremovepreferito")
public class AddPreferitoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("utente");
        String isbn = request.getParameter("id");
        String flag = request.getParameter("flag");
        LibroDAO ldao = new LibroDAO();
        if(user != null && user.isAdmin() == false) {
            PreferitoDAO dao = new PreferitoDAO();
            if(ldao.doRetrieveByIsbn(isbn) == null){
                return;
            }else if(flag.equals("0")) {
                Preferito n = new Preferito();
                n.setUtente(user.getId());
                n.setLibro(isbn);
                dao.add(n);
                response.setContentType("application/text");
                response.getWriter().append("0");
            }else if(flag.equals("1")){
                Preferito n = new Preferito();
                n.setUtente(user.getId());
                n.setLibro(isbn);
                dao.remove(n);
                response.setContentType("application/text");
                response.getWriter().append("1");
            }else{
                return;
            }
        }else{
            return;
        }
    }
}
