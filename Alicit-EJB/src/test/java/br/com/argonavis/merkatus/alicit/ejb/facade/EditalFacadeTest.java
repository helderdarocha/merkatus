/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import br.com.argonavis.merkatus.alicit.edital.Codigo;
import br.com.argonavis.merkatus.alicit.edital.Edital;
import br.com.argonavis.merkatus.alicit.edital.PregaoEletronico;
import br.com.argonavis.merkatus.alicit.ejb.LookupService;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.CompradorFacadeRemote;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.EditalFacadeRemote;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author helderdarocha
 */
public class EditalFacadeTest {
    LookupService<EditalFacadeRemote> editalService = new LookupService<>();
    LookupService<CompradorFacadeRemote> compradorService = new LookupService<>();
    EditalFacadeRemote editalFacade = editalService.lookupBean(EditalFacade.class, EditalFacadeRemote.class);
    CompradorFacadeRemote compradorFacade = compradorService.lookupBean(CompradorFacade.class, CompradorFacadeRemote.class);
    
    @Before
    public void testCreate() throws Exception {
        Comprador comprador = Comprador.createCompradorComprasNet();
        compradorFacade.create(comprador);
        Comprador found = compradorFacade.querySingle("select c from Comprador c where c.identificador = 'COMPRASNET'");
        
        int count = editalFacade.count();
        Edital edital = new PregaoEletronico(found, PregaoEletronico.Tipo.COMPRA_DIRETA, new Codigo("000001"));
        editalFacade.create(edital);
        assertEquals(count + 1, editalFacade.count());
    }
    
    @After
    public void testRemove() throws Exception {
        int count = editalFacade.count();
        Edital found = editalFacade.querySingle("select e from Edital e where e.numeroEdital.codigo = '000001'");
        
        Comprador comprador = found.getComprador();
        
        editalFacade.remove(found);
        compradorFacade.remove(comprador);
        assertEquals(count - 1, editalFacade.count());
    }
    
    @Test
    public void testMerge() throws Exception {
        Edital found = editalFacade.querySingle("select e from Edital e where e.numeroEdital.codigo = '000001'");
        Edital merged = editalFacade.edit(found);
        assertNotNull(merged);
    }

    @Test
    public void testFind() throws Exception {
        Edital foundQuery = editalFacade.querySingle("select e from Edital e where e.numeroEdital.codigo = '000001'");
        System.out.println("Comprador ID: " + foundQuery.getId());
        Edital foundGet = editalFacade.find(foundQuery.getId());
        assertEquals(foundQuery, foundGet);
    }
    
    @Test
    public void testFindAll() throws Exception {
        int count = editalFacade.count();
        List<Edital> all = editalFacade.findAll();
        assertEquals(count, all.size());
    }

    @Test
    public void testCount() throws Exception {
        int elements = editalFacade.count();
        assertTrue(elements > 0);
    }
    
}
