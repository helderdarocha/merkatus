/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author helderdarocha
 */
@Embeddable
public class Endereco implements Serializable {
    private String latitude;
    private String longitude;
    
    private String cep;
    
    private String pais = "Brasil";
    private String uf;
    private String cidade;
    
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    
    private String endereco;
    
    public Endereco() {}
    
    public Endereco(String endereco, String cidade, String uf, String cep) {
        this.endereco = endereco;
        
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }
    public Endereco(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public Endereco(String rua, String numero, String complemento, String bairro, String cidade, String uf, String cep) {
        this(rua + ", " + numero + ", " + ((complemento != null)?(complemento + ", "):("")) + bairro, 
             cidade, 
             uf, 
             cep);

        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
    }
    
    public int setupWithGeoLocation() throws Exception {
        return setupWithGeoLocation(this.latitude, this.longitude);
    }
    
    public int setupWithGeoLocation(String latitude, String longitude) throws Exception {
        // use geolocation to determine uf, city, cep, bairro, endereco
        // validate and return status
        // see https://developers.google.com/maps/documentation/geocoding/
        // http://www.mkyong.com/java/java-find-location-using-ip-address/
        return -1;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.endereco = rua + ", " + numero + ", " + ((complemento != null)?(complemento + ", "):("")) + bairro;
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.endereco = rua + ", " + numero + ", " + ((complemento != null)?(complemento + ", "):("")) + bairro;
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.endereco = rua + ", " + numero + ", " + ((complemento != null)?(complemento + ", "):("")) + bairro;
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.endereco = rua + ", " + numero + ", " + ((complemento != null)?(complemento + ", "):("")) + bairro;
        this.bairro = bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.rua = null;
        this.numero = null;
        this.bairro = null;
        this.complemento = null;
        this.endereco = endereco;
    }
    
    @Override
    public String toString() {
        String dados = "";
        if (endereco != null) {
            dados += endereco + ", " + cidade + ", " + uf + ", " + cep;
        }
        if (latitude !=null && longitude != null) {
            if (endereco == null) {
                dados = "";
            } else {
                dados += ", ";
            }
            dados += "Geolocation: " + latitude + ", " + longitude;
        }
        return dados;
    }
    
    /**
     * Latitude and longitude are not used to calculate equality or hashCode.
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Endereco)) return false;
        Endereco other = (Endereco) o;
        return other.endereco.equals(this.endereco) 
            && this.cidade.equals(this.cidade)
            && this.cep.equals(this.cep)
            && this.uf.equals(this.uf);
    }
    
    @Override
    public int hashCode() {
        return (this.endereco + this.cidade + this.cep + this.uf).hashCode();
    }
}
