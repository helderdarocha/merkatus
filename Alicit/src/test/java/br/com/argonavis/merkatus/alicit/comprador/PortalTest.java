/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.comprador;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author helderdarocha
 */
public class PortalTest {
    
    Portal bb  = BancoBrasil.criar();
    Portal bec = BolsaEletronicaCompras.criar();
    Portal cn  = ComprasNet.criar();

    @Test
    public void testStaticGetPortais() {
        Set keySet = new HashSet<>();
        keySet.add("BB");
        keySet.add("BEC");
        keySet.add("COMPRASNET");
        keySet.add("TEST");
        assertEquals("Base de portais incompleta ou inconsistente", keySet, Portal.getPortais().keySet());
    }

    @Test
    public void testStaticGetPortal() {
        assertSame("Duplicata de portal BB", bb, Portal.getPortal("BB"));
        assertSame("Duplicata de portal BEC", bec, Portal.getPortal("BEC"));
        assertSame("Duplicata de portal CN", cn, Portal.getPortal("COMPRASNET"));
        assertNull("Portal inexistente retornado", Portal.getPortal("Fake"));
    }

    @Test
    public void testGetIdCodigoComprador() {
        assertEquals("Codigo BB incorreto", "ORGAO", bb.getIdCodigoComprador());
        assertEquals("Codigo BEC incorreto", "OC", bec.getIdCodigoComprador());
        assertEquals("Codigo COMPRASNET incorreto", "UASG", cn.getIdCodigoComprador());
    }
    
    @Test
    public void testGetMascaraCodigoComprador() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        assertEquals("Mascara de validacao do codigo incorreta BEC", 
                "\\d{6}\\d{5}"+year+"OC\\d{5}", bec.getMascaraCodigoComprador(new Date()));
        assertEquals("Mascara de validacao do codigo incorreta BB", 
                "\\d{8}", bb.getMascaraCodigoComprador(null));
        assertEquals("Mascara de validacao do codigo incorreta CN", 
                "\\d{6}", cn.getMascaraCodigoComprador(null));
    }

    @Test
    public void testGetNomeCurto() {
        assertEquals("Portal BB", bb.getNomeCurto());
        assertEquals("Portal BEC", bec.getNomeCurto());
        assertEquals("Portal ComprasNET", cn.getNomeCurto());
    }

    @Test
    public void testGetNomeLongo() {
        assertEquals("Portal de Compras Banco do Brasil", bb.getNomeLongo());
        assertEquals("Bolsa Eletrônica de Compras", bec.getNomeLongo());
        assertEquals("Portal de Compras do Governo Federal", cn.getNomeLongo());
    }

    @Test
    public void testGetWebsite() {
        assertEquals("www.licitacoes-e.com.br", bb.getWebsite());
        assertEquals("www.bec.sp.gov.br", bec.getWebsite());
        assertEquals("www.comprasnet.gov.br", cn.getWebsite());
    }
    
    @Test
    public void testEquals() {
        assertEquals("Portais diferentes quando nao deveriam ser: BB", bb, new BancoBrasil());
        assertEquals("Portais diferentes quando nao deveriam ser: BEC", bec, new BolsaEletronicaCompras());
        assertEquals("Portais diferentes quando nao deveriam ser: CN", cn, new ComprasNet());
    }
    
    @Test
    public void testSame() {
        assertSame("Portais objetos diferentes quando nao deveriam ser: BB", bb, BancoBrasil.criar());
        assertSame("Portais objetos diferentes quando nao deveriam ser: BEC", bec, BolsaEletronicaCompras.criar());
        assertSame("Portais objetos diferentes quando nao deveriam ser: CN", cn, ComprasNet.criar());
    }
    
    @Test
    public void testHashCode() throws ParseException {
        assertEquals("HashCode inconsistente: BB", bb.hashCode(), new BancoBrasil().hashCode());
        assertEquals("HashCode inconsistente: BEC", bec.hashCode(), new BolsaEletronicaCompras().hashCode());
        assertEquals("HashCode inconsistente: CN", cn.hashCode(), new ComprasNet().hashCode());
    }
}
