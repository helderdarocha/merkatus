/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.alicit.web;

import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import br.com.argonavis.merkatus.alicit.edital.CNPJ;
import br.com.argonavis.merkatus.alicit.edital.Codigo;
import br.com.argonavis.merkatus.alicit.edital.DispensaLicitacao;
import br.com.argonavis.merkatus.alicit.edital.Edital;
import br.com.argonavis.merkatus.alicit.edital.Email;
import br.com.argonavis.merkatus.alicit.edital.Endereco;
import br.com.argonavis.merkatus.alicit.edital.PregaoEletronico;
import br.com.argonavis.merkatus.alicit.edital.Telefone;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.CompradorFacadeRemote;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.EditalFacadeRemote;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.persistence.NoResultException;

/**
 *
 * @author helderdarocha
 */
@Named
@SessionScoped
public class CurrentEditalManagedBean implements Serializable {

    @EJB
    EditalFacadeRemote editalFacade;
    @EJB
    CompradorFacadeRemote compradorFacade;

    private Edital currentEdital;

    private long editalId;
    private Long editalCompradorId;
    private String editalNumero;
    private String editalTipo;
    private String editalNumeroEditalComprador;
    private String editalCnpjComprador;
    private Date editalDataAbertura;
    private String editalEmail;

    private String editalTelefoneEntregaDdd;
    private String editalTelefoneEntregaNumero;

    private String editalTelefoneCobrancaDdd;
    private String editalTelefoneCobrancaNumero;

    private String editalEnderecoEntregaEndereco;
    private String editalEnderecoEntregaCidade;
    private String editalEnderecoEntregaUf;
    private String editalEnderecoEntregaCep;

    private String editalEnderecoEnvioDocumentacaoEndereco;
    private String editalEnderecoEnvioDocumentacaoCidade;
    private String editalEnderecoEnvioDocumentacaoUf;
    private String editalEnderecoEnvioDocumentacaoCep;

    private String tipoPE;
    private DispensaLicitacao.Tipo tipoDL;

    private static final Map<String, String> tipoEditaisMap = new HashMap<>();

    static {
        tipoEditaisMap.put("Pregão Eletrônico", "PE");
        tipoEditaisMap.put("Dispensa de Licitação", "DL");
    }

    public Map<String, String> getTipoEditaisMap() {
        return tipoEditaisMap;
    }

    private static final Map<String, String> tipoPEMap = new HashMap<>();

    static {
        tipoPEMap.put(PregaoEletronico.Tipo.COMPRA_DIRETA.toString(), "cd");
        tipoPEMap.put(PregaoEletronico.Tipo.SRP.toString(), "srp");
        tipoPEMap.put(PregaoEletronico.Tipo.SRP_CARONA.toString(), "carona");
    }

    public Map<String, String> getTipoPEMap() {
        return tipoPEMap;
    }

    private static final Map<String, DispensaLicitacao.Tipo> tipoDLMap = new HashMap<>();

    static {
        tipoDLMap.put(DispensaLicitacao.Tipo.COMPRA_DIRETA.toString(), DispensaLicitacao.Tipo.COMPRA_DIRETA);
        tipoDLMap.put(DispensaLicitacao.Tipo.CONVITE_ELETRONICO.toString(), DispensaLicitacao.Tipo.CONVITE_ELETRONICO);
        tipoDLMap.put(DispensaLicitacao.Tipo.COTACAO_ELETRONICA.toString(), DispensaLicitacao.Tipo.COTACAO_ELETRONICA);
    }

    public Map<String, DispensaLicitacao.Tipo> getTipoDLMap() {
        return tipoDLMap;
    }

    public Long getEditalCompradorId() {
        return editalCompradorId;
    }

    public void setEditalCompradorId(Long editalCompradorId) {
        this.editalCompradorId = editalCompradorId;
    }

    public long getEditalId() {
        return editalId;
    }

    public void setEditalId(long editalId) {
        this.editalId = editalId;
    }

    public String getEditalNumeroEditalComprador() {
        return editalNumeroEditalComprador;
    }

    public void setEditalNumeroEditalComprador(String editalNumeroEditalComprador) {
        this.editalNumeroEditalComprador = editalNumeroEditalComprador;
    }

    public String getEditalNumero() {
        return editalNumero;
    }

    public void setEditalNumero(String editalNumero) {
        this.editalNumero = editalNumero;
    }

    public String getEditalTipo() {
        return editalTipo;
    }

    public void setEditalTipo(String editalTipo) {
        this.editalTipo = editalTipo;
    }

    public EditalFacadeRemote getEditalFacade() {
        return editalFacade;
    }

    public void setEditalFacade(EditalFacadeRemote editalFacade) {
        this.editalFacade = editalFacade;
    }

    public String getEditalCnpjComprador() {
        return editalCnpjComprador;
    }

    public void setEditalCnpjComprador(String editalCnpjComprador) {
        this.editalCnpjComprador = editalCnpjComprador;
    }

    public Date getEditalDataAbertura() {
        return editalDataAbertura;
    }

    public void setEditalDataAbertura(Date editalDataAbertura) {
        this.editalDataAbertura = editalDataAbertura;
    }

    public String getEditalEmail() {
        return editalEmail;
    }

    public void setEditalEmail(String editalEmail) {
        this.editalEmail = editalEmail;
    }

    public String getEditalTelefoneEntregaDdd() {
        return editalTelefoneEntregaDdd;
    }

    public void setEditalTelefoneEntregaDdd(String editalTelefoneEntregaDdd) {
        this.editalTelefoneEntregaDdd = editalTelefoneEntregaDdd;
    }

    public String getEditalTelefoneEntregaNumero() {
        return editalTelefoneEntregaNumero;
    }

    public void setEditalTelefoneEntregaNumero(String editalTelefoneEntregaNumero) {
        this.editalTelefoneEntregaNumero = editalTelefoneEntregaNumero;
    }

    public String getEditalTelefoneCobrancaDdd() {
        return editalTelefoneCobrancaDdd;
    }

    public void setEditalTelefoneCobrancaDdd(String editalTelefoneCobrancaDdd) {
        this.editalTelefoneCobrancaDdd = editalTelefoneCobrancaDdd;
    }

    public String getEditalTelefoneCobrancaNumero() {
        return editalTelefoneCobrancaNumero;
    }

    public void setEditalTelefoneCobrancaNumero(String editalTelefoneCobrancaNumero) {
        this.editalTelefoneCobrancaNumero = editalTelefoneCobrancaNumero;
    }

    public String getEditalEnderecoEntregaEndereco() {
        return editalEnderecoEntregaEndereco;
    }

    public void setEditalEnderecoEntregaEndereco(String editalEnderecoEntregaEndereco) {
        this.editalEnderecoEntregaEndereco = editalEnderecoEntregaEndereco;
    }

    public String getEditalEnderecoEntregaCidade() {
        return editalEnderecoEntregaCidade;
    }

    public void setEditalEnderecoEntregaCidade(String editalEnderecoEntregaCidade) {
        this.editalEnderecoEntregaCidade = editalEnderecoEntregaCidade;
    }

    public String getEditalEnderecoEntregaUf() {
        return editalEnderecoEntregaUf;
    }

    public void setEditalEnderecoEntregaUf(String editalEnderecoEntregaUf) {
        this.editalEnderecoEntregaUf = editalEnderecoEntregaUf;
    }

    public String getEditalEnderecoEntregaCep() {
        return editalEnderecoEntregaCep;
    }

    public void setEditalEnderecoEntregaCep(String editalEnderecoEntregaCep) {
        this.editalEnderecoEntregaCep = editalEnderecoEntregaCep;
    }

    public String getEditalEnderecoEnvioDocumentacaoEndereco() {
        return editalEnderecoEnvioDocumentacaoEndereco;
    }

    public void setEditalEnderecoEnvioDocumentacaoEndereco(String editalEnderecoEnvioDocumentacaoEndereco) {
        this.editalEnderecoEnvioDocumentacaoEndereco = editalEnderecoEnvioDocumentacaoEndereco;
    }

    public String getEditalEnderecoEnvioDocumentacaoCidade() {
        return editalEnderecoEnvioDocumentacaoCidade;
    }

    public void setEditalEnderecoEnvioDocumentacaoCidade(String editalEnderecoEnvioDocumentacaoCidade) {
        this.editalEnderecoEnvioDocumentacaoCidade = editalEnderecoEnvioDocumentacaoCidade;
    }

    public String getEditalEnderecoEnvioDocumentacaoUf() {
        return editalEnderecoEnvioDocumentacaoUf;
    }

    public void setEditalEnderecoEnvioDocumentacaoUf(String editalEnderecoEnvioDocumentacaoUf) {
        this.editalEnderecoEnvioDocumentacaoUf = editalEnderecoEnvioDocumentacaoUf;
    }

    public String getEditalEnderecoEnvioDocumentacaoCep() {
        return editalEnderecoEnvioDocumentacaoCep;
    }

    public void setEditalEnderecoEnvioDocumentacaoCep(String editalEnderecoEnvioDocumentacaoCep) {
        this.editalEnderecoEnvioDocumentacaoCep = editalEnderecoEnvioDocumentacaoCep;
    }

    public Comprador getComprador() {
        return compradorFacade.find(this.editalCompradorId);
    }

    /**
     * Ajax action - requires editalCompradorId, editalTipo, editalSubTipo
     *
     * @param event
     */
    public void criarEditalTemp(AjaxBehaviorEvent event) {
        if (this.editalCompradorId != null && this.editalTipo != null) {
            this.editalId = 0; // transient
            try {
                if (this.editalTipo.equals("PE")) {
                    PregaoEletronico edital = PregaoEletronico.class.newInstance();
                    edital.setTipo(PregaoEletronico.Tipo.SRP); ///////// TEMPORARY
                    edital.setComprador(getComprador());
                    this.currentEdital = edital;
                } else if (this.editalTipo.equals("DL")) {
                    DispensaLicitacao edital = DispensaLicitacao.class.newInstance();
                    edital.setTipo(this.tipoDL);
                    edital.setComprador(getComprador());
                    this.currentEdital = edital;
                } else {
                    assert false : "The only supported types are 'DL' and 'PE'";
                }
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(CurrentEditalManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Edital getCurrentEdital() {
        return currentEdital;
    }

    public void setCurrentEdital(Edital currentEdital) {
        this.currentEdital = currentEdital;
    }

    public void unsetCurrentEdital() {
        this.currentEdital = null;
        editalCompradorId = null;
        editalTipo = null;
        tipoPE = null;
        tipoDL = null;
    }

    public String getTipoPE() {
        return tipoPE;
    }

    public void setTipoPE(String tipoPE) {
        this.tipoPE = tipoPE;
    }

    public DispensaLicitacao.Tipo getTipoDL() {
        return tipoDL;
    }

    public void setTipoDL(DispensaLicitacao.Tipo tipoDL) {
        this.tipoDL = tipoDL;
    }

    public boolean setCurrentEdital() {
        try {
            Edital edital = editalFacade.find(editalId);

            try {
                //System.out.println(edital.getProdutos().size()); // test for lazy initialization
                //System.out.println(edital.getIhs().size()); // test for lazy initialization
            } catch (Exception ex) {
                Logger.getLogger(CurrentEditalManagedBean.class.getName()).log(Level.SEVERE, "Framework error: must pre-fetch collections in select Edital", ex);
            }

            this.setCurrentEdital(edital);
            return edital != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    public String criarEdital() throws ParseException {
        if (this.editalNumero != null) {
            this.currentEdital.setNumeroEdital(new Codigo(this.editalNumero));
            this.currentEdital.setNumeroEditalComprador(new Codigo(this.editalNumeroEditalComprador));
            this.currentEdital.setCnpjComprador(new CNPJ(this.editalCnpjComprador));
            this.currentEdital.setDataAbertura(this.editalDataAbertura.getTime());
            this.currentEdital.setEmail(new Email(this.editalEmail));
            this.currentEdital.setTelefoneEntrega(new Telefone(this.editalTelefoneEntregaDdd, this.editalTelefoneEntregaNumero));
            this.currentEdital.setTelefoneCobranca(new Telefone(this.editalTelefoneCobrancaDdd, this.editalTelefoneCobrancaNumero));
            this.currentEdital.setEnderecoEntrega(new Endereco(this.editalEnderecoEntregaEndereco, this.editalEnderecoEntregaCidade, this.editalEnderecoEntregaUf, this.editalEnderecoEntregaCep));
            this.currentEdital.setEnderecoEnvioDocumentacao(new Endereco(this.editalEnderecoEnvioDocumentacaoEndereco, this.editalEnderecoEnvioDocumentacaoCidade, this.editalEnderecoEnvioDocumentacaoUf, this.editalEnderecoEnvioDocumentacaoCep));
            editalFacade.create(this.currentEdital);
        }
        this.unsetCurrentEdital();
        return "editais";
    }

    public String atualizar() {
        editalFacade.edit(this.currentEdital);
        return "editais";
    }

    public String remover() {
        if (this.setCurrentEdital()) {
            //currentEdital.getProdutos().clear();
            //currentEdital.getIhs().clear();
            editalFacade.remove(this.currentEdital);
            this.unsetCurrentEdital();
        }
        return "editais";
    }
}
