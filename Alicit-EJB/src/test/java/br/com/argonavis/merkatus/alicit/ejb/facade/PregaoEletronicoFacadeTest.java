/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import br.com.argonavis.merkatus.alicit.comprador.ComprasNet;
import br.com.argonavis.merkatus.alicit.edital.Codigo;
import br.com.argonavis.merkatus.alicit.edital.Edital;
import br.com.argonavis.merkatus.alicit.edital.PregaoEletronico;
import br.com.argonavis.merkatus.alicit.ejb.LookupService;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.PregaoEletronicoFacadeRemote;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author helderdarocha
 */
public class PregaoEletronicoFacadeTest {
    LookupService<PregaoEletronicoFacadeRemote> service = new LookupService<>();
    PregaoEletronicoFacadeRemote facade = service.lookupBean(PregaoEletronicoFacade.class, PregaoEletronicoFacadeRemote.class);
    
    PregaoEletronico edital;
    
    @Before
    public void setUp() {
        edital = new PregaoEletronico(new ComprasNet(), PregaoEletronico.Tipo.COMPRA_DIRETA, new Codigo("000002"));
        edital.setNomeDisplay("TEST");
    }
    
    @After
    public void tearDown() {
        // remove all
    }

    @Test
    public void testCreate() throws Exception {
        facade.create(edital);
        // test
    }

    @Test
    public void testEdit() throws Exception {
        edital.setNomeDisplay("TEST-TEST");
        Edital c = facade.edit(edital);
        String nome = c.getNomeDisplay();
        assertEquals("TEST-TEST", nome);
    }

    @Test
    public void testRemove() throws Exception {
        int count = facade.count();
        facade.create(edital);
        assertEquals(count + 1, facade.count());
        facade.remove(edital);
        //assertEquals(count, facade.count());
    }

    @Test
    public void testFind() throws Exception {
        facade.create(edital);
        edital = facade.edit(edital);
        System.out.println(edital.getId());
        // test
        Edital c = facade.find(edital.getId());
        assertEquals(edital, c);
    }

    @Test
    public void testFindAll() throws Exception {
        int count = facade.count();
        facade.create(edital);
        List<PregaoEletronico> all = facade.findAll();
        assertEquals(count + 1, all.size());
    }

    @Test
    public void testCount() throws Exception {
        facade.create(edital);
        int elements = facade.count();
        assertTrue(elements > 0);
    }
}
