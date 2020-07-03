package controller;

import model.Categoria;
import model.CategoriaDAO;
import model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/deletecategoria")
public class DeleteCategoriaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if(utente != null && utente.isAdmin() == true){
            String id = request.getParameter("id");
            CategoriaDAO dao = new CategoriaDAO();
            int idnum = -1;
            try {
                idnum = Integer.parseInt(id);
            }catch(NumberFormatException ex){
                throw new MyServletException("La categora da eliminare non esiste");
            }
            if (dao.doRetriveById(idnum) != null){
                dao.doDelete(idnum);
                List<Categoria> categorie = dao.doRetrieveAll();
                getServletContext().setAttribute("categorie", categorie);
                request.getRequestDispatcher("WEB-INF/jsp/deletecategoria.jsp").forward(request,response);
            }else{
                throw new MyServletException("La categoria da eliminare non esiste");
            }
        }else{
            throw new MyServletException("Sezione dedicata ai soli amministratori, perfavore prima fai il login");
        }
    }
}
