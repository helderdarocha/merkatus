/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.proposta;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author helderdarocha
 */
public class PendenciaTest {
    
    @Before
    public void setUp() {
        Pendencia p1 = new Pendencia();
        p1.setDescricao("Pendencia x");
        //p1.addItem(new Produto());
        //p1.addProduto(new Produto());
        p1.getDescricao();
        p1.setConcluida(true);
        p1.setUrgente(true);
        p1.setOpcional(true);
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
