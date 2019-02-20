package br.com.senaigo.simpleadapter.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.com.senaigo.simpleadapter.model.Produto;

public class ListaProdutoUtil {

    public static List<Produto> getListProduto(){
        List<Produto> list = new LinkedList<>();
        for (int i = 0; i < 10; i++){
            list.add(new Produto((long) (i + 1), "Produto " + i, "Descrição Qualquer", (50.8 + i)));
        }
        return list;
    }

    public static List<Map<String, Object>> getListMapProduto(){
        List<Map<String, Object>> list = new LinkedList<>();
        List<Produto> listProduto = getListProduto();
        for (int i = 0; i < listProduto.size(); i++){
            list.add(listProduto.get(i).getMap());
        }
        return list;
    }
}
