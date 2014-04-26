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
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author helderdarocha
 */
@Named
@RequestScoped
public class CategoriaManagedBean implements Serializable {

    @EJB
    CategoriaFacadeRemote categoriaFacade;
    @Inject
    CurrentCategoriaManagedBean currentCategoriaManagedBean;

    Map<String, Long> categoriasMap;
    private Long categoriaId;

    public List<Categoria> getCategorias() {
        return categoriaFacade.findAll(); // lazy!
    }

    public Map<String, Long> getCategoriasMap() {
        if (categoriasMap == null) {
            categoriasMap = new HashMap<>();
        }
        categoriasMap.clear();
        for (Categoria c : getCategorias()) {
            categoriasMap.put(c.getNomeAbsoluto(), c.getId());
        }
        return categoriasMap;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String exibirCategoria() {
        currentCategoriaManagedBean.setCategoriaId(categoriaId);
        currentCategoriaManagedBean.setCurrentCategoria();
        return "categoriasExibir";
    }

    public String cadastrarNovaCategoria() {
        currentCategoriaManagedBean.setCategoriaNome(null);
        currentCategoriaManagedBean.setIdCategoriaContexto(null);
        currentCategoriaManagedBean.unsetCurrentCategoria();
        return "categoriasCriar";
    }

    public String editarCategoria() {
        currentCategoriaManagedBean.setCategoriaId(categoriaId);
        currentCategoriaManagedBean.setCurrentCategoria();
        return "categoriasEditar";
    }
}
