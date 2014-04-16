/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.comprador;

import br.com.argonavis.merkatus.alicit.edital.DispensaLicitacao;
import br.com.argonavis.merkatus.alicit.edital.Edital;
import br.com.argonavis.merkatus.alicit.edital.PregaoEletronico;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author helderdarocha
 */
public class BancoBrasilTest {
    
    Comprador comprador = new BancoBrasil();
    
    @Test
    public void testGetIdCodigoComprador() {
        assertEquals("ORGAO", comprador.getIdCodigoComprador());
    }

    @Test
    public void testGetMascara() {
        assertEquals("\\d{8}", comprador.getMascara(null));
    }

    @Test
    public void testValidarCodigoComprador() {
        assertTrue(comprador.validarCodigoComprador("12345678", null));
    }

    @Test
    public void testGetNomeCurto() {
        assertEquals("Portal BB", comprador.getNomeCurto());
    }

    @Test
    public void testGetNomeLongo() {
        assertEquals("Portal de Compras Banco do Brasil", comprador.getNomeLongo());
    }

    @Test
    public void testGetCodigo() {
        assertEquals("BB", comprador.getCodigo());
    }

    @Test
    public void testGetWebsite() {
        assertEquals("www.licitacoes-e.com.br", comprador.getWebsite());
    }
    
}
