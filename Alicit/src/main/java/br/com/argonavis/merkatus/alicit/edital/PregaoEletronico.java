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
public class PregaoEletronico extends Edital {
    public enum Tipo {
        COMPRA_DIRETA,
        SRP,
        SRP_CARONA; 
    }
    
    private Tipo tipo;

    public PregaoEletronico() {}
    public PregaoEletronico(Tipo tipo) {}
    public PregaoEletronico(Comprador comprador, Tipo tipo) {
        super(comprador);
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "PregaoEletronico[ id=" + getId() + " ]";
    }
    
}
