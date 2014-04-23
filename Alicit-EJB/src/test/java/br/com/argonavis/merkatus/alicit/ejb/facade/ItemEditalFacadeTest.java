/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.edital.componente.ItemEdital;
import br.com.argonavis.merkatus.alicit.edital.componente.ItemHabilitacao;
import br.com.argonavis.merkatus.alicit.ejb.LookupService;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ItemEditalFacadeRemote;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author helderdarocha
 */
public class ItemEditalFacadeTest {
    
    LookupService<ItemEditalFacadeRemote> service = new LookupService<>();
    ItemEditalFacadeRemote facade = service.lookupBean(ItemEditalFacade.class, ItemEditalFacadeRemote.class);
    
    @Before
    public void testCreate() throws Exception {
        ItemEdital ie = new ItemHabilitacao("IH121", "Certidão negativa 123");
        int count = facade.count();
        facade.create(ie);
        assertEquals(count + 1, facade.count());
    }
    
    @After
    public void testRemove() throws Exception {
        int count = facade.count();
        ItemEdital found = facade.querySingle("select ih from ItemEdital ih where ih.codigo = 'IH121'");
        facade.remove(found);
        assertEquals(count - 1, facade.count());
    }
    
    @Test
    public void testMerge() throws Exception {
        ItemEdital found = facade.querySingle("select ih from ItemEdital ih where ih.codigo = 'IH121'");
        ItemEdital merged = facade.edit(found);
        assertNotNull(merged);
    }

    @Test
    public void testFind() throws Exception {
        ItemEdital foundQuery = facade.querySingle("select ih from ItemEdital ih where ih.codigo = 'IH121'");
        System.out.println("ItemEdital ID: " + foundQuery.getId());
        ItemEdital foundGet = facade.find(foundQuery.getId());
        assertEquals(foundQuery, foundGet);
    }

    @Test
    public void testFindAll() throws Exception {
        int count = facade.count();
        List<ItemEdital> all = facade.findAll();
        assertEquals(count, all.size());
    }

    @Test
    public void testCount() throws Exception {
        int elements = facade.count();
        assertTrue(elements > 0);
    }
    
}
