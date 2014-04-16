/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital;

import java.text.ParseException;

/**
 *
 * @author helderdarocha
 */
class Email {
    private String mascara = "^(([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5}){1,25})$";
    private String email;
    
    public Email(String email) throws ParseException {
        this.email = validate(email, mascara);
    }
    private String validate(String email, String mascara) throws ParseException {
        if (email.matches(mascara)) {
            return email;
        }
        throw new ParseException("Email incorreto", 0);
    }
    @Override
    public String toString() {
        return email;
    }
}
