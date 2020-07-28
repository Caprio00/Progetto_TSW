package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Categoria;
import model.CategoriaDAO;
import model.Libro;
import model.LibroDAO;

@WebServlet(name = "HomeServlet", urlPatterns="", loadOnStartup=1)
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void init() throws ServletException {
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		List<Categoria> categorie = categoriaDAO.doRetrieveAll();
		getServletContext().setAttribute("categorie", categorie);
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LibroDAO libroDAO = new LibroDAO();
		String page= request.getParameter("page");
		if(page == null){
			page="1";
			request.setAttribute("page", "1");
		}else{
			request.setAttribute("page", page);
		}
		String maxlimiti = request.getParameter("n");
		int totlibriindex = libroDAO.countdoRetrieveAll();
		if(maxlimiti == null){
			maxlimiti = "10";
			request.setAttribute("n", "10");
		}else{
			request.setAttribute("n", maxlimiti);
		}
		int maxlimitint = 10;
		try {
			maxlimitint =  Integer.parseInt(maxlimiti);
		}catch (NumberFormatException er){
			maxlimitint = 10;
		}
		int ceck= maxlimitint % 10;
		if(ceck!=0 || maxlimitint<=0){
			maxlimitint = 10;
		}
		int pageint = 0;
		try{
			pageint = Integer.parseInt(page);
		}catch (NumberFormatException er){
			throw  new MyServletException("Non ci sono libri presenti in questa pagina");
		}
		int totlibri= (int) Math.ceil((double) totlibriindex/maxlimitint);
		if(totlibri<pageint && !page.equals("1")){
			response.sendRedirect("?page=" + totlibri + "&n=" +maxlimiti);
			return;
		}
		request.setAttribute("totlibri",totlibri);
		try{
			int l = Integer.parseInt(page);
			l=l*maxlimitint;
			List<Libro> prodotti=null;
			prodotti = libroDAO.doRetrieveAll(l-maxlimitint,maxlimitint);
			if (prodotti.isEmpty()){
				throw new MyServletException("Non ci sono libri presenti in questa pagina");
			}else if (l+1>totlibriindex){
				request.setAttribute("next", "-1");
			}
			if(page.equals("1")){
				request.setAttribute("title", "Home");
			}else{
				request.setAttribute("title", "Pagina " + page);
			}

			request.setAttribute("prodotti", prodotti);
		} catch (NumberFormatException er){
			throw new MyServletException("Non ci sono libri presenti in questa pagina");
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
