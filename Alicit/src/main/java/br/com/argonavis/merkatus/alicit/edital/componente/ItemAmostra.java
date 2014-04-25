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
public class ItemAmostra extends ItemEdital {
    public ItemAmostra() {}
    public ItemAmostra(String codigo, String descricao) {
        super(codigo, descricao);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ItemAmostra)) {
            return false;
        }
        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Amostra " + super.toString();
    }
}
