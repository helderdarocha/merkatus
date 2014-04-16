/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital;

import br.com.argonavis.merkatus.alicit.comprador.BancoBrasil;
import br.com.argonavis.merkatus.alicit.comprador.BolsaEletronicaCompras;
import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import br.com.argonavis.merkatus.alicit.comprador.ComprasNet;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author helderdarocha
 */
public class CodigoTest {
    
    Comprador cn = new ComprasNet();
    Comprador bb = new BancoBrasil();
    Comprador bc = new BolsaEletronicaCompras();

    @org.junit.Test
    public void testToString() throws ParseException {
        Codigo cnUasg = new Codigo("123456", cn.getMascara(new Date()));
        Codigo bbOrgao = new Codigo("12345678", bb.getMascara(new Date()));
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Codigo becOc = new Codigo("12345678901"+year+"OC12345", bc.getMascara(new Date()));
        
        assertEquals("123456", cnUasg.toString());
        assertEquals("12345678", bbOrgao.toString());
        assertEquals("12345678901"+year+"OC12345", becOc.toString());
    }
    
    @Test
    public void testInvalidCodigo() {
        try {
           Codigo cnUasg = new Codigo("12345", cn.getMascara(new Date()));
           fail("Invalid codigo passed for UASG!");
        } catch (ParseException e) { }
        
        try {
           Codigo bbOrgao = new Codigo("123456789", bb.getMascara(new Date()));
           fail("Invalid codigo passed for bb orgao!");
        } catch (ParseException e) { }
        
        try {
           Codigo becOc = new Codigo("123456789011900OC12345", bc.getMascara(new Date()));
           fail("Invalid codigo passed for bec oc!");
        } catch (ParseException e) { }
    }
    
    @Test
    public void testEquals() throws ParseException {
        int ano = 2014;
        Codigo b1 = new Codigo("12345678901"+ano+"OC12345", bc.getMascara(new Date()));
        Codigo b2 = new Codigo("12345678901"+ano+"OC12345");
        assertEquals(b1, b2);
    }
    
    @Test
    public void testHashCode() throws ParseException {
        int ano = 2014;
        Codigo b1 = new Codigo("12345678901"+ano+"OC12345", bc.getMascara(new Date()));
        Codigo b2 = new Codigo("12345678901"+ano+"OC12345");
        assertEquals(b1.hashCode(), b2.hashCode());
    }
    
}
