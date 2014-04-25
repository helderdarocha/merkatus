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
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;

/**
 *
 * @author helderdarocha
 */
@Named
@SessionScoped
public class CurrentCategoriaManagedBean implements Serializable {
    @EJB CategoriaFacadeRemote categoriaFacade;
    
    private Categoria currentCategoria;
    private String categoriaNome;
    private Long idCategoriaContexto;
 
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
        } else if (this.categoriaNome != null) {
            try {
                Categoria categoria = categoriaFacade.getByNomeAndIdContexto(categoriaNome, idCategoriaContexto);
                this.setCurrentCategoria(categoria);
                return categoria != null;
            } catch (NoResultException e) {
                return false;
            }
        }
        return false;
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
            this.setCurrentCategoria(categoriaFacade.getByNomeAndIdContexto(categoriaNome, idCategoriaContexto));
        }
        return "categorias";
    }
    
    /**
     * Removes current tag from database.
     * @return navigation key
     */
    public String remover() {
        // No need to confirm before removing individual tags
        if (this.setCurrentCategoria()) {
            currentCategoria.getSubCategorias().clear();
            currentCategoria.setContexto(null);
            categoriaFacade.remove(currentCategoria);
            this.unsetCurrentCategoria();
        }
        return "categorias";
    }
}
