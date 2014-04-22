/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.produto;

import br.com.argonavis.merkatus.alicit.proposta.ResultadoLicitacao;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author helderdarocha
 */
public class ProdutoTest {
    
    public ProdutoTest() {
    }
    
    @Before
    public void setUp() {
        Produto p = new Produto();
        p.setCodigo("P123");
        p.setNome("Produto X");
        Categoria c = new Categoria("Categoria");
        Categoria s = new Categoria("SubCategoria");
        c.addSubCategoria(s);
        p.setCategoria(s);
        Tag t1 = new Tag("produto");
        p.addTag(t1);
        p.setPreco(new BigDecimal(123));
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetId() {
    }

    @Test
    public void testSetId() {
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void testToString() {
    }
    
}
