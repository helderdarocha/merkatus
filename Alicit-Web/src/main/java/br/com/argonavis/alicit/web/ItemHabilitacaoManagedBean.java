/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.alicit.web;

import br.com.argonavis.merkatus.alicit.edital.componente.ItemHabilitacao;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ItemHabilitacaoFacadeRemote;
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
public class ItemHabilitacaoManagedBean implements Serializable {

    @EJB
    ItemHabilitacaoFacadeRemote itemHabilitacaoFacade;
    @Inject
    CurrentItemHabilitacaoManagedBean currentItemHabilitacaoManagedBean;

    Map<String, String> itemHabilitacaoMap;

    private String currentItemHabilitacaoCodigo;

    public List<ItemHabilitacao> getItensHabilitacao() {
        return itemHabilitacaoFacade.findAll();
    }

    public Map<String, String> getItemHabilitacaoMap() {
        if (itemHabilitacaoMap == null) {
            itemHabilitacaoMap = new HashMap<>();
        }
        itemHabilitacaoMap.clear();
        for (ItemHabilitacao c : getItensHabilitacao()) {
            itemHabilitacaoMap.put(c.getCodigo(), c.getCodigo());
        }
        return itemHabilitacaoMap;
    }

    public String getCurrentItemHabilitacaoCodigo() {
        return currentItemHabilitacaoCodigo;
    }

    public void setCurrentItemHabilitacaoCodigo(String currentItemHabilitacaoCodigo) {
        this.currentItemHabilitacaoCodigo = currentItemHabilitacaoCodigo;
    }

    public String exibirItemHabilitacao() {
        currentItemHabilitacaoManagedBean.setItemHabilitacaoCodigo(currentItemHabilitacaoCodigo);
        currentItemHabilitacaoManagedBean.setCurrentItemHabilitacao();
        return "itensHabilitacaoExibir";
    }

    public String cadastrarNovoItemHabilitacao() {
        currentItemHabilitacaoManagedBean.setItemHabilitacaoCodigo(null);
        currentItemHabilitacaoManagedBean.unsetCurrentItemHabilitacao();
        return "itensHabilitacaoCriar";
    }

    public String editarItemHabilitacao() {
        currentItemHabilitacaoManagedBean.setItemHabilitacaoCodigo(currentItemHabilitacaoCodigo);
        currentItemHabilitacaoManagedBean.setCurrentItemHabilitacao();
        return "itensHabilitacaoEditar";
    }

}
