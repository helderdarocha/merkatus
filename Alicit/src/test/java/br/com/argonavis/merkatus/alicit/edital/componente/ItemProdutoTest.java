/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital.componente;

import br.com.argonavis.merkatus.alicit.produto.Produto;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author helderdarocha
 */
public class ItemProdutoTest {
    
    ItemProduto item = new ItemProduto();
    
    @Before
    public void setUp() {
        item.setQuantidade(3);
        Produto p = new Produto("P123", "Produto X");
        p.setPreco(BigDecimal.valueOf(200.00));
        item.setProduto(p);
    }
    
    @Test
    public void testGetValorTotal() {
        assertEquals(BigDecimal.valueOf(600.0), item.getValorTotal());
    }

    @Test
    public void testHashCode() {
        ItemProduto ip = new ItemProduto();
        Produto p = new Produto("P123", "Produto X");
        ip.setProduto(p);
        item.setQuantidade(3);
        assertEquals(item.hashCode(), ip.hashCode());
    }

    @Test
    public void testEquals() {
        ItemProduto ip = new ItemProduto();
        Produto p = new Produto("P123", "Produto X");
        ip.setProduto(p);
        item.setQuantidade(3);
        assertEquals(item, ip);
    }
    
}
