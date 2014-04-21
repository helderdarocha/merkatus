package br.com.argonavis.merkatus.alicit.edital;

import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author helderdarocha
 */
@Entity
@Inheritance
@XmlRootElement
public abstract class Edital implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nomeDisplay;
    
    @Embedded
    @AttributeOverrides({ 
       @AttributeOverride(name="codigo", column=@Column(name="NUMERO_EDITAL"))
    })
    @Column(unique = true)
    private Codigo numeroEdital;
    
    @Embedded
    @AttributeOverrides({
       @AttributeOverride(name="codigo", column=@Column(name="CODIGO_COMPRADOR"))
    }) 
    private Codigo numeroEditalComprador;
    
    @Embedded
    private CNPJ cnpjComprador;

    @Embedded
    @AttributeOverrides({ 
       @AttributeOverride(name="latitude", column=@Column(name="LATITUDE_DOC")), 
       @AttributeOverride(name="longitude", column=@Column(name="LONGITUDE_DOC")), 
       @AttributeOverride(name="cep", column=@Column(name="CEP_DOC")),
       @AttributeOverride(name="pais", column=@Column(name="PAIS_DOC")),
       @AttributeOverride(name="uf", column=@Column(name="UF_DOC")),
       @AttributeOverride(name="cidade", column=@Column(name="CIDADE_DOC")),
       @AttributeOverride(name="rua", column=@Column(name="RUA_DOC")),
       @AttributeOverride(name="numero", column=@Column(name="NUMERO_DOC")),
       @AttributeOverride(name="complemento", column=@Column(name="COMPLEMENTO_DOC")),
       @AttributeOverride(name="bairro", column=@Column(name="BAIRRO_DOC")),
       @AttributeOverride(name="endereco", column=@Column(name="ENDERECO_DOC")),
       @AttributeOverride(name="numero", column=@Column(name="NUMERO_DOC")) 
    }) 
    private Endereco enderecoEnvioDocumentacao;
    
    @Embedded
    @AttributeOverrides({ 
       @AttributeOverride(name="latitude", column=@Column(name="LATITUDE_ENT")), 
       @AttributeOverride(name="longitude", column=@Column(name="LONGITUDE_ENT")), 
       @AttributeOverride(name="cep", column=@Column(name="CEP_ENT")),
       @AttributeOverride(name="pais", column=@Column(name="PAIS_ENT")),
       @AttributeOverride(name="uf", column=@Column(name="UF_ENT")),
       @AttributeOverride(name="cidade", column=@Column(name="CIDADE_ENT")),
       @AttributeOverride(name="rua", column=@Column(name="RUA_ENT")),
       @AttributeOverride(name="numero", column=@Column(name="NUMERO_ENT")),
       @AttributeOverride(name="complemento", column=@Column(name="COMPLEMENTO_ENT")),
       @AttributeOverride(name="bairro", column=@Column(name="BAIRRO_ENT")),
       @AttributeOverride(name="endereco", column=@Column(name="ENDERECO_ENT")),
       @AttributeOverride(name="numero", column=@Column(name="NUMERO_ENT")) 
    }) 
    private Endereco enderecoEntrega;
    
    @Embedded
    private Email email;
    
    @Embedded
    @AttributeOverrides({ 
       @AttributeOverride(name="ddi", column=@Column(name="TELDDI_ENT")), 
       @AttributeOverride(name="ddd", column=@Column(name="TELDDD_ENT")), 
       @AttributeOverride(name="numero", column=@Column(name="TELNUM_ENT")) 
    }) 
    private Telefone telefoneEntrega;
    
    @Embedded
    @AttributeOverrides({ 
       @AttributeOverride(name="ddi", column=@Column(name="TELDDI_COB")), 
       @AttributeOverride(name="ddd", column=@Column(name="TELDDD_COB")), 
       @AttributeOverride(name="numero", column=@Column(name="TELNUM_COB"))
    }) 
    private Telefone telefoneCobranca;

    @ManyToOne
    private Comprador comprador;

    protected Edital() {
    }

    public Edital(Comprador comprador, Codigo numeroEdital) {
        this.comprador = comprador;
        this.numeroEdital = numeroEdital;
    }
    
    public void setIdentificacao(String nomeDisplay, Codigo numeroEdital, Codigo numeroEditalComprador, CNPJ cnpjComprador) {
        this.nomeDisplay = nomeDisplay;
        this.numeroEdital = numeroEdital;
        this.numeroEditalComprador = numeroEditalComprador;
        this.cnpjComprador = cnpjComprador;
    }
    
    public void setContatos(Endereco enderecoEnvioDocumentacao, Endereco enderecoEntrega, Email email, Telefone telefoneEntrega, Telefone telefoneCobranca) {
        this.enderecoEnvioDocumentacao = enderecoEnvioDocumentacao;
        this.enderecoEntrega = enderecoEntrega;
        this.email = email;
        this.telefoneEntrega = telefoneEntrega;
        this.telefoneCobranca = telefoneCobranca;
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

    protected void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Comprador getComprador() {
        return comprador;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Edital)) return false;
        Edital other = (Edital) o;
        return other.numeroEdital.equals(this.numeroEdital);
    }
    
    @Override
    public int hashCode() {
        return (this.numeroEdital).hashCode();
    }

}