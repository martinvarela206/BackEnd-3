package database;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import com.mycompany.alumnosapp.Alumno;

@Stateless
public class AlumnoDAO {
    @PersistenceContext(unitName = "AlumnosAppPU")
    private EntityManager em;

    public void crear(Alumno alumno) {
        em.persist(alumno); // JTA gestiona la transacción
    }

    public Alumno buscarPorId(int id) {
        return em.find(Alumno.class, id);
    }

    public List<Alumno> listarTodos() {
        return em.createNamedQuery("Alumno.findAll", Alumno.class).getResultList();
    }

    public void actualizar(Alumno alumno) {
        em.merge(alumno); // JTA gestiona la transacción
    }

    public void eliminar(int id) {
        Alumno alumno = em.find(Alumno.class, id);
        if (alumno != null) {
            em.remove(alumno); // JTA gestiona la transacción
        }
    }
}