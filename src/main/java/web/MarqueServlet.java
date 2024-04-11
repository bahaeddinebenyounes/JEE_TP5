package web;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import dao.MarqueDaoImpl;
import dao.IMarqueDao;
import metier.entities.Marque;
@WebServlet (name="catServ",urlPatterns= {"/categories","/saisieMarque",
"/saveMarque","/supprimerCat","/editerCat","/updateCat"})

public class MarqueServlet extends HttpServlet {
IMarqueDao metier;
@Override
public void init() throws ServletException {
metier = new MarqueDaoImpl();
}
@Override
protected void doGet(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
String path=request.getServletPath();
System.out.println("PATH "+path);
if (path.equals("/categories"))
{
MarqueModel model= new MarqueModel();
List<Marque> cats = metier.getAllMarques();
model.setMarques(cats);
request.setAttribute("model", model);
request.getRequestDispatcher("categories.jsp").forward(request,response);
}
else if (path.equals("/saisieMarque") )
{
request.getRequestDispatcher("saisieMarque.jsp").forward(request,response
);
}
else if (path.equals("/saveMarque") &&

request.getMethod().equals("POST"))

{
Date dateCat= new Date();
String nom=request.getParameter("nom");
String pattern = "yyyy-MM-dd";
SimpleDateFormat simpleDateFormat = new

SimpleDateFormat(pattern);
try {

dateCat =

simpleDateFormat.parse(request.getParameter("dateCat"));

} catch (ParseException e) {
e.printStackTrace();
}
Marque cat = metier.save(new Marque(nom,dateCat));
request.setAttribute("categorie", cat);
response.sendRedirect("categories");
}
else if (path.equals("/supprimerCat"))
{
Long id= Long.parseLong(request.getParameter("id"));
metier.deleteMarque(id);
response.sendRedirect("categories");
}
else if (path.equals("/editerCat") )
{
Long id= Long.parseLong(request.getParameter("id"));
Marque cat = metier.getMarque(id);
request.setAttribute("categorie", cat);
request.getRequestDispatcher("editerMarque.jsp").forward(request,response
);
}
else if (path.equals("/updateCat") )
{
Date dateCat= new Date();
Long id = Long.parseLong(request.getParameter("id"));
String nom=request.getParameter("nom");
Marque cat = new Marque();
cat.setIdCat(id);
cat.setNomCat(nom);
String pattern = "yyyy-MM-dd";
SimpleDateFormat simpleDateFormat = new

SimpleDateFormat(pattern);
try {

dateCat =

simpleDateFormat.parse(request.getParameter("dateCreation"));

} catch (ParseException e) {
e.printStackTrace();
}
cat.setDateCreation(dateCat);
metier.updateMarque(cat);
response.sendRedirect("categories");
}
else
{
response.sendError(Response.SC_NOT_FOUND);
}
}
@Override
protected void doPost(HttpServletRequest request,

HttpServletResponse response) throws

ServletException, IOException {
doGet(request,response);
}
}