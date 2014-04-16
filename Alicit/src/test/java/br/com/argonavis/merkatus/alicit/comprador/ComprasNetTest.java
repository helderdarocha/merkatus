/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.comprador;

import br.com.argonavis.merkatus.alicit.PersistenceUtilities;
import br.com.argonavis.merkatus.alicit.edital.CNPJ;
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
public class ComprasNetTest {
    
    Comprador comprador = new ComprasNet();

    @Test
    public void testGetIdCodigoComprador() {
        assertEquals("UASG", comprador.getIdCodigoComprador());
    }

    @Test
    public void testGetMascara() {
        assertEquals("\\d{6}", comprador.getMascara(null));
    }

    @Test
    public void testValidarCodigoComprador() {
        assertTrue(comprador.validarCodigoComprador("123456", null));
    }

    @Test
    public void testGetNomeCurto() {
        assertEquals("Portal ComprasNET", comprador.getNomeCurto());
    }

    @Test
    public void testGetNomeLongo() {
        assertEquals("Portal de Compras do Governo Federal", comprador.getNomeLongo());
    }

    @Test
    public void testGetCodigo() {
        assertEquals("ComprasNET", comprador.getCodigo());
    }

    @Test
    public void testGetWebsite() {
        assertEquals("www.comprasnet.gov.br", comprador.getWebsite());
    }
    

}
