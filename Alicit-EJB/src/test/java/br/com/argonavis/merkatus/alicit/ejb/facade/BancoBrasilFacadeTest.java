/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.comprador.BancoBrasil;
import br.com.argonavis.merkatus.alicit.comprador.BolsaEletronicaCompras;
import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import br.com.argonavis.merkatus.alicit.ejb.LookupService;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.BancoBrasilFacadeRemote;
import java.util.List;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author helderdarocha
 */
public class BancoBrasilFacadeTest {
    LookupService<BancoBrasilFacadeRemote> service = new LookupService<>();
    BancoBrasilFacadeRemote facade = service.lookupBean(BancoBrasilFacade.class, BancoBrasilFacadeRemote.class);
    
    BancoBrasil comprador = new BancoBrasil();
    
    @Test
    public void testFind() throws Exception {
        facade.create(comprador);
        comprador = facade.edit(comprador);
        System.out.println("BancoBrasil ID: " + comprador.getId());
        Comprador c = facade.find(comprador.getId());
        assertEquals(comprador, c);
        facade.remove(comprador);
    }

    @Test
    public void testFindAll() throws Exception {
        int count = facade.count();
        facade.create(comprador);
        List<BancoBrasil> all = facade.findAll();
        assertEquals(count + 1, all.size());
        facade.remove(comprador);
    }

    @Test
    public void testCount() throws Exception {
        facade.create(comprador);
        int elements = facade.count();
        assertTrue(elements > 0);
        facade.remove(comprador);
    }
    
}
