/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital;

/**
 *
 * @author helderdarocha
 */
class Endereco {
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
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }
    
    @Override
    public String toString() {
        if (endereco != null) {
            return endereco + ", " + cidade + ", " + uf + ", " + cep;
        } else {
            return rua + ", " + numero + ", " + ((complemento != null)?(complemento + ", "):("")) + bairro + ", " + cidade + ", " + uf + ", " + cep + ", " + bairro;
        }
    }
}
