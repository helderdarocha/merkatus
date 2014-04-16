/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital;

import java.text.ParseException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author helderdarocha
 */
public class TelefoneTest {
    
    @org.junit.Test
    public void testFormatNumero() {
        Telefone t1 = new Telefone("11", "992938293");
        Telefone t2 = new Telefone("85", "32938293");
        assertEquals("992938293", t1.formatNumero());
        assertEquals("32938293", t2.formatNumero());
    }

    @org.junit.Test
    public void testToString() {
        Telefone t1 = new Telefone("11", "992938293");
        Telefone t2 = new Telefone("85", "32938293");
        assertEquals("(11)992938293", t1.toString());
        assertEquals("(85)32938293", t2.toString());
    }

    @org.junit.Test
    public void testGetNumeroDiscavel() {
        Telefone t1 = new Telefone("11", "992938293");
        Telefone t2 = new Telefone("85", "32938293");
        assertEquals("+5511992938293", t1.getNumeroDiscavel());
        assertEquals("+558532938293", t2.getNumeroDiscavel());
    }
    
     @org.junit.Test
    public void testEquals() {
        Telefone t1 = new Telefone("11", "992938293");
        Telefone t2 = new Telefone("11", "992938293");
        assertEquals(t1, t2);
    }
    
        @Test
    public void testHashCode() throws ParseException {
        Telefone t1 = new Telefone("11", "992938293");
        Telefone t2 = new Telefone("11", "992938293");
        assertEquals(t1.hashCode(), t2.hashCode());
    }
}
