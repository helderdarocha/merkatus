/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.alicit.web;

import br.com.argonavis.merkatus.alicit.ejb.facade.remote.CategoriaFacadeRemote;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ProdutoFacadeRemote;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.TagFacadeRemote;
import br.com.argonavis.merkatus.alicit.produto.Categoria;
import br.com.argonavis.merkatus.alicit.produto.Produto;
import br.com.argonavis.merkatus.alicit.produto.Tag;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CurrentProdutoManagedBean implements Serializable {

    @EJB
    ProdutoFacadeRemote produtoFacade;
    @EJB
    TagFacadeRemote tagFacade;
    @EJB
    CategoriaFacadeRemote categoriaFacade;

    private Produto currentProduto;

    private Long produtoId;
    private String produtoNome;
    private Long produtoCategoriaId;
    private String produtoCodigo;
    private String produtoNomesTag;

    public Produto getCurrentProduto() {
        return currentProduto;
    }

    public void setCurrentProduto(Produto currentProduto) {
        this.currentProduto = currentProduto;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public Long getProdutoCategoriaId() {
        return produtoCategoriaId;
    }

    public void setProdutoCategoriaId(Long produtoCategoriaId) {
        this.produtoCategoriaId = produtoCategoriaId;
    }

    public String getProdutoCodigo() {
        return produtoCodigo;
    }

    public void setProdutoCodigo(String produtoCodigo) {
        this.produtoCodigo = produtoCodigo;
    }

    public String getProdutoNomesTag() {
        return produtoNomesTag;
    }

    public void setProdutoNomesTag(String produtoNomesTag) {
        this.produtoNomesTag = produtoNomesTag;
    }

    public void unsetCurrentProduto() {
        this.currentProduto = null;
    }

    public boolean setCurrentProduto() {
        if (currentProduto != null) {
            return true;
        } else {
            try {
                Produto produto = produtoFacade.find(produtoId);

                try {
                    System.out.println(produto.getTags().size()); // test for lazy initialization
                } catch (Exception ex) {
                    Logger.getLogger(CurrentProdutoManagedBean.class.getName()).log(Level.SEVERE, "Framework error: must pre-fetch Tags collection in select Produto", ex);
                }

                this.setCurrentProduto(produto);
                return produto != null;
            } catch (NoResultException e) {
                return false;
            }
        }
    }

    public String criar() {
        if (this.produtoCodigo != null && this.produtoNome != null) {
            produtoFacade.create(new Produto(this.produtoCodigo, this.produtoNome));
            this.setCurrentProduto(produtoFacade.getByCodigo(produtoCodigo));
        }

        if (produtoNomesTag != null && !produtoNomesTag.isEmpty()) {
            String[] tagNamesArray = produtoNomesTag.split(","); // what happens if null or only one tag?
            for (String tagName : tagNamesArray) {
                String name = tagName.trim();
                Tag t = null;
                try {
                    t = tagFacade.getByNome(name);
                    if (t == null) {
                        tagFacade.create(new Tag(name));
                        t = tagFacade.getByNome(name);
                    }
                } catch (Exception e) {
                    Logger.getLogger(CurrentProdutoManagedBean.class.getName()).log(Level.SEVERE, "Tag inclusion failed during Produto creation", e);
                }
                currentProduto.getTags().add(t);
            }
        }
        Categoria cat = categoriaFacade.find(produtoCategoriaId);
        currentProduto.setCategoria(cat);
        
        produtoFacade.edit(currentProduto);
        
        this.unsetCurrentProduto();
        return "produtos";
    }
    
    public String atualizar() {
        if (this.setCurrentProduto()) {
            produtoFacade.edit(currentProduto);
            this.unsetCurrentProduto();
        }
        return "produtos";
    }

    public String remover() {
        if (this.setCurrentProduto()) {
            currentProduto.getTags().clear();
            produtoFacade.remove(currentProduto);
            this.unsetCurrentProduto();
        }
        return "produtos";
    }
}
