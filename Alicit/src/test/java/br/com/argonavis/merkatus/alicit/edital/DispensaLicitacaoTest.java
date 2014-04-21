/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital;

import br.com.argonavis.merkatus.alicit.comprador.BancoBrasil;
import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author helderdarocha
 */
public class DispensaLicitacaoTest {
    
    @Test
    public void testGetComprador() {
        DispensaLicitacao d1 = new DispensaLicitacao();
        assertNull(d1.getComprador());
        
        Comprador c1 = Comprador.createCompradorComprasNet();
        DispensaLicitacao d2 = new DispensaLicitacao(c1, DispensaLicitacao.Tipo.COMPRA_DIRETA, new Codigo("123456"));
        assertEquals(c1, d2.getComprador());
        
        DispensaLicitacao d3 = new DispensaLicitacao(c1, DispensaLicitacao.Tipo.CONVITE_ELETRONICO, new Codigo("123457"));
        assertEquals(c1, d3.getComprador());
    }
    
    @Test
    public void testGetTipo() {
        DispensaLicitacao d1 = new DispensaLicitacao();
        assertNull(d1.getTipo());
        
        Comprador c1 = Comprador.createCompradorBB();
        DispensaLicitacao d2 = new DispensaLicitacao(c1, DispensaLicitacao.Tipo.COMPRA_DIRETA, new Codigo("123459"));
        assertEquals(DispensaLicitacao.Tipo.COMPRA_DIRETA, d2.getTipo());
        
        DispensaLicitacao d3 = new DispensaLicitacao(c1, DispensaLicitacao.Tipo.CONVITE_ELETRONICO, new Codigo("123458"));
        assertEquals(DispensaLicitacao.Tipo.CONVITE_ELETRONICO, d3.getTipo());
    }
    
}
