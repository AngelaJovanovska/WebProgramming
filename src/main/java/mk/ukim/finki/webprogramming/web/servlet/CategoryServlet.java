package mk.ukim.finki.webprogramiranje.web.servlet;

import mk.ukim.finki.webprogramiranje.Service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "category-servlet", urlPatterns = "/servlet/category")
public class CategoryServlet extends HttpServlet {

    private final CategoryService categoryService;

    public CategoryServlet(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //prikaz na celosna lista od kategorija
        String ipAdress = req.getRemoteAddr();
        String clientAgent =req.getHeader("User-Agent");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>Info about our request</h3>");
        out.format("IP Adress: %s,Client Agent: %s ", ipAdress,clientAgent);


        out.println("<h2>Category list</h2>");
        out.println("<ul>");
        categoryService.listCategories().forEach(r->
                out.format("<li>%s (%s)</li>",r.getName(),r.getDescription()));
        out.println("</ul>");
        out.println("<h3>Add Category</h3>");

        out.println("<form method ='POST' action='/servlet/category'>");
        out.println("<label form='name'>Name</label>");
        out.println("<input id='name' type='text' name='name'/>");
        out.println("<label form='description'>Desctiption</label>");
        out.println("<input id='description' type='text' name='description'/>");
        out.println("<input type='submit' value='Submit'/>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //dodavanje na nova kategorija od strana na korisnikot
        String categoryName = req.getParameter("name");
        String categoryDescription = req.getParameter("description");
        categoryService.create(categoryName,categoryDescription);
        resp.sendRedirect("/servlet/category");
    }

}

