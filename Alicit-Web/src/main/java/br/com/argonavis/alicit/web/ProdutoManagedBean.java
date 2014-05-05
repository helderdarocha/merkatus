/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.alicit.web;

import br.com.argonavis.merkatus.alicit.ejb.facade.remote.CategoriaFacadeRemote;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ProdutoFacadeRemote;
import br.com.argonavis.merkatus.alicit.produto.Categoria;
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
    @EJB
    CategoriaFacadeRemote categoriaFacade;

    Map<String, String> produtosMap;

    private Long produtoId;
    private Long categoriaId;

    public List<Produto> getProdutos() {
        if (categoriaId != null) {
            Categoria categoria = categoriaFacade.find(categoriaId);
            return produtoFacade.findByCategoria(categoria);
        }
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

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }
    
    public String exibirProduto() {
        currentProdutoManagedBean.unsetCurrentProduto();
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
        currentProdutoManagedBean.unsetCurrentProduto();
        currentProdutoManagedBean.setProdutoId(produtoId);
        currentProdutoManagedBean.setCurrentProduto();
        return "produtosEditar";
    }

}
