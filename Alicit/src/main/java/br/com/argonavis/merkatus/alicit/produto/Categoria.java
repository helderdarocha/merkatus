/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.produto;

import java.io.Serializable;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    
    @Column(unique=true)
    private String nomeAbsoluto;
    
    @ManyToOne
    private Categoria contexto ;
    
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "contexto", fetch = FetchType.EAGER)
    private Set<Categoria> subCategorias = new HashSet<>();
    
    protected Categoria() {}
    public Categoria(String nome) {
        this.nome = nome;
        this.contexto = null;
        updateNomeAbsoluto();
    }
    public Categoria(String nome, Categoria parent) {
        this.nome = nome;
        this.contexto = parent;
        updateNomeAbsoluto();
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
        hash += (contexto != null ? contexto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        
        boolean parentSame = (this.contexto == null && other.contexto == null) || (this.contexto != null && this.contexto.equals(other.contexto));
        boolean nameSame   = (this.nome == null && other.nome == null) || (this.nome != null && this.nome.equals(other.nome));
        
        return parentSame && nameSame;
    }

    @Override
    public String toString() {
        String p = "";
        if (contexto != null) {
            p = contexto.toString();
        }
        return p + "/" + nome;
    }
    
    private void updateNomeAbsoluto() {
        if (getContexto() != null) {
            nomeAbsoluto = getContexto().getNomeAbsoluto() + "/" + getNome();
        } else {
            nomeAbsoluto = getNome();
        }
    }

    public String getNomeAbsoluto() {
        if (nomeAbsoluto == null) {
            updateNomeAbsoluto();
        }
        return nomeAbsoluto;
    }

    public void setNomeAbsoluto(String nomeAbsoluto) throws ParseException {
        this.nomeAbsoluto = nomeAbsoluto;
        String[] splitNome = splitNomeCategoria(nomeAbsoluto);
        this.nome = splitNome[0];
        if (this.contexto != null) {
            this.contexto.setNomeAbsoluto(splitNome[1]);
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getContexto() {
        return contexto;
    }

    public void setContexto(Categoria contexto) {
        this.contexto = contexto;
    }

    public Set<Categoria> getSubCategorias() {
        return subCategorias;
    }

    public void setSubCategorias(Set<Categoria> subCategorias) {
        this.subCategorias = subCategorias;
    }
    
    public void addSubCategoria(Categoria subCategoria) {
        this.subCategorias.add(subCategoria);
        subCategoria.contexto = this;
    }
    
    public Categoria detachSubCategoria(String nome) {
        for(Categoria found : this.subCategorias) {
            if(found.getNome().equals(nome)) {
                found.contexto = null;
                if (this.subCategorias.remove(found)) {
                    return found;
                }
            }
        }
        return null;
    }
    
    public Categoria getRoot() {
        Categoria root = this;
        while(root.contexto != null) {
           root = root.contexto;
        }
        return root;
    }
    
    /**
     * Returns array containing [0] relative name of category, 
     * @param nomeAbsolutoContexto
     * @return
     * @throws ParseException 
     */
    public static String[] splitNomeCategoria(String nomeAbsolutoContexto) throws ParseException {
        int lastSlash = nomeAbsolutoContexto.lastIndexOf("/");
        String[] nomeArray = new String[2];
        if(lastSlash < 0) { // no slash, only set name
            nomeArray[0] = nomeAbsolutoContexto;
        } else {
            nomeArray[0] = nomeAbsolutoContexto.substring(lastSlash); // everything after the slash
            nomeArray[1] = nomeAbsolutoContexto.substring(0, lastSlash); // everything before (contexto)
            
            if (nomeArray[0].contains("/")) {
                throw new ParseException("Nome relativo de categoria nao pode incluir '/'", 0);
            }
        }
        return nomeArray;
    }
    
}
