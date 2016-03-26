/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Estudiante;

/**
 *
 * @author jsnar
 */
@Stateless
public class EstudianteFacade extends AbstractFacade<Estudiante> implements EstudianteFacadeLocal {

    @PersistenceContext(unitName = "UnivalleDemoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteFacade() {
        super(Estudiante.class);
    }
    
}
