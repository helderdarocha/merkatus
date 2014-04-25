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
public class ProdutoManagedBean implements Serializable {

    @EJB
    ProdutoFacadeRemote produtoFacade;
    @EJB
    TagFacadeRemote tagFacade;
    @EJB
    CategoriaFacadeRemote categoriaFacade;

    private List<Produto> produtos;
    Map<String, String> produtosMap;

    private boolean showCadastrarProdutoPanel;

    private String descricaoProduto;
    private String codigoProduto;
    private Long idProduto;
    private String nomeProduto;
    private Long idCategoria;
    private String tagNames;

    public List<Produto> getProdutos() {
        if (produtos == null) {
            produtos = produtoFacade.findAll();
        }
        return produtos;
    }

    public Map<String, String> getProdutosMap() {
        if (produtosMap == null) {
            produtosMap = new HashMap<>();
            for (Produto c : getProdutos()) {
                produtosMap.put(c.getCodigo(), c.getNome());
            }
        }
        return produtosMap;
    }

    public void resetSession() {
        this.showCadastrarProdutoPanel = false;
    }

    public boolean isShowCadastrarProdutoPanel() {
        return showCadastrarProdutoPanel;
    }

    public void setShowCadastrarProdutoPanel(boolean showCadastrarProdutoPanel) {
        this.showCadastrarProdutoPanel = showCadastrarProdutoPanel;
    }

    public String showCadastrarProdutoPanel() {
        this.showCadastrarProdutoPanel = true;
        return null;
    }

    public String hideCadastrarProdutoPanel() {
        resetSession();
        return null;
    }

    public String criar() {
        Produto produto = new Produto(this.codigoProduto, this.nomeProduto);
        produtoFacade.create(produto);
        produto = produtoFacade.getByCodigo(codigoProduto);
        
        if (tagNames != null && !tagNames.isEmpty()) {
            String[] tagNamesArray = tagNames.split(",");
            for (String tagName : tagNamesArray) {
                String name = tagName.trim();
                Tag t = null;
                try {
                    t = tagFacade.getByNome(name);

                    System.out.println("Tag: " + t);

                    if (t == null) {
                        tagFacade.create(new Tag(name));
                        t = tagFacade.getByNome(name);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                produto.getTags().add(t);
            }
        }
        Categoria cat = categoriaFacade.find(idCategoria);
        //Categoria cat = categoriaFacade.find(952L);
        produto.setCategoria(cat);

        resetSession();
        return "produtos";
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getTagNames() {
        return tagNames;
    }

    public void setTagNames(String tagNames) {
        this.tagNames = tagNames;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

}
