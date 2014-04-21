/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.alicit.web;

import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import br.com.argonavis.merkatus.alicit.edital.Codigo;
import br.com.argonavis.merkatus.alicit.edital.DispensaLicitacao;
import br.com.argonavis.merkatus.alicit.edital.Edital;
import br.com.argonavis.merkatus.alicit.edital.PregaoEletronico;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.CompradorFacadeRemote;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.DispensaLicitacaoFacadeRemote;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.EditalFacadeRemote;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.PregaoEletronicoFacadeRemote;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author helderdarocha
 */
@Named
//@ConversationScoped
@SessionScoped
public class EditalManagedBean implements Serializable {

    @EJB
    EditalFacadeRemote editalFacade;
    @EJB
    PregaoEletronicoFacadeRemote peFacade;
    @EJB
    DispensaLicitacaoFacadeRemote dlFacade;
    @EJB
    CompradorFacadeRemote compradorFacade;

    //@Inject
    //private Conversation conversation;
    
    List<Edital> editaisFiltrados;

    private String idComprador;
    private boolean showCadastrarEditalPanel = false;
    private String numeroEdital;

    private String nomeDisplay;
    private Date dataAbertura;
    private PregaoEletronico.Tipo tipoPE;
    private DispensaLicitacao.Tipo tipoDL;

    private String tipoEdital;
    private static final Map<String, String> tipoEditaisMap = new HashMap<>();

    static {
        tipoEditaisMap.put("Pregão Eletrônico", "PE");
        tipoEditaisMap.put("Dispensa de Licitação", "DL");
    }
/*
    @PostConstruct
    public void init() {
        conversation.begin();
    }

    public String getConversationId() {
        return conversation.getId();
    }
*/
    
    public void resetSession() {
        idComprador = numeroEdital = nomeDisplay = tipoEdital = null;
        dataAbertura = null;
        tipoPE = null;
        tipoDL = null;
        this.showCadastrarEditalPanel = false;
    }

    public List<Edital> getEditaisFiltrados() {
        return editaisFiltrados;
    }

    public void setEditaisFiltrados(List<Edital> editaisFiltrados) {
        this.editaisFiltrados = editaisFiltrados;
    }

    public boolean isShowCadastrarEditalPanel() {
        return showCadastrarEditalPanel;
    }

    public void setShowCadastrarEditalPanel(boolean showCadastrarEditalPanel) {
        this.showCadastrarEditalPanel = showCadastrarEditalPanel;
    }

    public Map<String, String> getTipoEditaisMap() {
        return tipoEditaisMap;
    }

    public String getTipoEdital() {
        return tipoEdital;
    }

    public void setTipoEdital(String tipoEdital) {
        this.tipoEdital = tipoEdital;
    }

    public String getNomeDisplay() {
        return nomeDisplay;
    }

    public void setNomeDisplay(String nomeDisplay) {
        this.nomeDisplay = nomeDisplay;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public void setIdComprador(String idComprador) {
        this.idComprador = idComprador;
    }

    public String getIdComprador() {
        return idComprador;
    }

    public String getNumeroEdital() {
        return numeroEdital;
    }

    public void setNumeroEdital(String numeroEdital) {
        this.numeroEdital = numeroEdital;
    }

    public PregaoEletronico.Tipo getTipoPE() {
        return tipoPE;
    }

    public void setTipoPE(PregaoEletronico.Tipo tipoPE) {
        this.tipoPE = tipoPE;
    }

    public DispensaLicitacao.Tipo getTipoDL() {
        return tipoDL;
    }

    public void setTipoDL(DispensaLicitacao.Tipo tipoDL) {
        this.tipoDL = tipoDL;
    }

    public Comprador getComprador() {
        return compradorFacade.querySingle("select c from Comprador c where c.identificador = '" + idComprador + "'");
    }

    public List<Edital> getEditais() {
        return editalFacade.findAll();
    }

    public List<PregaoEletronico> getPregoesEletronicos() {
        return peFacade.findAll();
    }

    public List<DispensaLicitacao> getDispensasLicitacao() {
        return dlFacade.findAll();
    }

    public List<Edital> getEditaisbyComprador() {
        return editalFacade.queryList("select e from Edital e where e.comprador.identificador = '" + idComprador + "'");
    }

    public int getCountEditais() {
        return editalFacade.count();
    }

    public String criarPregaoEletronico() {
        // validate data
        Edital edital = new PregaoEletronico(getComprador(), tipoPE, new Codigo(numeroEdital));
        return criarEdital(edital);
    }

    public String criarDispensaLicitacao() {
        // validate data
        Edital edital = new DispensaLicitacao(getComprador(), tipoDL, new Codigo(numeroEdital));
        return criarEdital(edital);
    }
    
    private String criarEdital(Edital edital) {
        edital.setNomeDisplay(nomeDisplay);
        editalFacade.create(edital);
        resetSession();
        //conversation.end();
        return "editais";
    }
    
    public String showCadastrarEditalPanel() {
        this.showCadastrarEditalPanel = true;
        return null;
    }
    
    public String hideCadastrarEditalPanel() {
        resetSession();
        return null;
    }
    
    public String editarEdital() {
        // validate data
        return "editarEdital?acao=editar";
    }
    
    public String exbirEdital() {
        // validate data
        return "editarEdital?acao=exibir";
    }
    
    public Collection<String> getTiposEditalValues() {
        return tipoEditaisMap.values();
    }
}
