/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.alicit.web;

import br.com.argonavis.merkatus.alicit.ejb.facade.remote.CategoriaFacadeRemote;
import br.com.argonavis.merkatus.alicit.produto.Categoria;
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
public class CurrentCategoriaManagedBean implements Serializable {
    @EJB CategoriaFacadeRemote categoriaFacade;
    
    private Categoria currentCategoria;
    private String categoriaNome;
    private Long categoriaId;
    private Long idCategoriaContexto;

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
 
    /**
     * Get current categoria.
     * @return Current categoria of this managed bean.
     */
    public Categoria getCurrentCategoria() {
        return currentCategoria;
    }
    
    /**
     * Sets categoria as current categoria of managed bean
     * @param currentCategoria The categoria which will be the current categoria.
     */
    public void setCurrentCategoria(Categoria currentCategoria) {
        this.currentCategoria = currentCategoria;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }

    public Long getIdCategoriaContexto() {
        return idCategoriaContexto;
    }

    public void setIdCategoriaContexto(Long idCategoriaContexto) {
        this.idCategoriaContexto = idCategoriaContexto;
    }
    
    /**
     * Makes current categoria of this managed bean equal to null.
     */
    public void unsetCurrentCategoria() {
        this.currentCategoria = null;
    }
    
    /**
     * Sets current categoria if not defined and if categoriaContexto/categoriaNome refers to an existing categoria in the database.
     * @return false if no tag is set.
     */
    public boolean setCurrentCategoria() {
        if (currentCategoria != null) {
            return true;
        } else {
            try {
                Categoria categoria = categoriaFacade.find(categoriaId);
                System.out.println(categoria.getSubCategorias().size()); // test for lazy initialization
                this.setCurrentCategoria(categoria);
                return categoria != null;
            } catch (NoResultException e) {
                return false;
            }
        }
    }

    /**
     * Creates new tag.
     * @return navigation key
     */
    public String criar() {
        if (this.categoriaNome != null) {
            if (this.idCategoriaContexto == null) {
                categoriaFacade.create(new Categoria(this.categoriaNome));
            } else {
                categoriaFacade.create(new Categoria(this.categoriaNome, categoriaFacade.find(idCategoriaContexto)));
            }
            //this.setCurrentCategoria(categoriaFacade.getByNomeAndIdContexto(categoriaNome, idCategoriaContexto));
        }
        //this.unsetCurrentCategoria();
        return "categorias";
    }
    
    /**
     * Removes current categoria from database.
     * @return navigation key
     */
    public String remover() {
        // No need to confirm before removing individual categoria
        if (this.setCurrentCategoria()) {
            for(Categoria sub: currentCategoria.getSubCategorias()) {
                sub.setContexto(null);
                categoriaFacade.edit(sub);
            }
            currentCategoria.getSubCategorias().clear();
            currentCategoria.setContexto(null);
            categoriaFacade.remove(currentCategoria);
            this.unsetCurrentCategoria();
        }
        return "categorias";
    }
}
