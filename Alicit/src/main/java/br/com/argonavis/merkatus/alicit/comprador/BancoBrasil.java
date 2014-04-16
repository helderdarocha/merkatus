/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.comprador;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author helderdarocha
 */
@Entity
@XmlRootElement
public class BancoBrasil extends Comprador {
    public BancoBrasil() {
        super("Portal BB", "Portal de Compras Banco do Brasil", "BB", "www.licitacoes-e.com.br");
    }
    
    @Override
    public String getIdCodigoComprador() {
        return "Orgao";
    }

    @Override
    public String getMascara(Date dataEdital) {
        return "\\d{8}";
    }
}
