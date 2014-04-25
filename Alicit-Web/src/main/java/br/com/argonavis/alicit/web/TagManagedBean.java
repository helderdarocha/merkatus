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
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author helderdarocha
 */
@Named
@SessionScoped
public class TagManagedBean implements Serializable {

    @EJB
    TagFacadeRemote tagFacade;
    @Inject
    CurrentTagManagedBean currentTagManagedBean;

    Map<String, String> tagsMap;

    public List<Tag> getTags() {
        return tagFacade.findAll();
    }

    public Map<String, String> getTagsMap() {
        if (tagsMap == null) {
            tagsMap = new HashMap<>();
        }
        tagsMap.clear();
        for (Tag c : getTags()) {
            tagsMap.put(c.getNome(), c.getNome());
        }
        return tagsMap;
    }
    
    public String exibirTag(String tagNome) {
        currentTagManagedBean.setTagNome(tagNome);
        currentTagManagedBean.setCurrentTag();
        return "tagsExibir";
    }

    public String cadastrarNovaTag() {
        currentTagManagedBean.setTagNome(null);
        currentTagManagedBean.unsetCurrentTag();
        return "tagsCriar";
    }

    public String editarTag(String tagNome) {
        currentTagManagedBean.setTagNome(tagNome);
        currentTagManagedBean.setCurrentTag();
        return "tagsEditar";
    }


}
