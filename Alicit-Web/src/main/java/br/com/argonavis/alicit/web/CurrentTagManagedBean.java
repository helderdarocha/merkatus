/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.alicit.web;

import br.com.argonavis.merkatus.alicit.ejb.facade.remote.TagFacadeRemote;
import br.com.argonavis.merkatus.alicit.produto.Tag;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;

/**
 *
 * @author helderdarocha
 */
@Named
@RequestScoped
public class CurrentTagManagedBean implements Serializable {
    @EJB TagFacadeRemote tagFacade;
    
    private Tag currentTag;
    private String tagNome;
 
    /**
     * Get current tag.
     * @return Current tag of this managed bean.
     */
    public Tag getCurrentTag() {
        return currentTag;
    }

    /**
     * Form setter. 
     * @param tagNome 
     */
    public void setTagNome(String tagNome) {
        this.tagNome = tagNome;
    }
    
    /**
     * Form getter.
     * @return 
     */
    public String getTagNome() {
        return tagNome;
    }
    
    /**
     * Sets tag as current tag of managed bean
     * @param currentTag The tag which will be the current tag.
     */
    public void setCurrentTag(Tag currentTag) {
        this.currentTag = currentTag;
    }
    
    /**
     * Makes current tag of this managed bean equal to null.
     */
    public void unsetCurrentTag() {
        this.currentTag = null;
    }
    
    /**
     * Sets current tag if not defined and if tagNome refers to an existing tag in the database.
     * @return false if no tag is set.
     */
    public boolean setCurrentTag() {
        System.out.println("Current: "  + currentTag);
        System.out.println("Tag Nome: " + this.tagNome);
        if (currentTag != null) {
            return true;
        } else if (this.tagNome != null) {
            try {
                Tag tag = tagFacade.getByNome(this.tagNome);
                this.setCurrentTag(tag);
                return true;
            } catch (NoResultException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Creates new tag.
     * @return navigation key
     */
    public String criar() {
        if (this.tagNome != null) {
            Tag tag = new Tag(this.tagNome);
            tagFacade.create(tag);
            //this.setCurrentTag(tagFacade.getByNome(this.tagNome));
        }
        return "tags";
    }
    
    /**
     * Removes current tag from database.
     * @return navigation key
     */
    public String remover() {
        // No need to confirm before removing individual tags
        if (this.setCurrentTag()) {
            tagFacade.remove(currentTag);
            this.unsetCurrentTag();
        }
        return "tags";
    }
}
