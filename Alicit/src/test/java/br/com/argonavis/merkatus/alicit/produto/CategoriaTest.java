/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.produto;

import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author helderdarocha
 */
public class CategoriaTest {
    
    Categoria cat = new Categoria(CategoriaBase.COMPUTADORES.toString());
    Categoria sub = new Categoria("Sub categoria 1");
    Categoria sub2 = new Categoria("Sub categoria 2");
    Categoria ss = new Categoria("Sub categoria 3");

    @Before
    public void setUp() {
        cat.addSubCategoria(sub);
        cat.addSubCategoria(sub2);
        sub2.addSubCategoria(ss);
    }

    @Test
    public void testGetParent() {
        Categoria p1 = ss.getParent();
        Categoria p2 = ss.getParent().getParent();
        assertEquals(p1, sub2);
        assertEquals(p2, cat);
    }
    
    @Test
    public void testGetRoot() {
        Categoria root = ss.getRoot();
        assertEquals(root, cat);
    }
    
    @Test
    public void testSubCategorias() {
        Set<Categoria> children = cat.getSubCategorias();
        assertEquals(2, children.size());
    }
    
    @Test
    public void detachSubCategoria() {
        Set<Categoria> children = sub2.getSubCategorias();
        for(Categoria c: children) {
            System.out.println(c);
        }
        int size = children.size();
        Categoria removed = sub2.detachSubCategoria("Sub categoria 3");
        assertTrue(removed.getParent() == null);
        children = sub2.getSubCategorias();
        for(Categoria c: children) {
            System.out.println(c);
        }
        assertEquals(size - 1, children.size());
        
    }

    @Test
    public void testHashCode() {
        Categoria m = new Categoria("moo", cat);
        int h1 = m.hashCode();
        Categoria n = new Categoria("moo");
        n.setParent(cat);
        int h2 = m.hashCode();
        Categoria o = new Categoria("moo");
        cat.addSubCategoria(o);
        int h3 = m.hashCode();
        
        assertEquals(h1, h2);
        assertEquals(h2, h3);
        assertEquals(h1, h3);
    }

    @Test
    public void testEquals() {
        Categoria m = new Categoria("moo", cat);
        Categoria n = new Categoria("moo");
        n.setParent(cat);
        assertEquals(m, n);
        
        Categoria o = new Categoria("moo");
        cat.addSubCategoria(o);
        
        assertEquals(m, o);
    }

    @Test
    public void testToString() {
        Categoria m = new Categoria("moo", cat);
        assertEquals("/Computadores/moo", m.toString());
        assertEquals("/Computadores/Sub categoria 2/Sub categoria 3", ss.toString());
    }
    
}
