package br.com.senaigo.simpleadapter.model;

import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import br.com.senaigo.simpleadapter.model.interfaces.GetMap;

public class Pedido implements GetMap {

    private Long id;
    private String cliente;
    private String data;
    private List<Produto> produtos;
    private BigDecimal valor;

    public Pedido() {}
    public Pedido(Long id, String cliente, String data, List<Produto> produtos, BigDecimal valor) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.produtos = produtos;
        this.valor = valor;
    }

    @Override
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("cliente", cliente);
        map.put("data", data);
        map.put("produtos", produtos);
        map.put("valor", valor);
        return map;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Produto> getProdutos() {
        if(produtos == null){
            produtos = new LinkedList<>();
        }
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public BigDecimal getValor() {
        if(valor == null){
            valor = new BigDecimal(0);
        }
        return valor;
    }

    public void addValor(BigDecimal valor){
        if(this.valor == null){
            this.valor = new BigDecimal(0);
        }
        this.valor = this.valor.add(valor);
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }
    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente='" + cliente + '\'' +
                ", data='" + data + '\'' +
                ", produtos=" + produtos +
                ", valor=" + valor +
                '}';
    }
}
