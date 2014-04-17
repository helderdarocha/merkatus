/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author helderdarocha
 */
@Embeddable
public class Telefone implements Serializable {
    private String ddi = "55";
    private String ddd;
    private String numero;
    
    protected Telefone() {}
    
    public Telefone(String ddd, String numero) {
        this.ddd = ddd;
        this.numero = numero;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String formatNumero() {
        return numero;
    }
    
    @Override
    public String toString() {
        return "("+ddd+")" + formatNumero();
    }
    
    public String getNumeroDiscavel() {
        return "+" + ddi + ddd + numero;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Telefone)) return false;
        Telefone other = (Telefone) o;
        return other.getNumeroDiscavel().equals(this.getNumeroDiscavel());
    }
    
    @Override
    public int hashCode() {
        return this.getNumeroDiscavel().hashCode();
    }
}
