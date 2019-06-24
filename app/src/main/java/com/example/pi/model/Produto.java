package com.example.pi.model;

import java.util.List;

public class Produto {
    private Long id;
    private String nome,sexo;
    private double custo, preco;
    private List<Imagem> imagens;
    private List<Categoria> categoria;
    int qnt;

    private List<String> lista_id_imagens;

    public List<String> getLista_id_imagens() {
        return lista_id_imagens;
    }

    public void setLista_id_imagens(List<String> lista_id_imagens) {
        this.lista_id_imagens = lista_id_imagens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    public List<Categoria> getCategoria() {
        return categoria;
    }

    public void setCategoria(List<Categoria> categoria) {
        this.categoria = categoria;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
