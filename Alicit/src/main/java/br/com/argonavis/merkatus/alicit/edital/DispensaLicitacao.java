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

    @Override
    public String getTipoEdital() {
        return "Dispensa de Licitação";
    }
    
    public enum Tipo {
        CONVITE_ELETRONICO("Convite Eletrônico"),
        COTACAO_ELETRONICA("Cotação Eletrônica"),
        COMPRA_DIRETA("Compra Direta");
        private final String descricao;
        Tipo(String descricao) {this.descricao = descricao;}
        @Override
        public String toString() {
            return descricao;
        }
    }
    private Tipo tipo;

    public DispensaLicitacao() {}
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
