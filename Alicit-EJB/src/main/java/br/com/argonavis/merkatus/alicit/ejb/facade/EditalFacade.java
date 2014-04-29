/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.edital.Codigo;
import br.com.argonavis.merkatus.alicit.edital.Edital;
import br.com.argonavis.merkatus.alicit.edital.Edital_;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.EditalFacadeRemote;
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
    
    @Override
    public Edital getByNumeroEdital(String numeroEdital) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Edital> cq = cb.createQuery(Edital.class);
        Root<Edital> root = cq.from(Edital.class);
        Predicate condition =  cb.equal(root.get(Edital_.numeroEdital), new Codigo(numeroEdital));
        cq.where(condition);
        TypedQuery<Edital> q = getEntityManager().createQuery(cq);
        try {
            Edital c = q.getSingleResult();
            c.getItensHabilitacao().size(); // pre-fetch
            c.getItensProduto().size();
            return c;
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
