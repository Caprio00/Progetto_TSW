package controller;

import model.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@WebServlet("/deletelibro")
public class DeleteLibroServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if(utente != null && utente.isAdmin() == true){
            String id = request.getParameter("id");
            LibroDAO dao = new LibroDAO();
            if (dao.doRetrieveByIsbn(id) != null){
                Libro l = dao.doRetrieveByIsbn(id);
                String destinazione = "img" + File.separator + l.getPath();
                String pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione)).toString();
                File file = new File(pathDestinazione);
                if(file.delete())
                {
                    dao.doDelete(id);
                    request.getRequestDispatcher("WEB-INF/jsp/deletelibro.jsp").forward(request,response);

                }
                else
                {
                    throw new MyServletException("Errore durante la cancellazione");
                }
            }else{
                throw new MyServletException("Il libro da eliminare non esiste");
            }
        }else{
            throw new MyServletException("Sezione dedicata ai soli amministratori, perfavore prima fai il login");
        }
    }
}
