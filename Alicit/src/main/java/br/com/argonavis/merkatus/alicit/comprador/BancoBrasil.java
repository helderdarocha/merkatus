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
public class BancoBrasil extends Portal {

    public static Portal criar() {
        return getPortal("BB");
    }

    protected BancoBrasil() {
        super("Portal BB", "Portal de Compras Banco do Brasil", "www.licitacoes-e.com.br");
    }

    @Override
    public String getIdCodigoComprador() {
        return "ORGAO";
    }

    @Override
    public String getMascaraCodigoComprador(Date dataEdital) {
        return "\\d{8}";
    }
}
