package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import dao.IMarqueDao;
import dao.IVetementDao;
import dao.MarqueDaoImpl;
import dao.VetementDaoImpl;
import metier.entities.Marque;
import metier.entities.Vetement;

@WebServlet (name="cs",urlPatterns= {"/controleur","*.do"})
public class ControleurServlet extends HttpServlet {
	
	 IVetementDao metier;
	 IMarqueDao metierCat;
	 @Override
	public void init() throws ServletException {
		metier = new VetementDaoImpl();
		metierCat = new MarqueDaoImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			             HttpServletResponse response) 
			            throws ServletException, IOException {
		String path=request.getServletPath();
		if (path.equals("/index.do"))
		{
			request.getRequestDispatcher("vetements.jsp").forward(request,response);
		}
		else if (path.equals("/chercher.do"))
		{
			String motCle=request.getParameter("motCle");
			VetementModele model= new VetementModele();
			model.setMotCle(motCle);
			List<Vetement> prods = metier.VetementsParMC(motCle);
			model.setVetements(prods);
			request.setAttribute("model", model);
			request.getRequestDispatcher("vetements.jsp").forward(request,response);
		}
		else if (path.equals("/saisie.do")  )
		{
			List<Marque> cats = metierCat.getAllMarques();
			MarqueModel model= new MarqueModel();
			model.setMarques(cats);
			request.setAttribute("catModel", model);
			request.getRequestDispatcher("saisieVetement.jsp").forward(request,response);
		}
		else if (path.equals("/save.do")  && request.getMethod().equals("POST"))
		{
		    String nom=request.getParameter("nom");
		    Long categorieId=Long.parseLong(request.getParameter("categorie"));
			double prix = Double.parseDouble(request.getParameter("prix"));
			Marque cat = metierCat.getMarque(categorieId);
			Vetement p = metier.save(new Vetement(nom,prix));
			request.setAttribute("vetement", p);
			response.sendRedirect("chercher.do?motCle=");
			
			
			
			
			request.getRequestDispatcher("confirmation.jsp").forward(request,response);
		}
		else if (path.equals("/supprimer.do"))
		{
		    Long id= Long.parseLong(request.getParameter("id"));
		    metier.deleteVetement(id);
		    response.sendRedirect("chercher.do?motCle=");
					
			//request.getRequestDispatcher("confirmation.jsp").forward(request,response);
		}
		else if (path.equals("/editer.do")  )
		{
			Long id= Long.parseLong(request.getParameter("id"));
		    Vetement p = metier.getVetement(id);
		    request.setAttribute("vetement", p);
		    
		    List<Marque> cats = metierCat.getAllMarques();
		    MarqueModel model= new MarqueModel();
		    model.setMarques(cats);
		    request.setAttribute("catModel", model);
		    
			request.getRequestDispatcher("editerVetement.jsp").forward(request,response);
		}
		else if (path.equals("/update.do")  )
		{
			 Long id = Long.parseLong(request.getParameter("id"));
			 String nom=request.getParameter("nom");
			 double prix = Double.parseDouble(request.getParameter("prix"));
			 Long categorieId=Long.parseLong(request.getParameter("categorie"));
			 Vetement p = new Vetement();
			 p.setIdVetement(id);
			 p.setNomVetement(nom);
			 p.setPrix(prix);
			 Marque cat = metierCat.getMarque(categorieId);
			 p.setMarque(cat);
			 metier.updateVetement(p);
			 request.setAttribute("vetement", p);
			 request.getRequestDispatcher("confirmation.jsp").forward(request,response);
		}
		else
		{
			response.sendError(Response.SC_NOT_FOUND);		
		}	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}