/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import br.com.argonavis.merkatus.alicit.comprador.ComprasNet;
import br.com.argonavis.merkatus.alicit.comprador.ConsumidorFinal;
import br.com.argonavis.merkatus.alicit.ejb.LookupService;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ComprasNetFacadeRemote;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author helderdarocha
 */
public class ComprasNetFacadeTest {
    LookupService<ComprasNetFacadeRemote> service = new LookupService<>();
    ComprasNetFacadeRemote facade = service.lookupBean(ComprasNetFacade.class, ComprasNetFacadeRemote.class);
    
    ComprasNet comprador = new ComprasNet();
    
    @Test
    public void testFind() throws Exception {
        facade.create(comprador);
        comprador = facade.edit(comprador);
        System.out.println("ComprasNet ID: " + comprador.getId());
        Comprador c = facade.find(comprador.getId());
        assertEquals(comprador, c);
        facade.remove(comprador);
    }

    @Test
    public void testFindAll() throws Exception {
        int count = facade.count();
        facade.create(comprador);
        List<ComprasNet> all = facade.findAll();
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
