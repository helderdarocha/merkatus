/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.edital.componente;

import br.com.argonavis.merkatus.alicit.produto.Produto;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author helderdarocha
 */
@Entity
public class ItemProduto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private Produto produto;
    
    private int quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public int hashCode() {
        if (this.produto == null) return 0;
        return produto.hashCode();
    }
    
    public BigDecimal getValorTotal() {
        if (this.produto == null) {
            return BigDecimal.ZERO;
        }
        return produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ItemProduto)) {
            return false;
        }
        ItemProduto other = (ItemProduto) object;
        if ((this.produto == null && other.produto != null) || (this.produto != null && !this.produto.equals(other.produto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.argonavis.merkatus.alicit.edital.componente.ItemProduto[ id=" + id + " ]";
    }
    
}
