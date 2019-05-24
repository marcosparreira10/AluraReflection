/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.alurator;

import br.com.alura.alurator.conversor.ConversorXML;
import br.com.alura.alurator.reflexao.Reflexao;
import br.com.alura.alurator.protocolo.Request;
import java.util.Map;

/**
 *
 * @author marcosparreira
 */
public class Alurator {

    private String pacoteBase;

    public Alurator(String pacoteBase) {
        this.pacoteBase = pacoteBase;
    }

    public Object executa(String url) {

        Request request = new Request(url);
        
        String nomeMetodo = request.getNomeMetodo();
        String nomeControle = request.getNomeControle();
        Map<String, Object> params = request.getQueryParams();

        Object retorno = new Reflexao()
                .refleteClasse(pacoteBase + nomeControle)
                .criaInstancia()
                .getMetodo(nomeMetodo,params)
                .invoca();

//        obj.metodo()
        System.out.println(retorno);
        
        retorno = new ConversorXML().converte(retorno);
        
        return retorno;

    }
}
