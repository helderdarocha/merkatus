/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.merkatus.alicit.ejb.facade;

import java.util.List;

/**
 *
 * @author helderdarocha
 */
public interface AbstractFacadeInterface<T> {
    
    void create(T entity);
    T edit(T object);
    void remove(T object);
    T find(Object id);
    T querySingle(String q);
    List<T> queryList(String q);
    List<T> findAll();
    List<T> findRange(int[] range);
    int count();
    
}
