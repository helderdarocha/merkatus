/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.comprador;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author helderdarocha
 */
@Entity
@XmlRootElement
public class Comprador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    Portal portal;
    
    @Column(unique = true)
    private String identificador;

    protected Comprador() {}
    protected Comprador(String identificador) throws IllegalArgumentException {
        this.identificador = identificador.toUpperCase();
        if (!Portal.getPortais().containsKey(this.identificador)) {
            throw new IllegalArgumentException("Portal inexistente. Identificador: " + identificador);
        }
    }
    
    protected void initComprador() {
        this.portal = Portal.getPortal(identificador);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getIdentificador() {
        return this.identificador;
    }
    
    public Portal getPortal() {
        return this.portal;
    }
    
    public boolean validarCodigoComprador(String codigo, Date dataEdital) {
        return codigo.matches(portal.getMascaraCodigoComprador(dataEdital));
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Comprador)) return false;
        Comprador other = (Comprador) o;
        return other.identificador.equals(this.identificador);
    }
    
    @Override
    public int hashCode() {
        return (this.identificador).hashCode();
    }
    
    public static Comprador createComprador(String identificador) {
        Comprador comprador = new Comprador(identificador);
        comprador.initComprador();
        return comprador;
    }
    
    public static Comprador createCompradorComprasNet() {
        return createComprador("COMPRASNET");
    }
    public static Comprador createCompradorBEC() {
        return createComprador("BEC");
    }
    public static Comprador createCompradorBB() {
        return createComprador("BB");
    }
    
    public static Comprador createCompradorForTest() {
        return createComprador("TEST");
    }
}
