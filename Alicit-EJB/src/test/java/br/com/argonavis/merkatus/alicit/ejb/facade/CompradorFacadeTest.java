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
    
    Comprador comprador = new ConsumidorFinal();

    @Test
    public void testCreate() throws Exception {
        facade.create(comprador);
        facade.remove(comprador);
    }
/*
    @Test
    public void testEdit() throws Exception {
        comprador.setNomeCurto("Novo Nome Curto");
        Comprador c = facade.edit(comprador);
        String nome = c.getNomeCurto();
        assertEquals("Novo Nome Curto", nome);
    }

    @Test
    public void testRemove() throws Exception {
        int count = facade.count();
        facade.create(comprador);
        assertEquals(count + 1, facade.count());
        facade.remove(comprador);
        //assertEquals(count, facade.count());
    }

    @Test
    public void testFind() throws Exception {
        facade.create(comprador);
        comprador = facade.edit(comprador);
        System.out.println("Comprador ID: " + comprador.getId());
        Comprador c = facade.find(comprador.getId());
        assertEquals(comprador, c);
    }

    @Test
    public void testFindAll() throws Exception {
        int count = facade.count();
        facade.create(comprador);
        List<Comprador> all = facade.findAll();
        assertEquals(count + 1, all.size());
    }

    @Test
    public void testCount() throws Exception {
        facade.create(comprador);
        int elements = facade.count();
        assertTrue(elements > 0);
    }
    */
}
