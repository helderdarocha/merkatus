/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital;

import java.io.Serializable;
import java.text.ParseException;
import javax.persistence.Embeddable;

/**
 * Representa um código qualquer, validado por uma máscara.
 * @author helderdarocha
 */
@Embeddable
public class Codigo implements Serializable {
    private String codigo;

    protected Codigo() {}
    
    /**
     * Cria um código.
     * @param codigo Código a ser representado
     * @param mascara Máscara para validar.
     * @throws ParseException 
     */
    public Codigo(String codigo, String mascara) throws ParseException {
        this.codigo = validate(codigo, mascara);
    }
    public Codigo(String codigo) {
        try {
           this.codigo = validate(codigo, null);
        } catch (ParseException e) {
            assert false : "ParseException will never happen.";
        }
    }
    private String validate(String codigo, String mascara) throws ParseException {
        if (mascara == null) {
            return codigo;
        }
        if (codigo.matches(mascara)) {
            return codigo;
        }
        throw new ParseException("Codigo incorreto: " + codigo + " deve combinar com : " + mascara, 0);
    }

    public String getValue() {
        return codigo;
    }

    public void setValue(String codigo) {
        try {
           this.codigo = validate(codigo, null);
        } catch (ParseException e) {
            assert false : "ParseException will never happen.";
        }
    }

    /**
     * Retorna o código.
     * @return 
     */
    @Override
    public String toString() {
        return codigo;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Codigo)) return false;
        Codigo other = (Codigo) o;
        return other.codigo.equals(this.codigo);
    }
    
    @Override
    public int hashCode() {
        return this.codigo.hashCode();
    }
}
