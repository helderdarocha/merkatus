/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.merkatus.alicit;

import static br.com.argonavis.merkatus.alicit.PersistenceUtilities.emf;
import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import br.com.argonavis.merkatus.alicit.comprador.ComprasNet;
import br.com.argonavis.merkatus.alicit.edital.CNPJ;
import br.com.argonavis.merkatus.alicit.edital.Codigo;
import br.com.argonavis.merkatus.alicit.edital.Edital;
import br.com.argonavis.merkatus.alicit.edital.PregaoEletronico;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author helderdarocha
 */
public class SistemaTest {

    // integration tests
    @Test
    public void testGetCompradores() {
        EntityManager em = PersistenceUtilities.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Sistema sistema = null;
        Comprador comprador = null;
        try {
            sistema = new Sistema();
            em.persist(sistema);
            
            Set<Comprador> compradores = sistema.getCompradores();
            assertNotNull(compradores);
            assertEquals(0, compradores.size());

            comprador = new ComprasNet();
            compradores.add(comprador);
            compradores = sistema.getCompradores();
            assertEquals(1, compradores.size());
            assertEquals("ComprasNET", compradores.iterator().next().getCodigo());
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.remove(sistema);
            em.remove(comprador);
            em.close();
        }
    }

    @Test
    public void testGetEditais() throws ParseException {
        EntityManager em = PersistenceUtilities.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Sistema sistema = null;
        Comprador comprador = null;
        Edital edital = null;
        try {
            sistema = new Sistema();
            em.persist(sistema);

            Set<Edital> editais = sistema.getEditais();
            assertNotNull(editais);
            assertEquals(0, editais.size());

            edital = new PregaoEletronico(PregaoEletronico.Tipo.COMPRA_DIRETA);
            comprador = new ComprasNet();

            edital.setComprador(comprador);
            edital.setIdentificacao("Pregao no. 1", new Codigo("123456"), new Codigo("45343"), new CNPJ("04.239.747/0001-58"));
            sistema.addEdital(edital);

            editais = sistema.getEditais();
            assertEquals(1, editais.size());
            assertEquals("04239747000158", editais.iterator().next().getCnpjComprador().toNormalizedString());
            assertEquals(comprador, editais.iterator().next().getComprador());
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.remove(sistema);
            em.remove(edital);
            em.remove(comprador);
            em.close();
        }

    }

}
