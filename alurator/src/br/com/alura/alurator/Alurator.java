/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.alurator;

import br.com.alura.alurator.conversor.ConversorXML;
import br.com.alura.alurator.ioc.ContainerIoc;
import br.com.alura.alurator.reflexao.ManipuladorObjeto;
import br.com.alura.alurator.reflexao.Reflexao;
import br.com.alura.alurator.protocolo.Request;

import java.util.Map;

/**
 *
 * @author marcosparreira
 */
public class Alurator {

    private String pacoteBase;
    private ContainerIoc container;

    public Alurator(String pacoteBase) {
        this.pacoteBase = pacoteBase;
        this.container = new ContainerIoc();
    }

    public Object executa(String url) {

        Request request = new Request(url);
        
        String nomeMetodo = request.getNomeMetodo();
        String nomeControle = request.getNomeControle();
        Map<String, Object> params = request.getQueryParams();
        
        Class<?> classeControle = new Reflexao().getClasse( pacoteBase + nomeControle);
        Object instanciaControle = container.getInstancia(classeControle);        
        Object retorno = new ManipuladorObjeto(instanciaControle)
                .getMetodo(nomeMetodo,params)
                .invoca();

//        obj.metodo()
        System.out.println(retorno);
        
        retorno = new ConversorXML().converte(retorno);
        
        return retorno;

    }
}
