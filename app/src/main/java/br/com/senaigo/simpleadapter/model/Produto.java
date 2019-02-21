package br.com.senaigo.simpleadapter.model;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    public void setMap(Map<String, Object> map) {
        id = (Long) map.get("id");
        nome = (String) map.get("nome");
        descricao = (String) map.get("descricao");
        preco = (Double) map.get("preco");
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
        if(preco == null){
            preco = 0.0;
        }
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
