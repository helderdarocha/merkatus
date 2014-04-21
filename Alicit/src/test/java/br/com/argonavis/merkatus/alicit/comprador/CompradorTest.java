/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.comprador;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author helderdarocha
 */
public class CompradorTest {
    
    Comprador bb;
    Comprador bec;
    Comprador cn;
    
    @Before
    @Test
    public void testCreateAndInit() {
        bb  = new Comprador("BB");
        bb.initComprador();
        bec = new Comprador("BEC");
        bec.initComprador();
        cn  = new Comprador("COMPRASNET");
        cn.initComprador();
    }
    
    @Test
    public void testFailCreate() {
        try {
            bb  = new Comprador("FAKE");
            fail("Criacao de comprador invalido nao causou excecao");
        } catch (IllegalArgumentException e) {}
    }
    
    @Test
    public void testGetIdentificador() {
        assertEquals("Identificador BB incorreto", "BB", bb.getIdentificador());
        assertEquals("Identificador BEC incorreto", "BEC", bec.getIdentificador());
        assertEquals("Identificador COMPRASNET incorreto", "COMPRASNET", cn.getIdentificador());
        
    }
    
    @Test
    public void testGetPortal() {
        assertTrue("Portal BB incorreto", bb.getPortal() instanceof BancoBrasil);
        assertTrue("Portal BEC incorreto", bec.getPortal() instanceof BolsaEletronicaCompras);
        assertTrue("Portal COMPRASNET incorreto", cn.getPortal() instanceof ComprasNet);
    }

    @Test
    public void testValidarCodigoComprador() {
        int ano = Calendar.getInstance().get(Calendar.YEAR);
        assertTrue("Codigo BEC OC valida incorretamente", bec.validarCodigoComprador("12345678901"+ano+"OC12345", new Date()));
        assertTrue("Codigo BB Orgao valida incorretamente", bb.validarCodigoComprador("12345678", null));
        assertTrue("Codigo ComprasNet UASG valida incorretamente", cn.validarCodigoComprador("123456", null));
    }

    @Test
    public void testEquals() {
        assertEquals("Compradores diferentes quando nao deveriam ser: BB!", bb, Comprador.createCompradorBB());
        assertEquals("Compradores diferentes quando nao deveriam ser: BEC!", bec, Comprador.createCompradorBEC());
        assertEquals("Compradores diferentes quando nao deveriam ser: CN!", cn, Comprador.createCompradorComprasNet());
    }
    
    @Test
    public void testHashCode() throws ParseException {
        assertEquals("HashCode inconsistente: BB", bb.hashCode(), Comprador.createCompradorBB().hashCode());
        assertEquals("HashCode inconsistente: BEC", bec.hashCode(), Comprador.createCompradorBEC().hashCode());
        assertEquals("HashCode inconsistente: CN", cn.hashCode(), Comprador.createCompradorComprasNet().hashCode());
    }
    
}
