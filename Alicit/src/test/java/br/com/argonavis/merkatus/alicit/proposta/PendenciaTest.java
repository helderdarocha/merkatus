/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.proposta;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author helderdarocha
 */
public class PendenciaTest {
    
    @Before
    public void setUp() {
        Pendencia p1 = new Pendencia();
        p1.setDescricao("Pendencia x");
        p1.addItem(Produto.class, new Produto());
        p1.getDescricao();
        p1.marcarConcluida();
        p1.marcarUrgente();
        p1.marcarOpcional();
        p1.isConcluida();
        p1.isUrgente();
        p1.isOpcional();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetId() {
    }

    @Test
    public void testSetId() {
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void testToString() {
    }
    
}
