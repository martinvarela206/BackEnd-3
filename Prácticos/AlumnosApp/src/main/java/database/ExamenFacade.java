/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.mycompany.alumnosapp.Examen;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author Usuario
 */
@Stateless
public class ExamenFacade extends AbstractFacade<Examen> {

    @PersistenceContext(unitName = "AlumnosAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExamenFacade() {
        super(Examen.class);
    }
    
    public List<Object[]> listarMesasJulio() {
        return em.createQuery(
            "SELECT e, a FROM Examen e JOIN e.alumno a WHERE e.fecha BETWEEN :inicio AND :fin"
        )
        .setParameter("inicio", java.sql.Timestamp.valueOf("2025-07-01 00:00:00"))
        .setParameter("fin", java.sql.Timestamp.valueOf("2025-07-31 23:59:59"))
        .getResultList();
    }
}
