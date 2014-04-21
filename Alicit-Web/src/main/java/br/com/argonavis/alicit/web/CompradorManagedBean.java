/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.alicit.web;

import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.CompradorFacadeRemote;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author helderdarocha
 */
@Named
@SessionScoped
public class CompradorManagedBean implements Serializable {
    @EJB CompradorFacadeRemote compradorFacade;
    
    private List<Comprador> compradores;
    Map<String, String> compradoresMap;
    
    public List<Comprador> getCompradores() {
        if (compradores == null) {
            compradores = compradorFacade.findAll();
        }
        return compradores;
    }
    
    public Map<String, String> getCompradoresMap() {
        if (compradoresMap == null) {
            compradoresMap = new HashMap<>();
            for(Comprador c: getCompradores()) {
                compradoresMap.put(c.getIdentificador(), c.getIdentificador());
            }
        }
        return compradoresMap;
    }
}
