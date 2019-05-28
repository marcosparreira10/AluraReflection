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
    	
            Class<?> classe = getClasse(fqn);
            
            return new ManipuladorClasse(classe);
    }

	public Class<?> getClasse(String fqn) {
		 try {
	            Class<?> classe = Class.forName(fqn);
	            return classe;

	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            throw new RuntimeException();
	        }
	}

}
