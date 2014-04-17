/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import br.com.argonavis.merkatus.alicit.comprador.ConsumidorFinal;
import br.com.argonavis.merkatus.alicit.ejb.LookupService;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.CompradorFacadeRemote;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author helderdarocha
 */
public class CompradorFacadeTest {
    LookupService<CompradorFacadeRemote> service = new LookupService<>();
    CompradorFacadeRemote facade = service.lookupBean(CompradorFacade.class, CompradorFacadeRemote.class);
 
    @Test
    public void testCreate() throws Exception {
        ConsumidorFinal comprador = new ConsumidorFinal("testCreate");
        facade.create(comprador);
        facade.remove(comprador);
    }

    @Test
    public void testEdit() throws Exception {
        ConsumidorFinal comprador = new ConsumidorFinal("testEdit");
        comprador.setNomeCurto("Novo Nome Curto");
        Comprador c = facade.edit(comprador);
        String nome = c.getNomeCurto();
        assertEquals("Novo Nome Curto", nome);
    }

    @Test
    public void testRemove() throws Exception {
        ConsumidorFinal comprador = new ConsumidorFinal("testRemove");
        int count = facade.count();
        facade.create(comprador);
        assertEquals(count + 1, facade.count());
        facade.remove(comprador);
        assertEquals(count, facade.count());
    }

    @Test
    public void testFind() throws Exception {
        ConsumidorFinal comprador = new ConsumidorFinal("testFind");
        facade.create(comprador);
        comprador = (ConsumidorFinal)facade.edit(comprador);
        System.out.println("Comprador ID: " + comprador.getId());
        Comprador c = facade.find(comprador.getId());
        assertEquals(comprador, c);
    }

    @Test
    public void testFindAll() throws Exception {
        int count = facade.count();
        List<Comprador> all = facade.findAll();
        assertEquals(count, all.size());
    }

    @Test
    public void testCount() throws Exception {
        Comprador comprador = new ConsumidorFinal("testCount");
        facade.create(comprador);
        int elements = facade.count();
        assertTrue(elements > 0);
    }
    
}
