/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.proposta;

import java.io.Serializable;

/**
 *
 * @author helderdarocha
 */
public class ResultadoLicitacao implements Serializable {
    private int posicao;

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
    
}
