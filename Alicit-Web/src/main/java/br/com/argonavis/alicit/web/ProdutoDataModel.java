/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.alicit.web;

import br.com.argonavis.merkatus.alicit.ejb.facade.remote.ProdutoFacadeRemote;
import br.com.argonavis.merkatus.alicit.produto.Produto;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author helderdarocha
 */
public class ProdutoDataModel extends ListDataModel<Produto> implements SelectableDataModel<Produto> {  
    
    @EJB
    ProdutoFacadeRemote produtoFacade;

    public ProdutoDataModel() {
    }

    public ProdutoDataModel(List<Produto> data) {
        super(data);
    }
    
    @Override
    public Produto getRowData(String codigo) {
        return produtoFacade.getByCodigo(codigo);
    }

    @Override
    public Object getRowKey(Produto produto) {
        return produto.getCodigo();
    }
}