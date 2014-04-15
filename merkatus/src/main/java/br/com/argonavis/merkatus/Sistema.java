/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus;

import br.com.argonavis.merkatus.entity.licitacao.Concurso;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author helderdarocha
 */
public class Sistema {
    private List<Concurso> concursos = new ArrayList<>();
    public void cadastrarConcurso(Concurso concurso) {
        this.concursos.add(concurso);
    }
}
