/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.comprador;

import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author helderdarocha
 */
@Entity
public class ConsumidorFinal extends Comprador {
    
    public ConsumidorFinal() {
        super("Consumidor Final Nome Curto", "Consumidor Final Nome Longo", "ConsumidorID", "www.consumidor.com");
    }
    public ConsumidorFinal(String nomeCurto, String nomeLongo, String identificador, String website) {
        super(nomeCurto, nomeLongo, identificador, website);
    }
    public ConsumidorFinal(String identificador) {
        super("Consumidor Final Nome Curto", "Consumidor Final Nome Longo", identificador, "www.consumidor.com");
    }
    
    @Override
    public String getIdCodigoComprador() {
        return "Código do comprador";
    }

    @Override
    public String getMascara(Date dataEdital) {
        return "(\\d.*|\\w.*|\\W.*).*";
    }
    
}
