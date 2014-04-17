/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit;

import br.com.argonavis.merkatus.alicit.comprador.Comprador;
import br.com.argonavis.merkatus.alicit.edital.Edital;
import java.util.Set;

/**
 *
 * @author helderdarocha
 */
public interface Sistema {
    Set<Comprador> getCompradores();
    void addComprador(Comprador comprador);
    void setCompradores(Set<Comprador> compradores);
    Set<Edital> getEditais();
    void addEdital(Edital edital);
    void setEditais(Set<Edital> editais);
}
