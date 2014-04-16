/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.comprador;

import java.util.Date;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author helderdarocha
 */
@Entity
@XmlRootElement
public class ComprasNet extends Comprador {
    
    private String mascara;

    public ComprasNet() {
        super("Portal ComprasNET", "Portal de Compras do Governo Federal", "ComprasNET", "www.comprasnet.gov.br");
    }

    @Override
    public String getIdCodigoComprador() {
        return "UASG";
    }

    @Override
    public String getMascara(Date dataEdital) {
        return "\\d{6}";
    }

}
