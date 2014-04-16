/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital;

import java.text.ParseException;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author helderdarocha
 */
public class CNPJTest {
    
    @org.junit.Test
    public void testRegexp() {
        assertTrue("04239747000158".matches("\\d{14}"));
        assertTrue("04.239.747/0001-58".matches("\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}"));
    }
    
    @org.junit.Test
    public void testToString() throws Exception {
        CNPJ cnpj1 = new CNPJ("04.239.747/0001-58");
        assertEquals("04.239.747/0001-58", cnpj1.toString());
        CNPJ cnpj2 = new CNPJ("04239747000158");
        assertEquals("04.239.747/0001-58", cnpj2.toString());
    }

    @org.junit.Test
    public void testToNormalizedString() throws Exception {
        CNPJ cnpj1 = new CNPJ("04.239.747/0001-58");
        assertEquals("04239747000158", cnpj1.toNormalizedString());
        CNPJ cnpj2 = new CNPJ("04239747000158");
        assertEquals("04239747000158", cnpj2.toNormalizedString());
    }
    
    @Test
    public void testEquals() throws ParseException {
        CNPJ c1 = new CNPJ("04.239.747/0001-58");
        CNPJ c2 = new CNPJ("04239747000158");
        assertEquals(c1, c2);
    }
    
    @Test
    public void testHashCode() throws ParseException {
        CNPJ c1 = new CNPJ("04.239.747/0001-58");
        CNPJ c2 = new CNPJ("04239747000158");
        assertEquals(c1.hashCode(), c2.hashCode());
    }
}
