/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.produto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author helderdarocha
 */
public class TagTest {
    
    Tag t = new Tag("tag");
  
    
   
    @Test
    public void testGetNome() {
        assertEquals("tag", t.getNome());
    }

    @Test
    public void testHashCode() {
        Tag t1 = new Tag("tag");
        assertEquals(t1.hashCode(), t.hashCode());
    }

    @Test
    public void testEquals() {
        Tag t1 = new Tag("tag");
        assertEquals(t1, t);
    }

    @Test
    public void testToString() {
        assertEquals("tag", t.toString());
    }
    
}
