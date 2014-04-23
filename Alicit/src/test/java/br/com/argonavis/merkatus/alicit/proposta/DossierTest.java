/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.proposta;

import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import br.com.argonavis.merkatus.alicit.edital.Codigo;
import br.com.argonavis.merkatus.alicit.edital.Edital;
import br.com.argonavis.merkatus.alicit.edital.PregaoEletronico;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author helderdarocha
 */
public class DossierTest {

    @Before
    public void setUp() {
        Dossier dossier = new Dossier();
        Edital e = new PregaoEletronico(Comprador.createCompradorComprasNet(), PregaoEletronico.Tipo.SRP, new Codigo("12345"));
        dossier.setEdital(e);
        dossier.gerarCheckList();
        List<Pendencia> pendencias = dossier.getPendencias();
        dossier.setStatus(StatusDossier.NOVO);
        Pendencia p1 = new Pendencia();
        dossier.addPendencia(p1);
        dossier.setResultado(new ResultadoLicitacao());
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
