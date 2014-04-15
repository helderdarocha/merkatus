/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.entity.licitacao.orgao;

import br.com.argonavis.merkatus.entity.licitacao.Concurso;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author helderdarocha
 */
@Entity
@XmlRootElement
public class ConcursoComprasNet extends Concurso {
    
    private String uasg;

    public ConcursoComprasNet() {}
    public ConcursoComprasNet(String nome) {
        super(nome);
    }

}
