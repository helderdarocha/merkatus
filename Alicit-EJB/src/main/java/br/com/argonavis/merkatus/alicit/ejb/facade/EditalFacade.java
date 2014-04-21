/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.edital.Edital;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.EditalFacadeRemote;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author helderdarocha
 */
@Stateless
@Remote(EditalFacadeRemote.class)
public class EditalFacade extends AbstractFacade<Edital> implements EditalFacadeRemote {
    @PersistenceContext(unitName = "Alicit-EJB-1.0.0-PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public EditalFacade() {
        super(Edital.class);
    }
    
}
