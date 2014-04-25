/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.alicit.web;

import br.com.argonavis.merkatus.alicit.ejb.facade.remote.TagFacadeRemote;
import br.com.argonavis.merkatus.alicit.produto.Tag;
import java.io.Serializable;
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
@SessionScoped
public class TagManagedBean implements Serializable {
    @EJB TagFacadeRemote tagFacade;
    
    private boolean showCadastrarTagPanel = false;
    private String nomeTag;
    
    private List<Tag> tags;
    Map<String, String> tagsMap;
    
    public List<Tag> getTags() {
        if (tags == null) {
            tags = tagFacade.findAll();
        }
        return tags;
    }
    
    public Map<String, String> getTagsMap() {
        if (tagsMap == null) {
            tagsMap = new HashMap<>();
            for(Tag c: getTags()) {
                tagsMap.put(c.getNome(), c.getNome());
            }
        }
        return tagsMap;
    }
    
        
    public void resetSession() {
        this.showCadastrarTagPanel = false;
    }

    public boolean isShowCadastrarTagPanel() {
        return showCadastrarTagPanel;
    }

    public void setShowCadastrarTagPanel(boolean showCadastrarTagPanel) {
        this.showCadastrarTagPanel = showCadastrarTagPanel;
    }

    public String getNomeTag() {
        return nomeTag;
    }

    public void setNomeTag(String nomeTag) {
        this.nomeTag = nomeTag;
    }
    
    public String showCadastrarTagPanel() {
        this.showCadastrarTagPanel = true;
        return null;
    }
    
    public String hideCadastrarTagPanel() {
        resetSession();
        return null;
    }
    
    public String criar() {
        Tag tag = new Tag(this.nomeTag);
        tagFacade.create(tag);
        resetSession();
        return "tags";
    }
}
