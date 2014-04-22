/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.edital.componente.ItemEdital;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ItemEditalFacadeRemote;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author helderdarocha
 */
public class ItemEditalFacade extends AbstractFacade<ItemEdital> implements ItemEditalFacadeRemote {
    @PersistenceContext(unitName = "Alicit-EJB-1.0.0-PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public ItemEditalFacade() {
        super(ItemEdital.class);
    }
}
