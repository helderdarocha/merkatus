/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.merkatus.alicit.produto;

/**
 *
 * @author helderdarocha
 */
public enum CategoriaBase {

    COMPONENTES("Componentes"),
    NOTEBOOKS_TABLETS("Notebooks e tablets"),
    COMPUTADORES("Computadores"),
    SERVIDORES("Servidores"),
    NETWORKING("Networking"),
    ARMAZENAMENTO("Armazenamento"),
    ENERGIA("Energia"),
    SEGURANCA("Segurança"),
    AUTOMACAO_AIDC("Automação e AIDC"),
    TELEFONES_CELULARES("Telefones e celulares"),
    AUDIO_VIDEO("Áudio e vídeo"),
    IMAGEM_IMPRESSAO("Imagem e impressão"),
    SOFTWARE("Software"),
    GAMES("Games"),
    ACESSORIOS("Acessórios");
    
    private String descricao;
    private Categoria categoria;

    CategoriaBase(String descricao) {
        this.descricao = descricao;
    }

    public String toString() {
        return descricao;
    }
    public Categoria getCategoria() {
        return new Categoria(descricao);
    }
}
