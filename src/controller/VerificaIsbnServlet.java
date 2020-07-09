package controller;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import model.LibroDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ceckisbn")
public class VerificaIsbnServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String isbn = request.getParameter("id");
            if(isbn != null){
                LibroDAO dao = new LibroDAO();
                response.setContentType("application/text");
                if(dao.doRetrieveByIsbn(isbn) == null){
                    response.getWriter().append("ok");
                }else{
                    response.getWriter().append("no");
                }
            }else{
                throw new MyServletException("Errore");
            }
    }
}
