/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ProdutoFacadeRemote;
import br.com.argonavis.merkatus.alicit.produto.Categoria;
import br.com.argonavis.merkatus.alicit.produto.Produto;
import br.com.argonavis.merkatus.alicit.produto.Produto_;
import br.com.argonavis.merkatus.alicit.produto.Tag;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
@Remote(ProdutoFacadeRemote.class)
public class ProdutoFacade extends AbstractFacade<Produto> implements ProdutoFacadeRemote {
    @PersistenceContext(unitName = "Alicit-EJB-1.0.0-PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public ProdutoFacade() {
        super(Produto.class);
    }
    
    @Override
    public List<Produto> findByTag(Tag tag) {
        String q = "select p from Produto p, Tag t where t.nome = :tag and t member of p.tags";
        Query query = em.createQuery(q, Produto.class);
        query.setParameter("tag", tag.getNome());
        return query.getResultList();
    }
    
    @Override
    public Long countByTag(Tag tag) {
        String q = "select count(p) from Produto p, Tag t where t.nome = :tag and t member of p.tags";
        Query query = em.createQuery(q, Produto.class);
        query.setParameter("tag", tag.getNome());
        return (Long)query.getSingleResult();
    }
    
    @Override
    public Produto find(Object id) {
        Produto p = super.find(id);
        p.getTags().size(); // pre-fetch
        return p;
    }
    
    @Override
    public Produto getByCodigo(String codigo) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Produto> cq = cb.createQuery(Produto.class);
        Root<Produto> root = cq.from(Produto.class);
        Predicate condition = cb.equal(root.get(Produto_.codigo), codigo);
        cq.where(condition);
        TypedQuery<Produto> q = getEntityManager().createQuery(cq);
        try {
            Produto p = q.getSingleResult();
            p.getTags().size(); // prefetch tags collection
            return p;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Produto> findByCategoria(Categoria categoria) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Produto> cq = cb.createQuery(Produto.class);
        Root<Produto> root = cq.from(Produto.class);
        Predicate condition = cb.or(
                      cb.equal(root.get(Produto_.categoria), categoria),  
                      cb.equal(root.get(Produto_.categoria), categoria.getContexto()));
        
        cq.where(condition);
        TypedQuery<Produto> q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }
}
