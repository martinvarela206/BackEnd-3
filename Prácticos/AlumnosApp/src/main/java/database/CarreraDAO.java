package database;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import com.mycompany.alumnosapp.Carrera;

@Stateless
public class CarreraDAO {
    @PersistenceContext(unitName = "AlumnosAppPU")
    private EntityManager em;

    public List<Carrera> listarTodos() {
        return em.createNamedQuery("Carrera.findAll", Carrera.class).getResultList();
    }

    public Carrera buscarPorId(int id) {
        return em.find(Carrera.class, id);
    }
}