package br.com.cursomc.dto;

import br.com.cursomc.domain.Produto;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {

    private Integer id;
    private String nome;
    private Double preco;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto prod) {
        this.id = prod.getId();
        this.nome = prod.getNome();
        this.preco = prod.getPreco();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
