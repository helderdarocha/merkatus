/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.merkatus.alicit;

import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import br.com.argonavis.merkatus.alicit.edital.CNPJ;
import br.com.argonavis.merkatus.alicit.edital.Codigo;
import br.com.argonavis.merkatus.alicit.edital.Edital;
import br.com.argonavis.merkatus.alicit.edital.PregaoEletronico;
import br.com.argonavis.merkatus.alicit.edital.componente.ItemHabilitacao;
import br.com.argonavis.merkatus.alicit.edital.componente.ItemProduto;
import br.com.argonavis.merkatus.alicit.produto.Categoria;
import br.com.argonavis.merkatus.alicit.produto.CategoriaBase;
import br.com.argonavis.merkatus.alicit.produto.Produto;
import br.com.argonavis.merkatus.alicit.produto.Tag;
import java.math.BigDecimal;
import java.text.ParseException;
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
    
    @Test
    public void testItemProdutoEdital() throws ParseException {
        // Create produtos
        Produto p1 = new Produto("Teste946394623", "Produto Teste 1");
        p1.setPreco(BigDecimal.valueOf(100.00));
        em.persist(p1);
        p1 = em.merge(p1);
        Produto p2 = new Produto("Teste334258495", "Produto Teste 2");
        p2.setPreco(BigDecimal.valueOf(30.00));
        em.persist(p2);
        p2 = em.merge(p2);
        
        // Create itens for produtos
        ItemProduto i1 = new ItemProduto();
        em.persist(i1);
        ItemProduto i2 = new ItemProduto();
        em.persist(i2);
        i1.setProduto(p1);
        i1.setQuantidade(5);
        i2.setProduto(p2);
        i2.setQuantidade(3);
        
        i1 = em.merge(i1);
        i2 = em.merge(i2);
        
        // Calculate totals
        assertEquals(BigDecimal.valueOf(500.00), i1.getValorTotal());
        assertEquals(BigDecimal.valueOf(90.00), i2.getValorTotal());
        
        // Create edital
        Comprador comprador = Comprador.createCompradorForTest();
        em.persist(comprador);
        
        Codigo numero = new Codigo("000000");
        Edital edital = new PregaoEletronico(comprador, PregaoEletronico.Tipo.COMPRA_DIRETA, numero);
        edital.setIdentificacao("Pregao no. 1", new Codigo("000000"), new Codigo("45343"), new CNPJ("04.239.747/0001-58"));
        em.persist(edital); // will create new comprador by cascade persist
        edital = em.merge(edital);
        
        // add itens to edital
        int size = edital.getItensProduto().size();
        assertEquals(0, size);
        
        edital.getItensProduto().add(i1);
        edital.addItemProduto(i2);
        
        size = edital.getItensProduto().size();
        assertEquals(2, size);
        
        // get itens
        ItemProduto i = edital.getItemProduto("Teste946394623");
        i.getProduto().setPreco(BigDecimal.valueOf(75.00));
        i.setQuantidade(4);
        assertEquals(BigDecimal.valueOf(300.0), i.getValorTotal());
        
        // calculate value of edital
        assertEquals(BigDecimal.valueOf(390.0), edital.getValorTotal());
        
        // remove edital
        em.remove(edital);
        // remove comprador
        em.remove(comprador);
        // remove itens
        em.remove(i1);
        em.remove(i2);
        // remove produtos
        em.remove(p1);
        em.remove(p2);
    }
    
    @Test
    public void testItemHabilitacaoEdital() throws ParseException {
        // Create ihs
        ItemHabilitacao item1 = new ItemHabilitacao("IH9991235", "Certidao Negativa XYZ");
        em.persist(item1);
        item1 = em.merge(item1);
        ItemHabilitacao item2 = new ItemHabilitacao("IH0003232", "Certidao Negativa ABC");
        em.persist(item2);
        item2 = em.merge(item2);
        
        // Create edital
        Comprador comprador = Comprador.createCompradorForTest();
        em.persist(comprador);
        
        Codigo numero = new Codigo("000000");
        Edital edital = new PregaoEletronico(comprador, PregaoEletronico.Tipo.COMPRA_DIRETA, numero);
        edital.setIdentificacao("Pregao no. 1", new Codigo("000000"), new Codigo("45343"), new CNPJ("04.239.747/0001-58"));
        em.persist(edital); // will create new comprador by cascade persist
        edital = em.merge(edital);
        
        // add itens to edital
        int size = edital.getItensHabilitacao().size();
        assertEquals(0, size);
        
        edital.getItensHabilitacao().add(item1);
        edital.addItemHabilitacao(item2);
        
        size = edital.getItensHabilitacao().size();
        assertEquals(2, size);
        
        // get itens by edital
        ItemHabilitacao item = edital.getItemHabilitacao("IH0003232");
        assertEquals(item2, item);
        
        // remove edital
        em.remove(edital);
        // remove comprador
        em.remove(comprador);
        // remove itens
        em.remove(item1);
        em.remove(item2);
    }


    // integration tests
    @Test
    public void testGetCompradores() throws Exception {
        Comprador comprador = null;

        List compradores = PersistenceUtilities.findAll(Comprador.class, em);
        assertNotNull(compradores);
        int size = compradores.size();

        comprador = Comprador.createCompradorForTest();
        em.persist(comprador);

        compradores = PersistenceUtilities.findAll(Comprador.class, em);
        assertEquals(size + 1, compradores.size());

        em.remove(em.merge(comprador));
        compradores = PersistenceUtilities.findAll(Comprador.class, em);
        
        tx.commit();
        em.close();
        assertEquals(size, compradores.size());
    }

    @Test
    public void testGetItensHabilitacao() {
        ItemHabilitacao item = null;

        List itens = PersistenceUtilities.findAll(ItemHabilitacao.class, em);
        assertNotNull(itens);
        int size = itens.size();

        item = new ItemHabilitacao("IH999", "Certidao Negativa XYZ");
        em.persist(item);

        itens = PersistenceUtilities.findAll(ItemHabilitacao.class, em);
        assertEquals(size + 1, itens.size());

        em.remove(em.merge(item));
        itens = PersistenceUtilities.findAll(ItemHabilitacao.class, em);
        
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

        cat = new Categoria("Teste");
        em.persist(cat);

        categorias = PersistenceUtilities.findAll(Categoria.class, em);
        assertEquals(size + 1, categorias.size());

        em.remove(em.merge(cat));
        categorias = PersistenceUtilities.findAll(Categoria.class, em);
        
        tx.commit();
        em.close();
        assertEquals(size, categorias.size());

    }
   
    @Test
    public void testProdutos() {
        Categoria cat = CategoriaBase.COMPUTADORES.getCategoria();
        em.persist(cat);
        
        Tag tag1 = new Tag("tag-teste-573485673");
        Tag tag2 = new Tag("tag-teste-204742694");
        Tag tag3 = new Tag("tag-teste-016375233");
        
        tag1 = em.merge(tag1);
        tag2 = em.merge(tag2);
        tag3 = em.merge(tag3);
        
        long tag3ID = tag3.getId();

        List<Produto> produtos = PersistenceUtilities.findAll(Produto.class, em);
        assertNotNull(produtos);
        int size = produtos.size();

        Produto produto = new Produto("Teste946394623", "Teste");
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
        assertEquals("Teste946394623", found.getCodigo());
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
        assertEquals("Teste946394623", found2.getCodigo());
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

        Tag tag = new Tag("tag-teste-573485673");
        em.persist(tag);

        tags = PersistenceUtilities.findAll(Tag.class, em);
        assertEquals(size + 1, tags.size());

        em.remove(em.merge(tag));
        
        tags = PersistenceUtilities.findAll(Tag.class, em);
        assertEquals(size, tags.size());
        
        tx.commit();
        em.close();
    }

    @Test
    public void testGetEditais() throws ParseException {
        Comprador comprador = null;
        Edital edital = null;
        List editais = PersistenceUtilities.findAll(Edital.class, em);
        assertNotNull(editais);
        int size = editais.size();

        comprador = Comprador.createCompradorForTest();
        Codigo numero = new Codigo("000000");
        edital = new PregaoEletronico(comprador, PregaoEletronico.Tipo.COMPRA_DIRETA, numero);
        em.persist(comprador);

        edital.setIdentificacao("Pregao no. 1", new Codigo("000000"), new Codigo("45343"), new CNPJ("04.239.747/0001-58"));
        em.persist(edital); // will create new comprador by cascade persist
        edital = em.merge(edital);

        editais = PersistenceUtilities.findAll(Edital.class, em);
        assertEquals(size + 1, editais.size());
        
        assertEquals("04239747000158", ((Edital) em.find(Edital.class, edital.getId())).getCnpjComprador().toNormalizedString());
        assertEquals(comprador, (em.find(Edital.class, edital.getId())).getComprador());

        em.remove(em.merge(edital));
        em.remove(em.merge(comprador));
        
        editais = PersistenceUtilities.findAll(Edital.class, em);
        
        tx.commit();
        em.close();
        assertEquals(size, editais.size());
    }

}
