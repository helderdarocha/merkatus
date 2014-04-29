/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.edital.componente.ItemHabilitacao;
import br.com.argonavis.merkatus.alicit.ejb.LookupService;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ItemHabilitacaoFacadeRemote;
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
public class ItemHabilitacaoFacadeTest {
    
    LookupService<ItemHabilitacaoFacadeRemote> service = new LookupService<>();
    ItemHabilitacaoFacadeRemote facade = service.lookupBean(ItemHabilitacaoFacade.class, ItemHabilitacaoFacadeRemote.class);
    
    @Before
    public void testCreate() throws Exception {
        ItemHabilitacao ie = new ItemHabilitacao("IH121", "Certidão negativa 123");
        int count = facade.count();
        facade.create(ie);
        assertEquals(count + 1, facade.count());
    }
    
    @After
    public void testRemove() throws Exception {
        int count = facade.count();
        ItemHabilitacao found = facade.querySingle("select ih from ItemHabilitacao ih where ih.codigo = 'IH121'");
        facade.remove(found);
        assertEquals(count - 1, facade.count());
    }
    
    @Test
    public void testEquals() {
        ItemHabilitacao ih2 = new ItemHabilitacao("IH121", "Certidão negativa 123");
        ItemHabilitacao found = facade.querySingle("select ih from ItemHabilitacao ih where ih.codigo = 'IH121'");
        assertEquals(ih2, found);
    }
    
    @Test
    public void testMerge() throws Exception {
        ItemHabilitacao found = facade.querySingle("select ih from ItemHabilitacao ih where ih.codigo = 'IH121'");
        ItemHabilitacao merged = facade.edit(found);
        assertNotNull(merged);
    }

    @Test
    public void testFind() throws Exception {
        ItemHabilitacao foundQuery = facade.querySingle("select ih from ItemHabilitacao ih where ih.codigo = 'IH121'");
        System.out.println("ItemEdital ID: " + foundQuery.getId());
        ItemHabilitacao foundGet = facade.find(foundQuery.getId());
        assertEquals(foundQuery, foundGet);
    }

    @Test
    public void testFindAll() throws Exception {
        int count = facade.count();
        List<ItemHabilitacao> all = facade.findAll();
        assertEquals(count, all.size());
    }

    @Test
    public void testCount() throws Exception {
        int elements = facade.count();
        assertTrue(elements > 0);
    }
    
}
