/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.merkatus.alicit;

import br.com.argonavis.merkatus.alicit.produto.Categoria;
import br.com.argonavis.merkatus.alicit.produto.CategoriaBase;
import br.com.argonavis.merkatus.alicit.produto.Produto;
import br.com.argonavis.merkatus.alicit.produto.Tag;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author helderdarocha
 */
public class SistemaTest {

    EntityManager em;
    EntityTransaction tx;

    @Before
    public void setUp() {
        em = PersistenceUtilities.emf.createEntityManager();
        tx = em.getTransaction();
        tx.begin();
    }

    @After
    public void tearDown() {
        em = null;
    }
/*
    // integration tests
    @Test
    public void testGetCompradores() throws Exception {
        Comprador comprador = null;

        List compradores = PersistenceUtilities.findAll(Comprador.class, em);
        assertNotNull(compradores);
        int size = compradores.size();

        comprador = Comprador.createCompradorBB();
        em.persist(comprador);

        compradores = PersistenceUtilities.findAll(Comprador.class, em);
        assertEquals(size + 1, compradores.size());

        em.remove(em.merge(comprador));
        tx.commit();
        em.close();
        assertEquals(size, compradores.size());
    }

    @Test
    public void testGetItensEdital() {
        ItemEdital item = null;

        List itens = PersistenceUtilities.findAll(ItemEdital.class, em);
        assertNotNull(itens);
        int size = itens.size();

        item = new ItemHabilitacao("IH999", "Certidao Negativa XYZ");
        em.persist(item);

        itens = PersistenceUtilities.findAll(ItemEdital.class, em);
        assertEquals(size + 1, itens.size());

        em.remove(em.merge(item));
        tx.commit();
        em.close();
        assertEquals(size, itens.size());

    }

    @Test
    public void testGetCategorias() {
        Categoria cat = null;
        List<Categoria> categorias = PersistenceUtilities.findAll(Categoria.class, em);
        assertNotNull(categorias);
        int size = categorias.size();

        cat = CategoriaBase.COMPUTADORES.getCategoria();
        em.persist(cat);

        categorias = PersistenceUtilities.findAll(Categoria.class, em);
        assertEquals(size + 1, categorias.size());

        em.remove(em.merge(cat));
        tx.commit();
        em.close();
        assertEquals(size, categorias.size());

    }
   */ 
    @Test
    public void testProdutos() {
        Categoria cat = CategoriaBase.COMPUTADORES.getCategoria();
        em.persist(cat);
        
        Tag tag1 = new Tag("teste");
        Tag tag2 = new Tag("livro");
        Tag tag3 = new Tag("coisa");
        
        tag1 = em.merge(tag1);
        tag2 = em.merge(tag2);
        tag3 = em.merge(tag3);
        
        long tag3ID = tag3.getId();

        List<Produto> produtos = PersistenceUtilities.findAll(Produto.class, em);
        assertNotNull(produtos);
        int size = produtos.size();

        Produto produto = new Produto("PXXX", "Teste");
        em.persist(produto);
        produto.setCategoria(cat);
        produto.setPreco(new BigDecimal(123.00));
        produto.addTag(tag1);
        produto.addTag(tag2);

        produtos = PersistenceUtilities.findAll(Produto.class, em);
        assertEquals(size + 1, produtos.size());
 
        //Produto produto = em.find(Produto.class, 1257L); // TEMP
        
        Produto found = em.merge(produto);
        System.out.println("ID: " + found.getId());
        assertEquals("PXXX", found.getCodigo());
        assertEquals("Teste", found.getNome());
        assertEquals(cat, found.getCategoria());
        Set<Tag> tags = new HashSet<>();
        tags.add(tag1);
        tags.add(tag2);
        assertEquals(tags, found.getTags());
        
        tag3 = em.find(Tag.class, tag3ID);
        tags.clear();
        tags.add(tag3);
        
        produto.getTags().add(tag3);
        
        produto.setTags(tags);
        assertEquals(tag3, produto.getTags().iterator().next());
        
        Produto found2 = em.find(Produto.class, found.getId());
        assertEquals("PXXX", found2.getCodigo());
        assertEquals(new BigDecimal(123.00), found.getPreco());

        produto.getTags().clear();
        em.remove(em.merge(produto));
        em.remove(em.merge(cat));
        em.remove(em.merge(tag1));
        em.remove(em.merge(tag2));
        em.remove(em.merge(tag3));

        produtos = PersistenceUtilities.findAll(Produto.class, em);
        assertEquals(size, produtos.size());
        
        tx.commit();
        em.close();
    }
    

    @Test
    public void testTags() {
        List<Tag> tags = PersistenceUtilities.findAll(Tag.class, em);
        assertNotNull(tags);
        int size = tags.size();

        Tag tag = new Tag("teste");
        em.persist(tag);

        tags = PersistenceUtilities.findAll(Tag.class, em);
        assertEquals(size + 1, tags.size());

        em.remove(em.merge(tag));
        
        tags = PersistenceUtilities.findAll(Tag.class, em);
        assertEquals(size, tags.size());
        
        tx.commit();
        em.close();
    }
/*
    @Test
    public void testGetEditais() throws ParseException {
        Comprador comprador = null;
        Edital edital = null;
        List editais = PersistenceUtilities.findAll(Edital.class, em);
        assertNotNull(editais);
        int size = editais.size();

        comprador = Comprador.createCompradorComprasNet();
        Codigo numero = new Codigo("123457");
        edital = new PregaoEletronico(comprador, PregaoEletronico.Tipo.COMPRA_DIRETA, numero);
        em.persist(comprador);

        edital.setIdentificacao("Pregao no. 1", new Codigo("123456"), new Codigo("45343"), new CNPJ("04.239.747/0001-58"));
        em.persist(edital); // will create new comprador by cascade persist

        editais = PersistenceUtilities.findAll(Edital.class, em);
        assertEquals(size + 1, editais.size());
        assertEquals("04239747000158", ((Edital) editais.iterator().next()).getCnpjComprador().toNormalizedString());
        assertEquals(comprador, ((Edital) editais.iterator().next()).getComprador());

        em.remove(em.merge(edital));
        em.remove(em.merge(comprador));
        tx.commit();
        em.close();
        assertEquals(size, editais.size());
    }
*/
}
