/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital.componente;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author helderdarocha
 */
public class ItemDocumentoTest {
    
    ItemDocumento id = new ItemDocumento();
    
    @Before
    public void setUp() {
        id.setCodigo("PDGSKD");
        id.setDescricao("Documento XYZ");
    }
    
    @Test
    public void testGetDescricao() {
        assertEquals("Documento XYZ", id.getDescricao());
    }
    
    @Test
    public void testGetCodigo() {
        assertEquals("PDGSKD", id.getCodigo());
    }

    @Test
    public void testHashCode() {
        ItemDocumento id2 = new ItemDocumento("PDGSKD", "Documento XYZ");
        assertEquals(id.hashCode(), id2.hashCode());
    }

    @Test
    public void testEquals() {
        ItemDocumento id2 = new ItemDocumento("PDGSKD", "Documento XYZ");
        assertEquals(id, id2);
    }

    @Test
    public void testToString() {
        assertEquals("Documento PDGSKD Documento XYZ", id.toString());
    }
    
}
