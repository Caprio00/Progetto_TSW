
package controller;

import model.Login;
import model.LoginDAO;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class CookieLoginFilter extends HttpFilter {
	private static final long serialVersionUID = 1L;
	private final UtenteDAO utenteDAO = new UtenteDAO();
	private final LoginDAO loginDAO = new LoginDAO();


	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String path = request.getRequestURI();
		if (!path.contains("/Login") && !path.contains("/Logout")) {
			HttpSession session = request.getSession();
			Utente utente = (Utente) session.getAttribute("utente");
			if (utente == null) {
				Cookie cookies[] = request.getCookies();
				Cookie loginCookie = cookies == null ? null
						: Arrays.stream(cookies).filter(c -> c.getName().equals("login")).findAny().orElse(null);

				if (loginCookie != null) {
					String[] sp = loginCookie.getValue().split("_");
					String id = sp[0];
					String token = sp.length > 1 ? sp[1] : null;

					Login login = loginDAO.doRetrieveById(id);
					if (login != null && login.getToken().equals(token)) {
						utente = utenteDAO.doRetrieveById(login.getIdutente());
						session.setAttribute("utente", utente);
						token = UUID.randomUUID().toString();
						login.setToken(token);
						loginDAO.doUpdate(login);
						loginCookie.setValue(id + "_" + token);
						loginCookie.setMaxAge(30 * 24 * 60 * 60); // 30 giorni
						response.addCookie(loginCookie);
					} else {
						loginCookie.setMaxAge(0);
						response.addCookie(loginCookie);
						if (login != null) {
							loginDAO.doDelete(id);
						}
					}
				}
			}
		}
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

		chain.doFilter(request, response);
	}

}
