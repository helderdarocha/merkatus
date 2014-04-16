/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit;

import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import br.com.argonavis.merkatus.alicit.edital.Edital;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author helderdarocha
 */
@Entity
public class Sistema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Comprador> compradores;
    
    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Edital> editais;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Comprador> getCompradores() {
        return compradores;
    }

    public void setCompradores(Set<Comprador> compradores) {
        this.compradores = compradores;
    }

    public Set<Edital> getEditais() {
        return editais;
    }
    
    public void addEdital(Edital edital) {
        this.editais.add(edital);
    }

    public void setEditais(Set<Edital> editais) {
        this.editais = editais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sistema)) {
            return false;
        }
        Sistema other = (Sistema) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.argonavis.merkatus.alicit.Sistema[ id=" + id + " ]";
    }
    
}
