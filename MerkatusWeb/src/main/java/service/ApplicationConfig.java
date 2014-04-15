/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author helderdarocha
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(service.AmostraFacadeREST.class);
        resources.add(service.ConcursoBBFacadeREST.class);
        resources.add(service.ConcursoBECFacadeREST.class);
        resources.add(service.ConcursoComprasNetFacadeREST.class);
        resources.add(service.ConcursoFacadeREST.class);
        resources.add(service.DocumentoFacadeREST.class);
        resources.add(service.EditalDispensaLicitacaoFacadeREST.class);
        resources.add(service.EditalFacadeREST.class);
        resources.add(service.EditalPregaoEletronicoFacadeREST.class);
        resources.add(service.HabilitacaoFacadeREST.class);
        resources.add(service.ProdutoFacadeREST.class);
    }
    
}
