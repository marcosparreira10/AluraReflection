/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.alurator.convesor.anotacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author marcosparreira
 */
@Retention(RetentionPolicy.RUNTIME)//qual tempo ira executar essa anotacion no caso irei passar configuracao em timpo de execucao
@Target({ElementType.TYPE, ElementType.FIELD})//passar onde era ira funcionar em ... em um metodo ou em um atributo, num parametro.... no caso iremos passar um arrays que iremos usar mais de um lugar... no caso Element.type podemos usar no lugar de uma declaracao de uma classe e ElementField em um atributo qualquer
public @interface NomeTagXML {
    
    public String value(); // conversao se tiver apenas um atributo, para nao ter q passar value na classe, vc usa value, que se nao teria q igualar esse tributo ao q estava passando value = "", na classe onde ira usar
}
