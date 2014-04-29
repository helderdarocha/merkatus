/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.edital.componente.ItemProduto;import br.com.argonavis.merkatus.alicit.edital.componente.ItemProduto_;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ItemProdutoFacadeRemote;
import br.com.argonavis.merkatus.alicit.produto.Produto;
;
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
@Remote(ItemProdutoFacadeRemote.class)
public class ItemProdutoFacade extends AbstractFacade<ItemProduto> implements ItemProdutoFacadeRemote {
    @PersistenceContext(unitName = "Alicit-EJB-1.0.0-PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public ItemProdutoFacade() {
        super(ItemProduto.class);
    }
    
    @Override
    public ItemProduto getByCodigoProduto(String codigo) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ItemProduto> cq = cb.createQuery(ItemProduto.class);
        Root<ItemProduto> root = cq.from(ItemProduto.class);
        Predicate condition = cb.equal(root.get(ItemProduto_.produto), new Produto(codigo, ""));
        cq.where(condition);
        TypedQuery<ItemProduto> q = getEntityManager().createQuery(cq);
        return q.getSingleResult();
    }
}
