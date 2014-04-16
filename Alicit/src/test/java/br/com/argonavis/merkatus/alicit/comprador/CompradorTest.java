/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.comprador;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author helderdarocha
 */
public class CompradorTest {
    
    Comprador comprador = new CompradorImpl();

    @Test
    public void testGetIdCodigoComprador() {
        assertEquals("123", comprador.getIdCodigoComprador());
    }

    @Test
    public void testGetMascara() {
        assertEquals("\\d.*", comprador.getMascara(null));
    }
    
    @Test
    public void testValidarCodigoComprador() {
        assertTrue(comprador.validarCodigoComprador("123", null));
    }

    public class CompradorImpl extends Comprador {

        public String getIdCodigoComprador() {
            return "123";
        }

        public String getMascara(Date dataEdital) {
            return "\\d.*";
        }
    }
    
}
