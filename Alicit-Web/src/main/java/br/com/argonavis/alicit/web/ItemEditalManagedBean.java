/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.alicit.web;

import br.com.argonavis.merkatus.alicit.edital.componente.ItemAmostra;
import br.com.argonavis.merkatus.alicit.edital.componente.ItemEdital;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ItemEditalFacadeRemote;
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
public class ItemEditalManagedBean implements Serializable {
    @EJB ItemEditalFacadeRemote itemFacade;
    
    private boolean showCadastrarItemPanel = false;
    private String descricaoItem;
    private String codigoItem;
    
    private List<ItemEdital> itens;
    Map<String, String> itensMap;
    
    public List<ItemEdital> getItens() {
        if (itens == null) {
            itens = itemFacade.findAll();
        }
        return itens;
    }
    
    public Map<String, String> getItensMap() {
        if (itensMap == null) {
            itensMap = new HashMap<>();
            for(ItemEdital c: getItens()) {
                itensMap.put(c.getCodigo(), c.getDescricao());
            }
        }
        return itensMap;
    }
    
        public void resetSession() {
        this.showCadastrarItemPanel = false;
    }

    public boolean isShowCadastrarItemPanel() {
        return showCadastrarItemPanel;
    }

    public void setShowCadastrarItemPanel(boolean showCadastrarItemPanel) {
        this.showCadastrarItemPanel = showCadastrarItemPanel;
    }

    public String getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }
    
    public String showCadastrarItemPanel() {
        this.showCadastrarItemPanel = true;
        return null;
    }
    
    public String hideCadastrarItemPanel() {
        resetSession();
        return null;
    }
    
    public String criar() {
        ItemEdital item = new ItemAmostra(this.codigoItem, this.descricaoItem);
        itemFacade.create(item);
        resetSession();
        return "itens";
    }
}
