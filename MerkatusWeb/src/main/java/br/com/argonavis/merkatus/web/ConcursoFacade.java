/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.web;

import br.com.argonavis.merkatus.entity.licitacao.Concurso;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author helderdarocha
 */
@Stateless
public class ConcursoFacade extends AbstractFacade<Concurso> {
    @PersistenceContext(unitName = "br.com.argonavis_MerkatusWeb_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConcursoFacade() {
        super(Concurso.class);
    }
    
}
