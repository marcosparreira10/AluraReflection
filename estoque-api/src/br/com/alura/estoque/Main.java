
package br.com.alura.estoque;

import br.com.alura.alurator.Alurator;
import java.util.Scanner;

/**
 *
 * @author marcosparreira
 */
public class Main {

    /**
     * Simula o navegador.
     * 2 aplicacao rodando Alurator e estoque-api
     */
    
    public static void main(String[] args) throws Exception {

        /*
		 * Casos possiveis:
		 * /controlador/metodo
		 * /controlador/metodo?param1=valor1&param2=valor2
                 
                 * /produto/filtra?nome=produto
                 
                 * /produto/filtra?nome=produto&marca=marca 1
                 * /produto/filtra?marca=marca 1&nome=produto
         */
        try (Scanner s = new Scanner(System.in)) {
            String url = s.nextLine();
            //pacote base da aplicacao
            Alurator alurator = new Alurator("br.com.alura.estoque.controle.");
            while (!url.equals("exit")) {
                Object response = alurator.executa(url);

                System.out.println("Response: " + response);

                url = s.nextLine();
            }
        }
    }
}
