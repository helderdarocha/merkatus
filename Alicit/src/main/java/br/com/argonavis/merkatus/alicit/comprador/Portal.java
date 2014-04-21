/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.comprador;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author helderdarocha
 */
public abstract class Portal implements Serializable {
    
    private static final Map<String, Portal> portais = new HashMap<>();
    static {
        portais.put("BEC", new BolsaEletronicaCompras());
        portais.put("BB", new BancoBrasil());
        portais.put("COMPRASNET", new ComprasNet());
    }

    public static Map<String, Portal> getPortais() {
        return portais;
    }

    public static Portal getPortal(String identificador) {
        return portais.get(identificador);
    }
    
    private String nomeCurto;
    private String nomeLongo;
    private String website;
    
    protected Portal(String nomeCurto, String nomeLongo, String website) {
        this.nomeLongo = nomeLongo;
        this.nomeCurto = nomeCurto;
        this.website = website;
    }
    
    /**
     * Retorna o identificador (nome ou sigla) do código do edital para este comprador.
     * @return string contendo o nome do código.
     */
    public abstract String getIdCodigoComprador();
    
    /**
     * Retorna a máscara usada para validar o código do comprador.
     * @param dataEdital
     * @return máscara usada na expressão regular de validação.
     */
    public abstract String getMascaraCodigoComprador(Date dataEdital);

    public String getNomeCurto() {
        return nomeCurto;
    }

    public void setNomeCurto(String nomeCurto) {
        this.nomeCurto = nomeCurto;
    }

    public String getNomeLongo() {
        return nomeLongo;
    }

    public void setNomeLongo(String nomeLongo) {
        this.nomeLongo = nomeLongo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Portal)) return false;
        if (!(o.getClass().equals(this.getClass()))) return false;
        Portal other = (Portal) o;
        return  other.nomeCurto.equals(this.nomeCurto) &&
                other.nomeLongo.equals(this.nomeLongo) &&
                other.website.equals(this.website);
    }
    
    @Override
    public int hashCode() {
        return (this.nomeCurto + this.nomeLongo + this.website).hashCode();
    }

}
