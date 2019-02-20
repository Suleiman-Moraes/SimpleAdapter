package br.com.senaigo.simpleadapter.model;

import java.util.HashMap;
import java.util.Map;

import br.com.senaigo.simpleadapter.model.interfaces.GetMap;

public class Produto implements GetMap {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;

    public Produto() {}
    public Produto(Long id, String nome, String descricao, Double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    @Override
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("nome", nome);
        map.put("descricao", descricao);
        map.put("preco", preco);
        return map;
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
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
