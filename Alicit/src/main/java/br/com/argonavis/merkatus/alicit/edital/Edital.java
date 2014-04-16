package br.com.argonavis.merkatus.alicit.edital;

import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author helderdarocha
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
public abstract class Edital implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nomeDisplay;
    private Codigo numeroEdital;
    private Codigo numeroEditalComprador;
    private CNPJ cnpjComprador;
    private Endereco enderecoEnvioDocumentacao;
    private Endereco enderecoEntrega;
    private Email email;
    private Telefone telefoneEntrega;
    private Telefone telefoneCobranca;

    @ManyToOne
    private Comprador comprador;

    public Edital() {
    }

    public Edital(Comprador comprador) {
        this.comprador = comprador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDisplay() {
        return nomeDisplay;
    }

    public void setNomeDisplay(String nomeDisplay) {
        this.nomeDisplay = nomeDisplay;
    }

    public Codigo getNumeroEdital() {
        return numeroEdital;
    }

    public void setNumeroEdital(Codigo numeroEdital) {
        this.numeroEdital = numeroEdital;
    }

    public Codigo getNumeroEditalComprador() {
        return numeroEditalComprador;
    }

    public void setNumeroEditalComprador(Codigo numeroEditalComprador) {
        this.numeroEditalComprador = numeroEditalComprador;
    }

    public CNPJ getCnpjComprador() {
        return cnpjComprador;
    }

    public void setCnpjComprador(CNPJ cnpjComprador) {
        this.cnpjComprador = cnpjComprador;
    }

    public Endereco getEnderecoEnvioDocumentacao() {
        return enderecoEnvioDocumentacao;
    }

    public void setEnderecoEnvioDocumentacao(Endereco enderecoEnvioDocumentacao) {
        this.enderecoEnvioDocumentacao = enderecoEnvioDocumentacao;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Telefone getTelefoneEntrega() {
        return telefoneEntrega;
    }

    public void setTelefoneEntrega(Telefone telefoneEntrega) {
        this.telefoneEntrega = telefoneEntrega;
    }

    public Telefone getTelefoneCobranca() {
        return telefoneCobranca;
    }

    public void setTelefoneCobranca(Telefone telefoneCobranca) {
        this.telefoneCobranca = telefoneCobranca;
    }
    
    

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Comprador getComprador() {
        return comprador;
    }

}