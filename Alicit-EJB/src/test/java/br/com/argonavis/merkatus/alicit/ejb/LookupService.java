/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb;

import br.com.argonavis.merkatus.alicit.ejb.facade.AbstractFacadeInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author helderdarocha
 * @param <T>
 */
public class LookupService<T extends AbstractFacadeInterface> {
    
    private static final String ejbModuleName = "Alicit-EJB";
    
    public T lookupBean(Class bean, Class beanInterface) {
        String lookupString = 
                "java:global/"+ejbModuleName+"/"+bean.getSimpleName()+"!"+beanInterface.getCanonicalName();
        System.out.println("Lookup String: " + lookupString);
        try {
            Context c = new InitialContext();
            return (T) c.lookup(lookupString);
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
