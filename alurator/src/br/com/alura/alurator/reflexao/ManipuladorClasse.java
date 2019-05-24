/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.alurator.reflexao;

import java.lang.reflect.Constructor;

/**
 *
 * @author marcosparreira
 */
public class ManipuladorClasse {

    private final Class<?> classe;

public ManipuladorClasse(Class<?> classe) {
        this.classe = classe;
    }

    public ManipuladorConstrutor getConstrutorPadrao() {
        try {
            Constructor<?> construtorPadrao = classe.getDeclaredConstructor();
             return new ManipuladorConstrutor(construtorPadrao);
                
        } catch ( SecurityException | NoSuchMethodException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public ManipuladorObjeto criaInstancia() {
        Object instancia = getConstrutorPadrao().invoca();
        
        return new ManipuladorObjeto(instancia);
    }
    
}
