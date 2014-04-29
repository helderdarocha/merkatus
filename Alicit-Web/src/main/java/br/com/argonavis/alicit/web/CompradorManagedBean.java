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

    @EJB
    CompradorFacadeRemote compradorFacade;

    Map<String, Long> compradoresMap;

    public List<Comprador> getCompradores() {
        return compradorFacade.findAll();
    }

    public Map<String, Long> getCompradoresMap() {
        if (compradoresMap == null) {
            compradoresMap = new HashMap<>();
        }
        compradoresMap.clear();
        for (Comprador c : getCompradores()) {
            compradoresMap.put(c.getIdentificador(), c.getId());
        }

        return compradoresMap;
    }
}
