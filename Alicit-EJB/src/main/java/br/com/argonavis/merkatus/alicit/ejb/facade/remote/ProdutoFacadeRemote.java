/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade.remote;

import br.com.argonavis.merkatus.alicit.ejb.facade.AbstractFacadeInterface;
import br.com.argonavis.merkatus.alicit.produto.Categoria;
import br.com.argonavis.merkatus.alicit.produto.Produto;
import br.com.argonavis.merkatus.alicit.produto.Tag;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author helderdarocha
 */
@Remote
public interface ProdutoFacadeRemote extends AbstractFacadeInterface<Produto> {

    public Produto getByCodigo(String codigo);

    public Long countByTag(Tag tag);
    public List<Produto> findByCategoria(Categoria categoria);

    public List<Produto> findByTag(Tag tag);
}