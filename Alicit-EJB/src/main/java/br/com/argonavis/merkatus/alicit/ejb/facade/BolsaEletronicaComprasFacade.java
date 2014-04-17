/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.comprador.BolsaEletronicaCompras;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.BancoBrasilFacadeRemote;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.BolsaEletronicaComprasFacadeRemote;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author helderdarocha
 */
@Stateless
@Remote(BolsaEletronicaComprasFacadeRemote.class)
public class BolsaEletronicaComprasFacade extends AbstractFacade<BolsaEletronicaCompras> implements BolsaEletronicaComprasFacadeRemote {
    @PersistenceContext(unitName = "Alicit-EJB-1.0.0-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BolsaEletronicaComprasFacade() {
        super(BolsaEletronicaCompras.class);
    }
    
}
