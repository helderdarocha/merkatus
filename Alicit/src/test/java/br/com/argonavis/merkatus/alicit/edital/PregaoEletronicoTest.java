/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital;

import br.com.argonavis.merkatus.alicit.comprador.BancoBrasil;
import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author helderdarocha
 */
public class PregaoEletronicoTest {
    
    @Test
    public void testGetComprador() {
        PregaoEletronico d1 = new PregaoEletronico();
        assertNull(d1.getComprador());
        
        Comprador c1 = Comprador.createCompradorBB();
        PregaoEletronico d2 = new PregaoEletronico(c1, PregaoEletronico.Tipo.COMPRA_DIRETA, new Codigo("223777"));
        assertEquals(c1, d2.getComprador());
        
        PregaoEletronico d3 = new PregaoEletronico(c1, PregaoEletronico.Tipo.SRP_CARONA, new Codigo("223776"));
        assertEquals(c1, d3.getComprador());
    }
    
    @Test
    public void testGetTipo() {
        PregaoEletronico d1 = new PregaoEletronico();
        assertNull(d1.getTipo());
        
        Comprador c1 = Comprador.createCompradorBB();
        PregaoEletronico d2 = new PregaoEletronico(c1, PregaoEletronico.Tipo.COMPRA_DIRETA, new Codigo("323777"));
        assertEquals(PregaoEletronico.Tipo.COMPRA_DIRETA, d2.getTipo());
        
        PregaoEletronico d3 = new PregaoEletronico(c1, PregaoEletronico.Tipo.SRP_CARONA, new Codigo("323776"));
        assertEquals(PregaoEletronico.Tipo.SRP_CARONA, d3.getTipo());
    }
    
}
