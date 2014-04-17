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

    protected DispensaLicitacao() {}
    public DispensaLicitacao(Comprador comprador, Tipo tipo, Codigo numero) {
        super(comprador, numero);
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "DispensaLicitacao[ id=" + getId() + " ]";
    }
    
}
