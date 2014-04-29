/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.edital.componente.ItemHabilitacao;
import br.com.argonavis.merkatus.alicit.edital.componente.ItemHabilitacao_;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ItemHabilitacaoFacadeRemote;
;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
@Remote(ItemHabilitacaoFacadeRemote.class)
public class ItemHabilitacaoFacade extends AbstractFacade<ItemHabilitacao> implements ItemHabilitacaoFacadeRemote {
    @PersistenceContext(unitName = "Alicit-EJB-1.0.0-PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public ItemHabilitacaoFacade() {
        super(ItemHabilitacao.class);
    }
    
    @Override
    public ItemHabilitacao getByCodigo(String codigo) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ItemHabilitacao> cq = cb.createQuery(ItemHabilitacao.class);
        Root<ItemHabilitacao> root = cq.from(ItemHabilitacao.class);
        Predicate condition = cb.equal(root.get(ItemHabilitacao_.codigo), codigo);
        cq.where(condition);
        TypedQuery<ItemHabilitacao> q = getEntityManager().createQuery(cq);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
