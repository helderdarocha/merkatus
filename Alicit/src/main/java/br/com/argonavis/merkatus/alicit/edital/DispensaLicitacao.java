/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital;

import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author helderdarocha
 */
@Entity
@XmlRootElement
public class DispensaLicitacao extends Edital {
    public enum Tipo {
        CONVITE_ELETRONICO,
        COTACAO_ELETRONICA,
        COMPRA_DIRETA;
    }
    private Tipo tipo;

    public DispensaLicitacao() {}
    public DispensaLicitacao(Tipo tipo) {}
    public DispensaLicitacao(Comprador concurso, Tipo tipo) {
        super(concurso);
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "DispensaLicitacao[ id=" + getId() + " ]";
    }
    
}
