/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.alicit.web;

import java.math.BigDecimal;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author helderdarocha
 */
@Named(value = "currentEditalManagedBean")
@Dependent
public class CurrentEditalManagedBean {

    private BigDecimal valorTotal;
    private long id;
    private String numeroEdital;
    private String tipo;
    
    public CurrentEditalManagedBean() {
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumeroEdital() {
        return numeroEdital;
    }

    public void setNumeroEdital(String numeroEdital) {
        this.numeroEdital = numeroEdital;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
