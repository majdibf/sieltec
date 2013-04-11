package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.startup.UserConfig;

public class RestrictionFilter implements Filter {
	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		// Cast des objets request et response
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// Récupération de la session depuis la requête
		HttpSession session = request.getSession();
		// (HttpSession) facesContext.getExternalContext().getSession(false);

		String pageRequested = request.getRequestURL().toString();

		// Si l'objet utilisateur n'existe pas dans la session en cours, alors
		// l'utilisateur n'est pas connecté.

		if (session.getAttribute("userConnected") != null
				&& pageRequested.contains("authentification.jsf")) {
			// Redirection vers la page publique
			response.sendRedirect(request.getContextPath()
					+ "/xhtml/authentification/accueil.jsf");
		} else if (session.getAttribute("userConnected") == null
				&& !pageRequested.contains("authentification.jsf")
				&& !pageRequested.endsWith(".css")
				&& !pageRequested.endsWith(".js")
				&& !pageRequested.endsWith(".png")
				&& !pageRequested.contains(".js.jsf")
				&& !pageRequested.contains(".gif.jsf")
				&& !pageRequested.contains(".css.jsfis")) {
			// Redirection vers la page publique
			System.out
					.println("********************************************************"
							+ pageRequested
							+ "is redirected to Authentification.jsf");
			response.sendRedirect(request.getContextPath()
					+ "/xhtml/authentification/authentification.jsf");
		} else {
			// Affichage de la page restreinte
			chain.doFilter(request, response);
		}

	}

	public void destroy() {
	}
}