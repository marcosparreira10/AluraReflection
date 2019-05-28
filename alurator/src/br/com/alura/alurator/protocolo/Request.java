/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.alurator.protocolo;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author marcosparreira
 */
public class Request {

    private String nomeControle;
    private String nomeMetodo;
    private Map<String, Object> queryParams;


    public Request(String url) {
        /*
            * Casos possiveis
            * /controlador/Metodo
            * /controlador/metodo?para1=valor1&param2=valor2
        
            * /controlador/metodo para1=valor1&param2=valor2
        */
        //tirando a primeira barra com replaceFirst... quebrando split fica 2 vetores
        String[] partesUrl = url.replaceFirst("/", "")
                .split("[?]");
        
        String[] controleEMetodo = partesUrl[0].split("/");
        
        //aqui estou pegando primeiro caracter da palavra digitada e colocando ela no maiuscula com Character.toUpperCase
        //logo apois com substring coloco restante da palavra apartir do vetor 1 que no caso tira p minusculo para comeca a palavra no roduto, mais so com P quue ficou maiusculo juntando da palavra ProdutoController
        
        nomeControle = Character.toUpperCase(controleEMetodo[0].charAt(0))
                + controleEMetodo[0].substring(1) + "Controller";
        
        //pegando nome do metodo a ser usado, segunda posicao no arrays
        nomeMetodo = controleEMetodo[1];
        
        //condicao ternaria se partesURL for mair q 1, 
        queryParams = partesUrl.length > 1 //queryParams manipula String valor mais objeto
                ? new QueryParamsBuilder().comParametros(partesUrl[1]).build()
                : new HashMap<String, Object>();
    }
    
    public String getNomeControle(){
        return nomeControle;
    }
    
    public String getNomeMetodo(){
        return nomeMetodo;
    }
    
    public Map<String, Object> getQueryParams(){
        return queryParams;
    }
}
