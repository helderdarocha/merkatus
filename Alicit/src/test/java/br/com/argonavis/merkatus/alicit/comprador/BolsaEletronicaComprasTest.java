/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.comprador;

import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author helderdarocha
 */
public class BolsaEletronicaComprasTest {
    
    Comprador comprador = new BolsaEletronicaCompras();
    
        @Test
    public void testGetIdCodigoComprador() {
        assertEquals("OC", comprador.getIdCodigoComprador());
    }

    @Test
    public void testGetMascara() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        assertEquals("\\d{6}\\d{5}"+year+"OC\\d{5}", comprador.getMascara(new Date()));
    }

    @Test
    public void testValidarCodigoComprador() {
        int ano = Calendar.getInstance().get(Calendar.YEAR);
        assertTrue(comprador.validarCodigoComprador("12345678901"+ano+"OC12345", new Date()));
    }

    @Test
    public void testGetNomeCurto() {
        assertEquals("Portal BEC", comprador.getNomeCurto());
    }

    @Test
    public void testGetNomeLongo() {
        assertEquals("Bolsa Eletrônica de Compras", comprador.getNomeLongo());
    }

    @Test
    public void testGetCodigo() {
        assertEquals("BEC", comprador.getCodigo());
    }

    @Test
    public void testGetWebsite() {
        assertEquals("www.bec.sp.gov.br", comprador.getWebsite());
    }
    
    // testes de relacionamento - integracao
    
    @Test
    public void testGetEditais() {
        
    }

    @Test
    public void testAddEdital() {
        
    }

    @Test
    public void testRemoveEdital() {
        
    }

    @Test
    public void testSetEditais() {
        
    }
    
}
