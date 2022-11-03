package mk.ukim.finki.webprogramiranje.web.servlet;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "hello", urlPatterns = "/hello")
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name =req.getParameter("name");
        String lastname = req.getParameter("lastname");

        PrintWriter writer = resp.getWriter();
        writer.format("<html><head></head><body>Hi %s %s!</body></html>", name, lastname);
        writer.flush();
    }
}
