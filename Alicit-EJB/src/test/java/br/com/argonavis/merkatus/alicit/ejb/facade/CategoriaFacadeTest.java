/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.ejb.LookupService;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.CategoriaFacadeRemote;
import br.com.argonavis.merkatus.alicit.produto.Categoria;
import br.com.argonavis.merkatus.alicit.produto.CategoriaBase;
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
public class CategoriaFacadeTest {
    
    LookupService<CategoriaFacadeRemote> service = new LookupService<>();
    CategoriaFacadeRemote facade = service.lookupBean(CategoriaFacade.class, CategoriaFacadeRemote.class);

    @Before
    public void testCreate() throws Exception {
        Categoria categoria = CategoriaBase.ENERGIA.getCategoria();
        int count = facade.count();
        facade.create(categoria);
        assertEquals(count + 1, facade.count());
    }
    
    @After
    public void testRemove() throws Exception {
        int count = facade.count();
        Categoria found = facade.getByNome("Energia");
        facade.remove(found);
        assertEquals(count - 1, facade.count());
    }
    
    @Test
    public void testCategoriaWithParent() throws Exception {
        Categoria subCat = new Categoria("Solar", facade.getByNome("Energia"));
        facade.create(subCat);
        Categoria found = facade.getByNomeAndContexto("Solar", "Energia");
        assertEquals("Energia/Solar", found.getNomeAbsoluto());
        found.getSubCategorias().clear();
        subCat.setContexto(null);
        facade.remove(found);
    }
    
    @Test
    public void testMerge() throws Exception {
        Categoria found = facade.getByNome("Energia");
        Categoria merged = facade.edit(found);
        assertNotNull(merged);
    }

    @Test
    public void testFind() throws Exception {
        Categoria foundQuery = facade.getByNome("Energia");
        System.out.println("Categoria ID: " + foundQuery.getId());
        Categoria foundGet = facade.find(foundQuery.getId());
        assertEquals(foundQuery, foundGet);
    }

    @Test
    public void testFindAll() throws Exception {
        int count = facade.count();
        List<Categoria> all = facade.findAll();
        assertEquals(count, all.size());
    }

    @Test
    public void testCount() throws Exception {
        int elements = facade.count();
        assertTrue(elements > 0);
    }
    
}
