/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.ejb.LookupService;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.TagFacadeRemote;
import br.com.argonavis.merkatus.alicit.produto.Tag;
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
public class TagFacadeTest {
    
    LookupService<TagFacadeRemote> service = new LookupService<>();
    TagFacadeRemote facade = service.lookupBean(TagFacade.class, TagFacadeRemote.class);
    
    @Before
    public void testCreate() throws Exception {
        Tag tag = new Tag("produto");
        int count = facade.count();
        facade.create(tag);
        assertEquals(count + 1, facade.count());
    }
    
    @After
    public void testRemove() throws Exception {
        int count = facade.count();
        Tag found = facade.getByNome("produto");
        facade.remove(found);
        assertEquals(count - 1, facade.count());
    }
    
    @Test
    public void testMerge() throws Exception {
        Tag found = facade.getByNome("produto");
        Tag merged = facade.edit(found);
        assertNotNull(merged);
    }

    @Test
    public void testFind() throws Exception {
        Tag foundQuery = facade.getByNome("produto");
        System.out.println("Tag ID: " + foundQuery.getId());
        Tag foundGet = facade.find(foundQuery.getId());
        assertEquals(foundQuery, foundGet);
    }

    @Test
    public void testFindAll() throws Exception {
        int count = facade.count();
        List<Tag> all = facade.findAll();
        assertEquals(count, all.size());
    }

    @Test
    public void testCount() throws Exception {
        int elements = facade.count();
        assertTrue(elements > 0);
    }
    
}
