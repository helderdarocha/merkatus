/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import javax.persistence.Embeddable;

/**
 * Representa um CNPJ.
 * @author helderdarocha
 */
@Embeddable
public class CNPJ implements Serializable {
    private String normalizedCnpj;
    private String formattedCnpj;

    protected CNPJ() {}
    
    /**
     * Cria um CNPJ e recebe um string formatado xx.xxx.xxx/xxxx-xx ou 14 digitos.
     * Retorna CNPJ normalizado (14 digitos) ou formatado.
     * @param cnpj string com CNPJ
     * @throws ParseException se formato estiver incorreto
     */
    public CNPJ(String cnpj) throws ParseException {
        validar(cnpj);
    }
    private void validar(String cnpj) throws ParseException {
        if (cnpj.matches("\\d{14}")) {
            normalizedCnpj = cnpj;
            formattedCnpj = format();
        } else if (cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}")) {
            formattedCnpj = cnpj;
            normalizedCnpj = normalize();
        } else {
            throw new ParseException("CNPJ invalido: " + cnpj, 0);
        }
    }
    
    public void setValue(String cnpj) throws ParseException {
        validar(cnpj);
    }
    public String getValue() {
        return formattedCnpj;
    }
    public String getValueNormalized() {
        return normalizedCnpj;
    }
    
    // formata número
    private String format() {
        String suffix = normalizedCnpj.substring(8,12);
        String base = normalizedCnpj.substring(0, 8);
        String crc = normalizedCnpj.substring(12,14);
        
        Locale locale  = new Locale("pt", "BR");
        DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        df.applyLocalizedPattern("00.000.000");

        String formattedBase = df.format(Integer.parseInt(base));
        return formattedBase + "/" + suffix + "-" + crc;
    }
    
    // converte CNPJ em apenas digitos
    private String normalize() {
        return formattedCnpj.replaceAll("[./-]", "");
    }
    
    /**
     * Retorna CNPJ formatado de maneira padrão xx.xxx.xxx/xx
     * @return 
     */
    @Override
    public String toString() {
        return formattedCnpj;
    }
    
    /**
     * Retorna CNPJ usando apenas 14 dígitos
     * @return 
     */
    public String toNormalizedString() {
        return normalizedCnpj;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CNPJ)) return false;
        CNPJ other = (CNPJ) o;
        return other.normalizedCnpj.equals(this.normalizedCnpj);
    }
    
    @Override
    public int hashCode() {
        return this.normalizedCnpj.hashCode();
    }
}
