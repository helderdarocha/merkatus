/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital;

import java.text.ParseException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author helderdarocha
 */
public class EnderecoTest {

    Endereco detail = new Endereco("Rua Direita", "123", "sala 12", "centro", "Sao Paulo", "SP", "01002-001");
    Endereco simple = new Endereco("Rua Direita, 123, sala 12, centro", "Sao Paulo", "SP", "01002-001");
    Endereco geo    = new Endereco("123.34567", "-90.34567");
    
    @org.junit.Test
    public void testToString() {
        assertEquals("Rua Direita, 123, sala 12, centro, Sao Paulo, SP, 01002-001", detail.toString());
        assertEquals("Rua Direita, 123, sala 12, centro, Sao Paulo, SP, 01002-001", simple.toString());
        assertEquals("Geolocation: 123.34567, -90.34567", geo.toString());
        geo.setEndereco("Rua Direita, 123, sala 12, centro");
        assertEquals("Rua Direita, 123, sala 12, centro, null, null, null, Geolocation: 123.34567, -90.34567", geo.toString());
        geo.setEndereco(null);
        assertEquals("Geolocation: 123.34567, -90.34567", geo.toString());
        geo.setRua("Rua Direita");
        geo.setNumero("123");
        geo.setBairro("centro");
        geo.setCidade("Sao Paulo");
        assertEquals("Rua Direita, 123, centro, Sao Paulo, null, null, Geolocation: 123.34567, -90.34567", geo.toString());
        geo.setComplemento("sala 12");
        assertEquals("Rua Direita, 123, sala 12, centro, Sao Paulo, null, null, Geolocation: 123.34567, -90.34567", geo.toString());
    }
    
    @org.junit.Test
    public void testEquals() {
        assertEquals(detail, simple);
    }
    
    @Test
    public void testHashCode() throws ParseException {
        assertEquals(detail.hashCode(), detail.hashCode());
    }
    
    @Test
    public void testSetupWithGeoLocation() throws Exception {
        // not implemented
    
    }
}
