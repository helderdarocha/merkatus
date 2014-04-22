/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.produto;

import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author helderdarocha
 */
public class CategoriaTest {

    @Before
    public void setUp() {
        Categoria cat = new Categoria("aaa");
        Categoria sub = new Categoria("sub");
        Categoria sub2 = new Categoria("sub2");
        Categoria ss = new Categoria("ss");
        cat.addSubCategoria(sub);
        cat.addSubCategoria(sub2);
        sub2.addSubCategoria(ss);
        Categoria p = ss.getParent();
        Categoria parent = ss.getParent().getParent();
        Categoria root = ss.getRoot();
        Set<Categoria> children = cat.getSubCategorias();
        Categoria removed = sub2.detachSubCategoria("ss");
        
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
