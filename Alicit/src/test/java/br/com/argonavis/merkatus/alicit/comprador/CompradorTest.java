/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.comprador;

import br.com.argonavis.merkatus.alicit.edital.Codigo;
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
public class CompradorTest {
    
    Comprador comprador = new BancoBrasil();
    Edital e1 = new PregaoEletronico(comprador, PregaoEletronico.Tipo.SRP, new Codigo("123456"));
    Edital e2 = new DispensaLicitacao(comprador, DispensaLicitacao.Tipo.COMPRA_DIRETA, new Codigo("654321"));
    Edital e3 = new PregaoEletronico(comprador, PregaoEletronico.Tipo.COMPRA_DIRETA, new Codigo("999999"));

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
    public void testSetEditais() {
        setupEditais();
    }

    private void setupEditais() {
        Set<Edital> editais = new HashSet<>();
        editais.add(e1);
        editais.add(e2);
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
        comprador.addEdital(e3);
        assertEquals(3, comprador.getEditais().size());
        assertEquals(comprador, comprador.getEditais().iterator().next().getComprador());
    }

    @Test
    public void testRemoveEdital() {
        setupEditais();
        comprador.removeEdital(comprador.getEditais().iterator().next());
        assertEquals(1, comprador.getEditais().size());
    }
    
    @Test
    public void testEquals() {
        Comprador comprador1 = new BancoBrasil();
        assertEquals(comprador, comprador1);
    }
    
    @Test
    public void testHashCode() throws ParseException {
        Comprador comprador1 = new BancoBrasil();
        assertEquals(comprador.hashCode(), comprador1.hashCode());
    }
    
}
