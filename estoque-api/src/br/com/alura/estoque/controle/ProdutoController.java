/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.estoque.controle;

import br.com.alura.estoque.dao.ProdutoDaoMock;
import br.com.alura.estoque.modelo.Produto;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author marcosparreira
 */
public class ProdutoController {

    private ProdutoDaoMock produtoDao;

    public ProdutoController(ProdutoDaoMock produtoDao) {
        this.produtoDao = produtoDao;
    }

    public List<Produto> lista() {
        return produtoDao.lista();
    }

    public List<Produto> filtra(String nome) {
        return produtoDao.lista().stream()
                .filter(produto -> produto.getNome().toLowerCase().startsWith(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Produto> filtra(String nome, String marca) {
        return produtoDao.lista().stream()
                .filter(produto
                        -> produto.getNome().toLowerCase().startsWith(nome.toLowerCase())
                && produto.getMarca().equalsIgnoreCase(marca)
                )
                .collect(Collectors.toList());
    }
}
