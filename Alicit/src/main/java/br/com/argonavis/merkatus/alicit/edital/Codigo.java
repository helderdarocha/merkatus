/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital;

import java.text.ParseException;

/**
 * Representa um código qualquer, validado por uma máscara.
 * @author helderdarocha
 */
class Codigo {
    private String codigo;
    
    /**
     * Cria um código.
     * @param codigo Código a ser representado
     * @param mascara Máscara para validar.
     * @throws ParseException 
     */
    public Codigo(String codigo, String mascara) throws ParseException {
        this.codigo = validate(codigo, mascara);
    }
    private String validate(String codigo, String mascara) throws ParseException {
        throw new ParseException("Codigo incorreto", 0);
    }
    
    /**
     * Retorna o código.
     * @return 
     */
    @Override
    public String toString() {
        return codigo;
    }
}
