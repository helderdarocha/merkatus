/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.entity.licitacao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author helderdarocha
 */
@Entity
@XmlRootElement
public class EditalDispensaLicitacao extends Edital {
    public enum Tipo {
        CONVITE_ELETRONICO,
        COTACAO_ELETRONICA,
        COMPRA_DIRETA;
    }
    private Tipo tipo;

    public EditalDispensaLicitacao() {}
    public EditalDispensaLicitacao(Tipo tipo) {}
    public EditalDispensaLicitacao(Concurso concurso, Tipo tipo) {
        super(concurso);
        this.tipo = tipo;
    }
/*
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof EditalDispensaLicitacao)) {
            return false;
        }
        EditalDispensaLicitacao other = (EditalDispensaLicitacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
*/
    @Override
    public String toString() {
        return "br.com.argonavis.merkatus.entity.licitacao.DispensaLicitacao[ id=" + getId() + " ]";
    }
    
}
