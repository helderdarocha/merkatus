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
 *
 * @author helderdarocha
 */
@Embeddable
public class Email implements Serializable {
    private String mascara = "^(([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5}){1,25})$";
    private String email;
    
    protected Email() {}
    
    public Email(String email) throws ParseException {
        this.email = validate(email, mascara);
    }
    private String validate(String email, String mascara) throws ParseException {
        if (email.matches(mascara)) {
            return email;
        }
        throw new ParseException("Email com formato invalido.", 0);
    }
    @Override
    public String toString() {
        return email;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Email)) return false;
        Email other = (Email) o;
        return other.email.equals(this.email);
    }
    
    @Override
    public int hashCode() {
        return this.email.hashCode();
    }
}
