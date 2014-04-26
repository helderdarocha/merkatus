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
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author helderdarocha
 */
@Named
@RequestScoped
public class TagManagedBean implements Serializable {

    @EJB
    TagFacadeRemote tagFacade;
    @Inject
    CurrentTagManagedBean currentTagManagedBean;

    Map<String, String> tagsMap;

    private String currentTagNome;

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

    public String getCurrentTagNome() {
        return currentTagNome;
    }

    public void setCurrentTagNome(String currentTagNome) {
        this.currentTagNome = currentTagNome;
    }

    public String exibirTag() {
        currentTagManagedBean.setTagNome(currentTagNome);
        currentTagManagedBean.setCurrentTag();
        return "tagsExibir";
    }

    public String cadastrarNovaTag() {
        currentTagManagedBean.setTagNome(null);
        currentTagManagedBean.unsetCurrentTag();
        return "tagsCriar";
    }

    public String editarTag() {
        currentTagManagedBean.setTagNome(currentTagNome);
        currentTagManagedBean.setCurrentTag();
        return "tagsEditar";
    }

}
