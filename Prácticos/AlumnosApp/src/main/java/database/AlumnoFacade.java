/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.mycompany.alumnosapp.Alumno;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author Usuario
 */
@Stateless
public class AlumnoFacade extends AbstractFacade<Alumno> {

    @PersistenceContext(unitName = "AlumnosAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlumnoFacade() {
        super(Alumno.class);
    }
    
    public List<Alumno> alumnosSinExamenes2025() {
        // YEAR() tampoco es estándar JPQL, para máxima compatibilidad filtra por rango de fechas
        // Aquí se deja como ejemplo, pero si da error, usa BETWEEN como arriba
        return em.createQuery(
            "SELECT a FROM Alumno a WHERE a.idalumno NOT IN (SELECT DISTINCT e.alumno.idalumno FROM Examen e WHERE e.fecha BETWEEN :inicio AND :fin)"
        )
        .setParameter("inicio", java.sql.Timestamp.valueOf("2025-01-01 00:00:00"))
        .setParameter("fin", java.sql.Timestamp.valueOf("2025-12-31 23:59:59"))
        .getResultList();
    }
    
}
