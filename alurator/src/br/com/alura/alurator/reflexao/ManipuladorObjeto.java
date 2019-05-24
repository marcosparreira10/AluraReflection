/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.alurator.reflexao;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Stream;

/**
 *
 * @author marcosparreira
 */
public class ManipuladorObjeto {

    private final Object instancia;

    public ManipuladorObjeto(Object instancia) {
        this.instancia = instancia;

    }

    public ManipuladorMetodo getMetodo(String nomeMetodo, Map<String, Object> params) {

        //1) Pegar todos os metodos da classe.
        //2) Filtrar todos os metodos de modo que.
        //2.1) Tenha o mesmo nome informado pelo usuario
        //2.2) Tenham a mesma quantidade de parametros passados na URL;
        //2.3) E que cada um dos parametros tenha os mesmos nome e tipos iguais aos passados na URL
        //3) Lança uma RuntimeException caso nenhum metodo seja encontrado
        /* 
            /filtra?nome=produto => filtra (String nome)
            /filtra?nome=produto&marca=marca 1 => filtra(String nome, String marca)
            /filtra?marca=marca 1&nome=produto => filtra(String nome, String marca)
            /filtra?batman=marca 1&nome=produto => filtra(String nome, String batman)
         */
        Stream<Method> metodos = Stream.of(instancia.getClass().getDeclaredMethods());
        //classe Stream para filtrar metodo ela usa lampda como passagem metodo
        //dentro do metodo filter instancia "metodo" dentro para manipular
        Method metodoSelecionado = metodos.filter(metodo ->
                metodo.getName().equals(nomeMetodo) //comprara metodo.getName()(traz nome do campo) com campo passado na URL nomeMetodo
                && metodo.getParameterCount() == params.values().size() //metodo.getParameterCount conta quantos parametro tem metodo, ja params.value.size  conta quantos parametros foi passado pela url
                && Stream.of(metodo.getParameters()) //todos parametros metodo
                        .allMatch(arg  ->//arg vai percorrer por todos parametros //allMatch passa por todos parametros da stream
                           params.keySet().contains(arg.getName()) //keyset retorna a chave do mapa especifica pega conjunto de mapas compara arg, arg q e nome campo
                        && params.get(arg.getName()).getClass().equals(arg.getType()) //params.get valor pasado pelo usuario do mapa - getClass() pega a classe - comparo pelo tipo que ele aceita
                        )
        )
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Metodo nao encontrado"));

    
        return new ManipuladorMetodo(instancia, metodoSelecionado, params);

    }

}
