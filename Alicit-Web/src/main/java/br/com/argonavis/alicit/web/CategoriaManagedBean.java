/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.alicit.web;

import br.com.argonavis.merkatus.alicit.ejb.facade.remote.CategoriaFacadeRemote;
import br.com.argonavis.merkatus.alicit.produto.Categoria;
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
public class CategoriaManagedBean implements Serializable {
    @EJB CategoriaFacadeRemote categoriaFacade;
    
    private List<Categoria> categorias;
    Map<String, Long> categoriasMap;
    
    private boolean showCadastrarCategoriaPanel = false;
    private String nomeCategoria;
    private Long idParent;
    
    public List<Categoria> getCategorias() {
        if (categorias == null) {
            categorias = categoriaFacade.findAll();
        }
        return categorias;
    }
    
    public Map<String, Long> getCategoriasMap() {
        if (categoriasMap == null) {
            categoriasMap = new HashMap<>();
            for(Categoria c: getCategorias()) {
                categoriasMap.put(c.toString(), c.getId());
            }
        }
        return categoriasMap;
    }
    
    public void resetSession() {
        nomeCategoria = null;
        idParent = null;
        this.showCadastrarCategoriaPanel = false;
    }

    public boolean isShowCadastrarCategoriaPanel() {
        return showCadastrarCategoriaPanel;
    }

    public void setShowCadastrarCategoriaPanel(boolean showCadastrarCategoriaPanel) {
        this.showCadastrarCategoriaPanel = showCadastrarCategoriaPanel;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public Long getIdParent() {
        return idParent;
    }

    public void setIdParent(Long idParent) {
        this.idParent = idParent;
    }
    
    public String showCadastrarCategoriaPanel() {
        this.showCadastrarCategoriaPanel = true;
        return null;
    }
    
    public String hideCadastrarCategoriaPanel() {
        resetSession();
        return null;
    }
    
    public String criar() {
        Categoria categoria = null;
        if (this.idParent == null) {
            categoria = new Categoria(this.nomeCategoria);
        } else {
            Categoria pai = categoriaFacade.find(this.idParent);
            categoria = new Categoria(this.nomeCategoria, pai);
        }
        categoriaFacade.create(categoria);
        resetSession();
        return "categorias";
    }
}
