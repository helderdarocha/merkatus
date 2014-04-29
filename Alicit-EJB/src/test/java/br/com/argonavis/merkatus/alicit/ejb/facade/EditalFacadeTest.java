/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import br.com.argonavis.merkatus.alicit.edital.Codigo;
import br.com.argonavis.merkatus.alicit.edital.Edital;
import br.com.argonavis.merkatus.alicit.edital.PregaoEletronico;
import br.com.argonavis.merkatus.alicit.edital.componente.ItemHabilitacao;
import br.com.argonavis.merkatus.alicit.edital.componente.ItemProduto;
import br.com.argonavis.merkatus.alicit.ejb.LookupService;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.CompradorFacadeRemote;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.EditalFacadeRemote;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ItemHabilitacaoFacadeRemote;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ItemProdutoFacadeRemote;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ProdutoFacadeRemote;
import br.com.argonavis.merkatus.alicit.produto.Produto;
import java.math.BigDecimal;
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
public class EditalFacadeTest {
    LookupService<EditalFacadeRemote> editalService = new LookupService<>();
    LookupService<CompradorFacadeRemote> compradorService = new LookupService<>();
    EditalFacadeRemote editalFacade = editalService.lookupBean(EditalFacade.class, EditalFacadeRemote.class);
    CompradorFacadeRemote compradorFacade = compradorService.lookupBean(CompradorFacade.class, CompradorFacadeRemote.class);
    LookupService<ProdutoFacadeRemote> produtoService = new LookupService<>();
    ProdutoFacadeRemote produtoFacade = produtoService.lookupBean(ProdutoFacade.class, ProdutoFacadeRemote.class);
    LookupService<ItemProdutoFacadeRemote> itemProdutoService = new LookupService<>();
    ItemProdutoFacadeRemote itemProdutoFacade = itemProdutoService.lookupBean(ItemProdutoFacade.class, ItemProdutoFacadeRemote.class);
    LookupService<ItemHabilitacaoFacadeRemote> ItemHabilitacaoService = new LookupService<>();
    ItemHabilitacaoFacadeRemote itemHabilitacaoFacade = ItemHabilitacaoService.lookupBean(ItemHabilitacaoFacade.class, ItemHabilitacaoFacadeRemote.class);
    
    @Before
    public void testCreate() throws Exception {
        Comprador comprador = Comprador.createCompradorForTest();
        compradorFacade.create(comprador);
        Comprador found = compradorFacade.querySingle("select c from Comprador c where c.identificador = 'TEST'");
        
        int count = editalFacade.count();
        Edital edital = new PregaoEletronico(found, PregaoEletronico.Tipo.COMPRA_DIRETA, new Codigo("000000"));
        editalFacade.create(edital);
        assertEquals(count + 1, editalFacade.count());
    }
    
    @After
    public void testRemove() throws Exception {
        int count = editalFacade.count();
        Edital found = editalFacade.querySingle("select e from Edital e where e.numeroEdital.codigo = '000000'");
        
        Comprador comprador = found.getComprador();
        
        editalFacade.remove(found);
        compradorFacade.remove(comprador);
        assertEquals(count - 1, editalFacade.count());
    }
    
    @Test
    public void testItemHabilitacao() throws Exception {
        Edital found = editalFacade.getByNumeroEdital("000000");
        
        int ic = itemHabilitacaoFacade.count();
        // Create ihs
        ItemHabilitacao item1 = new ItemHabilitacao("IH9991235", "Certidao Negativa XYZ");
        itemHabilitacaoFacade.create(item1);
        assertEquals(ic+1, itemHabilitacaoFacade.count());
        item1 = itemHabilitacaoFacade.getByCodigo("IH9991235");
        
        ItemHabilitacao item2 = new ItemHabilitacao("IH0003232", "Certidao Negativa ABC");
        itemHabilitacaoFacade.create(item2);
        assertEquals(ic+2, itemHabilitacaoFacade.count());
        item2 = itemHabilitacaoFacade.getByCodigo("IH0003232");
        
        // add itens to edital
        int size = found.getItensHabilitacao().size();
        assertEquals(0, size);
       
        found.getItensHabilitacao().add(item1);
        found.addItemHabilitacao(item2);
        editalFacade.edit(found);
        
        size = found.getItensHabilitacao().size();
        assertEquals(2, size);
        
        // get itens by edital
        ItemHabilitacao item = found.getItemHabilitacao("IH0003232");
        assertEquals(item2, item);
 
        // remove itens
        found.getItensHabilitacao().clear();
        editalFacade.edit(found);
        
        itemHabilitacaoFacade.remove(item1);
        itemHabilitacaoFacade.remove(item2);
        
        assertEquals(0, found.getItensHabilitacao().size());
    }
    
    @Test
    public void testItemProduto() throws Exception {
        Edital found = editalFacade.getByNumeroEdital("000000");
        
        int pc = produtoFacade.count();
        
        // Create produtos
        Produto p1 = new Produto("Teste946394623", "Produto Teste 1");
        p1.setPreco(BigDecimal.valueOf(100.00));
        produtoFacade.create(p1);
        assertEquals(pc+1, produtoFacade.count());
        p1 = produtoFacade.getByCodigo("Teste946394623");
        Produto p2 = new Produto("Teste334258495", "Produto Teste 2");
        p2.setPreco(BigDecimal.valueOf(30.00));
        produtoFacade.create(p2);
        assertEquals(pc+2, produtoFacade.count());
        p2 = produtoFacade.getByCodigo("Teste334258495");
        
        // Create itens for produtos
        ItemProduto i1 = new ItemProduto();
        itemProdutoFacade.create(i1);
        ItemProduto i2 = new ItemProduto();
        itemProdutoFacade.create(i2);
        i1.setProduto(p1);
        i1.setQuantidade(5);
        i2.setProduto(p2);
        i2.setQuantidade(3);
        i1 = itemProdutoFacade.edit(i1);
        i2 = itemProdutoFacade.edit(i2);

         // add itens to edital
        int size = found.getItensProduto().size();
        assertEquals(0, size);
        
        found.getItensProduto().add(i1);
        found.addItemProduto(i2);
        editalFacade.edit(found);
        
        size = found.getItensProduto().size();
        assertEquals(2, size);
        
        // get itens
        ItemProduto i = found.getItemProduto("Teste946394623");
        i.getProduto().setPreco(BigDecimal.valueOf(75.00));
        i.setQuantidade(4);
        
        editalFacade.edit(found);
        assertEquals(BigDecimal.valueOf(300.0), i.getValorTotal());
        
        // calculate value of edital
        assertEquals(BigDecimal.valueOf(390.0), found.getValorTotal());
 
        // remove itens
        found.getItensProduto().clear();
        editalFacade.edit(found);
    
        itemProdutoFacade.remove(i1);
        itemProdutoFacade.remove(i2);
        // remove produtos
        produtoFacade.remove(p1);
        produtoFacade.remove(p2);
        
        assertEquals(0, found.getItensProduto().size());
    }
    /*
    @Test
    public void testSetComprador() throws Exception {
        Edital found = editalFacade.querySingle("select e from Edital e where e.numeroEdital.codigo = '000000'");
        found.setComprador(found.getComprador());
    }
    
    @Test
    public void testMerge() throws Exception {
        Edital found = editalFacade.querySingle("select e from Edital e where e.numeroEdital.codigo = '000000'");
        Edital merged = editalFacade.edit(found);
        assertNotNull(merged);
    }

    @Test
    public void testFind() throws Exception {
        Edital foundQuery = editalFacade.querySingle("select e from Edital e where e.numeroEdital.codigo = '000000'");
        System.out.println("Comprador ID: " + foundQuery.getId());
        Edital foundGet = editalFacade.find(foundQuery.getId());
        assertEquals(foundQuery, foundGet);
    }
    
    @Test
    public void testFindAll() throws Exception {
        int count = editalFacade.count();
        List<Edital> all = editalFacade.findAll();
        assertEquals(count, all.size());
    }
*/
    @Test
    public void testCount() throws Exception {
        int elements = editalFacade.count();
        assertTrue(elements > 0);
    }
    
}
