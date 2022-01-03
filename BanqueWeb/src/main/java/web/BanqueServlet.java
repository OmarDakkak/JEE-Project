package web;

import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.entities.Compte;
import metier.session.IBanqueRemote;

/**
 * Servlet implementation class BanqueServlet
 */
@WebServlet(name="cs", urlPatterns = {"*.do"})
public class BanqueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IBanqueRemote metier;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BanqueServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getParameter("action");
		if(action!=null)
		if(action.equals("delete")) {
			Long code = Long.parseLong(request.getParameter("code"));
			try {
				final Hashtable jndiProperties = new Hashtable();
				jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
				final Context context = new InitialContext(jndiProperties);
				final String appName = "BanqueEAR";
				final String moduleName = "BanqueEJB";
				final String beanName = "BQ";
				final String viewClassName = IBanqueRemote.class.getName();
				metier = (IBanqueRemote)context.lookup("ejb:"+appName+"/"+moduleName+"/"+beanName+"!"+viewClassName);
				metier.supprimerCompte(code);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		request.setAttribute("comptes", metier.getAllComptes());
		request.getRequestDispatcher("Banque.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			final Hashtable jndiProperties = new Hashtable();
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context context = new InitialContext(jndiProperties);
			final String appName = "BanqueEAR";
			final String moduleName = "BanqueEJB";
			final String beanName = "BQ";
			final String viewClassName = IBanqueRemote.class.getName();
			metier = (IBanqueRemote)context.lookup("ejb:"+appName+"/"+moduleName+"/"+beanName+"!"+viewClassName);
			String action = request.getParameter("action");
			if(action.equals("verser")) {
				Long code = Long.parseLong(request.getParameter("code1"));
				double montant = Double.parseDouble(request.getParameter("montant"));
				metier.verser(montant, code);
				
			} else if(action.equals("retirer")) {
				Long code = Long.parseLong(request.getParameter("code1"));
				double montant = Double.parseDouble(request.getParameter("montant"));
				metier.retirer(montant, code);
				
			} else if(action.equals("virement")) {
				Long code1 = Long.parseLong(request.getParameter("code1"));
				Long code2 = Long.parseLong(request.getParameter("code2"));
				double montant = Double.parseDouble(request.getParameter("montant"));
				metier.virement(montant, code1, code2);
				
			}else if(action.equals("ajouter")) {
				double montant;
				String categorie;
				if(request.getParameter("montant").equals("")) {
					montant = 0;
				}else {
					montant = Double.parseDouble(request.getParameter("montant"));
				}
				if(request.getParameter("categorie").equals("")) {
					categorie="Informatique/Logiciel";
				}else {
					categorie = request.getParameter("categorie");
				}
				metier.addCompte(new Compte(montant, categorie, new Date(), true));
				
			}else if(action.equals("update")) {
				Long code = Long.parseLong(request.getParameter("code1"));
				String categorie = request.getParameter("categorie");
				metier.updateCategorie(code, categorie);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		doGet(request, response);
		
	}

}
