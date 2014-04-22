/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.produto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author helderdarocha
 */
@Entity
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    
    @ManyToOne
    private Categoria parent ;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private Set<Categoria> subCategorias = new HashSet<>();
    
    public Categoria() {}
    public Categoria(String nome) {
        this.nome = nome;
        this.parent = null;
    }
    public Categoria(String nome, Categoria parent) {
        this.nome = nome;
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nome != null ? nome.hashCode() : 0);
        hash += (parent != null ? parent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        
        boolean parentSame = (this.parent == null && other.parent == null) || (this.parent != null && this.parent.equals(other.parent));
        boolean nameSame   = (this.nome == null && other.nome == null) || (this.nome != null && this.nome.equals(other.nome));
        
        return parentSame && nameSame;
    }

    @Override
    public String toString() {
        String p = "";
        if (parent != null) {
            p = parent.toString();
        }
        return p + "/" + nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getParent() {
        return parent;
    }

    public void setParent(Categoria parent) {
        this.parent = parent;
    }

    public Set<Categoria> getSubCategorias() {
        return subCategorias;
    }

    public void setSubCategorias(Set<Categoria> subCategorias) {
        this.subCategorias = subCategorias;
    }
    
    public void addSubCategoria(Categoria subCategoria) {
        this.subCategorias.add(subCategoria);
        subCategoria.parent = this;
    }
    
    public Categoria detachSubCategoria(String nome) {
        for(Categoria found : this.subCategorias) {
            if(found.getNome().equals(nome)) {
                found.parent = null;
                if (this.subCategorias.remove(found)) {
                    return found;
                }
            }
        }
        return null;
    }
    
    public Categoria getRoot() {
        Categoria root = this;
        while(root.parent != null) {
           root = root.parent;
        }
        return root;
    }
    
}
