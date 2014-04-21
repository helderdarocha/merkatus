/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital.componente;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author helderdarocha
 */
public class ItemDocumentoTest {
    
    public ItemDocumentoTest() {
    }
    
    @Before
    public void setUp() {
        ItemDocumento id = new ItemDocumento();
        id.setCodigo("IHPRXYZ");
        id.setDescricao("Prova de regularidade XYZ");
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
