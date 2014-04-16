/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit;

import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import br.com.argonavis.merkatus.alicit.edital.Edital;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author helderdarocha
 */
public class SistemaTest {
    
    Sistema sistema = new Sistema();
    
    @Before
    public void init() {
        persist(sistema);
    }
    
    @After
    public void destroy() {
        remove(sistema);
    }
    
    // integration tests
    
    @Test
    public void testGetCompradores() {
        Set<Comprador> compradores = sistema.getCompradores();
    }

    @Test
    public void testGetEditais() {
        Set<Edital> editais = sistema.getEditais();
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Alicit-1.0.0-PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public void merge(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Alicit-1.0.0-PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public void remove(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Alicit-1.0.0-PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
