package br.com.senaigo.simpleadapter;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.com.senaigo.simpleadapter.model.Pedido;
import br.com.senaigo.simpleadapter.util.ListaProdutoUtil;
import br.com.senaigo.simpleadapter.util.StringUtil;

public class TelaPedido extends AppCompatActivity {

    private EditText id;
    private EditText cliente;
    private EditText data;
    private EditText valor;
    private ListView produtos;
    private ListView pedidos;

    private int poss;

    private List<Map<String, Object>> listPedidos;
    private SimpleAdapter simpleAdapter;

    private Button deletar;

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
        produtos = findViewById(R.id.listViewProdutos);
        produtos.setAdapter(new SimpleAdapter(this, ListaProdutoUtil.getListMapProduto(), R.layout.item, new String[]{"preco", "nome"}, new int[]{R.id.textItemID, R.id.textItemNome}));

        pedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mapSelecionado = listPedidos.get(position);
                String textoBtn = getString(R.string.btn_deletar) + " " + mapSelecionado.get("id");
                deletar.setText(textoBtn);
                poss = position;
                deletar.setEnabled(Boolean.TRUE);
            }
        });
    }

    public void adicionar(View view) {
        try {
            Pedido pedido = new Pedido();
            pedido.setId(Long.valueOf(StringUtil.isNotNullOrEmpty(id.getText().toString()) ? id.getText().toString() : "0"));
            pedido.setCliente(cliente.getText().toString());
            pedido.setData(data.getText().toString());
            pedido.setValor(new BigDecimal(StringUtil.isNotNullOrEmpty(valor.getText().toString()) ? valor.getText().toString() : "0"));

            simpleAdapter = new SimpleAdapter(this, listPedidos, R.layout.item, new String[]{"id", "cliente"}, new int[]{R.id.textItemID, R.id.textItemNome});
            listPedidos.add(pedido.getMap());
            pedidos.setAdapter(simpleAdapter);
        }catch (Exception e){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Erro!");
            alertDialog.setMessage("Deu Mel");
            alertDialog.show();
        }
    }

    public void deletar(View view){
        listPedidos.remove(poss);
        simpleAdapter = new SimpleAdapter(this, listPedidos, R.layout.item, new String[]{"id", "cliente"}, new int[]{R.id.textItemID, R.id.textItemNome});
        pedidos.setAdapter(simpleAdapter);
    }
}
