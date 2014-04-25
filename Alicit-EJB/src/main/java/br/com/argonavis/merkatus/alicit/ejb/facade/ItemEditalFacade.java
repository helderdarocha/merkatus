/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.edital.componente.ItemEdital;
import br.com.argonavis.merkatus.alicit.edital.componente.ItemEdital_;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ItemEditalFacadeRemote;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author helderdarocha
 */
@Stateless
@Remote(ItemEditalFacadeRemote.class)
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
    
    public ItemEdital getByCodigo(String codigo) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ItemEdital> cq = cb.createQuery(ItemEdital.class);
        Root<ItemEdital> root = cq.from(ItemEdital.class);
        Predicate condition = cb.equal(root.get(ItemEdital_.codigo), codigo);
        cq.where(condition);
        TypedQuery<ItemEdital> q = getEntityManager().createQuery(codigo, ItemEdital.class);
        return q.getSingleResult();
    }
}
