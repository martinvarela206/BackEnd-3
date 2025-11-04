package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import database.ExamenFacade;
import database.AlumnoFacade;
import database.DocenteFacade;
import com.mycompany.alumnosapp.Examen;
import com.mycompany.alumnosapp.Alumno;
import com.mycompany.alumnosapp.Docente;

@WebServlet("/consultas")
public class ConsultasServlet extends HttpServlet {
    @EJB
    private ExamenFacade examenFacade;
    @EJB
    private AlumnoFacade alumnoFacade;
    @EJB
    private DocenteFacade docenteFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Listar mesas de examen del turno de julio
        List<Object[]> mesasJulio = examenFacade.listarMesasJulio();
        request.setAttribute("mesasJulio", mesasJulio);

        // 2. Alumnos sin examenes en 2025
        List<Alumno> alumnosSinExamenes = alumnoFacade.alumnosSinExamenes2025();
        request.setAttribute("alumnosSinExamenes", alumnosSinExamenes);

        // 3. Docentes con más de dos materias
        List<Docente> docentesMasDeDosMaterias = docenteFacade.docentesMasDeDosMaterias();
        request.setAttribute("docentesMasDeDosMaterias", docentesMasDeDosMaterias);

        RequestDispatcher rd = request.getRequestDispatcher("consultas.jsp");
        rd.forward(request, response);
    }
}