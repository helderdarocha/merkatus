/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.alicit.web.setup;

//import br.com.argonavis.merkatus.alicit.comprador.Comprador;
//import br.com.argonavis.merkatus.alicit.ejb.facade.CompradorFacade;
import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import br.com.argonavis.merkatus.alicit.ejb.facade.remote.CompradorFacadeRemote;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author helderdarocha
 */
@Named
@SessionScoped
public class SetupManagedBean implements Serializable {

    @EJB
    CompradorFacadeRemote facade;

    private boolean installed = false;

    public String setup() { 
        if (!this.installed) {
            try {
                facade.create(Comprador.createCompradorBB());
            } catch (EJBException e) {
                Logger.getGlobal().fine(e.getMessage());
            }
            try {
                facade.create(Comprador.createCompradorBEC());
            } catch (EJBException e) {
                Logger.getGlobal().fine(e.getMessage());
            }
            try {
                facade.create(Comprador.createCompradorComprasNet());
            } catch (EJBException e) {
                Logger.getGlobal().fine(e.getMessage());
            }
            checkInstallation();
        } 
        return "setup";
    }

    public String reset() {
        if (this.installed) {
            try {
                throw new EJBException("Desinstalação não foi implementada!");
            } catch (EJBException e) {
                Logger.getGlobal().fine(e.getMessage());
            }
        }
        return "setup";
    }

    
    public void checkInstallation() {
        try {
            Comprador t1 = facade.querySingle("select c from Comprador c where c.identificador = 'BB'");
            Comprador t2 = facade.querySingle("select c from Comprador c where c.identificador = 'BEC'");
            Comprador t3 = facade.querySingle("select c from Comprador c where c.identificador = 'COMPRASNET'");
            this.installed = t1 != null && t2 != null && t3 != null;
        } catch(EJBException e) {
            this.installed = false;
        }
    }

    public boolean isInstalled() {
        return installed;
    }
    
}
