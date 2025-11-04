package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import database.AlumnoFacade; // Importar el gestor AlumnoDAO (ahora EJB)
import database.CarreraFacade; // Importar el gestor CarreraDAO
import com.mycompany.alumnosapp.Alumno; // Importar la entidad Alumno
import com.mycompany.alumnosapp.Carrera; // Importar la entidad Carrera

@WebServlet("/alumno")
public class AlumnoServlet extends HttpServlet {
    @EJB
    private AlumnoFacade alumnoFacade;

    @EJB
    private CarreraFacade carreraFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Listar todos los alumnos
        List<Alumno> alumnos = alumnoFacade.findAll();
        request.setAttribute("alumnos", alumnos);
        
        // Obtener carreras para la lista desplegable
        List<Carrera> carreras = carreraFacade.findAll();
        request.setAttribute("carreras", carreras);
        
        RequestDispatcher rd = request.getRequestDispatcher("alumno.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("eliminar".equals(accion)) {
            String idStr = request.getParameter("idalumno");
            try {
                int id = Integer.parseInt(idStr);
                Alumno alumno = alumnoFacade.find(id);
                if (alumno != null) {
                    alumnoFacade.remove(alumno);
                }
            } catch (NumberFormatException e) {
                // Manejar error si el id no es válido
            }
            response.sendRedirect("alumno");
            return;
        } else if ("agregar".equals(accion) || accion == null) {
            // Si no se envía "accion", se asume agregar por compatibilidad con el formulario actual
            String nombre = request.getParameter("nombre");
            String registro = request.getParameter("registro");
            String fkIdcarreraStr = request.getParameter("fkIdcarrera");
            int fkIdcarrera = 0;
            try {
                fkIdcarrera = Integer.parseInt(fkIdcarreraStr);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Carrera inválida");
                return;
            }
            Alumno alumno = new Alumno();
            alumno.setNombre(nombre);
            alumno.setRegistro(registro);

            Carrera carrera = carreraFacade.find(fkIdcarrera);
            alumno.setFkIdcarrera(carrera);

            alumnoFacade.create(alumno);
            response.sendRedirect("alumno");
            return;
        }
        // Aquí puedes añadir más acciones en el futuro, por ejemplo:
        // else if ("actualizar".equals(accion)) { ... }
    }
}