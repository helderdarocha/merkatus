/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.ejb.LookupService;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ProdutoFacadeRemote;
import br.com.argonavis.merkatus.alicit.produto.Produto;
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
public class ProdutoFacadeTest {
    
    LookupService<ProdutoFacadeRemote> service = new LookupService<>();
    ProdutoFacadeRemote facade = service.lookupBean(ProdutoFacade.class, ProdutoFacadeRemote.class);
    
    @Before
    public void testCreate() throws Exception {
        Produto produto = new Produto("P123", "Produto XYZ");
        int count = facade.count();
        facade.create(produto);
        assertEquals(count + 1, facade.count());
    }
    
    @After
    public void testRemove() throws Exception {
        int count = facade.count();
        Produto found = facade.querySingle("select p from Produto p where p.codigo = 'P123'");
        facade.remove(found);
        assertEquals(count - 1, facade.count());
    }
    
    @Test
    public void testMerge() throws Exception {
        Produto found = facade.querySingle("select p from Produto p where p.codigo = 'P123'");
        Produto merged = facade.edit(found);
        assertNotNull(merged);
    }

    @Test
    public void testFind() throws Exception {
        Produto foundQuery = facade.querySingle("select p from Produto p where p.codigo = 'P123'");
        System.out.println("Produto ID: " + foundQuery.getId());
        Produto foundGet = facade.find(foundQuery.getId());
        assertEquals(foundQuery, foundGet);
    }

    @Test
    public void testFindAll() throws Exception {
        int count = facade.count();
        List<Produto> all = facade.findAll();
        assertEquals(count, all.size());
    }

    @Test
    public void testCount() throws Exception {
        int elements = facade.count();
        assertTrue(elements > 0);
    }
    
}
