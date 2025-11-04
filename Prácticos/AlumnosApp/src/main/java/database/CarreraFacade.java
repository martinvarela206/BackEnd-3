/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.mycompany.alumnosapp.Carrera;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import com.mycompany.alumnosapp.Facultad;

/**
 *
 * @author Usuario
 */
@Stateless
public class CarreraFacade extends AbstractFacade<Carrera> {

    @PersistenceContext(unitName = "AlumnosAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarreraFacade() {
        super(Carrera.class);
    }
    
    public List<Carrera> findByFacultad(Facultad facultad) {
        return em.createQuery("SELECT c FROM Carrera c WHERE c.fkIdfacultad = :facultad", Carrera.class)
                 .setParameter("facultad", facultad)
                 .getResultList();
    }
}
