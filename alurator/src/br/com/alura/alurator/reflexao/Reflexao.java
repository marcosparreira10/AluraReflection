/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.alurator.reflexao;

/**
 *
 * @author marcosparreira
 */
public class Reflexao {

    public Reflexao() {
    }

    public ManipuladorClasse refleteClasse(String fqn) {
        try {
            Class<?> classe = Class.forName(fqn);
            return new ManipuladorClasse(classe);

        } catch (ClassNotFoundException | SecurityException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

}
