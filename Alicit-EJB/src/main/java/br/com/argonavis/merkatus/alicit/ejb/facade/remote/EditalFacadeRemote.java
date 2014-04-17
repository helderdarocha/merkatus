/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade.remote;

import br.com.argonavis.merkatus.alicit.edital.Edital;
import br.com.argonavis.merkatus.alicit.ejb.facade.AbstractFacadeInterface;
import javax.ejb.Remote;

/**
 *
 * @author helderdarocha
 */
@Remote
public interface EditalFacadeRemote extends AbstractFacadeInterface<Edital> {}