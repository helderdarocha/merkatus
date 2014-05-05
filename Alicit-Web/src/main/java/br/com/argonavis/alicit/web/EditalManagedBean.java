/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.alicit.web;

import br.com.argonavis.merkatus.alicit.edital.DispensaLicitacao;
import br.com.argonavis.merkatus.alicit.edital.Edital;
import br.com.argonavis.merkatus.alicit.edital.PregaoEletronico;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.DispensaLicitacaoFacadeRemote;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.EditalFacadeRemote;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.PregaoEletronicoFacadeRemote;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author helderdarocha
 */
@Named
@RequestScoped
public class EditalManagedBean implements Serializable {

    @EJB
    EditalFacadeRemote editalFacade;
    @EJB
    PregaoEletronicoFacadeRemote peFacade;
    @EJB
    DispensaLicitacaoFacadeRemote dlFacade;
    
    @Inject
    CurrentEditalManagedBean currentEditalManagedBean;

    private String idComprador;

    Map<String, Long> editaisMap;
    private Long editalId;

    public List<Edital> getEditais() {
        return editalFacade.findAll(); // lazy!
    }

    public Map<String, Long> getEditaisMap() {
        if (editaisMap == null) {
            editaisMap = new HashMap<>();
        }
        editaisMap.clear();
        for (Edital c : getEditais()) {
            editaisMap.put(c.getNomeDisplay(), c.getId());
        }
        return editaisMap;
    }

    public Long getEditalId() {
        return editalId;
    }

    public void setEditalId(Long editalId) {
        this.editalId = editalId;
    }

    public List<PregaoEletronico> getPregoesEletronicos() {
        return peFacade.findAll();
    }

    public List<DispensaLicitacao> getDispensasLicitacao() {
        return dlFacade.findAll();
    }

    public List<Edital> getEditaisbyComprador() {
        return editalFacade.queryList("select e from Edital e where e.comprador.identificador = '" + idComprador + "'");
    }

    public String exibirEdital() {
        currentEditalManagedBean.setEditalId(editalId);
        currentEditalManagedBean.setCurrentEdital();
        return "editaisExibir";
    }

    public String cadastrarNovoEdital() {
        currentEditalManagedBean.setEditalNumero(null);
        currentEditalManagedBean.unsetCurrentEdital();
        return "editaisCriar";
    }

    public String editarEdital() {
        currentEditalManagedBean.setEditalId(editalId);
        currentEditalManagedBean.setCurrentEdital();
        currentEditalManagedBean.setMostrarListaProdutos(false);
        return "editaisEditar";
    }
    

}
