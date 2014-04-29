/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.alicit.web;

import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ProdutoFacadeRemote;
import br.com.argonavis.merkatus.alicit.produto.Produto;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author helderdarocha
 */
@Named
@SessionScoped
public class ProdutoManagedBean implements Serializable {

    @EJB
    ProdutoFacadeRemote produtoFacade;
    @Inject
    CurrentProdutoManagedBean  currentProdutoManagedBean;

    private List<Produto> produtos;
    Map<String, String> produtosMap;

    private Long produtoId;

    public List<Produto> getProdutos() {
        return produtoFacade.findAll();
    }
    public Map<String, String> getProdutosMap() {
        if (produtosMap == null) {
            produtosMap = new HashMap<>();
        }
        produtosMap.clear();
        for (Produto c : getProdutos()) {
            produtosMap.put(c.getCodigo(), c.getNome());
        }
        return produtosMap;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }
    
    public String exibirProduto() {
        currentProdutoManagedBean.setProdutoId(produtoId);
        currentProdutoManagedBean.setCurrentProduto();
        return "produtosExibir";
    }

    public String cadastrarNovoProduto() {
        currentProdutoManagedBean.setProdutoCodigo(null);
        currentProdutoManagedBean.setProdutoNome(null);
        currentProdutoManagedBean.unsetCurrentProduto();
        return "produtosCriar";
    }

    public String editarProduto() {
        currentProdutoManagedBean.setProdutoId(produtoId);
        currentProdutoManagedBean.setCurrentProduto();
        return "produtosEditar";
    }

}
