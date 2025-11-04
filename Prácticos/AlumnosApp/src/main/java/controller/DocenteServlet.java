package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import database.DocenteFacade; // El Facade generado por NetBeans
import com.mycompany.alumnosapp.Docente; // La entidad Docente

@WebServlet("/docentes")
public class DocenteServlet extends HttpServlet {
    @EJB
    private DocenteFacade docenteFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Docente> docentes = docenteFacade.findAll();
        request.setAttribute("docentes", docentes);
        RequestDispatcher rd = request.getRequestDispatcher("docentes.jsp");
        rd.forward(request, response);
    }
}