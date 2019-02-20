package br.com.senaigo.simpleadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.com.senaigo.simpleadapter.model.Pedido;

public class TelaPedido extends AppCompatActivity {

    private EditText id;
    private EditText cliente;
    private EditText data;
    private EditText valor;
    private ListView produtos;
    private ListView pedidos;

    private List<Map<String, Object>> listPedidos;
    private SimpleAdapter simpleAdapter;

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

        simpleAdapter = new SimpleAdapter(this, )
    }

    public void adicionar(View view) {
        Pedido pedido = new Pedido();
        pedido.setId(Long.valueOf(id.getText().toString()));
        pedido.setCliente(cliente.getText().toString());
        pedido.setData(data.getText().toString());
        pedido.setValor(new BigDecimal(valor.getText().toString()));

        listPedidos.add(pedido.getMap());
    }
}
