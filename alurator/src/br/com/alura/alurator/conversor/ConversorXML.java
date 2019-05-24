    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.alurator.conversor;

import br.com.alura.alurator.convesor.anotacao.NomeTagXML;
import java.lang.reflect.Field;
import java.util.Collection;

/**
 *
 * @author marcosparreira
 */
public class ConversorXML {

    public ConversorXML() {
    }

    public Object converte(Object objeto) {
        try {
            Class<?> classeObjeto = objeto.getClass(); //pegando objeto da classe
            
            StringBuilder xmlBuilder = new StringBuilder();

            if (objeto instanceof Collection) { //verificando objeto e instancia de Collection, esse medoto serve quando for passado uma lista  ele consiga converter pra xml
                Collection<?> colecao = (Collection<?>) objeto;

                xmlBuilder.append("<lista>");

                for (Object o : colecao) {
                    String xml = (String) converte(o);
                    xmlBuilder.append(xml);
                }

                xmlBuilder.append("</lista>");

            } else {
                // Verificando se nossa classe em questao esta usando usando tagXML, se tiver ira vim representando a nossa tag ussada na anotacao, se nao ira retornar null
                NomeTagXML anotacaoClasse = classeObjeto.getDeclaredAnnotation(NomeTagXML.class);
                //aqui verifico retorno se anotacao vier null(quer dizer q minha classe nao representa nenhuma anotacao xml), no caso retornatia full qualyfydy name da classe,
                String nomeClasse = 
                        anotacaoClasse == null
                        ? classeObjeto.getName()
                        : anotacaoClasse.value();//se tudo estiver representado serto na classe retorna xml da classe

                xmlBuilder.append("<" + nomeClasse + ">");

                for (Field atributo : classeObjeto.getDeclaredFields()) {
                    atributo.setAccessible(true);
                    //declarando minha anotacao agora os atributos, se a classe fizer parte das anotaçoes
                    NomeTagXML anotacaoAtributo = atributo.getDeclaredAnnotation(NomeTagXML.class);

                    String nomeAtributo = 
                            anotacaoAtributo == null
                            ? atributo.getName()
                            : anotacaoAtributo.value();
                    
                    Object valorAtributo = atributo.get(objeto);  
                    
                    xmlBuilder.append("<" + nomeAtributo + ">");
                    xmlBuilder.append(valorAtributo);
                    xmlBuilder.append("</" + nomeAtributo + ">");


                }

                xmlBuilder.append("</" + nomeClasse + ">");
            }
            
            return xmlBuilder.toString();

        } catch (IllegalArgumentException | IllegalAccessException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro na geração do XML");
        }
    }

    /* pra quem passa lista no metodo converte exemplo que pode passar objeto ou lista
   <lista>
        <produto>
         <nome> Produto 1 </nome>
         <valor> 20.0 </valor>
         <marca>Marca 1 </marca>
        </produto>
    </lista>

        <produto>
         <nome> Produto 2 </nome>
         <valor> 20.0 </valor>
         <marca>Marca 2 </marca>
        </produto>
    
     */
}
