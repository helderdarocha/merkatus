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
    @Override
    public String getIdCodigoComprador() {
        return "Código do comprador";
    }

    @Override
    public String getMascara(Date dataEdital) {
        return "(\\d.*|\\w.*|\\W.*).*";
    }
    
}
