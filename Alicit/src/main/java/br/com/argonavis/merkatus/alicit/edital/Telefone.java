/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital;

/**
 *
 * @author helderdarocha
 */
class Telefone {
    private String ddi = "55";
    private String ddd;
    private String numero;
    
    public Telefone(String ddd, String numero) {
        this.ddd = ddd;
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
        return ddi + ddd + numero;
    }
}
