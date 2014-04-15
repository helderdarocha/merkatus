/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.entity.licitacao;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author helderdarocha
 */
@Entity
@XmlRootElement
public class EditalPregaoEletronico extends Edital {
    public enum Tipo {
        COMPRA_DIRETA,
        SRP,
        SRP_CARONA; 
    }
    
    private Tipo tipo;

    public EditalPregaoEletronico() {}
    public EditalPregaoEletronico(Tipo tipo) {}
    public EditalPregaoEletronico(Concurso concurso, Tipo tipo) {
        super(concurso);
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "br.com.argonavis.merkatus.entity.licitacao.PregaoEletronico[ id=" + getId() + " ]";
    }
    
}
