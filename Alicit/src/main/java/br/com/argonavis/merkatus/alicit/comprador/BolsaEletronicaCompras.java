/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.comprador;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author helderdarocha
 */
public class BolsaEletronicaCompras extends Portal {
    
    public static Portal criar() {
        return getPortal("BEC");
    }
    
    protected BolsaEletronicaCompras() {
        super("Portal BEC", "Bolsa Eletrônica de Compras", "www.bec.sp.gov.br");
    }
    
    @Override
    public String getIdCodigoComprador() {
        return "OC";
    }

    @Override
    public String getMascaraCodigoComprador(Date dataEdital) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataEdital);
        int year = calendar.get(Calendar.YEAR);
        return "\\d{6}\\d{5}"+year+"OC\\d{5}";
    }
}
