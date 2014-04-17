/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.comprador;

import br.com.argonavis.merkatus.alicit.edital.Edital;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author helderdarocha
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED) 
@XmlRootElement
public abstract class Comprador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nomeCurto;
    private String nomeLongo;
    
    @Column(unique = true)
    private String codigo;
    private String website;
    
    @OneToMany(mappedBy="comprador", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<Edital> editais;

    protected Comprador() {}
    public Comprador(String nomeCurto, String nomeLongo, String codigo, String website) {
        this.nomeLongo = nomeLongo;
        this.nomeCurto = nomeCurto;
        this.codigo = codigo;
        this.website = website;
    }
    
    /**
     * Retorna o identificador (nome ou sigla) do código do edital para este comprador.
     * @return string contendo o nome do código.
     */
    public abstract String getIdCodigoComprador();
    
    /**
     * Retorna a máscara usada para validar o código do comprador.
     * @param dataEdital
     * @return máscara usada na expressão regular de validação.
     */
    public abstract String getMascara(Date dataEdital);
    
    /**
     * Valida o código específico do comprador para o edital. A data é opcional.
     * @param codigo O código do edital no comprador especificado.
     * @param dataEdital A data do edital (opcional para BB e ComprasNet)
     * @return true se código for válido.
     */
    public boolean validarCodigoComprador(String codigo, Date dataEdital) {
        return codigo.matches(getMascara(dataEdital));
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCurto() {
        return nomeCurto;
    }

    public void setNomeCurto(String nomeCurto) {
        this.nomeCurto = nomeCurto;
    }

    public String getNomeLongo() {
        return nomeLongo;
    }

    public void setNomeLongo(String nomeLongo) {
        this.nomeLongo = nomeLongo;
    }

    public String getCodigo() {
        return codigo;
    }

    protected void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Set<Edital> getEditais() {
        return editais;
    }

    public void addEdital(Edital edital) {
        this.editais.add(edital);
        edital.setComprador(this);
    }
    
    public void removeEdital(Edital edital) {
        edital.setComprador(null);
        this.editais.remove(edital);
    }
    
    public void setEditais(Set<Edital> editais) {
        this.editais = editais;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Comprador)) return false;
        Comprador other = (Comprador) o;
        return other.codigo.equals(this.codigo);
    }
    
    @Override
    public int hashCode() {
        return (this.codigo).hashCode();
    }
    
}
