/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import br.com.argonavis.merkatus.alicit.ejb.LookupService;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.CompradorFacadeRemote;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author helderdarocha
 */
public class CompradorFacadeTest {
    LookupService<CompradorFacadeRemote> service = new LookupService<>();
    CompradorFacadeRemote facade = service.lookupBean(CompradorFacade.class, CompradorFacadeRemote.class);
 
    @Before
    public void testCreate() throws Exception {
        Comprador comprador = Comprador.createCompradorForTest();
        int count = facade.count();
        facade.create(comprador);
        assertEquals(count + 1, facade.count());
    }
    
    @After
    public void testRemove() throws Exception {
        int count = facade.count();
        Comprador found = facade.querySingle("select c from Comprador c where c.identificador = 'TEST'");
        facade.remove(found);
        assertEquals(count - 1, facade.count());
    }
    
    @Test
    public void testMerge() throws Exception {
        Comprador found = facade.querySingle("select c from Comprador c where c.identificador = 'TEST'");
        Comprador merged = facade.edit(found);
        assertNotNull(merged);
    }

    @Test
    public void testFind() throws Exception {
        Comprador foundQuery = facade.querySingle("select c from Comprador c where c.identificador = 'TEST'");
        System.out.println("Comprador ID: " + foundQuery.getId());
        Comprador foundGet = facade.find(foundQuery.getId());
        assertEquals(foundQuery, foundGet);
    }

    @Test
    public void testFindAll() throws Exception {
        int count = facade.count();
        List<Comprador> all = facade.findAll();
        assertEquals(count, all.size());
    }

    @Test
    public void testCount() throws Exception {
        int elements = facade.count();
        assertTrue(elements > 0);
    }
    
}
