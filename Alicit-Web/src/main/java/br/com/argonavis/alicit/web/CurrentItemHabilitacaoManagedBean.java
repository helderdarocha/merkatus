/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.alicit.web;

import br.com.argonavis.merkatus.alicit.edital.componente.ItemHabilitacao;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ItemHabilitacaoFacadeRemote;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;

/**
 *
 * @author helderdarocha
 */
@Named
@RequestScoped
public class CurrentItemHabilitacaoManagedBean implements Serializable {
    @EJB ItemHabilitacaoFacadeRemote itemHabilitacaoFacade;
    
    private ItemHabilitacao currentItemHabilitacao;
    private String itemHabilitacaoCodigo;
    private String itemHabilitacaoDescricao;
 
    public ItemHabilitacao getCurrentItemHabilitacao() {
        return currentItemHabilitacao;
    }

    public void setItemHabilitacaoCodigo(String itemHabilitacaoCodigo) {
        this.itemHabilitacaoCodigo = itemHabilitacaoCodigo;
    }
    
    public String getItemHabilitacaoCodigo() {
        return itemHabilitacaoCodigo;
    }

    public String getItemHabilitacaoDescricao() {
        return itemHabilitacaoDescricao;
    }

    public void setItemHabilitacaoDescricao(String itemHabilitacaoDescricao) {
        this.itemHabilitacaoDescricao = itemHabilitacaoDescricao;
    }
    
    public void setCurrentItemHabilitacao(ItemHabilitacao currentItemHabilitacao) {
        this.currentItemHabilitacao = currentItemHabilitacao;
    }
    
    public void unsetCurrentItemHabilitacao() {
        this.currentItemHabilitacao = null;
    }
    
    public boolean setCurrentItemHabilitacao() {
        if (currentItemHabilitacao != null) {
            return true;
        } else if (this.itemHabilitacaoCodigo != null) {
            try {
                ItemHabilitacao itemHabilitacao = itemHabilitacaoFacade.getByCodigo(this.itemHabilitacaoCodigo);
                this.setCurrentItemHabilitacao(itemHabilitacao);
                return true;
            } catch (NoResultException e) {
                return false;
            }
        }
        return false;
    }

    public String criar() {
        if (this.itemHabilitacaoCodigo != null) {
            ItemHabilitacao itemHabilitacao = new ItemHabilitacao(this.itemHabilitacaoCodigo, this.itemHabilitacaoDescricao);
            itemHabilitacaoFacade.create(itemHabilitacao);
        }
        return "itensHabilitacao";
    }
    
    public String remover() {
        if (this.setCurrentItemHabilitacao()) {
            itemHabilitacaoFacade.remove(currentItemHabilitacao);
            this.unsetCurrentItemHabilitacao();
        }
        return "itensHabilitacao";
    }
}
