/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.merkatus.alicit;

import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import br.com.argonavis.merkatus.alicit.edital.CNPJ;
import br.com.argonavis.merkatus.alicit.edital.Codigo;
import br.com.argonavis.merkatus.alicit.edital.Edital;
import br.com.argonavis.merkatus.alicit.edital.PregaoEletronico;
import java.text.ParseException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
        Comprador comprador = null;
        try {
           List compradores = PersistenceUtilities.findAll(Comprador.class);
            assertNotNull(compradores);
            int size = compradores.size();

            comprador = Comprador.createCompradorBB();
            compradores.add(comprador);
            PersistenceUtilities.persist(comprador);
            
            compradores = PersistenceUtilities.findAll(Comprador.class);
            assertEquals(size + 1, compradores.size());
            
            em.remove(comprador); 
            assertEquals(size, compradores.size());
            
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        } 
    }

    @Test
    public void testGetEditais() throws ParseException {
        EntityManager em = PersistenceUtilities.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Comprador comprador = null;
        Edital edital = null;
        try {

            List editais = PersistenceUtilities.findAll(Edital.class);
            assertNotNull(editais);
            int size = editais.size();

            comprador = Comprador.createCompradorComprasNet();
            Codigo numero = new Codigo("123457");
            edital = new PregaoEletronico(comprador, PregaoEletronico.Tipo.COMPRA_DIRETA, numero);  
            //PersistenceUtilities.persist(comprador);

            edital.setIdentificacao("Pregao no. 1", new Codigo("123456"), new Codigo("45343"), new CNPJ("04.239.747/0001-58"));
            PersistenceUtilities.persist(edital); // will create new comprador by cascade persist

            editais = PersistenceUtilities.findAll(Edital.class);
            assertEquals(size + 1, editais.size());
            assertEquals("04239747000158", ((Edital)editais.iterator().next()).getCnpjComprador().toNormalizedString());
            assertEquals(comprador, ((Edital)editais.iterator().next()).getComprador());
            
            em.remove(edital);
            assertEquals(size, editais.size());
            em.remove(comprador);
            
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

    }

}
