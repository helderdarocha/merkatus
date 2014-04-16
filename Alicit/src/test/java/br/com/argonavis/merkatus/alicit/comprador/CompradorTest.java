/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.comprador;

import br.com.argonavis.merkatus.alicit.PersistenceUtilities;
import br.com.argonavis.merkatus.alicit.edital.DispensaLicitacao;
import br.com.argonavis.merkatus.alicit.edital.Edital;
import br.com.argonavis.merkatus.alicit.edital.PregaoEletronico;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author helderdarocha
 */
public class CompradorTest {
    
    Comprador comprador = new BancoBrasil();
    
    @Before
    public void init() {
        PersistenceUtilities.persist(comprador);
    }
    
    @After
    public void destroy() {
        PersistenceUtilities.remove(comprador);
    }

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
    
   // testes de relacionamento - integracao
    
   @Test
    public void testSetEditais() {
        setupEditais();
    }

    private void setupEditais() {
        Set<Edital> editais = new HashSet<>();
        editais.add(new PregaoEletronico(comprador, PregaoEletronico.Tipo.SRP));
        editais.add(new DispensaLicitacao(comprador, DispensaLicitacao.Tipo.COMPRA_DIRETA));
        comprador.setEditais(editais);
    }
    
    @Test
    public void testGetEditais() {
        setupEditais();
        assertEquals(2, comprador.getEditais().size());
    }
 
    @Test
    public void testAddEdital() throws ParseException {
        setupEditais();
        Edital edital = new PregaoEletronico(comprador, PregaoEletronico.Tipo.COMPRA_DIRETA);
        comprador.addEdital(edital);
        assertEquals(3, comprador.getEditais().size());
        assertEquals(comprador, comprador.getEditais().iterator().next().getComprador());
    }

    @Test
    public void testRemoveEdital() {
        setupEditais();
        comprador.removeEdital(comprador.getEditais().iterator().next());
        assertEquals(1, comprador.getEditais().size());
    }
    
}
