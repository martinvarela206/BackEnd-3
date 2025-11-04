package controller;
   
import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Si usas entidades:
import com.mycompany.alumnosapp.Carrera;
import com.mycompany.alumnosapp.Facultad;

// Si usas el Facade:
import database.CarreraFacade;
import database.FacultadFacade;

@WebServlet(name = "Manejador", loadOnStartup = 1, urlPatterns = {"/agregar", "/listar", "/facultad"})
public class Manejador extends HttpServlet {
    @EJB
    private FacultadFacade facultadFacade;
    @EJB
    private CarreraFacade carreraFacade;

    @Override
    public void init() throws ServletException {
        // Almacena la lista de facultades en el contexto global de la aplicación
        getServletContext().setAttribute("facultades", facultadFacade.findAll());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/facultad".equals(path)) {
            String facultadIdStr = request.getParameter("facultadId");
            try {
                int facultadId = Integer.parseInt(facultadIdStr);
                Facultad facultad = facultadFacade.find(facultadId);
                List<Carrera> carreras = carreraFacade.findByFacultad(facultad); // Este método lo puedes implementar en CarreraFacade
                request.setAttribute("facultad", facultad);
                request.setAttribute("carreras", carreras);
                request.getRequestDispatcher("facultad.jsp").forward(request, response);
                return;
            } catch (Exception e) {
                request.setAttribute("error", "Error: " + e.getMessage());
                request.getRequestDispatcher("facultad.jsp").forward(request, response);
            }
        }
        // ...otros casos...
    }
}