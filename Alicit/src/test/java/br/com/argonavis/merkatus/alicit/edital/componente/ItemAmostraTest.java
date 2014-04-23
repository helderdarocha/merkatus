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
public class ItemAmostraTest {
    
    ItemAmostra ia = new ItemAmostra();
    
    @Before
    public void setUp() {
        ia.setCodigo("IHPRXYZ");
        ia.setDescricao("Amostra XYZ");
    }
    
    @Test
    public void testGetDescricao() {
        assertEquals("Amostra XYZ", ia.getDescricao());
    }
    
    @Test
    public void testGetCodigo() {
        assertEquals("IHPRXYZ", ia.getCodigo());
    }

    @Test
    public void testHashCode() {
        ItemAmostra ia2 = new ItemAmostra("IHPRXYZ", "Amostra XYZ");
        assertEquals(ia.hashCode(), ia2.hashCode());
    }

    @Test
    public void testEquals() {
        ItemAmostra ia2 = new ItemAmostra("IHPRXYZ", "Amostra XYZ");
        assertEquals(ia, ia2);
    }

    @Test
    public void testToString() {
        assertEquals("Amostra IHPRXYZ Amostra XYZ", ia.toString());
    }
    
}
