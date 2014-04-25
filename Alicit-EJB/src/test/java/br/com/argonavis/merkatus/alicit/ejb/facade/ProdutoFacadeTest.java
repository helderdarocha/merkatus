/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.ejb.LookupService;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.CategoriaFacadeRemote;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ProdutoFacadeRemote;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.TagFacadeRemote;
import br.com.argonavis.merkatus.alicit.produto.Categoria;
import br.com.argonavis.merkatus.alicit.produto.CategoriaBase;
import br.com.argonavis.merkatus.alicit.produto.Produto;
import br.com.argonavis.merkatus.alicit.produto.Tag;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    LookupService<CategoriaFacadeRemote> catService = new LookupService<>();
    CategoriaFacadeRemote catFacade = catService.lookupBean(CategoriaFacade.class, CategoriaFacadeRemote.class);
    LookupService<TagFacadeRemote> tagService = new LookupService<>();
    TagFacadeRemote tagFacade = tagService.lookupBean(TagFacade.class, TagFacadeRemote.class);
    
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
        Produto found = facade.getByCodigo("P123");
        facade.remove(found);
        assertEquals(count - 1, facade.count());
    }
    
    @Test
    public void testWithCategory() throws Exception {
        Produto found = facade.getByCodigo("P123");
        Categoria cat = new Categoria(CategoriaBase.GAMES.toString());
        catFacade.create(cat);
        Categoria foundCat = catFacade.getByNome("Games");
        found.setCategoria(foundCat);
        assertEquals(cat, found.getCategoria());
        found.setCategoria(null);
        catFacade.remove(foundCat);
    }
    
    @Test
    public void testWithTags() throws Exception {
        Produto found = facade.getByCodigo("P123");
        Tag t1 = new Tag("teste");
        Tag t2 = new Tag("coisa");
        tagFacade.create(t1);
        tagFacade.create(t2);
        Set<Tag> tags = new HashSet<>();
        tags.add(t1);
        tags.add(t2);
        found.addTag(tagFacade.getByNome("coisa"));
        found.addTag(tagFacade.getByNome("teste"));
        assertEquals(2, found.getTags().size());
        assertEquals(tags, found.getTags());
        found.removeTag(tagFacade.getByNome("coisa"));
        assertEquals("teste", found.getTags().iterator().next().getNome());
        found.getTags().clear();
        tagFacade.remove(tagFacade.getByNome("coisa"));
        tagFacade.remove(tagFacade.getByNome("teste"));
    }
    
    @Test
    public void testMerge() throws Exception {
        Produto found = facade.getByCodigo("P123");
        Produto merged = facade.edit(found);
        assertNotNull(merged);
    }

    @Test
    public void testFind() throws Exception {
        Produto foundQuery = facade.getByCodigo("P123");
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
