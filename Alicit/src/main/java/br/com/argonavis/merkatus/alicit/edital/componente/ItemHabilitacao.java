/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital.componente;

import javax.persistence.Entity;

/**
 *
 * @author helderdarocha
 */
@Entity
public class ItemHabilitacao extends ItemEdital {

    public ItemHabilitacao() {
    }

    public ItemHabilitacao(String codigo, String descricao) {
        super(codigo, descricao);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ItemHabilitacao)) {
            return false;
        }
        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Habilitacao " + super.toString();
    }
    
}
