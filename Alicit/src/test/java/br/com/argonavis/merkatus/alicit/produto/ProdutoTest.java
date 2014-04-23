/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.produto;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author helderdarocha
 */
public class ProdutoTest {
    
    Produto p = new Produto("P123", "Produto X");
    
    @Before
    public void setUp() {
        Categoria cat = new Categoria("Categoria", CategoriaBase.COMPUTADORES.getCategoria());
        p.setCategoria(cat);
        Tag t1 = new Tag("produto");
        Tag t2 = new Tag("item");
        Tag t3 = new Tag("coisa");
        p.addTag(t1);
        p.addTag(t2);
        p.addTag(t3);
        p.setPreco(new BigDecimal(123));
    }
    
    @Test
    public void testGetPreco() {
        assertEquals(BigDecimal.valueOf(123), p.getPreco());
    }

    @Test
    public void testGetTags() {
        assertEquals(3, p.getTags().size());
        p.getTags().remove(new Tag("item"));
        p.getTags().remove(new Tag("coisa"));
        assertEquals(1, p.getTags().size());
        assertEquals("produto", p.getTags().iterator().next().toString());
    }

    @Test
    public void testGetCategoria() {
        assertEquals(new Categoria("Categoria", CategoriaBase.COMPUTADORES.getCategoria()), p.getCategoria());
    }
    
    @Test
    public void testGetNome() {
        assertEquals("Produto X", p.getNome());
    }
    
    @Test
    public void testGetCodigo() {
        assertEquals("P123", p.getCodigo());
    }

    @Test
    public void testHashCode() {
        Produto p2 = new Produto("P123", "Produto X");
        assertEquals(p.hashCode(), p2.hashCode());
    }

    @Test
    public void testEquals() {
        Produto p2 = new Produto("P123", "Produto X");
        assertEquals(p, p2);
    }

    @Test
    public void testToString() {
        assertEquals("P123 Produto X", p.toString());
    }
    
}
