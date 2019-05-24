/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.alurator.reflexao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 *
 * @author marcosparreira
 */
public class ManipuladorMetodo {

    private final Object instancia;
    private final Method metodo;
    private final Map<String, Object> params;

    public ManipuladorMetodo(Object instancia, Method metodo, Map<String, Object> params) {
        this.instancia = instancia;
        this.metodo = metodo;
        this.params = params;
    }
    
    public Object invoca(){
        try {     
            List<Object> parametros = new ArrayList<>();
            Stream.of(metodo.getParameters())
                    .forEach(p -> parametros.add(params.get(p.getName()))); //isso vai la no mapa e pegar valor exato do mapa
            
            return metodo.invoke(instancia, parametros.toArray()); //toArray converte a lista em arrays
        } catch (IllegalAccessException | IllegalArgumentException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } catch (InvocationTargetException ex){
            ex.printStackTrace();
            throw new RuntimeException("Erro dentro do método!",ex);
        }
                
    }
    
}
