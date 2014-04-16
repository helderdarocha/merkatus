/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.comprador;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author helderdarocha
 */
@Entity
@XmlRootElement
public class BolsaEletronicaCompras extends Comprador {
    public BolsaEletronicaCompras() {
        super("Portal BEC", "Bolsa Eletrônica de Compras", "BEC", "www.bec.sp.gov.br");
    }
    
    @Override
    public String getIdCodigoComprador() {
        return "OC";
    }

    @Override
    public String getMascara(Date dataEdital) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataEdital);
        int year = calendar.get(Calendar.YEAR);
        return "\\d{6}\\d{5}"+year+"OC\\d{5}";
    }
}
