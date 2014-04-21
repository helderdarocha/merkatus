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
public class ResultadoLicitacaoTest {
   
    
    @Before
    public void setUp() {
        ResultadoLicitacao res = new resultadoLicitacao();
        res.setPosicao(1);
        res.setResultadoPositivo();
        res.setResultadoNegativo();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
    }
    
}
