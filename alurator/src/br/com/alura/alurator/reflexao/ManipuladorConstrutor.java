/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.alurator.reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author marcosparreira
 */
public class ManipuladorConstrutor {

    private final Constructor<?> construtor;

   public ManipuladorConstrutor(Constructor<?> construtor) {
        this.construtor = construtor;
    }

    public Object invoca() {
        try {
            return construtor.newInstance();
            
        } catch ( IllegalArgumentException | 
                IllegalAccessException | InstantiationException ex) {
            ex.printStackTrace();
            return new RuntimeException(ex);
            
        } catch(InvocationTargetException ex){
            ex.printStackTrace();
            return new RuntimeException("Erro no construtor!",ex.getTargetException());
        }
    }

}