/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.merkatus.web;

import br.com.argonavis.merkatus.entity.licitacao.Concurso;
import br.com.argonavis.merkatus.entity.licitacao.EditalDispensaLicitacao;
import br.com.argonavis.merkatus.entity.licitacao.EditalPregaoEletronico;
import br.com.argonavis.merkatus.entity.licitacao.orgao.ConcursoBB;
import br.com.argonavis.merkatus.entity.licitacao.orgao.ConcursoBEC;
import br.com.argonavis.merkatus.entity.licitacao.orgao.ConcursoComprasNet;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author helderdarocha
 */
@ManagedBean
@RequestScoped
public class CadastrarConcursoManagedBean {

    private String concursoLabel;
    private String edital;
    private EditalPregaoEletronico.Tipo tipoPregao;
    private EditalDispensaLicitacao.Tipo tipoDispensa;
    
    private static Map<String, Concurso> concursoMap;
    private static Map<String, Class> editais;
    private static Map<String, EditalPregaoEletronico.Tipo>  tiposPregao;
    private static Map<String, EditalDispensaLicitacao.Tipo> tiposDispensa;
    static {
        concursoMap = new LinkedHashMap<>();
        concursoMap.put("Compras NET", new ConcursoComprasNet());
        concursoMap.put("BEC", new ConcursoBEC());
        concursoMap.put("BB", new ConcursoBB());
        
        editais = new LinkedHashMap<>();
        editais.put("Pregão Eletrônico", EditalPregaoEletronico.class);
        editais.put("Dispensa de licitação", EditalDispensaLicitacao.class);
        
        tiposPregao = new LinkedHashMap<>();
        tiposPregao.put("CompraDireta", EditalPregaoEletronico.Tipo.COMPRA_DIRETA);
        tiposPregao.put("SRP", EditalPregaoEletronico.Tipo.SRP);
        tiposPregao.put("SRP Carona", EditalPregaoEletronico.Tipo.SRP_CARONA);
        
        tiposDispensa = new LinkedHashMap<>();
        tiposDispensa.put("CompraDireta", EditalDispensaLicitacao.Tipo.COMPRA_DIRETA);
        tiposDispensa.put("Convite Eletrônico", EditalDispensaLicitacao.Tipo.CONVITE_ELETRONICO);
        tiposDispensa.put("Cotação Eletrônica", EditalDispensaLicitacao.Tipo.COTACAO_ELETRONICA);
    }

    public CadastrarConcursoManagedBean() {}

    public String getConcursoLabel() {
        return concursoLabel;
    }

    public void setConcursoLabel(String concursoLabel) {
        this.concursoLabel = concursoLabel;
    }

    public String getEdital() {
        return edital;
    }

    public void setEdital(String edital) {
        this.edital = edital;
    }
    
    public EditalDispensaLicitacao.Tipo getTipoDispensa() {
        return tipoDispensa;
    }

    public void setTipoDispensa(EditalDispensaLicitacao.Tipo tipo) {
        this.tipoDispensa = tipo;
    }
    
    public EditalPregaoEletronico.Tipo getTipoPregao() {
        return tipoPregao;
    }

    public void setTipoPregao(EditalPregaoEletronico.Tipo tipo) {
        this.tipoPregao = tipo;
    }

    public Map<String, Concurso> getConcursoMap() {
        return concursoMap;
    }
    
    public Map<String, Class> getEditais() {
        return editais;
    }
    
    public Map<String, EditalDispensaLicitacao.Tipo> getTiposDispensa() {
        return tiposDispensa;
    }
    
    public Map<String, EditalPregaoEletronico.Tipo> getTiposPregao() {
        return tiposPregao;
    }

}
