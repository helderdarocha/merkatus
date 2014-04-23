/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.proposta;

import br.com.argonavis.merkatus.alicit.edital.Edital;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author helderdarocha
 */
@Entity
public class Dossier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Edital edital;
    private List<Pendencia> pendencias = new ArrayList<>();
    private StatusDossier status;
    private ResultadoLicitacao resultado;

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

    public List<Pendencia> getPendencias() {
        return pendencias;
    }

    public void setPendencias(List<Pendencia> pendencias) {
        this.pendencias = pendencias;
    }
    
    public void addPendencia(Pendencia p) {
        this.pendencias.add(p);
    }

    public StatusDossier getStatus() {
        return status;
    }

    public void setStatus(StatusDossier status) {
        this.status = status;
    }

    public ResultadoLicitacao getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoLicitacao resultado) {
        this.resultado = resultado;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dossier)) {
            return false;
        }
        Dossier other = (Dossier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.argonavis.merkatus.alicit.proposta.Dossier[ id=" + id + " ]";
    }
    
    public void gerarCheckList() {
        
    }
    
}
