/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.comprador;

import java.util.Date;

/**
 *
 * @author helderdarocha
 */
public class ComprasNet extends Portal {
    
    public static Portal criar() {
        return getPortal("COMPRASNET");
    }

    protected ComprasNet() {
        super("Portal ComprasNET", "Portal de Compras do Governo Federal", "www.comprasnet.gov.br");
    }

    @Override
    public String getIdCodigoComprador() {
        return "UASG";
    }

    @Override
    public String getMascaraCodigoComprador(Date dataEdital) {
        return "\\d{6}";
    }

}
