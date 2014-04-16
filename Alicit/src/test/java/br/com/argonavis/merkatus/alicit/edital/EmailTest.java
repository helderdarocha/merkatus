/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital;

import java.text.ParseException;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author helderdarocha
 */
public class EmailTest {

    @Test
    public void testToString() throws ParseException {
        Email email = new Email("helder@argonavis.com.br");
        assertEquals("helder@argonavis.com.br", email.toString());
    }
    
    @Test
    public void testInvalidEmail() {
        try {
           Email email = new Email("@argonavis.com.br");
           fail("Invalid email passed!");
        } catch (ParseException e) { }
    }
    
    @org.junit.Test
    public void testEquals() throws ParseException {
        Email e1 = new Email("helder@argonavis.com.br");
        Email e2 = new Email("helder@argonavis.com.br");
        assertEquals(e1, e2);
    }
    
    @Test
    public void testHashCode() throws ParseException {
        Email e1 = new Email("helder@argonavis.com.br");
        Email e2 = new Email("helder@argonavis.com.br");
        assertEquals(e1.hashCode(), e2.hashCode());
    }
    
}
