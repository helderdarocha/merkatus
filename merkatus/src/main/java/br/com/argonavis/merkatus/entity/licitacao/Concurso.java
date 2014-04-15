/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.entity.licitacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author helderdarocha
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED) 
@XmlRootElement
public abstract class Concurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nome;
    private String numero;
    
    @OneToOne(mappedBy="concurso", cascade=CascadeType.ALL)
    private Edital edital;
    private long dataAbertura;
    
    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private List<Habilitacao> itensHabilitacao = new ArrayList<>();
    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private List<Documento>   documentosRequeridos;
    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private List<Amostra>     amostrasRequeridas;
    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private List<Produto>     produtosSolicitados;
    
    public Concurso() {}
    public Concurso(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Concurso)) {
            return false;
        }
        Concurso other = (Concurso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.argonavis.merkatus.entity.licitacao.Concurso[ id=" + id + " ]";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public long getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(long dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    @XmlTransient
    public List<Habilitacao> getItensHabilitacao() {
        return itensHabilitacao;
    }

    public void setItensHabilitacao(List<Habilitacao> itensHabilitacao) {
        this.itensHabilitacao = itensHabilitacao;
    }
    
    public void addItemHabilitacao(Habilitacao itemHabilitacao) {
        this.itensHabilitacao.add(itemHabilitacao);
    }

    @XmlTransient
    public List<Documento> getDocumentosRequeridos() {
        return documentosRequeridos;
    }

    public void setDocumentosRequeridos(List<Documento> documentosRequeridos) {
        this.documentosRequeridos = documentosRequeridos;
    }
    
    public void addDocumentoRequerido(Documento documento) {
        this.documentosRequeridos.add(documento);
    }

    @XmlTransient
    public List<Amostra> getAmostrasRequeridas() {
        return amostrasRequeridas;
    }

    public void setAmostrasRequeridas(List<Amostra> amostrasRequeridas) {
        this.amostrasRequeridas = amostrasRequeridas;
    }
    
    public void addAmostraRequerida(Amostra amostra) {
        this.amostrasRequeridas.add(amostra);
    }

    @XmlTransient
    public List<Produto> getProdutosSolicitados() {
        return produtosSolicitados;
    }

    public void setProdutosSolicitados(List<Produto> produtosSolicitados) {
        this.produtosSolicitados = produtosSolicitados;
    }
    
    public void addProdutoSolicitado(Produto produto) {
        this.produtosSolicitados.add(produto);
    }

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }
    
}
