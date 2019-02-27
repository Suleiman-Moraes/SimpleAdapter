package br.com.senaigo.simpleadapter;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.com.senaigo.simpleadapter.model.Pedido;
import br.com.senaigo.simpleadapter.model.Produto;
import br.com.senaigo.simpleadapter.util.ListaProdutoUtil;
import br.com.senaigo.simpleadapter.util.StringUtil;

import static java.lang.Long.valueOf;

public class TelaPedido extends AppCompatActivity {

    private EditText id;
    private EditText cliente;
    private EditText data;
    private EditText valor;
    private ListView produtos;
    private ListView produtosSelecionados;
    private ListView pedidos;

    private int poss;
    private Long idGenerate = 0l;

    private List<Map<String, Object>> listPedidos;
    private List<Map<String, Object>> listProdutos;
    private List<Produto> listProdutosSelecionados;
    private SimpleAdapter simpleAdapter;

    private Button deletar;
    private Button editar;

    private Map<String, Object> mapSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        id = findViewById(R.id.txtId);
        cliente = findViewById(R.id.txtCliente);
        data = findViewById(R.id.txtData);
        valor = findViewById(R.id.txtValor);
        produtos = findViewById(R.id.listViewProdutos);
        pedidos = findViewById(R.id.listViewPedidos);
        listPedidos = new LinkedList<>();
        deletar = findViewById(R.id.btn2);
        editar = findViewById(R.id.btn1);
        produtos = findViewById(R.id.listViewProdutos);
        produtosSelecionados = findViewById(R.id.listViewProdutosSelecionados);
        listProdutos = ListaProdutoUtil.getListMapProduto();
        listProdutosSelecionados = new LinkedList<>();
        produtos.setAdapter(new SimpleAdapter(this, listProdutos, R.layout.item, new String[]{"preco", "nome"}, new int[]{R.id.txtItemId, R.id.txtItemNome}));

        acaoListViewPedido();
        acaoListViewProduto();
        acaoListViewProdutoSelecionado();
    }

    public void adicionar(View view) {
        try {
            Pedido pedido = new Pedido();
            pedido.setCliente(StringUtil.isNotNullOrEmpty(cliente.getText().toString()) ? cliente.getText().toString() : "Não Informado");
            if(listProdutosSelecionados.size() == 0){
                throw new Exception("Selecione um produto!");
            }
            for(Produto aux : listProdutosSelecionados){
                pedido.getProdutos().add(aux);
                pedido.addValor(new BigDecimal(aux.getPreco()));
            }
            String iden = id.getText().toString();
            if(StringUtil.isNotNullOrEmpty(iden)){
                pedido.setId(valueOf(iden));
                listPedidos.remove(poss);
            }
            else{
                idGenerate ++;
                pedido.setId(idGenerate);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            pedido.setData(simpleDateFormat.format(new Date()));
            simpleAdapter = new SimpleAdapter(this, listPedidos, R.layout.item, new String[]{"id", "cliente"}, new int[]{R.id.txtItemId, R.id.txtItemNome});
            listPedidos.add(pedido.getMap());
            pedidos.setAdapter(simpleAdapter);
            listProdutosSelecionados = new LinkedList<>();
            listProdutos = ListaProdutoUtil.getListMapProduto();
            remotandoListProdutos();
            limparCampos();
        }catch (Exception e){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Erro!");
            alertDialog.setMessage(e.getMessage());
            alertDialog.show();
        }
    }

    public void deletar(View view){
        listPedidos.remove(poss);
        simpleAdapter = new SimpleAdapter(this, listPedidos, R.layout.item, new String[]{"id", "cliente"}, new int[]{R.id.txtItemId, R.id.txtItemNome});
        pedidos.setAdapter(simpleAdapter);
        deletar.setEnabled(Boolean.FALSE);
        editar.setEnabled(Boolean.FALSE);
        limparCampos();
        listProdutosSelecionados = new LinkedList<>();
        listProdutos = ListaProdutoUtil.getListMapProduto();
        remotandoListProdutos();
    }

    public void editar(View view) {
        try {
            id.setText(mapSelecionado.get("id") + "");
            cliente.setText(mapSelecionado.get("cliente") + "");
            data.setText(mapSelecionado.get("data") + "");
            valor.setText(mapSelecionado.get("valor") + "");
            editar.setEnabled(Boolean.FALSE);
            deletar.setEnabled(Boolean.FALSE);

            listProdutosSelecionados = new LinkedList<>();
            listProdutos = ListaProdutoUtil.getListMapProduto();
            for (int i = 0; i < ((List)mapSelecionado.get("produtos")).size(); i++){
                listProdutosSelecionados.add(((List<Produto>)mapSelecionado.get("produtos")).get(i));
            }
            listProdutos.removeAll(listProdutosSelecionados);
            remotandoListProdutos();
        }catch (Exception e){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Erro!");
            alertDialog.setMessage(e.getMessage());
            alertDialog.show();
        }
    }

    private void acaoListViewPedido(){
        pedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mapSelecionado = listPedidos.get(position);
                String textoBtn = getString(R.string.btn_deletar) + " " + mapSelecionado.get("id");
                String textoBtnEditar = getString(R.string.btn_editar) + " " + mapSelecionado.get("id");
                deletar.setText(textoBtn);
                editar.setText(textoBtnEditar);
                poss = position;
                deletar.setEnabled(Boolean.TRUE);
                editar.setEnabled(Boolean.TRUE);
            }
        });
    }

    private void acaoListViewProduto(){
        produtos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = listProdutos.get(position);
                listProdutos.remove(position);
                Produto aux = new Produto();
                aux.setMap(map);
                listProdutosSelecionados.add(aux);
                remotandoListProdutos();
            }
        });
    }

    private void acaoListViewProdutoSelecionado(){
        produtosSelecionados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto p = listProdutosSelecionados.get(position);
                listProdutosSelecionados.remove(position);
                listProdutos.add(p.getMap());
                remotandoListProdutos();
            }
        });
    }


    private void remotandoListProdutos(){
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listProdutos, R.layout.item, new String[]{"preco", "nome"}, new int[]{R.id.txtItemId, R.id.txtItemNome});
        produtos.setAdapter(simpleAdapter);
        ArrayAdapter<Produto> arrayAdapter = new ArrayAdapter<>(this, R.layout.item, listProdutosSelecionados);
//        SimpleAdapter simple = new SimpleAdapter(this, listProdutosSelecionados, R.layout.item, new String[]{"preco", "nome"}, new int[]{R.id.textItemID, R.id.textItemNome});
        produtosSelecionados.setAdapter(arrayAdapter);
    }

    private void limparCampos(){
        id.setText("");
        cliente.setText("");
        data.setText("");
        valor.setText("");
    }

    public void fecharPedidos(View view) {
        limparCampos();
        listProdutosSelecionados = new LinkedList<>();
        listProdutos = ListaProdutoUtil.getListMapProduto();
        remotandoListProdutos();
        deletar.setEnabled(Boolean.FALSE);
        editar.setEnabled(Boolean.FALSE);
        BigDecimal valorFinal = new BigDecimal(0);
        for(Map<String, Object> map : listPedidos){
            valorFinal = valorFinal.add((BigDecimal) map.get("valor"));
        }
        listPedidos = new LinkedList<>();
        simpleAdapter = new SimpleAdapter(this, listPedidos, R.layout.item, new String[]{"id", "cliente"}, new int[]{R.id.txtItemId, R.id.txtItemNome});
        pedidos.setAdapter(simpleAdapter);
        String texto = "Final dos trabalhos \nValor Final == R$: " + Math.round(valorFinal.doubleValue());
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Fim!");
        alertDialog.setMessage(texto);
        alertDialog.show();
    }
}
