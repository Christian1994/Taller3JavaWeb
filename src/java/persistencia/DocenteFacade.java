/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Docente;

/**
 *
 * @author jsnar
 */
@Stateless
public class DocenteFacade extends AbstractFacade<Docente> implements DocenteFacadeLocal {

    @PersistenceContext(unitName = "Taller3JavaWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocenteFacade() {
        super(Docente.class);
    }
    
}
