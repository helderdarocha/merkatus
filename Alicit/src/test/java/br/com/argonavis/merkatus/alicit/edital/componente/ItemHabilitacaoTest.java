/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital.componente;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author helderdarocha
 */
public class ItemHabilitacaoTest {
    
    ItemHabilitacao ih = new ItemHabilitacao();
    
    @Before
    public void setUp() {
        
        ih.setCodigo("PRUSOF");
        ih.setDescricao("Prova de regularidade XYZ");
    }
    
    @Test
    public void testGetNome() {
        assertEquals("Prova de regularidade XYZ", ih.getDescricao());
    }
    
    @Test
    public void testGetCodigo() {
        assertEquals("PRUSOF", ih.getCodigo());
    }

    @Test
    public void testHashCode() {
        ItemHabilitacao ih2 = new ItemHabilitacao("PRUSOF", "Prova de regularidade XYZ");
        assertEquals(ih.hashCode(), ih2.hashCode());
    }

    @Test
    public void testEquals() {
        ItemHabilitacao ih2 = new ItemHabilitacao("PRUSOF", "Prova de regularidade XYZ");
        assertEquals(ih, ih2);
    }

    @Test
    public void testToString() {
        assertEquals("Habilitacao PRUSOF Prova de regularidade XYZ", ih.toString());
    }
    
}
