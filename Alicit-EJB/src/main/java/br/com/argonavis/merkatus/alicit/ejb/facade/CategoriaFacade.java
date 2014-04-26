/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.ejb.facade.remote.CategoriaFacadeRemote;
import br.com.argonavis.merkatus.alicit.produto.Categoria;
import br.com.argonavis.merkatus.alicit.produto.Categoria_;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Remote(CategoriaFacadeRemote.class)
public class CategoriaFacade extends AbstractFacade<Categoria> implements CategoriaFacadeRemote {
    @PersistenceContext(unitName = "Alicit-EJB-1.0.0-PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }
    
    @Override
    public Categoria find(Object id) {
        Categoria cat = super.find(id);
        cat.getSubCategorias().size(); // pre-fetch
        return cat;
    }
    
    @Override
    public Categoria getByNome(String nome) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Categoria> cq = cb.createQuery(Categoria.class);
        Root<Categoria> root = cq.from(Categoria.class);
        Predicate condition =  cb.equal(root.get(Categoria_.nome), nome);
        cq.where(condition);
        TypedQuery<Categoria> q = getEntityManager().createQuery(cq);
        return q.getSingleResult();
    }
    
    @Override
    public Categoria getByNomeAndContexto(String nome, String nomeAbsolutoContexto) {
        Categoria parent;
        if (nomeAbsolutoContexto == null) {
            return getByNome(nome);
        } else {
            try {
                String nomeCat = Categoria.splitNomeCategoria(nomeAbsolutoContexto)[0];
                String nomeContexto = Categoria.splitNomeCategoria(nomeAbsolutoContexto)[1];
                parent = getByNomeAndContexto(nomeCat, nomeContexto);
            } catch (ParseException ex) {
                Logger.getLogger(CategoriaFacade.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Categoria> cq = cb.createQuery(Categoria.class);
        Root<Categoria> root = cq.from(Categoria.class);
        Predicate condition = cb.and(
                      cb.equal(root.get(Categoria_.nome), nome),  
                      cb.equal(root.get(Categoria_.contexto), parent));
        cq.where(condition);
        TypedQuery<Categoria> q = getEntityManager().createQuery(cq);
        try {
            Categoria c = q.getSingleResult();
            c.getSubCategorias().size(); // pre-fetch
            return c;
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Override
    public Categoria getByNomeAndIdContexto(String nome, Long idContexto) {
        Categoria parent;
        if (idContexto == null) {
            return getByNome(nome);
        } 
        try {
            parent = getEntityManager().find(Categoria.class, idContexto);
        } catch(NoResultException e) {
            return null;
        }
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Categoria> cq = cb.createQuery(Categoria.class);
        Root<Categoria> root = cq.from(Categoria.class);
        Predicate condition = cb.and(
                      cb.equal(root.get(Categoria_.nome), nome),  
                      cb.equal(root.get(Categoria_.contexto), parent));
        cq.where(condition);
        TypedQuery<Categoria> q = getEntityManager().createQuery(cq);
        try {
            Categoria c = q.getSingleResult();
            c.getSubCategorias().size(); // pre-fetch
            return c;
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
