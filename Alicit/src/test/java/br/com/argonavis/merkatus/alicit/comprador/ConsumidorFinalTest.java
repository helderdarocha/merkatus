/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.comprador;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author helderdarocha
 */
public class ConsumidorFinalTest {
    
    Comprador comprador = new ConsumidorFinal();
    
        @Test
    public void testGetIdCodigoComprador() {
        assertEquals("Código do comprador", comprador.getIdCodigoComprador());
    }

    @Test
    public void testGetMascara() {
        assertEquals("(\\d.*|\\w.*|\\W.*).*", comprador.getMascara(null));
    }

    @Test
    public void testValidarCodigoComprador() {
        assertTrue(comprador.validarCodigoComprador("A8473B228", null));
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
