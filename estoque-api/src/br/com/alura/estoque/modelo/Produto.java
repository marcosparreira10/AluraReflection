/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.estoque.modelo;

import br.com.alura.estoque.anotacao.NomeTagXML;

/**
 *
 * @author marcosparreira
 */
@NomeTagXML("product")
public class Produto{
    @NomeTagXML("name")
    private String nome;
    @NomeTagXML("value")
    private double valor;
    @NomeTagXML("brand")
    private String marca;

    public Produto(String nome, double valor, String marca) {
        this.nome = nome;
        this.valor = valor;
        this.marca = marca;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Produto [nome=" + nome + ", valor=" + valor + ", marca=" + marca + "]";
    }

}
